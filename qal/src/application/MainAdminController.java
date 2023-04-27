package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button announcement_add_button;

    @FXML
    private Button announcement_button;

    @FXML
    private Button lesson_button;

    @FXML
    private Button logout_button;

    @FXML
    private Button profile_button;

    @FXML
    private Button result_button;

    @FXML
    private Button student_button;

    @FXML
    private Button teacher_button;
    
    @FXML
    private AnchorPane insidePane;
 
    @FXML
    void profil_button_Click(ActionEvent event) {
    	try {
        	AnchorPane pane1= (AnchorPane) FXMLLoader.load(getClass().getResource("ProfilEditAdmin.fxml"));
        	insidePane.getChildren().setAll(pane1);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    @FXML
    void close_button_Click(ActionEvent event) {
    	 Platform.exit();
    }

    @FXML
    void initialize() {
    	 

    }

}
