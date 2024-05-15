package inventory.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    private Product product;
    private Part part1;
    private Part part2;

    @BeforeEach
    public void setUp() {
        ObservableList<Part> partList = FXCollections.observableArrayList();
        product = new Product(1, "TestProduct", 100.00, 10, 1, 20, partList);

        part1 = new InhousePart(1, "Part1", 10.00, 5, 1, 10, 101);
        part2 = new InhousePart(2, "Part2", 15.00, 3, 1, 10, 102);
    }

    @Test
    public void testName() {
        assertEquals(part1.getName(), "Part1");
        assertEquals(part2.getName(), "Part2");
    }

    @Test
    public void testPrice() {
        assertEquals(part1.getPrice(), 10.00);
        assertEquals(part2.getPrice(), 15.00);
    }

}