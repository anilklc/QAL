package application;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import DB.DBHelper;
import Models.tableViewResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ResultStudentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<tableViewResult, String> correctQuestion_clm;

    @FXML
    private TableColumn<tableViewResult, String> examName_clm;

    @FXML
    private Label info;

    @FXML
    private Label info1;

    @FXML
    private TableColumn<tableViewResult, String> lessonName_clm;

    @FXML
    private ComboBox<String> lesson_cBox;

    @FXML
    private Label lesson_label;

    @FXML
    private TableColumn<tableViewResult, String> loginDate_clm;

    @FXML
    private Label password_label;

    @FXML
    private PasswordField password_text;

    @FXML
    private TableColumn<tableViewResult, String> point_clm;

    @FXML
    private AnchorPane questionPane;

    @FXML
    private Button resultFind_button;

    @FXML
    private Button resultGet_button;

    @FXML
    private TableView<tableViewResult> tableview;

    @FXML
    private Label username_label;

    @FXML
    private TextField username_text;

    @FXML
    private Label uyari_label;

    @FXML
    private TableColumn<tableViewResult, String> wrongQuestion_clm;
    
    
    DBHelper db=new DBHelper();
    PreparedStatement statement;
    ResultSet resultSet;
    String query;
    

       
       String studentClass,studentID;
       void userInfo() {
         	
         	try {	
         		db.connectOpen();
         		query="SELECT * FROM student where studentUsername=? and studentPassword=?";
             	statement=db.connection.prepareStatement(query);
            	statement.setString(1, username_text.getText().trim());
            	statement.setString(2, password_text.getText().trim());
            	resultSet=statement.executeQuery();
            	resultSet.next();
            	
            	if (username_text.getText().equals(resultSet.getString("studentUsername")) && password_text.getText().equals(resultSet.getString("studentPassword")))
                {  studentID=resultSet.getString("studentID");
                   studentClass=resultSet.getString("studentClass");
                }
            	db.connectClose();
                statement.close();
                resultSet.close();
         	}catch (Exception e) {
         		System.out.println(e.getMessage());
     		} 
         	
         }

       tableViewResult tbl=null;
       ObservableList<tableViewResult> tblList=FXCollections.observableArrayList();
       
       private void refreshTable() {
     	  try {
     		    db.connectOpen();
     	        statement=db.connection.prepareStatement(query);
        		resultSet=statement.executeQuery();
        		tblList.clear();
              while(resultSet.next()) {
             	 
             	 tblList.add(new tableViewResult(resultSet.getString("studentName"),resultSet.getString("teacherName"),resultSet.getString("examName"),resultSet.getString("lessonName"),resultSet.getString("correctQuestion"),resultSet.getString("wrongQuestion"),resultSet.getString("point"),resultSet.getString("loginDate")));
             	 tableview.setItems(tblList);
              }
              db.connectClose();
              statement.close();
              resultSet.close();	
        		
        		
        	}catch (SQLException e) {
     			System.out.println(e.getMessage());
     		}

         }
        
         private void loadData() {
         	refreshTable();
         	examName_clm.setCellValueFactory(new PropertyValueFactory<>("examName"));
         	lessonName_clm.setCellValueFactory(new PropertyValueFactory<>("lessonName"));
         	correctQuestion_clm.setCellValueFactory(new PropertyValueFactory<>("correctQuestion"));
         	wrongQuestion_clm.setCellValueFactory(new PropertyValueFactory<>("wrongQuestion"));
         	point_clm.setCellValueFactory(new PropertyValueFactory<>("point"));
         	loginDate_clm.setCellValueFactory(new PropertyValueFactory<>("loginDate"));

         	
         }
         
         void comboBoxLoad(ComboBox<String> cmb) {
         	
         	try {	
         		db.connectOpen();
             	query="SELECT DISTINCT(lessonName) FROM result INNER JOIN student ON result.studentID=student.studentID INNER JOIN exam ON result.examID=exam.examID INNER JOIN teacher ON exam.teacherID=teacher.teacherID INNER JOIN lesson ON lesson.lessonID=teacher.teacherLessonID WHERE result.studentID="+studentID;
             	statement=db.connection.prepareStatement(query);
             	resultSet=statement.executeQuery();
             	lesson_cBox.getItems().clear();
             	
             	while (resultSet.next())
                 { cmb.getItems().addAll(resultSet.getString("lessonName")); 
                 }
             	db.connectClose();
                 statement.close();
                 resultSet.close();	
            
         		
         	}catch (Exception e) {
         		System.out.println(e.getMessage());
     		} 
         	
         }
    
    @FXML
    void resultFind_button_Click(ActionEvent event) {
    	if(lesson_cBox.getSelectionModel().getSelectedItem()!=null) {
    	query="SELECT studentName,studentSurname,teacherName,teacherSurname,examName,lessonName,correctQuestion,wrongQuestion,point,loginDate FROM result INNER JOIN student ON result.studentID=student.studentID INNER JOIN exam ON result.examID=exam.examID INNER JOIN teacher ON exam.teacherID=teacher.teacherID INNER JOIN lesson ON lesson.lessonID=teacher.teacherLessonID WHERE lesson.lessonName='"+lesson_cBox.getSelectionModel().getSelectedItem()+"'";
    	loadData();
    	}
    	
    	else {
    		 uyari_label.setVisible(true);
    	}
    }

    @FXML
    void resultGet_button_Click(ActionEvent event) {
    	
    	if(username_text.getText().trim()!="" & password_text.getText().trim()!="") {userInfo();
    	query="SELECT studentName,studentSurname,teacherName,teacherSurname,examName,lessonName,correctQuestion,wrongQuestion,point,loginDate FROM result INNER JOIN student ON result.studentID=student.studentID INNER JOIN exam ON result.examID=exam.examID INNER JOIN teacher ON exam.teacherID=teacher.teacherID INNER JOIN lesson ON lesson.lessonID=teacher.teacherLessonID WHERE result.studentID="+studentID;
    	loadData();
    	comboBoxLoad(lesson_cBox);
    	resultFind_button.setDisable(false);
    	}
    	
    	else {
   		 uyari_label.setVisible(true);
   	}
    }

    @FXML
    void initialize() {

    }

}
