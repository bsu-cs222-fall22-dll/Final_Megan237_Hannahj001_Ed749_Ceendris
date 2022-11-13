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
import java.util.ArrayList;

public class MainScreenGUI {
    public Label nameBox;
    public Label phoneNumberBox;
    public Label emailBox;
    public JSONReader jsonReader = new JSONReader();
    public Button profileButton;
    public Label phoneNumberBox111;
    public Label phoneNumberBox11;
    public Label phoneNumberBox1;
    public Button settingsButton;
    public Button roommate1Button;

    public String userEmail;
    public String roommate1Email;
    public String roommate2Email;
    public String roommate3Email;


    public void setEmail(String email){
        userEmail = email;
      }


    public void setRoommate1Email(String email){
        userEmail = email;
    }
    public void setRoommate2Email(String email){
        userEmail = email;
    }
    public void setRoommate3Email(String email){
        userEmail = email;
    }


    @FXML
    public void displayName(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        String name = jsonReader.getName(email);
        nameBox.setText(name);
    }
    @FXML
    /// ROOMMATES NEED TO GRAB ARAY LIST OF ROOMMATES FROM THE LOGIN PAGE
    public void displayRoommates(ArrayList<String> roommates){
        int roommatesNumber = roommates.size();


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


    public void openRoommate1(ActionEvent actionEvent) throws IOException, URISyntaxException, ParseException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayUserInfoGUI.fxml"));
        Parent root = loader.load();
        DisplayUserInfoGUI displayUser = loader.getController();

        displayUser.displayEmail(roommate1Email);
        displayUser.displayName(roommate1Email);
        displayUser.displayPhoneNumber(roommate1Email);
        displayUser.setDisplaySchedule(roommate1Email);

        profileButton.getScene().setRoot(root);

    }

    public void openRoommate2(ActionEvent actionEvent) throws IOException, URISyntaxException, ParseException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayUserInfoGUI.fxml"));
        Parent root = loader.load();
        DisplayUserInfoGUI displayUser = loader.getController();

        displayUser.displayEmail(roommate2Email);
        displayUser.displayName(roommate2Email);
        displayUser.displayPhoneNumber(roommate2Email);
        displayUser.setDisplaySchedule(roommate2Email);

        profileButton.getScene().setRoot(root);

    }

    public void openRoommate3(ActionEvent actionEvent) throws IOException, URISyntaxException, ParseException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayUserInfoGUI.fxml"));
        Parent root = loader.load();
        DisplayUserInfoGUI displayUser = loader.getController();

        displayUser.displayEmail(roommate3Email);
        displayUser.displayName(roommate3Email);
        displayUser.displayPhoneNumber(roommate3Email);
        displayUser.setDisplaySchedule(roommate3Email);

        profileButton.getScene().setRoot(root);

    }

    public void openSettings(ActionEvent actionEvent) throws IOException, URISyntaxException, ParseException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SettingsGUI.fxml"));
        Parent root = loader.load();
        SettingsGUI settingsGUI = loader.getController();

        settingsGUI.displayEmail(userEmail);
        settingsGUI.displayName(userEmail);
        settingsGUI.displayPhoneNumber(userEmail);

        settingsButton.getScene().setRoot(root);


    }


    public void reOpenMainScreen(String email) throws IOException, URISyntaxException, ParseException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainScreenGUI.fxml"));
        MainScreenGUI mainScreenGUI = loader.getController();
        mainScreenGUI.displayEmail(email);
        mainScreenGUI.displayName(email);
        mainScreenGUI.displayPhoneNumber(email);
        mainScreenGUI.setEmail(email);
    }
}
