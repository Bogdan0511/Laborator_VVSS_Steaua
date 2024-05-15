package inventory.service;

import inventory.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.Objects;

public class InventoryServiceTest_SRE {
    private InventoryRepository repository;

    private InventoryService inventoryService;

    @BeforeEach
    public void setUp() {
        repository = new InventoryRepository();
        inventoryService = new InventoryService(repository);
    }

    @Test
    public void testFindPart() {
        var list = inventoryService.getAllParts();
        assert(Objects.equals(list.get(0).getName(), "Cog"));
        assert(Objects.equals(list.get(1).getName(), "Spring"));
        assert(Objects.equals(list.get(2).getName(), "Screw"));
    }

    @Test
    public void testFindProduct() {
        var list = inventoryService.getAllProducts();
        assert(Objects.equals(list.get(0).getName(), "Clock"));
        assert(Objects.equals(list.get(1).getName(), "product2"));
        assert(Objects.equals(list.get(2).getName(), "products4"));
    }

}