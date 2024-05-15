package inventory.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import inventory.exception.ValidateException;
import inventory.model.InhousePart;
import inventory.model.OutsourcedPart;
import inventory.model.Part;
import inventory.model.Product;
import inventory.repository.InventoryRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class InventoryServiceTest {

    @InjectMocks
    private InventoryService inventoryService;

    @Mock
    private InventoryRepository repo; // Assuming Repository is the interface your repo object implements.

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        inventoryService = new InventoryService(repo);
    }

    @Test
    @DisplayName("ECP-addInhousePart_valid")
    @Tag("ECP")
    @EnabledOnOs(OS.WINDOWS)
    @Order(1)
    public void testAddInhousePart_ValidInput_ShouldPass() {
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
    @DisplayName("ECP-addOutsourcePart_valid")
    @Tag("ECP")
    public void testAddOutsourcePart_ValidInput_ShouldPass() {
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

    @Test
    @DisplayName("ECP-addProduct_invalidString")
    @Tag("ECP")
    @Order(2)
    public void testAddProduct3() {
        // Arrange
        String name = ""; //empty string
        double price = 10.99;
        int inStock = 5;
        int min = 1;
        int max = 10;
        ObservableList<Part> addParts = FXCollections.observableArrayList();

        when(repo.getAutoPartId()).thenReturn(1);
        doNothing().when(repo).addPart(any(OutsourcedPart.class));

        // Act & Assert
        assertThrows(ValidateException.class, () ->
                inventoryService.addProduct(name, price, inStock, min, max, addParts)
        );
    }

    @Test
    @DisplayName("ECP-addProduct_invalidStock")
    @Tag("ECP")
    @Order(3)
    public void testAddProduct4() {
        // Arrange
        String name = "Part1";
        double price = 10.99;
        int inStock = 100; // >Max
        int min = 1;
        int max = 10;
        ObservableList<Part> addParts = FXCollections.observableArrayList();

        when(repo.getAutoPartId()).thenReturn(1);
        doNothing().when(repo).addPart(any(OutsourcedPart.class));

        // Act & Assert
        assertThrows(ValidateException.class, () ->
                inventoryService.addProduct(name, price, inStock, min, max, addParts)
        );
    }

    @Test
    @DisplayName("BVA-addInhousePart_invalid")
    @Tag("BVA")
    @Order(4)
    public void testAddInhousePart1() {
        // Arrange
        String name = "Part1";
        double price = 10.99;
        int inStock = 0; // min-1.
        int min = 1;
        int max = 10;
        int partDynamicValue = 100;

        // Act & Assert
        assertThrows(ValidateException.class, () ->
                inventoryService.addInhousePart(name, price, inStock, min, max, partDynamicValue)
        );
    }

    @Test
    @DisplayName("BVA-addInhousePart_valid")
    @Tag("BVA")
    @Order(5)
    public void testAddInhousePart2() {
        // Arrange
        String name = "Part1";
        double price = 10.99;
        int inStock = 1; // Equals minimum value.
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
    @DisplayName("BVA-addProduct_valid")
    @Tag("BVA")
    @Order(6)
    public void testAddProduct1() {
        // Arrange
        String name = "Part1";
        double price = 10.99;
        int inStock = 9; // max-1
        int min = 1;
        int max = 10;
        ObservableList<Part> addParts = FXCollections.observableArrayList();

        when(repo.getAutoPartId()).thenReturn(1);
        doNothing().when(repo).addPart(any(OutsourcedPart.class));

        // Act
        inventoryService.addProduct(name, price, inStock, min, max, addParts);

        // Assert
        verify(repo, times(1)).addProduct(any(Product.class));
    }

    @Test
    @DisplayName("BVA-addProduct_invalid")
    @Tag("BVA")
    @Order(7)
    public void testAddProduct2() {
        // Arrange
        String name = "Part1";
        double price = 10.99;
        int inStock = 11; // max+1
        int min = 1;
        int max = 10;
        ObservableList<Part> addParts = FXCollections.observableArrayList();

        when(repo.getAutoPartId()).thenReturn(1);
        doNothing().when(repo).addPart(any(OutsourcedPart.class));

        // Act & Assert
        assertThrows(ValidateException.class, () ->
                inventoryService.addProduct(name, price, inStock, min, max, addParts)
        );
    }

    @Test
    @DisplayName("Valid part lookup - returns part")
    @Tag("WBT")
    @Order(9)
    public void testFindPartValid() {
        double expectedPrice = 10.99;
        int inStock = 1;
        int min = 1;
        int max = 10;
        int machineId = 32;

        Part mockPart = new InhousePart(1, "A", expectedPrice, inStock, min, max, machineId);
        when(repo.lookupPart("A")).thenReturn(mockPart);

        Part result = inventoryService.lookupPart("A");

        assertNotNull(result);
        assertEquals("A", result.getName());
        verify(repo, times(1)).lookupPart("A");
    }

    @Test
    @DisplayName("Invalid part lookup - return null")
    @Tag("WBT")
    @Order(10)
    public void testFindPartInvalid() {
        when(repo.lookupPart("")).thenReturn(null);

        Part result = inventoryService.lookupPart("");
        assertNull(result);

        verify(repo, times(1)).lookupPart("");
    }

    @Test
    @DisplayName("Invalid in stock value - return null")
    @Tag("WBT")
    @Order(11)
    public void testFindPartStockMinim() {
        double expectedPrice = 10.99;
        int inStock = 0;
        int min = 0;
        int max = 10;
        int machineId = 32;

        Part mockPart = new InhousePart(1, "X", expectedPrice, inStock, min, max, machineId);
        when(repo.lookupPart("X")).thenReturn(null);

        Part result = inventoryService.lookupPart("X");
        assertNull(result);

        verify(repo, times(1)).lookupPart("X");
    }

    @Test
    @DisplayName("Valid part lookup - returns part")
    @Tag("WBT")
    @Order(12)
    public void testFindPartValidById() {
        double expectedPrice = 10.99;
        int inStock = 1;
        int min = 1;
        int max = 10;
        int machineId = 32;

        Part mockPart = new InhousePart(1, "Y", expectedPrice, inStock, min, max, machineId);
        when(repo.lookupPart("1")).thenReturn(mockPart);

        Part result = inventoryService.lookupPart("1");

        assertNotNull(result);
        assertEquals(1, result.getPartId());
        verify(repo, times(1)).lookupPart("1");
    }

    @Test
    @DisplayName("Inalid part lookup - returns null because doesn't exists")
    @Tag("WBT")
    @Order(13)
    public void testFindPartInvalidNotExist() {
        double expectedPrice = 10.99;
        int inStock = 1;
        int min = 1;
        int max = 10;
        int machineId = 32;

        Part mockPart = new InhousePart(1, "Y", expectedPrice, inStock, min, max, machineId);
        when(repo.lookupPart("Z")).thenReturn(null);

        Part result = inventoryService.lookupPart("Z");

        assertNull(result);
        verify(repo, times(1)).lookupPart("Z");
    }
}