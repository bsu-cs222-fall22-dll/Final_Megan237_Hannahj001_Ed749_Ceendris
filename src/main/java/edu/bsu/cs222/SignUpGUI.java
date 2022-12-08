package edu.bsu.cs222;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import net.minidev.json.parser.ParseException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class SignUpGUI {

    public TextField firstNameInput;
    public TextField lastNameInput;
    public PasswordField password1Input;
    public PasswordField password2Input;
    public TextField emailInput;
    public TextField phoneNumberInput;
    public ProgressBar progressBar;
    public Button signUpButton;
    public Label passwordErrorBox;
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
        if(firstNameInput.getText() != null){
            if (firstNameProgress == 0) {
                firstNameProgress = 1;
                progress += 0.16;
                progressBar.setProgress(progress);
            }
        }if(Objects.equals(firstNameInput.getText(), "")){
            firstNameProgress = 0;
            progress -= 0.16;
            progressBar.setProgress(progress);
        }
    }
    @FXML
    public void lastNameProgress(KeyEvent keyEvent) {
        if(lastNameInput.getText() != null){
            if (lastNameProgress == 0) {
                lastNameProgress = 1;
                progress += 0.16;
                progressBar.setProgress(progress);
            }
        }if(Objects.equals(lastNameInput.getText(), "")){
            lastNameProgress = 0;
            progress -= 0.16;
            progressBar.setProgress(progress);
        }
    }
    @FXML
    public void password1Progress(KeyEvent keyEvent) {
        if(password1Input.getText() != null){
            if (password1Progress == 0) {
                password1Progress = 1;
                progress += 0.16;
                progressBar.setProgress(progress);
            }
        }if(Objects.equals(password1Input.getText(), "")){
            password1Progress = 0;
            progress -= 0.16;
            progressBar.setProgress(progress);
        }
    }
    @FXML
    public void password2Progress(KeyEvent keyEvent) {
        if(password2Input.getText() != null){
            if (password2Progress == 0) {
                password2Progress = 1;
                progress += 0.16;
                progressBar.setProgress(progress);
            }
        }if(Objects.equals(password2Input.getText(), "")){
            password2Progress = 0;
            progress -= 0.16;
            progressBar.setProgress(progress);
        }
    }
    @FXML
    public void emailProgress(KeyEvent keyEvent) {
        if(emailInput.getText() != null){
            if (emailProgress == 0) {
                emailProgress = 1;
                progress += 0.16;
                progressBar.setProgress(progress);
            }
        }if(Objects.equals(emailInput.getText(), "")){
            emailProgress = 0;
            progress -= 0.16;
            progressBar.setProgress(progress);
        }
    }
    @FXML
    public void phoneNumberProgress(KeyEvent keyEvent) {
        if(phoneNumberInput.getText() != null){
            if (phoneNumberProgress == 0) {
                phoneNumberProgress = 1;
                progress += 0.16;
                progressBar.setProgress(progress);
            }
        }if(Objects.equals(phoneNumberInput.getText(), "")){
            phoneNumberProgress = 0;
            progress -= 0.16;
            progressBar.setProgress(progress);
        }
    }
    @FXML
    public void checkPassword(KeyEvent mouseEvent) {
        String mainPassword = password1Input.getText();
        String checkPassword = password2Input.getText();
        if (mainPassword.equals(checkPassword)){
            passwordErrorBox.setTextFill(Color.web("#69c06d"));
            passwordErrorBox.setText("Passwords match");
        }else{
            passwordErrorBox.setTextFill(Color.web("#DC143C"));
            passwordErrorBox.setText("Passwords Do Not Match");
        }
    }
    @FXML
    public void completeSignUp(ActionEvent actionEvent) throws IOException, URISyntaxException, ParseException {
        if (firstNameInput.getText().equals("")||lastNameInput.getText().equals("")||emailInput.getText().equals("")||phoneNumberInput.getText().equals("")||password1Input.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sign Up Error");
            alert.setHeaderText("A Sign Up Error Occurred");
            alert.setContentText("You have not entered all of the sign up information");
            alert.showAndWait();
        }
// This is the check if the email is already being used
//        else if (Objects.equals(emailInput.getText(), jsonReader.getEmail(emailInput.getText()))){
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Sign Up Error");
//            alert.setHeaderText("A Sign Up Error Occurred");
//            alert.setContentText("This email is being used for an existing user.");
//            alert.showAndWait();
//        }
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
        return password1Input.getText();
    }
}