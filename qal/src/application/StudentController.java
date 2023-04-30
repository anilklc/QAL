package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class StudentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label info;

    @FXML
    private Label info1;

    @FXML
    private Button studentAdd_button;

    @FXML
    private TextField studentClassDel_text;

    @FXML
    private TextField studentClass_text;

    @FXML
    private ComboBox<?> studentName_cBox;

    @FXML
    private TextField studentName_text;

    @FXML
    private TextField studentPassword_text;

    @FXML
    private ComboBox<?> studentSurname_cBox;

    @FXML
    private TextField studentSurname_text;

    @FXML
    private TextField studentUsername_text;

    @FXML
    private Button teacherDelete_button;

    @FXML
    private Label uyariAdd_label;

    @FXML
    private Label uyaridel_label;

    @FXML
    void studentAdd_button_Click(ActionEvent event) {

    }

    @FXML
    void studentDelete_button_Click(ActionEvent event) {

    }

    @FXML
    void initialize() {


    }

}
