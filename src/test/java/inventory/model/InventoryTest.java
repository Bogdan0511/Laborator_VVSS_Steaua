package inventory.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    private Inventory repository; // A concrete class that implements the InventoryRepository interface.

    @BeforeEach
    void setUp() {
        repository = new Inventory(); // Initialize a new instance before each test.
    }

    @AfterEach
    void tearDown() {
        repository = null; // Clean up after each test.
    }

    @Test
    public void testFindPartInvalid() {
        Part result = repository.lookupPart("");
        assertNull(result);
    }

    @Test
    @DisplayName("Invalid in stock value - return null")
    @Tag("WBT")
    public void testFindPartStockMinim() {
        double expectedPrice = 10.99;
        int inStock = 0;
        int min = 0;
        int max = 10;
        int machineId = 32;

        Part part = new InhousePart(1, "X", expectedPrice, inStock, min, max, machineId);
        repository.addPart(part);

        // Assumption: Parts with stock value of 0 are not considered available
        Part result = repository.lookupPart("Z");
        assertNull(result);
    }

    @Test
    @DisplayName("Valid part lookup - returns part")
    @Tag("WBT")
    public void testFindPartValidById() {
        double expectedPrice = 10.99;
        int inStock = 1;
        int min = 1;
        int max = 10;
        int machineId = 32;

        Part part = new InhousePart(1, "Y", expectedPrice, inStock, min, max, machineId);
        repository.addPart(part);

        Part result = repository.lookupPart("Y");

        assertNotNull(result);
        assertEquals(1, result.getPartId());
    }

    @Test
    @DisplayName("Valid part lookup - returns part")
    @Tag("WBT")
    public void testFindPartValidById2() {
        double expectedPrice = 10.99;
        int inStock = 1;
        int min = 1;
        int max = 10;
        int machineId = 32;

        Part part = new InhousePart(1, "Y", expectedPrice, inStock, min, max, machineId);
        repository.addPart(part);

        Part result = repository.lookupPart("1");

        assertNotNull(result);
        assertEquals(1, result.getPartId());
    }

    @Test
    @DisplayName("Invalid part lookup - returns null because doesn't exist")
    @Tag("WBT")
    public void testFindPartInvalidNotExist() {
        double expectedPrice = 10.99;
        int inStock = 1;
        int min = 1;
        int max = 10;
        int machineId = 32;

        Part part = new InhousePart(1, "Y", expectedPrice, inStock, min, max, machineId);
        repository.addPart(part);

        Part result = repository.lookupPart("Z");

        assertNull(result);
    }
}