package com.onnivirtanen.inventory.domain.port.in;

import com.onnivirtanen.inventory.domain.command.AddNewProductCommand;
import com.onnivirtanen.inventory.domain.command.NewShelfLocationCommand;
import com.onnivirtanen.inventory.domain.command.ProductMissingCommand;
import com.onnivirtanen.inventory.domain.command.ReStockProductCommand;
import com.onnivirtanen.inventory.domain.command.RemoveProductCommand;
import com.onnivirtanen.inventory.domain.model.aggregate.Product;
import com.virtanen.event.events.OrderCreatedEvent;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface InventoryService {

    @Transactional(readOnly = false)
    Product addNewProduct(AddNewProductCommand command);

    @Transactional(readOnly = false)
    Product reStockProduct(ReStockProductCommand command);

    @Transactional(readOnly = false)
    void removeProductFromSelection(RemoveProductCommand command);

    @Transactional(readOnly = true)
    List<Product> findAllProducts();

    @Transactional(readOnly = false)
    void assignNewShelfLocation(NewShelfLocationCommand command);

    @Transactional(readOnly = false)
    void markProductMissing(ProductMissingCommand command);

    @Transactional(readOnly = false)
    void reserveItems(OrderCreatedEvent event);

}
