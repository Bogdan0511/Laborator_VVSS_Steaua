package inventory.controller;

import inventory.service.InventoryService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Controller, Initializable {
    private Stage stage;
    private Parent scene;
    @FXML
    TextField usernameTextField;
    @FXML
    TextField passwordTextField;
    @FXML
    Button loginButton;
    @Override
    public void setService(InventoryService service) {
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    boolean login(){
        return false;
    }
}