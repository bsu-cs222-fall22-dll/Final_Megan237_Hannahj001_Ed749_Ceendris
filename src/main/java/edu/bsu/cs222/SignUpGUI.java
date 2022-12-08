package edu.bsu.cs222;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import net.minidev.json.parser.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
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
    private double firstNameProgress = 0.0;
    private double lastNameProgress = 0.0;
    private double emailProgress = 0.0;
    private double phoneNumberProgress = 0.0;
    private double password1Progress = 0.0;
    private double password2Progress = 0.0;
    private double progress = 0.0;
    public JSONReader jsonReader = new JSONReader();



    @FXML
    public void firstNameProgress(KeyEvent keyEvent) {
        if (firstNameProgress == 0){
            firstNameProgress += 0.16;
            progress += 0.16;
            progressBar.setProgress(progress);
        }
    }
    @FXML
    public void lastNameProgress(KeyEvent keyEvent) {
        if(lastNameProgress == 0){
            lastNameProgress = 0.16;
            progress += 0.16;
            progressBar.setProgress(progress);
        }
    }
    @FXML
    public void password1Progress(KeyEvent keyEvent) {
        if(password1Progress == 0){
            password1Progress = 0.16;
            progress += 0.16;
            progressBar.setProgress(progress);
        }
    }
    @FXML
    public void password2Progress(KeyEvent keyEvent) {
        if(password2Progress == 0){
            password2Progress = 0.16;
            progress += 0.16;
            progressBar.setProgress(progress);
        }
    }
    @FXML
    public void emailProgress(KeyEvent keyEvent) {
        if(emailProgress == 0){
            emailProgress = 0.16;
            progress += 0.16;
            progressBar.setProgress(progress);
        }
    }
    @FXML
    public void phoneNumberProgress(KeyEvent keyEvent) {
        if(phoneNumberProgress == 0){
            phoneNumberProgress = 0.16;
            progress += 0.16;
            progressBar.setProgress(progress);
        }
    }


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
    public void completeSignUp(ActionEvent actionEvent) throws IOException, URISyntaxException, ParseException {
        String nameFromJSONDoc = jsonReader.getEmail(emailInput.getText());
        if (firstNameInput.getText().equals("")||lastNameInput.getText().equals("")||emailInput.getText().equals("")||phoneNumberInput.getText().equals("")||passwordInput.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sign Up Error");
            alert.setHeaderText("A Sign Up Error Occurred");
            alert.setContentText("You have not entered all of the sign up information");
            alert.showAndWait();
        }
        else if (Objects.equals(emailInput.getText(), nameFromJSONDoc)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sign Up Error");
            alert.setHeaderText("A Sign Up Error Occurred");
            alert.setContentText("This email is being used for an existing user.");
            alert.showAndWait();
        }
        else {
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
}
