package edu.bsu.cs222;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
        StringBuilder schedule;
        schedule = new StringBuilder("Monday\n");
        for (String day : jsonReader.mondayArray(email)){
            schedule.append(day).append("\n");
        }
        schedule.append("\nTuesday\n");
        for (String day : jsonReader.tuesdayArray(email)){
            schedule.append(day).append("\n");
        }
        schedule.append("\nWednesday\n");
        for (String day : jsonReader.wednesdayArray(email)){
            schedule.append(day).append("\n");
        }
        schedule.append("\nThursday\n");
        for (String day : jsonReader.thursdayArray(email)){
            schedule.append(day).append("\n");
        }
        schedule.append("\nFriday\n");
        for (String day : jsonReader.fridayArray(email)){
            schedule.append(day).append("\n");
        }
        displaySchedule.setText(schedule.toString());
    }
}

