package edu.bsu.cs222;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import net.minidev.json.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

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
    public Button calendarButton;
    public Label noRoommatesLabel;

    public ImageView mainUserImage;
    public ImageView roommate1Image;
    public ImageView roommate2Image;
    public ImageView roommate3image;


    public void setEmail(String email){
        userEmail = email;
      }

    @FXML
    public void displayMainUserImage(String email) throws FileNotFoundException, URISyntaxException, ParseException{
        String path = jsonReader.getImagePath(email);
        Image image = new Image(String.valueOf(Path.of(path).toUri()));
        mainUserImage.setImage(image);
    }

    @FXML
    public void displayRoommate1(String email) throws FileNotFoundException, URISyntaxException, ParseException{
        String path = jsonReader.getImagePath(email);
        Image image = new Image(String.valueOf(Path.of(path).toUri()));
        roommate1Image.setImage(image);
    }
    @FXML
    public void displayRoommate2(String email) throws FileNotFoundException, URISyntaxException, ParseException{
        String path = jsonReader.getImagePath(email);
        Image image = new Image(String.valueOf(Path.of(path).toUri()));
        roommate2Image.setImage(image);
    }
    @FXML
    public void displayRoommate3(String email) throws FileNotFoundException, URISyntaxException, ParseException{
        String path = jsonReader.getImagePath(email);
        Image image = new Image(String.valueOf(Path.of(path).toUri()));
        roommate3image.setImage(image);
    }


    @FXML
    public void displayName(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        String name = jsonReader.getName(email);
        nameBox.setText(name);
    }
    @FXML
    public void displayRoommates(ArrayList<String> roommates) throws FileNotFoundException, URISyntaxException, ParseException, java.text.ParseException {
        noRoommatesLabel.setVisible(false);
        if (roommates.size() == 3){

            roommate1Button.setText(roommates.get(0));
            roommate2Button.setText(roommates.get(1));
            roommate3Button.setText(roommates.get(2));
            status1.setText(displayStatus(roommates.get(0)));
            status2.setText(displayStatus(roommates.get(1)));
            status3.setText(displayStatus(roommates.get(2)));
            displayRoommate1(roommates.get(0));
            displayRoommate2(roommates.get(1));
            displayRoommate3(roommates.get(2));

        }
        else if(roommates.size() == 2){
            String roommateEmail1 = roommates.get(0);
            roommate1Button.setText(roommateEmail1);

            String roommateEmail2 = roommates.get(1);
            roommate2Button.setText(roommateEmail2);

            status1.setText(displayStatus(jsonReader.getEmail(roommateEmail1)));
            status2.setText(displayStatus(jsonReader.getEmail(roommateEmail2)));

            displayRoommate1(roommates.get(0));
            displayRoommate2(roommates.get(1));

            roommate3Button.setVisible(false);
            status3.setVisible(false);

        }
        else if(roommates.size() == 1){
            String roommateEmail = roommates.get(0);
            jsonReader.getName(roommateEmail);
            roommate1Button.setText(roommateEmail);
            displayRoommate1(roommates.get(0));
            status1.setText(displayStatus(roommates.get(0)));
            roommate2Button.setVisible(false);
            status2.setVisible(false);
            roommate3Button.setVisible(false);
            status3.setVisible(false);

        }else if(roommates.size() == 0){
            noRoommatesLabel.setVisible(true);
            roommate1Button.setVisible(false);
            roommate2Button.setVisible(false);
            roommate3Button.setVisible(false);
            status1.setVisible(false);
            status2.setVisible(false);
            status3.setVisible(false);

        }
        else {
            anchorPaneOfRoommates.setVisible(false);
        }
    }

    @FXML
    public String displayStatus(String email) throws FileNotFoundException, URISyntaxException, ParseException, java.text.ParseException {
        ArrayList<ArrayList<String>> days = jsonReader.getEvent(email);
        ArrayList<String> startTimes = jsonReader.getStartTime(email);
        ArrayList<String> endTimes = jsonReader.getEndTime(email);

        LocalTime localTime = LocalTime.now();
        LocalDate localDate = LocalDate.now();
        DayOfWeek dayOfWeek = DayOfWeek.from(localDate);

        ArrayList<String> monday = days.get(0);
        ArrayList<String> tuesday = days.get(1);
        ArrayList<String> wednesday = days.get(2);
        ArrayList<String> thursday = days.get(3);
        ArrayList<String> friday = days.get(4);

        if (dayOfWeek.name().equals("MONDAY")) {
            for (int i = 0; i < monday.size(); i++) {
                String startTime = startTimes.get(i) + ":00";
                String endTime = endTimes.get(i) + ":00";
                String currentTime = String.valueOf(localTime);
                Date x = makeCalendar(currentTime).getTime();

                if (x.after(makeCalendar(startTime).getTime()) && x.before(makeCalendar(endTime).getTime())) {
                    return("Away");
                }
            }
        }

        if (dayOfWeek.name().equals("TUESDAY")) {
            for (int i = 0; i < tuesday.size(); i++) {
                String startTime = startTimes.get(i) + ":00";
                String endTime = endTimes.get(i) + ":00";
                String currentTime = String.valueOf(localTime);
                Date x = makeCalendar(currentTime).getTime();

                if (x.after(makeCalendar(startTime).getTime()) && x.before(makeCalendar(endTime).getTime())) {
                    return("Away");
                }
            }
        }

        if (dayOfWeek.name().equals("WEDNESDAY")) {
            for (int i = 0; i < wednesday.size(); i++) {
                String startTime = startTimes.get(i) + ":00";
                String endTime = endTimes.get(i) + ":00";
                String currentTime = String.valueOf(localTime);
                Date x = makeCalendar(currentTime).getTime();

                if (x.after(makeCalendar(startTime).getTime()) && x.before(makeCalendar(endTime).getTime())) {
                    return("Away");
                }
            }
        }

        if (dayOfWeek.name().equals("THURSDAY")) {
            for (int i = 0; i < thursday.size(); i++) {
                String startTime = startTimes.get(i) + ":00";
                String endTime = endTimes.get(i) + ":00";
                String currentTime = String.valueOf(localTime);
                Date x = makeCalendar(currentTime).getTime();

                if (x.after(makeCalendar(startTime).getTime()) && x.before(makeCalendar(endTime).getTime())) {
                    return("Away");
                }
            }
        }
        if (dayOfWeek.name().equals("FRIDAY")) {
            for (int i = 0; i < friday.size(); i++) {
                String startTime = startTimes.get(i) + ":00";
                String endTime = endTimes.get(i) + ":00";
                String currentTime = String.valueOf(localTime);
                Date x = makeCalendar(currentTime).getTime();

                if (x.after(makeCalendar(startTime).getTime()) && x.before(makeCalendar(endTime).getTime())) {
                    return("Away");
                }
            }
        }
        return("Home");
    }

    public Calendar makeCalendar(String time) throws java.text.ParseException {
        Date currentDate = new SimpleDateFormat("HH:mm:ss").parse(time);
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(currentDate);
        currentCalendar.add(Calendar.DATE, 1);
        return currentCalendar;
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

    public void goToProfile() throws IOException, URISyntaxException, ParseException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayUserInfoGUI.fxml"));
        Parent root = loader.load();
        DisplayUserInfoGUI displayUser = loader.getController();

        displayUser.displayEmail(userEmail);
        displayUser.displayName(userEmail);
        displayUser.displayPhoneNumber(userEmail);
        displayUser.setDisplaySchedule(userEmail);
        displayUser.displayMainUserImage(userEmail);

        profileButton.getScene().setRoot(root);
    }

    public void openRoommate1() throws IOException, URISyntaxException, ParseException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RoommateDisplayGUI.fxml"));
        Parent root = loader.load();
        RoommateDisplayGUI roommateDisplayGUI = loader.getController();
        roommateDisplayGUI.displayEmail(userEmail);
        roommateDisplayGUI.displayName(userEmail);
        roommateDisplayGUI.displayPhoneNumber(userEmail);
        roommateDisplayGUI.setDisplaySchedule(roommate1Button.getText());
        roommateDisplayGUI.setRoommateDisplay(roommate1Button.getText());
        roommateDisplayGUI.displayMainUserImage(userEmail);
        profileButton.getScene().setRoot(root);


    }

    public void openRoommate2() throws IOException, URISyntaxException, ParseException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RoommateDisplayGUI.fxml"));
        Parent root = loader.load();
        RoommateDisplayGUI roommateDisplayGUI = loader.getController();
        roommateDisplayGUI.displayEmail(userEmail);
        roommateDisplayGUI.displayName(userEmail);
        roommateDisplayGUI.displayPhoneNumber(userEmail);
        roommateDisplayGUI.setDisplaySchedule(roommate2Button.getText());
        roommateDisplayGUI.setRoommateDisplay(roommate2Button.getText());
        roommateDisplayGUI.displayMainUserImage(userEmail);

        profileButton.getScene().setRoot(root);

    }

    public void openRoommate3() throws IOException, URISyntaxException, ParseException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RoommateDisplayGUI.fxml"));
        Parent root = loader.load();
        RoommateDisplayGUI roommateDisplayGUI = loader.getController();

        roommateDisplayGUI.displayEmail(userEmail);
        roommateDisplayGUI.displayName(userEmail);
        roommateDisplayGUI.displayPhoneNumber(userEmail);
        roommateDisplayGUI.setDisplaySchedule(roommate3Button.getText());
        roommateDisplayGUI.setRoommateDisplay(roommate3Button.getText());
        roommateDisplayGUI.displayMainUserImage(userEmail);
        profileButton.getScene().setRoot(root);

    }

    public void openSettings() throws IOException, URISyntaxException, ParseException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SettingsGUI.fxml"));
        Parent root = loader.load();
        SettingsGUI settingsGUI = loader.getController();

        settingsGUI.displayEmail(userEmail);
        settingsGUI.displayName(userEmail);
        settingsGUI.displayPhoneNumber(userEmail);
        settingsGUI.displayMainUserImage(userEmail);

        settingsButton.getScene().setRoot(root);
    }

    public void openCalendar() throws IOException, URISyntaxException, ParseException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayFullCalendarGUI.fxml"));
        Parent root = loader.load();
        DisplayFullCalendarGUI calendarGUI = loader.getController();

        calendarGUI.displayEmail(userEmail);
        calendarGUI.displayName(userEmail);
        calendarGUI.displayPhoneNumber(userEmail);
        calendarGUI.displayAllClasses();
        calendarGUI.displayMainUserImage(userEmail);

        calendarButton.getScene().setRoot(root);
    }
}
