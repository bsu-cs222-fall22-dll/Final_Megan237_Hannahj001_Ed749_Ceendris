package edu.bsu.cs222;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.minidev.json.parser.ParseException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

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
    public JSONReader jsonReader = new JSONReader();
    public Label nameBox;
    public Label emailBox;
    public Label phoneNumberBox;
    public File filePath;
    public Image photo;
    public JSONWriter writer = new JSONWriter();

    public void goBackToSettings(ActionEvent actionEvent) throws IOException, URISyntaxException, ParseException {
        String email = emailBox.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SettingsGUI.fxml"));
        Parent root = loader.load();
        SettingsGUI settingsGUI = loader.getController();
        settingsGUI.displayEmail(email);
        settingsGUI.displayName(email);
        settingsGUI.displayPhoneNumber(email);
        goBackButton.getScene().setRoot(root);
    }

    public void saveChanges(ActionEvent actionEvent) throws IOException, URISyntaxException, ParseException {

        JSONReader jsonReader = new JSONReader();
        String email = emailBox.getText();
        String file = "src/main/resources/" + email.replace(".","") + ".json";
        String json = new String(Files.readAllBytes(Paths.get(file)));

        String oldPassword = jsonReader.getPassword(email);
        String newPassword = changePasswordBox.getText();
        String oldPhoneNumber = jsonReader.getPhoneNumber(email);
        String newPhoneNumber = ChangePhoneNumberBox.getText();
        String oldFirstName = jsonReader.getName(email);
        String newFirstName = ChangeFirstNameBox.getText();
        String oldEmail = jsonReader.getEmail(email);
        String newEmail = ChangeEmailBox.getText();
        if (!Objects.equals(oldPassword, newPassword) || !Objects.equals(oldPhoneNumber, newPhoneNumber) || !Objects.equals(oldFirstName, newFirstName) || !Objects.equals(oldEmail, newEmail)) {
            if (newPassword.equals("")) {
                newPassword = oldPassword;
            }
            if (newPhoneNumber.equals("")) {
                newPhoneNumber = oldPhoneNumber;
            }
            if (newFirstName.equals("")) {
                newFirstName = oldFirstName;
            }
            if (newEmail.equals("")) {
                newEmail = oldEmail;
            }
            Path path = Paths.get(file);
            Files.writeString(path, json.replace(oldPassword, newPassword).replace(oldPhoneNumber, newPhoneNumber).replace(oldFirstName, newFirstName).replace(oldEmail, newEmail));
        }

        goBackToSettings(actionEvent);

    }



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

    public void changeUserPhoto(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        this.filePath = fileChooser.showOpenDialog(stage);

        try{
            BufferedImage bufferedImage = ImageIO.read(filePath);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            userImage.setImage(image);
            this.photo = userImage.getImage();

            System.out.println(filePath.getName());
            writer.writeToFileImage(filePath.getName(),filePath);





        } catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

}
