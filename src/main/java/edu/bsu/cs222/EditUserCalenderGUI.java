package edu.bsu.cs222;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import net.minidev.json.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class EditUserCalenderGUI {
    public Button goBackButton;
    public Label nameBox;
    public Label phoneNumberBox;
    public Label emailBox;
    public JSONReader jsonReader = new JSONReader();
    public TextField startTime;
    public TextField endTime;
    public Button addToSchedule;
    public TextField eventName;
    public ArrayList<String> days = new ArrayList<>();
    public CheckBox mondayCheck;
    public CheckBox tuesdayheckboxmain;
    public CheckBox wednesdayCheck;
    public CheckBox ThursdayCheck;
    public CheckBox fridayCheck;
    public CheckBox saturdayCheck;
    public CheckBox sundayCheck;


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

    public void goBackToSettings(ActionEvent actionEvent) throws IOException, URISyntaxException, ParseException {
        String email = emailBox.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SettingsGUI.fxml"));
        Parent root = loader.load();
        SettingsGUI settingsGUI = loader.getController();
        settingsGUI.displayEmail(email);
        settingsGUI.displayName(email);
        settingsGUI.displayPhoneNumber(email);
        goBackButton.getScene().setRoot(root);
    }

    public void addToSchedule(ActionEvent actionEvent) throws IOException, URISyntaxException, ParseException {
        if (mondayCheck.isSelected()) {
            days.add("monday");
        }
        if (tuesdayheckboxmain.isSelected()) {
            days.add("tuesday");
        }
        if (wednesdayCheck.isSelected()) {
            days.add("wednesday");
        }
        if (ThursdayCheck.isSelected()) {
            days.add("thursday");
        }
        if (fridayCheck.isSelected()) {
            days.add("friday");
        }
        if (saturdayCheck.isSelected()) {
            days.add("saturday");
        }
        if (sundayCheck.isSelected()) {
            days.add("sunday");
        }

        JSONWriter writer = new JSONWriter();
        String userEmail = emailBox.getText();
        String nameOfEvent = eventName.getText();
        writer.writeEvent(userEmail, nameOfEvent, days);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditUserCalenderGUI.fxml"));
        Parent root = loader.load();
        EditUserCalenderGUI editUserCalenderGUI = loader.getController();
        editUserCalenderGUI.displayEmail(userEmail);
        editUserCalenderGUI.displayName(userEmail);
        editUserCalenderGUI.displayPhoneNumber(userEmail);
        addToSchedule.getScene().setRoot(root);
    }
}
