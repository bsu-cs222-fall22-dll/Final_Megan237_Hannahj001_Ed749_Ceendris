package edu.bsu.cs222;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import net.minidev.json.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class SettingsGUI{


    public Button backButton;
    public Button logOutButton;
    public Button editScheduleButton;
    public Button editUserInfoButton;
    public String userEmail;
    public Button addRoommateButton;
    public Label nameBox;
    public Label phoneNumberBox;
    public Label emailBox;

    public JSONReader jsonReader = new JSONReader();


    @FXML
    public void displayName(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        String name = jsonReader.getName(email);
        nameBox.setText(name);
    }

    @FXML
    public void displayEmail(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        String Displayedemail = jsonReader.getEmail(email);
        emailBox.setText(Displayedemail);
    }

    @FXML
    public void displayPhoneNumber(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        String phoneNumber = jsonReader.getPhoneNumber(email);
        phoneNumberBox.setText(phoneNumber);
    }


    @FXML
    public void goBackToMainScreen(ActionEvent actionEvent) throws IOException, URISyntaxException, ParseException {
        MainScreenGUI mainScreenGUI = new MainScreenGUI();
        mainScreenGUI.reOpenMainScreen(userEmail);

    }

    public void GoToEditUserScreen(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditUserInformationGUI.fxml"));
        Parent root = loader.load();
        editUserInfoButton.getScene().setRoot(root);
    }

    public void GoToEditScheduleScreen(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditUserCalenderGUI.fxml"));
        Parent root = loader.load();
        editScheduleButton.getScene().setRoot(root);
    }

    public void LogOutAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginGUI.fxml"));
        Parent root = loader.load();
        logOutButton.getScene().setRoot(root);
    }

    public void addRoommate(ActionEvent actionEvent) {


    }
}
