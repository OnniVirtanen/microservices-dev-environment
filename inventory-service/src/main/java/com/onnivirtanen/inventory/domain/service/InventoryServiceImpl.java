package com.onnivirtanen.inventory.domain.service;

import com.onnivirtanen.inventory.domain.command.AddNewProductCommand;
import com.onnivirtanen.inventory.domain.command.NewShelfLocationCommand;
import com.onnivirtanen.inventory.domain.command.ProductMissingCommand;
import com.onnivirtanen.inventory.domain.command.ReStockProductCommand;
import com.onnivirtanen.inventory.domain.command.RemoveProductCommand;
import com.onnivirtanen.inventory.domain.exception.ProductAlreadyExistsException;
import com.onnivirtanen.inventory.domain.exception.ProductNotFoundException;
import com.onnivirtanen.inventory.domain.model.aggregate.Product;
import com.onnivirtanen.inventory.domain.port.in.InventoryService;
import com.onnivirtanen.inventory.domain.port.out.InventoryRepository;
import com.virtanen.event.EventProducer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;
    private final EventProducer eventProducer;

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

        return savedProduct;
    }

    @Override
    public Product reStockProduct(ReStockProductCommand command) {
        Product product = repository.findById(command.productId())
                .orElseThrow(() -> new ProductNotFoundException("No product found by given id"));

        product.reStock(command.quantity());
        Product updatedProduct = repository.update(product);

        return updatedProduct;
    }

    @Override
    public void removeProductFromSelection(RemoveProductCommand command) {
        repository.findById(command.productId())
                .orElseThrow(() -> new ProductNotFoundException("No product found by given id"));

        repository.deleteById(command.productId());
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
    }

    @Override
    public void markProductMissing(ProductMissingCommand command) {
        Product product = repository.findById(command.productId())
                .orElseThrow(() -> new ProductNotFoundException("No product found by given id"));

        product.markProductMissing(command.quantity());
        repository.update(product);
    }

}
