package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainStudentController {

    @FXML
    private Button announcement_button;

    @FXML
    private Button ask_button;

    @FXML
    private Button logout_button;

    @FXML
    private Button profile_button;

    @FXML
    private Button question_button;

    @FXML
    private Button result_button;
    
    @FXML
    private AnchorPane insidePane;
    
    @FXML
    void profile_button_Click(ActionEvent event) {
    	try {
        	AnchorPane pane1= (AnchorPane) FXMLLoader.load(getClass().getResource("ProfilEditStudent.fxml"));
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
    void initialize() {
        
   
    }

}
