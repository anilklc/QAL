package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


public class MainTeacherController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button announcement_add_button;

    @FXML
    private Button announcement_button;

    @FXML
    private Button ask_button;

    @FXML
    private Button logout_button;

    @FXML
    private Button profile_button;

    @FXML
    private Button question_add_button;

    @FXML
    private Button result_button;
    
    @FXML
    private AnchorPane insidePane;
    
    
    @FXML
    void question_add_button_Click(ActionEvent event) {
    	try {
        	AnchorPane pane1= (AnchorPane) FXMLLoader.load(getClass().getResource("QuestionAdd.fxml"));
        	insidePane.getChildren().setAll(pane1);
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
    }
    
    @FXML
    void result_button_Click(ActionEvent event) {
    	try {
        	AnchorPane pane1= (AnchorPane) FXMLLoader.load(getClass().getResource("ResultTeacher.fxml"));
        	insidePane.getChildren().setAll(pane1);
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
    }
    
    @FXML
    void ask_button_Click(ActionEvent event) {
    	try {
        	AnchorPane pane1= (AnchorPane) FXMLLoader.load(getClass().getResource("AskQuestionTeacher.fxml"));
        	insidePane.getChildren().setAll(pane1);
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
    }
    
    
    @FXML
    void announcement_button_Click(ActionEvent event) {
    	try {
        	AnchorPane pane1= (AnchorPane) FXMLLoader.load(getClass().getResource("AnnouncementTeacher.fxml"));
        	insidePane.getChildren().setAll(pane1);
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
    }
    
  @FXML
    void announcement_add_button_Click(ActionEvent event) {
    	try {
        	AnchorPane pane1= (AnchorPane) FXMLLoader.load(getClass().getResource("AnnouncementEditTeacher.fxml"));
        	insidePane.getChildren().setAll(pane1);
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
    }

    @FXML
    void close_button_Click(ActionEvent event) {
    	Platform.exit();
    }
    
    
    @FXML
    void profile_button_Click(ActionEvent event){
    	try {
        	AnchorPane pane1= (AnchorPane) FXMLLoader.load(getClass().getResource("ProfilEditTeacher.fxml"));
        	insidePane.getChildren().setAll(pane1);
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
    	
    	
    }

    @FXML
    void initialize() {
        

    }

}
