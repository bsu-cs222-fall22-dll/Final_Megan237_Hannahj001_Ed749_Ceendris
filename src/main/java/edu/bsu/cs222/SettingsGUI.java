package edu.bsu.cs222;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import net.minidev.json.parser.ParseException;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class SettingsGUI{


    public Button backButton;
    public Button logOutButton;
    public Button editScheduleButton;
    public Button editUserInfoButton;
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
    public void goBackToMainScreen(ActionEvent actionEvent) throws IOException, URISyntaxException, ParseException, java.text.ParseException {
        String email = emailBox.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainScreenGUI.fxml"));
        Parent root = loader.load();
        MainScreenGUI mainScreenGUI = loader.getController();
        mainScreenGUI.displayEmail(email);
        mainScreenGUI.displayName(email);
        mainScreenGUI.displayPhoneNumber(email);
        mainScreenGUI.setEmail(email);
        mainScreenGUI.displayRoommates(jsonReader.getRoommates(emailBox.getText()));
        backButton.getScene().setRoot(root);

    }

    public void GoToEditUserScreen(ActionEvent actionEvent) throws IOException, URISyntaxException, ParseException {
        String email = emailBox.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditUserInformationGUI.fxml"));
        Parent root = loader.load();
        EditUserInformationGUI editUserInformationGUI = loader.getController();
        editUserInformationGUI.displayEmail(email);
        editUserInformationGUI.displayName(email);
        editUserInformationGUI.displayPhoneNumber(email);
        editUserInfoButton.getScene().setRoot(root);
    }

    public void GoToEditScheduleScreen(ActionEvent actionEvent) throws IOException, URISyntaxException, ParseException {
        String email = emailBox.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditUserCalenderGUI.fxml"));
        Parent root = loader.load();
        EditUserCalenderGUI editUserCalenderGUI = loader.getController();
        editUserCalenderGUI.displayEmail(email);
        editUserCalenderGUI.displayName(email);
        editUserCalenderGUI.displayPhoneNumber(email);
        editUserCalenderGUI.displayRemovableEvents(email);

        editScheduleButton.getScene().setRoot(root);
    }

    public void LogOutAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginGUI.fxml"));
        Parent root = loader.load();
        logOutButton.getScene().setRoot(root);
    }

    public void addRoommate(ActionEvent actionEvent) throws IOException, URISyntaxException, ParseException {
        String email = emailBox.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RoommateAddGUI.fxml"));
        Parent root = loader.load();
        RoommateAddGUI roommateAddGUI = loader.getController();
        roommateAddGUI.displayEmail(email);
        roommateAddGUI.displayName(email);
        roommateAddGUI.displayPhoneNumber(email);
        roommateAddGUI.displayRemoveRoommates(email);

        editScheduleButton.getScene().setRoot(root);


    }
}
