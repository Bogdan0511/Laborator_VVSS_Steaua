package inventory.service;

import inventory.model.InhousePart;
import inventory.model.Inventory;
import inventory.model.OutsourcedPart;
import inventory.model.Product;
import inventory.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class InvetoryServiceTest_SR_E_Mock {
    @InjectMocks
    private InventoryRepository repository; // A concrete class that implements the InventoryRepository interface.

    @Mock
    private Inventory inventory;

    @Mock
    private InhousePart inhousePart;

    @Mock
    private Product product;

    private InventoryService inventoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        repository.setInventory(inventory);
        inventoryService = new InventoryService(repository);
    }

    @Test
    public void testAddInhousePart() {
        String name = "Part1";
        double price = 10.99;
        int inStock = 5;
        int min = 1;
        int max = 10;
        int partDynamicValue = 100;

        when(inventory.getAutoPartId()).thenReturn(1);
        doNothing().when(inventory).addPart(any(InhousePart.class));

        // Act
        inventoryService.addInhousePart(name, price, inStock, min, max, partDynamicValue);

        // Assert
        verify(inventory, times(1)).addPart(any(InhousePart.class));
    }

    @Test
    public void testAddOutsourcedPart() {
        String name = "Part1";
        double price = 10.99;
        int inStock = 5;
        int min = 1;
        int max = 10;
        String companyName = "Outsource Inc";

        when(inventory.getAutoPartId()).thenReturn(1);
        doNothing().when(inventory).addPart(any(OutsourcedPart.class));

        // Act
        inventoryService.addOutsourcePart(name, price, inStock, min, max, companyName);

        // Assert
        verify(inventory, times(1)).addPart(any(OutsourcedPart.class));
    }

    @Test
    public void testAddPart() {
        doNothing().when(inventory).addPart(inhousePart);

        repository.addPart(inhousePart);

        verify(inventory, times(1)).addPart(inhousePart);
    }

    @Test
    public void testAddProduct() {
        doNothing().when(inventory).addProduct(product);

        repository.addProduct(product);

        verify(inventory, times(1)).addProduct(product);
    }
}
