package edu.bsu.cs222;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class EditUserInformationGUI {
    public Button goBackButton;
    public Button saveChangesButton;
    public Button changeUserPhotoButton;
    public ImageView userImage;
    public TextField changePasswordBox;
    public TextField ChangeEmailBox;
    public TextField ChangePhoneNumberBox;
    public TextField changeLastNameBox;
    public TextField ChangeFirstNameBox;

    public void goBackToSettings(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SettingsGUI.fxml"));
        Parent root = loader.load();
        goBackButton.getScene().setRoot(root);
    }

    public void changeUserPhoto(ActionEvent actionEvent) {
    }

    public void saveChanges(ActionEvent actionEvent) {
    }
}
