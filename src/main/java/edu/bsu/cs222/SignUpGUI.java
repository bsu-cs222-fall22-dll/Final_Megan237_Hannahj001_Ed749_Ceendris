package edu.bsu.cs222;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Objects;

public class SignUpGUI {

    public TextField firstNameInput;
    public TextField lastNameInput;
    public PasswordField passwordInput;
    public PasswordField checkPasswordInput;
    public TextField emailInput;
    public TextField phoneNumberInput;
    public ProgressBar progressBar;
    public Label passwordErrorBox;
    public Button signUpButton;


    @FXML
    public void checkingPasswords(ActionEvent actionEvent) {
        String mainPassword = passwordInput.getText();
        String checkPassword = checkPasswordInput.getText();

        if (!Objects.equals(mainPassword, checkPassword)){
            passwordErrorBox.setText("Passwords Do Not Match");
        }
    }

    @FXML
    public void completeSignUp(ActionEvent actionEvent) throws IOException {
        if (firstNameInput.getText().equals("")||lastNameInput.getText().equals("")||emailInput.getText().equals("")||phoneNumberInput.getText().equals("")||passwordInput.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sign Up Error");
            alert.setHeaderText("A Sign Up Error Occurred");
            alert.setContentText("You have not entered all of the sign up information");
            alert.showAndWait();
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginGUI.fxml"));
            Parent root = loader.load();
            signUpButton.getScene().setRoot(root);
        }
    }

    public String getName(){
        return firstNameInput.getText();
    }

    public String getEmail(){
        return emailInput.getText();
    }

    public String getPhoneNumber(){
        return phoneNumberInput.getText();
    }

    public String getPassword(){
        return passwordInput.getText();
    }

}
