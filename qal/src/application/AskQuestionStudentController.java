package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AskQuestionStudentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button answerAdd_button;

    @FXML
    private TableColumn<?, ?> answerDate_clm;

    @FXML
    private Button answerRefresh_button;

    @FXML
    private Label answer_label;

    @FXML
    private TextArea answer_textarea;

    @FXML
    private Label info;

    @FXML
    private TableColumn<?, ?> lessonName_clm;

    @FXML
    private ComboBox<?> lesson_cBox;

    @FXML
    private Label lesson_label;

    @FXML
    private Label password_label;

    @FXML
    private PasswordField password_text;

    @FXML
    private TableColumn<?, ?> questionDate_clm;

    @FXML
    private TableColumn<?, ?> questionName_clm;

    @FXML
    private Label question_label;

    @FXML
    private TextArea question_textarea;

    @FXML
    private TableView<?> tableview;

    @FXML
    private TableColumn<?, ?> teacherName_clm;

    @FXML
    private TextField usernameDel_text;

    @FXML
    private Label username_label;
    
    @FXML
    private Label uyari_label;

    @FXML
    void answerAdd_button_Click(ActionEvent event) {

    }

    @FXML
    void answerRefresh_button_Click(ActionEvent event) {

    }

    @FXML
    void tableview_Click(MouseEvent event) {

    }

    @FXML
    void initialize() {
        

    }

}
