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
import java.util.ArrayList;

public class LoginGUI{
    public TextField emailInput;
    public PasswordField passwordInput;
    public Button enterButton;
    public JSONReader jsonReader = new JSONReader();

    @FXML
    public void loginUser() throws IOException, URISyntaxException, ParseException, java.text.ParseException {
        String email = emailInput.getText();
        String password = passwordInput.getText();
        email = email + "@bsu.edu";
        JSONReader reader = new JSONReader();
        String nameFromJSONDoc = reader.getEmail(email);
        String passwordFromJSONDoc = reader.getPassword(email);
        ArrayList<String> roommateList = reader.getRoommates(email);
        if (email.equals(nameFromJSONDoc) && password.equals(passwordFromJSONDoc)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainScreenGUI.fxml"));
            Parent root = loader.load();
            MainScreenGUI mainScreenGUI = loader.getController();
            mainScreenGUI.displayEmail(email);
            mainScreenGUI.displayName(email);
            mainScreenGUI.displayPhoneNumber(email);
            mainScreenGUI.setEmail(email);
            mainScreenGUI.displayRoommates(roommateList);
            enterButton.getScene().setRoot(root);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("The username and password do not match.");
            alert.setContentText("Please try again.");
            alert.showAndWait();
        }
    }
    public void openSignUpPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SignUpGUI.fxml"));
        Parent root = loader.load();
        enterButton.getScene().setRoot(root);
    }
}