package edu.bsu.cs222;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;

public class EditUserCalenderGUI {
    public Button goBackButton;

    public void goBackToSettings(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SettingsGUI.fxml"));
        Parent root = loader.load();
        goBackButton.getScene().setRoot(root);
    }
}
