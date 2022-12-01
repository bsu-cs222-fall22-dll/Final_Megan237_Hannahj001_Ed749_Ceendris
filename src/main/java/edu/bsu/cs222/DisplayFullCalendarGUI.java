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
