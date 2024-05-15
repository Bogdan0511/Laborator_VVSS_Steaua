package inventory.repository;

import inventory.model.InhousePart;
import inventory.model.Inventory;
import inventory.model.Part;
import inventory.model.Product;
import inventory.service.InventoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class InventoryRepositoryTest {

    @InjectMocks
    private InventoryRepository repository; // A concrete class that implements the InventoryRepository interface.

    @Mock
    private Inventory inventory;

    @Mock
    private InhousePart inhousePart;

    @Mock
    private Product product;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
        repository = null; // Clean up after each test.
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