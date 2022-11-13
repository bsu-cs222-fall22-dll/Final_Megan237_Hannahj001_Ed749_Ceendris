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
        String nameFromJSONDoc = reader.getEmail(email);
        String passwordFromJSONDoc = reader.getPassword(email);
        if (email.equals(nameFromJSONDoc) && password.equals(passwordFromJSONDoc)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainScreenGUI.fxml"));
            Parent root = loader.load();
            MainScreenGUI mainScreenGUI = loader.getController();
            mainScreenGUI.displayEmail(email);
            mainScreenGUI.displayName(email);
            mainScreenGUI.displayPhoneNumber(email);
            mainScreenGUI.setEmail(email);

            // NEEDS TO GRAB ARRAY LIST OF ROOMMATES FROM JSON
//            mainScreenGUI.displayRoommates(email);


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
