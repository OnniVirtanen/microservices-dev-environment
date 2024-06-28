package com.onnivirtanen.inventory.domain.service;

import com.onnivirtanen.inventory.domain.command.AddNewProductCommand;
import com.onnivirtanen.inventory.domain.command.NewShelfLocationCommand;
import com.onnivirtanen.inventory.domain.command.ProductMissingCommand;
import com.onnivirtanen.inventory.domain.command.ReStockProductCommand;
import com.onnivirtanen.inventory.domain.command.RemoveProductCommand;
import com.onnivirtanen.inventory.domain.event.OrderCreatedEvent;
import com.onnivirtanen.inventory.domain.event.ProductAddedEvent;
import com.onnivirtanen.inventory.domain.event.ProductAssignedNewShelfLocationEvent;
import com.onnivirtanen.inventory.domain.event.ProductMarkedMissingEvent;
import com.onnivirtanen.inventory.domain.event.ProductRemovedFromSelectionEvent;
import com.onnivirtanen.inventory.domain.event.ProductRestockedEvent;
import com.onnivirtanen.inventory.domain.event.ReserveItemsEvent;
import com.onnivirtanen.inventory.domain.exception.ProductAlreadyExistsException;
import com.onnivirtanen.inventory.domain.exception.ProductNotFoundException;
import com.onnivirtanen.inventory.domain.model.aggregate.Product;
import com.onnivirtanen.inventory.domain.port.in.InventoryService;
import com.onnivirtanen.inventory.domain.port.out.InventoryRepository;
import com.virtanen.event.EventProducer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;
    private final EventProducer eventProducer;
    private static final String KAFKA_TOPIC_NAME = "inventory";

    public InventoryServiceImpl(InventoryRepository repository, EventProducer eventProducer) {
        this.repository = repository;
        this.eventProducer = eventProducer;
    }

    @Override
    public Product addNewProduct(AddNewProductCommand command) {
        boolean productExists = repository.productExistsByEAN(command.barcode());
        if (productExists) {
            throw new ProductAlreadyExistsException("Product with same EAN already exists.");
        }
        Product product = Product.from(command);
        Product savedProduct = repository.save(product);
        eventProducer.publish(new ProductAddedEvent(savedProduct.getId().toString()), KAFKA_TOPIC_NAME);
        return savedProduct;
    }

    @Override
    public Product reStockProduct(ReStockProductCommand command) {
        Product product = repository.findById(command.productId())
                .orElseThrow(() -> new ProductNotFoundException("No product found by given id"));

        product.reStock(command.quantity());
        Product updatedProduct = repository.update(product);
        eventProducer.publish(new ProductRestockedEvent(updatedProduct.getId().toString()), KAFKA_TOPIC_NAME);
        return updatedProduct;
    }

    @Override
    public void removeProductFromSelection(RemoveProductCommand command) {
        repository.findById(command.productId())
                .orElseThrow(() -> new ProductNotFoundException("No product found by given id"));
        repository.deleteById(command.productId());
        eventProducer.publish(new ProductRemovedFromSelectionEvent(command.productId().toString()), KAFKA_TOPIC_NAME);
    }

    @Override
    public List<Product> findAllProducts() {
        return repository.findAll();
    }

    @Override
    public void assignNewShelfLocation(NewShelfLocationCommand command) {
        Product product = repository.findById(command.productId())
                .orElseThrow(() -> new ProductNotFoundException("No product found by given id"));
        product.assignShelfLocation(command.location());
        repository.update(product);
        eventProducer.publish(new ProductAssignedNewShelfLocationEvent(product.getId().toString()), KAFKA_TOPIC_NAME);
    }

    @Override
    public void markProductMissing(ProductMissingCommand command) {
        Product product = repository.findById(command.productId())
                .orElseThrow(() -> new ProductNotFoundException("No product found by given id"));
        product.markProductMissing(command.quantity());
        repository.update(product);
        eventProducer.publish(new ProductMarkedMissingEvent(product.getId().toString()), KAFKA_TOPIC_NAME);
    }

    @Override
    public void reserveItems(OrderCreatedEvent event) {
        Map<String, Integer> productsAmount = event.getProductsAmount();
        for (Map.Entry<String, Integer> entry : productsAmount.entrySet()) {
            String productId = entry.getKey();
            Integer amount = entry.getValue();
            Optional<Product> product = repository.findById(UUID.fromString(productId));
            if (product.isPresent()) {
                Product p = product.get();
                p.reserveAmount(amount);
                repository.save(p);
            }
        }
        eventProducer.publish(new ReserveItemsEvent(UUID.randomUUID().toString(), event.getOrderId()), KAFKA_TOPIC_NAME);
    }

}
