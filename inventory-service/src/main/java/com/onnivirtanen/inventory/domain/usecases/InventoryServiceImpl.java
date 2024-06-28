package com.onnivirtanen.inventory.domain.usecases;

import com.onnivirtanen.inventory.domain.model.aggregate.Product;
import com.onnivirtanen.inventory.domain.exception.ProductAlreadyExistsException;
import com.onnivirtanen.inventory.domain.exception.ProductNotFoundException;
import com.onnivirtanen.inventory.domain.port.in.InventoryService;
import com.onnivirtanen.inventory.domain.port.out.DomainEventPublisher;
import com.onnivirtanen.inventory.domain.port.out.InventoryRepository;
import com.onnivirtanen.inventory.domain.command.AddNewProductCommand;
import com.onnivirtanen.inventory.domain.command.NewShelfLocationCommand;
import com.onnivirtanen.inventory.domain.command.ProductMissingCommand;
import com.onnivirtanen.inventory.domain.command.ReStockProductCommand;
import com.onnivirtanen.inventory.domain.command.RemoveProductCommand;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;
    private final DomainEventPublisher eventPublisher;

    public InventoryServiceImpl(InventoryRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    @Transactional
    public Product addNewProduct(AddNewProductCommand command) {
        boolean productExists = repository.productExistsByEAN(command.barcode());
        if (productExists) {
            throw new ProductAlreadyExistsException("Product with same EAN already exists.");
        }

        Product product = Product.from(command);
        Product savedProduct = repository.save(product);

        publishDomainEvents(product);
        return savedProduct;
    }

    @Override
    @Transactional
    public Product reStockProduct(ReStockProductCommand command) {
        Product product = repository.findById(command.productId())
                .orElseThrow(() -> new ProductNotFoundException("No product found by given id"));

        product.reStock(command.quantity());
        Product updatedProduct = repository.update(product);

        publishDomainEvents(product);
        return updatedProduct;
    }

    @Override
    @Transactional
    public void removeProductFromSelection(RemoveProductCommand command) {
        repository.findById(command.productId())
                .orElseThrow(() -> new ProductNotFoundException("No product found by given id"));

        repository.deleteById(command.productId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAllProducts() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void assignNewShelfLocation(NewShelfLocationCommand command) {
        Product product = repository.findById(command.productId())
                .orElseThrow(() -> new ProductNotFoundException("No product found by given id"));

        product.assignShelfLocation(command.location());

        repository.update(product);
    }

    @Override
    @Transactional
    public void markProductMissing(ProductMissingCommand command) {
        Product product = repository.findById(command.productId())
                .orElseThrow(() -> new ProductNotFoundException("No product found by given id"));

        product.markProductMissing(command.quantity());
        repository.update(product);

        publishDomainEvents(product);
    }

    private void publishDomainEvents(Product product) {
        product.getDomainEvents().forEach(eventPublisher::publish);
        product.clearDomainEvents();
    }
}
