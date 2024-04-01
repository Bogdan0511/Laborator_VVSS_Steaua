package inventory.service;

import inventory.exception.ValidateException;
import inventory.model.Part;
import inventory.model.Product;
import inventory.repository.InventoryRepository;
import inventory.validator.ValidatorPart;
import inventory.validator.ValidatorProduct;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {

    private InventoryRepository repo;
    private InventoryService service;

    @BeforeEach
    void setUp() {
        repo = new InventoryRepository();
        ValidatorPart validatorPart = new ValidatorPart();
        ValidatorProduct validatorProduct = new ValidatorProduct();
        service = new InventoryService(repo, validatorPart, validatorProduct);
    }

    @AfterEach
    void tearDown() {
        repo = null;
        service = null;
    }

    @Test
    @DisplayName("In-house ECP")
    @Tag("ECP")
    void addInhousePartECP() {
        // ECP: Valid input
        //Arrange
        String name = "Valid Part";
        double price = 10.0;
        int stock = 20;
        int min = 5;
        int max = 50;
        int dynamicValue = 123;
        //Act
        service.addInhousePart(name, price, stock, min, max, dynamicValue);
        ObservableList<Part> parts = repo.getAllParts();
        //Assert
        assertEquals("Valid Part", parts.get(0).getName());

        // ECP: Invalid input (invalid stock value)
        //Arrange
        String nameInvalid = "Invalid Part";
        double priceInvalid = 10.0;
        int stockInvalid = -20;
        int minInvalid = 5;
        int maxInvalid = 50;
        int dynamicValueInvalid = 123;
        //Act and Assert
        assertThrows(ValidateException.class, () -> service.addInhousePart(nameInvalid, priceInvalid, stockInvalid, minInvalid, maxInvalid, dynamicValueInvalid));
    }

    @Test
    @DisplayName("In-house BVA")
    @Tag("BVA")
    @Timeout(1000)
    void addInhousePartBVA() {
        // BVA: Valid input
        //Arrange
        String name = "Valid Part";
        double price = 10.0;
        int stock = 20;
        int min = 5;
        int max = 50;
        int dynamicValue = 123;
        //Act
        service.addInhousePart(name, price, stock, min, max, dynamicValue);
        ObservableList<Part> parts = repo.getAllParts();
        //Assert
        assertEquals(1, parts.size());
        assertEquals("Valid Part", parts.get(0).getName());

        // BVA: Boundary value (minimum stock)
        //Arrange
        String nameBoundary = "Min Stock Part";
        double priceBoundary = 10.0;
        int stockBoundary = 1;
        int minBoundary = 1;
        int maxBoundary = 50;
        int dynamicValueBoundary = 123;
        //Act
        service.addInhousePart(nameBoundary, priceBoundary, stockBoundary, minBoundary, maxBoundary, dynamicValueBoundary);
        parts = repo.getAllParts();
        //Assert
        assertEquals(2, parts.size());
        assertEquals("Min Stock Part", parts.get(1).getName());

        // BVA: Boundary value (maximum stock)
        //Arrange
        String nameBoundary2 = "Max Stock Part";
        double priceBoundary2 = 10.0;
        int stockBoundary2 = 150;
        int minBoundary2 = 1;
        int maxBoundary2 = 150;
        int dynamicValueBoundary2 = 123;
        //Act
        service.addInhousePart(nameBoundary2, priceBoundary2, stockBoundary2, minBoundary2, maxBoundary2, dynamicValueBoundary2);
        parts = repo.getAllParts();
        //Assert
        assertEquals(3, parts.size());
        assertEquals("Max Stock Part", parts.get(2).getName());
    }

    @Test
    @DisplayName("Outsource ECP")
    @Tag("ECP")
    void addOutsourcePartECP() {
        // ECP: Valid input
        //Arrange
        String name = "Valid Part";
        double price = 10.0;
        int stock = 20;
        int min = 5;
        int max = 50;
        String dynamicValue = "Valid Company";
        //Act
        service.addOutsourcePart(name, price, stock, min, max, dynamicValue);
        ObservableList<Part> parts = repo.getAllParts();
        //Assert
        assertEquals("Valid Part", parts.get(0).getName());

        // ECP: Invalid input (null name)
        //Arrange
        String nameInvalid = "";
        double priceInvalid = 10.0;
        int stockInvalid = 20;
        int minInvalid = 5;
        int maxInvalid = 50;
        String dynamicValueInvalid = "Invalid Company";
        //Act and Assert
        assertThrows(ValidateException.class, () -> service.addOutsourcePart(nameInvalid, priceInvalid, stockInvalid, minInvalid, maxInvalid, dynamicValueInvalid));

        // ECP: Invalid input (negative price and negative stock)
        //Arrange
        String nameInvalid2 = "Invalid Part";
        double priceInvalid2 = -10.0;
        int stockInvalid2 = -5;
        int minInvalid2 = 5;
        int maxInvalid2 = 50;
        String dynamicValueInvalid2 = "Invalid Company";
        //Act and Assert
        assertThrows(ValidateException.class, () -> service.addOutsourcePart(nameInvalid2, priceInvalid2, stockInvalid2, minInvalid2, maxInvalid2, dynamicValueInvalid2));
    }

    @Test
    @DisplayName("Outsource BVA")
    @Tag("BVA")
    void addOutsourcePartBVA() {
        // BVA: Valid input
        //Arrange
        String name = "Valid Part";
        double price = 10.0;
        int stock = 777;
        int min = 1;
        int max = 1000;
        String dynamicValue = "Hornbach";
        //Act
        service.addOutsourcePart(name, price, stock, min, max, dynamicValue);
        ObservableList<Part> parts = repo.getAllParts();
        //Assert
        assertEquals("Valid Part", parts.get(0).getName());

        // BVA: Boundary value (minimum stock)
        //Arrange
        String nameBoundary = "Min Stock Part";
        double priceBoundary = 10.0;
        int stockBoundary = 0;
        int minBoundary = 1;
        int maxBoundary = 400;
        String dynamicValueBoundary = "Metro";
        // Act and Assert
        assertThrows(ValidateException.class, () -> service.addOutsourcePart(nameBoundary, priceBoundary, stockBoundary, minBoundary, maxBoundary, dynamicValueBoundary));

        // BVA: Boundary value (maximum stock)
        // Arrange
        String nameBoundary2 = "Max Stock Part";
        double priceBoundary2 = 10.0;
        int stockBoundary2 = 334;
        int minBoundary2 = 1;
        int maxBoundary2 = 333;
        String dynamicValueBoundary2 = "IKEA";
        // Act and Assert
        assertThrows(ValidateException.class, () -> service.addOutsourcePart(nameBoundary2, priceBoundary2, stockBoundary2, minBoundary2, maxBoundary2, dynamicValueBoundary2));

    }

    @Test
    @Disabled("Unnecessary yet")
    void addProduct() {
        ObservableList<Part> parts = repo.getAllParts();
        // Add some parts to the repository first
        service.addInhousePart("Part 1", 10.0, 20, 5, 50, 123);
        service.addOutsourcePart("Part 2", 15.0, 30, 10, 60, "Test Company");
        // Add product using the added parts
        service.addProduct("Test Product", 10000, 10, 5, 20, parts);
        ObservableList<Product> products = repo.getAllProducts();
        assertEquals(1, products.size());
        assertEquals("Test Product", products.get(0).getName());
    }
}