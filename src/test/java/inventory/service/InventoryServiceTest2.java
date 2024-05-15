package inventory.service;

import inventory.model.InhousePart;
import inventory.model.OutsourcedPart;
import inventory.repository.InventoryRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class InventoryServiceTest2 {

    private InventoryService inventoryService;

    @Mock
    private InventoryRepository repo; // Assuming Repository is the interface your repo object implements.

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        inventoryService = new InventoryService(repo);
    }

    @Test
    public void testAddInhousePart() {
        // Arrange
        String name = "Part1";
        double price = 10.99;
        int inStock = 5;
        int min = 1;
        int max = 10;
        int partDynamicValue = 100;

        when(repo.getAutoPartId()).thenReturn(1);
        doNothing().when(repo).addPart(any(InhousePart.class));

        // Act
        inventoryService.addInhousePart(name, price, inStock, min, max, partDynamicValue);

        // Assert
        verify(repo, times(1)).addPart(any(InhousePart.class));
    }

    @Test
    public void testAddOutsourcePart() {
        // Arrange
        String name = "Part1";
        double price = 10.99;
        int inStock = 5;
        int min = 1;
        int max = 10;
        String companyName = "Outsource Inc";

        when(repo.getAutoPartId()).thenReturn(1);
        doNothing().when(repo).addPart(any(OutsourcedPart.class));

        // Act
        inventoryService.addOutsourcePart(name, price, inStock, min, max, companyName);

        // Assert
        verify(repo, times(1)).addPart(any(OutsourcedPart.class));
    }
}
