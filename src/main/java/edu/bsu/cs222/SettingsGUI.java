package edu.bsu.cs222;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.minidev.json.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

public class SettingsGUI{


    public Button backButton;
    public Button logOutButton;
    public Button editScheduleButton;
    public Button editUserInfoButton;
    public Button addRoommateButton;
    public Label nameBox;
    public Label phoneNumberBox;
    public Label emailBox;

    public JSONReader jsonReader = new JSONReader();
    public ImageView mainUserImage;

    @FXML
    public void displayMainUserImage(String email) throws FileNotFoundException, URISyntaxException, ParseException{
        String path = jsonReader.getImagePath(email);
        Image image = new Image(String.valueOf(Path.of(path).toUri()));
        mainUserImage.setImage(image);
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
    public void goBackToMainScreen() throws IOException, URISyntaxException, ParseException, java.text.ParseException {
        String email = emailBox.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainScreenGUI.fxml"));
        Parent root = loader.load();
        MainScreenGUI mainScreenGUI = loader.getController();
        mainScreenGUI.displayEmail(email);
        mainScreenGUI.displayName(email);
        mainScreenGUI.displayPhoneNumber(email);
        mainScreenGUI.setEmail(email);
        mainScreenGUI.displayRoommates(jsonReader.getRoommates(emailBox.getText()));
        mainScreenGUI.displayMainUserImage(email);
        backButton.getScene().setRoot(root);

    }

    public void GoToEditUserScreen() throws IOException, URISyntaxException, ParseException {
        String email = emailBox.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditUserInformationGUI.fxml"));
        Parent root = loader.load();
        EditUserInformationGUI editUserInformationGUI = loader.getController();
        editUserInformationGUI.displayEmail(email);
        editUserInformationGUI.displayName(email);
        editUserInformationGUI.displayPhoneNumber(email);
        editUserInformationGUI.displayMainUserImage(email);
        editUserInfoButton.getScene().setRoot(root);
    }

    public void GoToEditScheduleScreen() throws IOException, URISyntaxException, ParseException {
        String email = emailBox.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditUserCalenderGUI.fxml"));
        Parent root = loader.load();
        EditUserCalenderGUI editUserCalenderGUI = loader.getController();
        editUserCalenderGUI.displayEmail(email);
        editUserCalenderGUI.displayName(email);
        editUserCalenderGUI.displayPhoneNumber(email);
        editUserCalenderGUI.displayRemovableEvents(email);
        editUserCalenderGUI.displayMainUserImage(email);

        editScheduleButton.getScene().setRoot(root);
    }

    public void LogOutAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginGUI.fxml"));
        Parent root = loader.load();
        logOutButton.getScene().setRoot(root);
    }

    public void addRoommate() throws IOException, URISyntaxException, ParseException {
        String email = emailBox.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RoommateAddGUI.fxml"));
        Parent root = loader.load();
        RoommateAddGUI roommateAddGUI = loader.getController();
        roommateAddGUI.displayEmail(email);
        roommateAddGUI.displayName(email);
        roommateAddGUI.displayPhoneNumber(email);
        roommateAddGUI.displayRemoveRoommates(email);
        roommateAddGUI.displayMainUserImage(email);

        editScheduleButton.getScene().setRoot(root);


    }
}
