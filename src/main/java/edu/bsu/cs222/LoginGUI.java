package edu.bsu.cs222;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.minidev.json.parser.ParseException;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.EventObject;
import java.util.Objects;

public class LoginGUI{
    public TextField emailInput;
    public Button enterButton;

    private Parent root;


    @FXML
    public void loginUser() throws IOException, URISyntaxException, ParseException {
        String email = emailInput.getText();
        email = email + "@bsu.edu";
        JSONReader reader = new JSONReader();
        String nameFromJSONDoc = reader.getEmail("ceendris@bsu.edu");
        if (email.equals(nameFromJSONDoc)) {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/DisplayUserInfoGUI.fxml"));
            primaryStage.setTitle("Profile");
            primaryStage.setScene(new Scene(root, 450, 700));
            primaryStage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("That user does not exist.");
            alert.setContentText("Please enter a different username.");
            alert.showAndWait();
        }
//        try{
//            switchToUserDisplay();
//            String email = emailInput.getText();
//            ((Node)event.getSource()).getScene().getWindow().hide();
//            loadUserDisplay("/DisplayUserInfoGUI.fxml");

//            System.out.println("HELLO");
//            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/DisplayUserInfoGUI.fxml")));
//            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();

//            Parent root;
//            Stage stage = (Stage) enterButton.getScene().getWindow();
//            stage.close();
//            FXMLLoader createUser = new FXMLLoader(getClass().getClassLoader().getResource("/DisplayUserInfoGUI.fxml"));
//            root = createUser.load();
//            DisplayUserInfoGUI displayUserInfoGUI = createUser.getController();
//            Stage secondStage = new Stage();
//            secondStage.setTitle("Login Page");
//            secondStage.setScene(new Scene(root));
//            secondStage.show();

//            FXMLLoader createUser = new FXMLLoader(getClass().getClassLoader().getResource("/DisplayUserInfoGUI.fxml"));
//            Parent root = (Parent) createUser.load();
//            Stage stage = new Stage();
//            stage.initStyle(StageStyle.DECORATED);
//            stage.setTitle("Login Page");
//            stage.setScene(new Scene(root));
//            stage.show();

//            Parent root = FXMLLoader.load(getClass().getResource("/DisplayUserInfoGUI.fxml"));
//            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//
//        }catch (IOException exception) {
//               exception.printStackTrace();
//            }
        }

 @FXML
 public void loadUserDisplay(String s) throws IOException {
     FXMLLoader createUser = new FXMLLoader(getClass().getClassLoader().getResource(s));
     Parent root = (Parent) createUser.load();
     Stage stage = new Stage();
     stage.initStyle(StageStyle.DECORATED);
     stage.setTitle("Login Page");
     stage.setScene(new Scene(root));
     stage.show();
 }

 @FXML
    public void switchToUserDisplay(ActionEvent event) throws IOException {
        try {
            String email = emailInput.getText();
            if (email == "ceendris") {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/DisplayUserInfoGUI.fxml")));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

 }

}
