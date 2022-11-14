package edu.bsu.cs222;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
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
    public Label status3;
    public Label status2;
    public Label status1;
    public Button settingsButton;

    public String userEmail;
    public ScrollPane roommateScrollPane;
    public AnchorPane anchorPaneOfRoommates;

    public Button roommate1Button;
    public Button roommate2Button;
    public Button roommate3Button;

    public MainScreenGUI() throws FileNotFoundException, URISyntaxException, ParseException {

    }


    public void setEmail(String email){
        userEmail = email;
      }


    @FXML
    public void displayName(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        String name = jsonReader.getName(email);
        nameBox.setText(name);
    }
    @FXML
    /// ROOMMATES NEED TO GRAB ARRAY LIST OF ROOMMATES FROM THE LOGIN PAGE
    public void displayRoommates(ArrayList<String> roommates){
        if (roommates.size() == 3){
            roommate1Button.setText(roommates.get(0));
            roommate2Button.setText(roommates.get(1));
            roommate3Button.setText(roommates.get(2));
        }
        else if(roommates.size() == 2){
            roommate1Button.setText(roommates.get(0));
            roommate2Button.setText(roommates.get(1));

            roommate3Button.setVisible(false);

        }
        else if(roommates.size() == 1){
            roommate1Button.setText(roommates.get(0));

            roommate2Button.setVisible(false);
            roommate3Button.setVisible(false);

        }
        else {
            anchorPaneOfRoommates.setVisible(false);
        }
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
        String roommate1E = jsonReader.getRoommates(userEmail).get(0);
        displayUser.displayEmail(roommate1E);
        displayUser.displayName(roommate1E);
        displayUser.displayPhoneNumber(roommate1E);
        displayUser.setDisplaySchedule(roommate1E);

        roommate1Button.getScene().setRoot(root);

    }

    public void openRoommate2(ActionEvent actionEvent) throws IOException, URISyntaxException, ParseException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayUserInfoGUI.fxml"));
        Parent root = loader.load();
        DisplayUserInfoGUI displayUser = loader.getController();
        String roommate2E = jsonReader.getRoommates(userEmail).get(1);
        displayUser.displayEmail(roommate2E);
        displayUser.displayName(roommate2E);
        displayUser.displayPhoneNumber(roommate2E);
        displayUser.setDisplaySchedule(roommate2E);

        roommate2Button.getScene().setRoot(root);

    }

    public void openRoommate3(ActionEvent actionEvent) throws IOException, URISyntaxException, ParseException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayUserInfoGUI.fxml"));
        Parent root = loader.load();
        DisplayUserInfoGUI displayUser = loader.getController();
        String roommate3E = jsonReader.getRoommates(userEmail).get(2);
        displayUser.displayEmail(roommate3E);
        displayUser.displayName(roommate3E);
        displayUser.displayPhoneNumber(roommate3E);
        displayUser.setDisplaySchedule(roommate3E);

        roommate3Button.getScene().setRoot(root);

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
}
