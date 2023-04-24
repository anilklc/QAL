package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
    void close_button_Click(ActionEvent event) {
    	Platform.exit();
    }

}
