package edu.bsu.cs222;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

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
    public void checkPassword(KeyEvent mouseEvent) {
        String mainPassword = passwordInput.getText();
        String checkPassword = checkPasswordInput.getText();

        if (mainPassword.equals(checkPassword)){
            passwordErrorBox.setTextFill(Color.web("#69c06d"));
            //#69c06d or #008000
            passwordErrorBox.setText("Passwords match");
        }else{
            passwordErrorBox.setTextFill(Color.web("#DC143C"));
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
            JSONWriter writer = new JSONWriter();
            writer.writeName(getName());
            writer.writeEmail(getEmail());
            writer.writePhoneNumber(getPhoneNumber());
            writer.writePassword(getPassword());
            writer.writeToFile(getEmail());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginGUI.fxml"));
            Parent root = loader.load();
            signUpButton.getScene().setRoot(root);

        }
    }

    public String getName(){
        String firstName = firstNameInput.getText();
        String lastName = lastNameInput.getText();

        return firstName +" "+ lastName;
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


    private boolean checkEmpty(String input){
        boolean check = false;
        if (!Objects.equals(input, "")){
            check = true;
        }
        return check;
    }


    public void progressBarUpdates(MouseEvent mouseEvent) {
        boolean firstNameCheck = checkEmpty(firstNameInput.getText());
        boolean lastNameCheck = checkEmpty(lastNameInput.getText());
        boolean emailCheck = checkEmpty(emailInput.getText());
        boolean phoneCheck = checkEmpty(phoneNumberInput.getText());
        boolean password1Check = checkEmpty(passwordInput.getText());
        boolean password2Check = checkEmpty(checkPasswordInput.getText());


    }
}
