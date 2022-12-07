package edu.bsu.cs222;

import edu.bsu.cs222.JSONReader;
import edu.bsu.cs222.MainScreenGUI;
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

public class RoommateDisplayGUI {
    public Button goBackToMainButton;
    public Label displaySchedule;
    public Label emailBox;
    public Label phoneNumberBox;
    public Label nameBox;
    public JSONReader jsonReader = new JSONReader();
    public Label nameScheduleLabel;

    @FXML
    public void setRoommateDisplay(String email){
        nameScheduleLabel.setText(email + " Schedule");

    }
    @FXML
    public void setDisplaySchedule(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        StringBuilder schedule;
        ArrayList<ArrayList<String>> allEvents = jsonReader.getEvent(email);
        ArrayList<String> monday = allEvents.get(0);
        ArrayList<String> tuesday = allEvents.get(1);
        ArrayList<String> wednesday = allEvents.get(2);
        ArrayList<String> thursday = allEvents.get(3);
        ArrayList<String> friday = allEvents.get(4);
        ArrayList<String> mondayTime = allEvents.get(5);
        ArrayList<String> tuesdayTime = allEvents.get(6);
        ArrayList<String> wednesdayTime = allEvents.get(7);
        ArrayList<String> thursdayTime = allEvents.get(8);
        ArrayList<String> fridayTime = allEvents.get(9);
        schedule = new StringBuilder("Monday\n");
        int j = 0;
        for (String day : monday) {
            schedule.append(mondayTime.get(j));
            schedule.append("-");
            schedule.append(mondayTime.get(j + 1));
            schedule.append(" ");
            schedule.append(day).append("\n");
            j = j + 2;
        }
        j=0;
        schedule.append("\nTuesday\n");
        for (String day : tuesday){
            schedule.append(tuesdayTime.get(j));
            schedule.append("-");
            schedule.append(tuesdayTime.get(j + 1));
            schedule.append(" ");
            schedule.append(day).append("\n");
            j = j + 2;
        }
        j=0;
        schedule.append("\nWednesday\n");
        for (String day : wednesday){
            schedule.append(wednesdayTime.get(j));
            schedule.append("-");
            schedule.append(wednesdayTime.get(j + 1));
            schedule.append(" ");
            schedule.append(day).append("\n");
            j = j + 2;
        }
        j=0;
        schedule.append("\nThursday\n");
        for (String day : thursday){
            schedule.append(thursdayTime.get(j));
            schedule.append("-");
            schedule.append(thursdayTime.get(j + 1));
            schedule.append(" ");
            schedule.append(day).append("\n");
            j = j + 2;
        }
        j=0;
        schedule.append("\nFriday\n");
        for (String day : friday){
            schedule.append(fridayTime.get(j));
            schedule.append("-");
            schedule.append(fridayTime.get(j + 1));
            schedule.append(" ");
            schedule.append(day).append("\n");
            j = j + 2;
        }
        displaySchedule.setText(schedule.toString());
    }



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
        goBackToMainButton.getScene().setRoot(root);
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
}
