package edu.bsu.cs222;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import net.minidev.json.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class DisplayFullCalendarGUI {
    public Label calendar;
    public Label nameBox;
    public Label phoneNumberBox;
    public Label emailBox;
    public Button goBackButton;
    public JSONReader jsonReader = new JSONReader();
    public ScrollPane scrollView;




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
    public void test(){

        calendar.setText("What\nDay\nIs\nIt\ni\nwant\nice\ncream\ntoday\nwith\nchocolate\nand\ncaramel\n" +
                "it\nis\ntime\to\npet\nall\nof\nthe\ncats\n");
    }

    @FXML
    public void displayAllClasses() throws FileNotFoundException, URISyntaxException, ParseException {
        StringBuilder schedule = new StringBuilder();
        ArrayList<String> roommates = jsonReader.getRoommates(emailBox.getText());
        roommates.add(emailBox.getText());
        ArrayList<String> allMonday = new ArrayList<>();
        ArrayList<String> allTuesday = new ArrayList<>();
        ArrayList<String> allWednesday = new ArrayList<>();
        ArrayList<String> allThursday = new ArrayList<>();
        ArrayList<String> allFriday = new ArrayList<>();
        for (String roommate : roommates) {
            ArrayList<ArrayList<String>> allEvents = jsonReader.getEvent(roommate);
            ArrayList<String> monday = allEvents.get(0);
            allMonday.addAll(monday);
            ArrayList<String> tuesday = allEvents.get(1);
            allTuesday.addAll(tuesday);
            ArrayList<String> wednesday = allEvents.get(2);
            allWednesday.addAll(wednesday);
            ArrayList<String> thursday = allEvents.get(3);
            allThursday.addAll(thursday);
            ArrayList<String> friday = allEvents.get(4);
            allFriday.addAll(friday);
        }

        schedule.append("\nMonday\n");
        for (String day : allMonday) {
            schedule.append(day).append("\n");
        }
        schedule.append("\nTuesday\n");
        for (String day : allTuesday) {
            schedule.append(day).append("\n");
        }
        schedule.append("\nWednesday\n");
        for (String day : allWednesday) {
            schedule.append(day).append("\n");
        }
        schedule.append("\nThursday\n");
        for (String day : allThursday) {
            schedule.append(day).append("\n");
        }
        schedule.append("\nFriday\n");
        for (String day : allFriday) {
            schedule.append(day).append("\n");

        }
        schedule.append("\nSaturday\nNo classes!\n\nSunday\nNo classes!\n\n");

        LocalDate localDate = LocalDate.now();
        DayOfWeek dayOfWeek = DayOfWeek.from(localDate);

        String mondaySubstring = schedule.substring(0, schedule.indexOf("Tuesday"));
        String tuesdaySubstring = schedule.substring(schedule.indexOf("Tuesday"), schedule.indexOf("Wednesday"));
        String wednesdaySubstring = schedule.substring(schedule.indexOf("Wednesday"), schedule.indexOf("Thursday"));
        String thursdaySubstring = schedule.substring(schedule.indexOf("Thursday"), schedule.indexOf("Friday"));
        String fridaySubstring = schedule.substring(schedule.indexOf("Friday"), schedule.indexOf("Saturday"));
        String saturdaySubstring = schedule.substring(schedule.indexOf("Saturday"), schedule.indexOf("Sunday"));
        String sundaySubstring = schedule.substring(schedule.indexOf("Sunday"), schedule.lastIndexOf("\n"));

        if (dayOfWeek.name().equals("MONDAY")){
            calendar.setText(mondaySubstring + tuesdaySubstring + wednesdaySubstring + thursdaySubstring + fridaySubstring + saturdaySubstring + sundaySubstring);
        }
        else if (dayOfWeek.name().equals("TUESDAY")) {
            calendar.setText(tuesdaySubstring + wednesdaySubstring + thursdaySubstring + fridaySubstring + saturdaySubstring + sundaySubstring + mondaySubstring);
        }
        else if (dayOfWeek.name().equals("WEDNESDAY")) {
            calendar.setText(wednesdaySubstring + thursdaySubstring + fridaySubstring + saturdaySubstring + sundaySubstring + mondaySubstring + tuesdaySubstring);
        }
        else if (dayOfWeek.name().equals("THURSDAY")) {
            calendar.setText(thursdaySubstring + fridaySubstring + saturdaySubstring + sundaySubstring + mondaySubstring + tuesdaySubstring + wednesdaySubstring);
        }
        else if (dayOfWeek.name().equals("FRIDAY")) {
            calendar.setText(fridaySubstring + saturdaySubstring + sundaySubstring + mondaySubstring + tuesdaySubstring + wednesdaySubstring + thursdaySubstring);
        }
        else if (dayOfWeek.name().equals("SATURDAY")) {
            calendar.setText(saturdaySubstring + sundaySubstring + mondaySubstring + tuesdaySubstring + wednesdaySubstring + thursdaySubstring + fridaySubstring);
        }
        else {
            calendar.setText(sundaySubstring + mondaySubstring + tuesdaySubstring + wednesdaySubstring + thursdaySubstring + fridaySubstring + saturdaySubstring);
        }

    }
    public void goBackToSettings(ActionEvent actionEvent) throws IOException, URISyntaxException, ParseException, java.text.ParseException {
        String email = emailBox.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainScreenGUI.fxml"));
        Parent root = loader.load();
        MainScreenGUI mainScreenGUI = loader.getController();
        mainScreenGUI.displayEmail(email);
        mainScreenGUI.displayName(email);
        mainScreenGUI.displayPhoneNumber(email);
        mainScreenGUI.setEmail(email);
        mainScreenGUI.displayRoommates(jsonReader.getRoommates(emailBox.getText()));
        goBackButton.getScene().setRoot(root);

    }
}
