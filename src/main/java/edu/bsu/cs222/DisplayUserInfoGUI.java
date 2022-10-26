package edu.bsu.cs222;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import net.minidev.json.parser.ParseException;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;


public class DisplayUserInfoGUI {
    @FXML
    public Label nameBox;
    public Label phoneNumberBox;
    public Label emailBox;
    public JSONReader jsonReader = new JSONReader();
    public Label displaySchedule;

    @FXML
    public void updateUserInformation(String name, String phoneNumber, String email) throws FileNotFoundException, URISyntaxException, ParseException {

        nameBox.setText(name);
        phoneNumberBox.setText(phoneNumber);
        emailBox.setText("no");
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

    @FXML
    public void setDisplaySchedule(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        String schedule;
        schedule = "Monday\n";
        for (String day : jsonReader.mondayArray(email)){
            schedule += day + "\n";
        }
        schedule += "\nTuesday\n";
        for (String day : jsonReader.tuesdayArray(email)){
            schedule += day + "\n";
        }
        schedule += "\nWednesday\n";
        for (String day : jsonReader.wednesdayArray(email)){
            schedule += day + "\n";
        }
        schedule += "\nThursday\n";
        for (String day : jsonReader.thursdayArray(email)){
            schedule += day + "\n";
        }
        schedule += "\nFriday\n";
        for (String day : jsonReader.fridayArray(email)){
            schedule += day + "\n";
        }
        displaySchedule.setText(schedule);
    }
}

