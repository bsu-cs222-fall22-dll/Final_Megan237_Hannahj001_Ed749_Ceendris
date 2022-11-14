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


    public void setEmail(String email){
        userEmail = email;
      }


    @FXML
    public void displayName(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        String name = jsonReader.getName(email);
        nameBox.setText(name);
    }
    @FXML
    public void displayRoommates(ArrayList<String> roommates){
        if (roommates.size() == 3){

            roommate1Button.setText(roommates.get(0));
            roommate2Button.setText(roommates.get(1));
            roommate3Button.setText(roommates.get(2));
            status1.setText(roommates.get(0));
            status2.setText(roommates.get(1));
            status3.setText(roommates.get(2));
        }
        else if(roommates.size() == 2){
            roommate1Button.setText(roommates.get(0));
            roommate2Button.setText(roommates.get(1));
            status1.setText(roommates.get(0));
            status2.setText(roommates.get(1));

            roommate3Button.setVisible(false);
            status3.setVisible(false);

        }
        else if(roommates.size() == 1){
            roommate1Button.setText(roommates.get(0));
            status1.setText(roommates.get(0));

            roommate2Button.setVisible(false);
            status2.setVisible(false);
            roommate3Button.setVisible(false);
            status3.setVisible(false);

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RoommateDisplayGUI.fxml"));
        Parent root = loader.load();
        RoommateDisplayGUI roommateDisplayGUI = loader.getController();
        roommateDisplayGUI.displayEmail(userEmail);
        roommateDisplayGUI.displayName(userEmail);
        roommateDisplayGUI.displayPhoneNumber(userEmail);
        roommateDisplayGUI.setDisplaySchedule(roommate1Button.getText());
        roommateDisplayGUI.setRoommateDisplay(roommate1Button.getText());
        profileButton.getScene().setRoot(root);

    }

    public void openRoommate2(ActionEvent actionEvent) throws IOException, URISyntaxException, ParseException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RoommateDisplayGUI.fxml"));
        Parent root = loader.load();
        RoommateDisplayGUI roommateDisplayGUI = loader.getController();
        roommateDisplayGUI.displayEmail(userEmail);
        roommateDisplayGUI.displayName(userEmail);
        roommateDisplayGUI.displayPhoneNumber(userEmail);
        roommateDisplayGUI.setDisplaySchedule(roommate2Button.getText());
        roommateDisplayGUI.setRoommateDisplay(roommate2Button.getText());

        profileButton.getScene().setRoot(root);

    }

    public void openRoommate3(ActionEvent actionEvent) throws IOException, URISyntaxException, ParseException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RoommateDisplayGUI.fxml"));
        Parent root = loader.load();
        RoommateDisplayGUI roommateDisplayGUI = loader.getController();

        roommateDisplayGUI.displayEmail(userEmail);
        roommateDisplayGUI.displayName(userEmail);
        roommateDisplayGUI.displayPhoneNumber(userEmail);
        roommateDisplayGUI.setDisplaySchedule(roommate3Button.getText());
        roommateDisplayGUI.setRoommateDisplay(roommate3Button.getText());

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
}
