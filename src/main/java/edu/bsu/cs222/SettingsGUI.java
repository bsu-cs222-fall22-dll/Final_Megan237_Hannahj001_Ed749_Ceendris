package edu.bsu.cs222;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import net.minidev.json.parser.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;

public class SettingsGUI{


    public Button backButton;
    public Button logOutButton;
    public Button editScheduleButton;
    public Button editUserInfoButton;
    public String userEmail;


    public void setEmail(String email){
        userEmail = email;
    }

    @FXML
    public void goBackToMainScreen(ActionEvent actionEvent) throws IOException, URISyntaxException, ParseException {
        MainScreenGUI mainScreenGUI = new MainScreenGUI();
        mainScreenGUI.reOpenMainScreen(userEmail);

    }

    public void GoToEditUserScreen(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditUserInformationGUI.fxml"));
        Parent root = loader.load();
        editUserInfoButton.getScene().setRoot(root);
    }

    public void GoToEditScheduleScreen(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditUserCalenderGUI.fxml"));
        Parent root = loader.load();
        editScheduleButton.getScene().setRoot(root);
    }

    public void LogOutAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginGUI.fxml"));
        Parent root = loader.load();
        logOutButton.getScene().setRoot(root);
    }
}
