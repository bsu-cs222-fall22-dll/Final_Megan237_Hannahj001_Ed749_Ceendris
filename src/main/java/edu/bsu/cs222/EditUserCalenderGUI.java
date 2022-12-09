package edu.bsu.cs222;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.minidev.json.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
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
    public CheckBox tuesdayCheckboxMain;
    public CheckBox wednesdayCheck;
    public CheckBox ThursdayCheck;
    public CheckBox fridayCheck;
    public CheckBox saturdayCheck;
    public CheckBox sundayCheck;
    public ChoiceBox<String> allEventsTab;
    public ChoiceBox <String> daysOfEventsTab;
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

    public void goBackToSettings() throws IOException, URISyntaxException, ParseException {
        String email = emailBox.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SettingsGUI.fxml"));
        Parent root = loader.load();
        SettingsGUI settingsGUI = loader.getController();
        settingsGUI.displayEmail(email);
        settingsGUI.displayName(email);
        settingsGUI.displayPhoneNumber(email);
        settingsGUI.displayMainUserImage(email);
        goBackButton.getScene().setRoot(root);
    }

    public boolean checkNullDays(){
        boolean value = mondayCheck.isSelected();
        if (tuesdayCheckboxMain.isSelected()) {
            value = true;
        }
        if (wednesdayCheck.isSelected()) {
            value = true;
        }
        if (ThursdayCheck.isSelected()) {
            value = true;
        }
        if (fridayCheck.isSelected()) {
            value = true;
        }
        if (saturdayCheck.isSelected()) {
            value = true;
        }
        if (sundayCheck.isSelected()) {
            value = true;
        }
        return value;
    }

    public void addToSchedule() throws IOException, URISyntaxException, ParseException {
        if (eventName.getText() == null || startTime.getText() == null || endTime.getText() == null || !checkNullDays()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("A Calender Error Occurred");
            alert.setContentText("You have not entered all of the information");
            alert.showAndWait();
        }else {

            if (mondayCheck.isSelected()) {
                days.add("monday");
            }
            if (tuesdayCheckboxMain.isSelected()) {
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
            writer.writeEvent(userEmail, nameOfEvent, days, startTime.getText(), endTime.getText());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditUserCalenderGUI.fxml"));
            Parent root = loader.load();
            EditUserCalenderGUI editUserCalenderGUI = loader.getController();
            editUserCalenderGUI.displayEmail(userEmail);
            editUserCalenderGUI.displayName(userEmail);
            editUserCalenderGUI.displayPhoneNumber(userEmail);
            addToSchedule.getScene().setRoot(root);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Event Added");
            alert.setHeaderText("Calender Has Been Updated");
            alert.setContentText("Exit Application to Finish Updating");
            alert.showAndWait();
        }
    }

    public void displayRemovableEvents(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        ArrayList<String> eventArrayList = jsonReader.getEventNames(email);
        ObservableList<String> eventList = FXCollections.observableArrayList("Select an Event");
        eventList.addAll(eventArrayList);
        allEventsTab.setItems(eventList);
    }

    public void displayDaysOfEvent(String email,String event) throws FileNotFoundException, URISyntaxException, ParseException {
        ArrayList<String> eventDayList = jsonReader.getDaysBasesOnEventName(email,event);
        ObservableList<String> dayList = FXCollections.observableArrayList();
        dayList.addAll(eventDayList);

        daysOfEventsTab.setValue("Select day/s to remove");
        daysOfEventsTab.setItems(dayList);

    }

    public void selectEvent() throws FileNotFoundException, URISyntaxException, ParseException {
        String selection = allEventsTab.getValue();
        if (selection == null || selection.equals("Select an Event")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("A Selection Error Occurred");
            alert.setContentText("You need to select an event to remove it.");
            alert.showAndWait();
        }else{
            displayDaysOfEvent(emailBox.getText(),selection);
        }
    }

    public void removeEvent(){
        String selection = allEventsTab.getValue();
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if (selection == null || selection.equals("Select day/s to remove")){
            alert.setTitle("Error");
            alert.setHeaderText("A Selection Error Occurred");
            alert.setContentText("You need to select the days of the event you wish to remove.");
        }else{

            alert.setTitle("Conformation");
            alert.setHeaderText("Event Removed");
            alert.setContentText("You have removed your event successfully");
        }
        alert.showAndWait();
    }
}
