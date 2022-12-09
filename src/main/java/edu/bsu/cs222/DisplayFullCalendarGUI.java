package edu.bsu.cs222;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.minidev.json.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;
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
    public ImageView userImage;

    @FXML
    public void displayMainUserImage(String email) throws FileNotFoundException, URISyntaxException, ParseException{
        String path = jsonReader.getImagePath(email);
        Image image = new Image(String.valueOf(Path.of(path).toUri()));
        userImage.setImage(image);
    }

    @FXML
    public void displayName(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        String name = jsonReader.getName(email);
        nameBox.setText(name);
    }

    @FXML
    public void displayEmail(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        String displayedEmail = jsonReader.getEmail(email);
        emailBox.setText(displayedEmail);
    }

    @FXML
    public void displayPhoneNumber(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        String phoneNumber = jsonReader.getPhoneNumber(email);
        phoneNumberBox.setText(phoneNumber);
    }

    @FXML
    public void displayAllClasses() throws FileNotFoundException, URISyntaxException, ParseException {
        StringBuilder schedule;
        ArrayList<String> roommates = jsonReader.getRoommates(emailBox.getText());
        roommates.add(emailBox.getText());
        ArrayList<String> allMonday = new ArrayList<>();
        ArrayList<String> allMondayTime = new ArrayList<>();
        ArrayList<String> allTuesday = new ArrayList<>();
        ArrayList<String> allTuesdayTime = new ArrayList<>();
        ArrayList<String> allWednesday = new ArrayList<>();
        ArrayList<String> allWednesdayTime = new ArrayList<>();
        ArrayList<String> allThursday = new ArrayList<>();
        ArrayList<String> allThursdayTime = new ArrayList<>();
        ArrayList<String> allFriday = new ArrayList<>();
        ArrayList<String> allFridayTime = new ArrayList<>();
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
            ArrayList<String> mondayTime = allEvents.get(5);
            allMondayTime.addAll(mondayTime);
            ArrayList<String> tuesdayTime = allEvents.get(6);
            allTuesdayTime.addAll(tuesdayTime);
            ArrayList<String> wednesdayTime = allEvents.get(7);
            allWednesdayTime.addAll(wednesdayTime);
            ArrayList<String> thursdayTime = allEvents.get(8);
            allThursdayTime.addAll(thursdayTime);
            ArrayList<String> fridayTime = allEvents.get(9);
            allFridayTime.addAll(fridayTime);
        }


        schedule = new StringBuilder("\nMonday\n");
        int j = 0;
        for (String day : allMonday) {
            schedule.append(allMondayTime.get(j));
            schedule.append("-");
            schedule.append(allMondayTime.get(j + 1));
            schedule.append(" ");
            schedule.append(day).append("\n");
            j = j + 2;
        }
        j=0;
        schedule.append("\nTuesday\n");
        for (String day : allTuesday){
            schedule.append(allTuesdayTime.get(j));
            schedule.append("-");
            schedule.append(allTuesdayTime.get(j + 1));
            schedule.append(" ");
            schedule.append(day).append("\n");
            j = j + 2;
        }
        j=0;
        schedule.append("\nWednesday\n");
        for (String day : allWednesday){
            schedule.append(allWednesdayTime.get(j));
            schedule.append("-");
            schedule.append(allWednesdayTime.get(j + 1));
            schedule.append(" ");
            schedule.append(day).append("\n");
            j = j + 2;
        }
        j=0;
        schedule.append("\nThursday\n");
        for (String day : allThursday){
            schedule.append(allThursdayTime.get(j));
            schedule.append("-");
            schedule.append(allThursdayTime.get(j + 1));
            schedule.append(" ");
            schedule.append(day).append("\n");
            j = j + 2;
        }
        j=0;
        schedule.append("\nFriday\n");
        for (String day : allFriday){
            schedule.append(allFridayTime.get(j));
            schedule.append("-");
            schedule.append(allFridayTime.get(j + 1));
            schedule.append(" ");
            schedule.append(day).append("\n");
            j = j + 2;
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

        switch (dayOfWeek.name()) {
            case "MONDAY" -> calendar.setText(mondaySubstring + tuesdaySubstring + wednesdaySubstring + thursdaySubstring + fridaySubstring + saturdaySubstring + sundaySubstring);
            case "TUESDAY" -> calendar.setText(tuesdaySubstring + wednesdaySubstring + thursdaySubstring + fridaySubstring + saturdaySubstring + sundaySubstring + mondaySubstring);
            case "WEDNESDAY" -> calendar.setText(wednesdaySubstring + thursdaySubstring + fridaySubstring + saturdaySubstring + sundaySubstring + mondaySubstring + tuesdaySubstring);
            case "THURSDAY" -> calendar.setText(thursdaySubstring + fridaySubstring + saturdaySubstring + sundaySubstring + mondaySubstring + tuesdaySubstring + wednesdaySubstring);
            case "FRIDAY" -> calendar.setText(fridaySubstring + saturdaySubstring + sundaySubstring + mondaySubstring + tuesdaySubstring + wednesdaySubstring + thursdaySubstring);
            case "SATURDAY" -> calendar.setText(saturdaySubstring + sundaySubstring + mondaySubstring + tuesdaySubstring + wednesdaySubstring + thursdaySubstring + fridaySubstring);
            default -> calendar.setText(sundaySubstring + mondaySubstring + tuesdaySubstring + wednesdaySubstring + thursdaySubstring + fridaySubstring + saturdaySubstring);
        }

    }

    public void goBackToMain() throws IOException, URISyntaxException, ParseException, java.text.ParseException {
        String email = emailBox.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainScreenGUI.fxml"));
        Parent root = loader.load();
        MainScreenGUI mainScreenGUI = loader.getController();
        mainScreenGUI.displayEmail(email);
        mainScreenGUI.displayName(email);
        mainScreenGUI.displayPhoneNumber(email);
        mainScreenGUI.setEmail(email);
        mainScreenGUI.displayMainUserImage(email);
        mainScreenGUI.displayRoommates(jsonReader.getRoommates(emailBox.getText()));
        goBackButton.getScene().setRoot(root);

    }
}
