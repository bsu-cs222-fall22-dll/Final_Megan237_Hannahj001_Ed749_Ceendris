import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RoommateAddGUI {
    public Label nameBox;
    public Label phoneNumberBox;
    public Label emailBox;
    public TextField roommatesEmailBox;
    public Button addRoommateButton;
    public Button goBackButton;


    @FXML
    public void addRoommate(ActionEvent actionEvent) {
        //WRITE TO JSON FILE
    }

    public void goBackToSettingsPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SettingsGUI.fxml"));
        Parent root = loader.load();
        goBackButton.getScene().setRoot(root);
    }
}
