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

public class MainScreenGUI {
    public Label nameBox;
    public Label phoneNumberBox;
    public Label emailBox;
    public JSONReader jsonReader = new JSONReader();
    public Button profileButton;

    public String userEmail;
    public Label phoneNumberBox111;
    public Label phoneNumberBox11;
    public Label phoneNumberBox1;
    public Button settingsButton;

    public void setEmail(String email){
        userEmail = email;
      }

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


    public void goToProfile(ActionEvent actionEvent) throws IOException, URISyntaxException, ParseException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayUserInfoGUI.fxml"));
        Parent root = loader.load();
        DisplayUserInfoGUI displayUser = loader.getController();

        displayUser.displayEmail(userEmail);
        displayUser.displayName(userEmail);
        displayUser.displayPhoneNumber(userEmail);
        displayUser.setDisplaySchedule(userEmail);

        profileButton.getScene().setRoot(root);
    }

    public void openRoommate1(ActionEvent actionEvent) {

    }

    public void openRoommate2(ActionEvent actionEvent) {

    }

    public void openRoommate3(ActionEvent actionEvent) {

    }

    public void openSettings(ActionEvent actionEvent) throws IOException {
        SettingsGUI settingsGUI = new SettingsGUI();
        settingsGUI.setEmail(userEmail);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SettingsGUI.fxml"));
        Parent root = loader.load();
        settingsButton.getScene().setRoot(root);

    }


    public void reOpenMainScreen(String email) throws IOException, URISyntaxException, ParseException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainScreenGUI.fxml"));
        Parent root = loader.load();

        MainScreenGUI mainScreenGUI = loader.getController();
        mainScreenGUI.displayEmail(email);
        mainScreenGUI.displayName(email);
        mainScreenGUI.displayPhoneNumber(email);
        mainScreenGUI.setEmail(email);
    }
}
