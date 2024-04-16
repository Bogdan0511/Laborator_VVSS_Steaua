package inventory.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    private Inventory inventory;

    @BeforeEach
    void setUp() {
        // Initialize the inventory with mock InhouseParts
        List<InhousePart> inhouseParts = new ArrayList<>();
        inventory = new Inventory();
        inventory.addPart(new InhousePart(1, "Part1", 10.0, 5, 1, 10, 123));
        inventory.addPart(new InhousePart(2, "Part2", 20.0, 10, 1, 20, 456));
        inventory.addPart(new InhousePart(3, "Part3", 15.0, 8, 1, 15, 789));


    }

    @Test
    void testLookupInhousePartExists() {
        // Arrange
        String searchItem = "Part2";

        // Act
        Part result = inventory.lookupPart(searchItem);

        // Assert
        assertNotNull(result);
        assertEquals("Part2", result.getName());
    }

    @Test
    void testLookupInhousePartDoesNotExist() {
        // Arrange
        String searchItem = "NonexistentPart";

        // Act
        Part result = inventory.lookupPart(searchItem);

        // Assert
        assertNull(result);
    }

    @Test
    void testLookupInhousePartWithEmptySearchItem() {
        // Arrange
        String searchItem = "";

        // Act
        Part result = inventory.lookupPart(searchItem);

        // Assert
        assertNull(result);
    }
}
