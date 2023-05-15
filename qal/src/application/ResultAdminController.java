package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ResultAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> correctQuestion_clm;

    @FXML
    private TableColumn<?, ?> examName_clm;

    @FXML
    private ComboBox<?> find_cBox;

    @FXML
    private Label find_label;

    @FXML
    private Label info;

    @FXML
    private Label info1;

    @FXML
    private ComboBox<?> lesson_cBox;

    @FXML
    private TableColumn<?, ?> loginDate_clm;

    @FXML
    private Label password_label;

    @FXML
    private PasswordField password_text;

    @FXML
    private TableColumn<?, ?> point_clm;

    @FXML
    private AnchorPane questionPane;

    @FXML
    private Button resultFind_button;

    @FXML
    private Button resultGet_button;

    @FXML
    private DatePicker start_date;

    @FXML
    private TableColumn<?, ?> studentName_clm;

    @FXML
    private TableView<?> tableview;

    @FXML
    private Label username_label;

    @FXML
    private TextField username_text;

    @FXML
    private TextField username_text1;

    @FXML
    private Label uyari_label;

    @FXML
    private TableColumn<?, ?> wrongQuestion_clm;

    @FXML
    void resultFind_button_Click(ActionEvent event) {

    }

    @FXML
    void resultGet_button_Click(ActionEvent event) {

    }

    @FXML
    void initialize() {
        

    }

}
