package edu.bsu.cs222;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.minidev.json.parser.ParseException;
import java.io.IOException;
import java.net.URISyntaxException;

public class LoginGUI{
    public TextField emailInput;
    public PasswordField passwordInput;
    public Button enterButton;


    @FXML
    public void loginUser() throws IOException, URISyntaxException, ParseException {
        String email = emailInput.getText();
        String password = passwordInput.getText();
        email = email + "@bsu.edu";
        JSONReader reader = new JSONReader();
        String nameFromJSONDoc = reader.getEmail("ceendris@bsu.edu");
        String passwordFromJSONDoc = reader.getPassword("ceendris@bsu.edu");
        if (email.equals(nameFromJSONDoc) && password.equals(passwordFromJSONDoc)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayUserInfoGUI.fxml"));
            Parent root = loader.load();
            DisplayUserInfoGUI displayUserInfoGUI = loader.getController();
            displayUserInfoGUI.displayEmail(email);
            displayUserInfoGUI.displayName(email);
            displayUserInfoGUI.displayPhoneNumber(email);
            displayUserInfoGUI.setDisplaySchedule(email);
            enterButton.getScene().setRoot(root);

        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("That user does not exist.");
            alert.setContentText("Please enter a different username.");
            alert.showAndWait();
        }

    }

    public void openSignUpPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SignUpGUI.fxml"));
        Parent root = loader.load();
        enterButton.getScene().setRoot(root);
    }
}
