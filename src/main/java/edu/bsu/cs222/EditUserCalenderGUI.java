package edu.bsu.cs222;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
    public CheckBox tuesdayheckboxmain;


    //Bug for days array, it adds it if you click then unclick a day


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

    public void mondayCheckBox(MouseEvent mouseEvent) {
        days.add("monday");
    }

    public void tuesdayCheckBox(ActionEvent mouseEvent) {
        days.add("tuesday");
    }

    public void wednesdayCheckBox(MouseEvent mouseEvent) {
        days.add("wednesday");
    }

    public void thursdayCheckBox(MouseEvent mouseEvent) {
        days.add("thursday");
    }

    public void FridayCheckBox(MouseEvent mouseEvent) {
        days.add("friday");
    }

    public void SaturdayCheckBox(MouseEvent mouseEvent) {
        days.add("saturday");
    }

    public void SundayCheckBox(MouseEvent mouseEvent) {
        days.add("sunday");
    }

    public void addToSchedule(ActionEvent actionEvent) throws IOException, URISyntaxException, ParseException {
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
