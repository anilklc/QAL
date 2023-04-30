package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TeacherController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label info;

    @FXML
    private Label info1;

    @FXML
    private Button teacherAdd_button;

    @FXML
    private TextField teacherClass_text;

    @FXML
    private Button teacherDelete_button;

    @FXML
    private ComboBox<String> teacherLessonName_text;

    @FXML
    private ComboBox<String> teacherName_cBox;

    @FXML
    private TextField teacherName_text;

    @FXML
    private TextField teacherPassword_text;

    @FXML
    private ComboBox<String> teacherSurname_cBox;

    @FXML
    private TextField teacherSurname_text;

    @FXML
    private TextField teacherUsername_text;

    @FXML
    private Label uyariAdd_label;

    @FXML
    private Label uyaridel_label;

    @FXML
    void teacherAdd_button_Click(ActionEvent event) {

    }

    @FXML
    void teacherDelete_button_Click(ActionEvent event) {

    }

    @FXML
    void initialize() {
        

    }

}
