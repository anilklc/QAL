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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ResultTeacherController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<tableViewResult, String> correctQuestion_clm;

    @FXML
    private TableColumn<tableViewResult, String> examName_clm;

    @FXML
    private ComboBox<String> find_cBox;

    @FXML
    private Label find_label;

    @FXML
    private Label info;

    @FXML
    private Label info1;

    @FXML
    private ComboBox<String> examName_cBox;

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
    private DatePicker start_date;

    @FXML
    private TableColumn<tableViewResult, String> studentName_clm;

    @FXML
    private TableView<tableViewResult> tableview;

    @FXML
    private Label username_label;

    @FXML
    private TextField username_text;

    @FXML
    private TextField userSearch_text;

    @FXML
    private Label uyari_label;

    @FXML
    private TableColumn<tableViewResult, String> wrongQuestion_clm;
    
    @FXML
    private TableColumn<tableViewResult, String> lessonName_clm;
    

    DBHelper db=new DBHelper();
    PreparedStatement statement;
    ResultSet resultSet;
    String query;
    

       
       String teacherLesson,teacherID;
       void userInfo() {
         	
         	try {	
         		db.connectOpen();
         		query="SELECT * FROM teacher WHERE teacherUsername=? and teacherPassword=?";
             	statement=db.connection.prepareStatement(query);
            	statement.setString(1, username_text.getText().trim());
            	statement.setString(2, password_text.getText().trim());
            	resultSet=statement.executeQuery();
            	resultSet.next();
            	
            	if (username_text.getText().equals(resultSet.getString("teacherUsername")) && password_text.getText().equals(resultSet.getString("teacherPassword")))
                {  teacherID=resultSet.getString("teacherID");
                   teacherLesson=resultSet.getString("teacherLessonID");
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
         	studentName_clm.setCellValueFactory(new PropertyValueFactory<>("studentName"));
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
        	query="SELECT examName FROM result INNER JOIN exam ON result.examID=exam.examID INNER JOIN teacher ON teacher.teacherID=exam.teacherID INNER JOIN student ON student.studentID=result.studentID INNER JOIN lesson ON lesson.lessonID=exam.lessonID WHERE teacher.teacherID="+teacherID;
        	statement=db.connection.prepareStatement(query);
        	resultSet=statement.executeQuery();
        	examName_cBox.getItems().clear();
        	
        	while (resultSet.next())
            { cmb.getItems().addAll(resultSet.getString("examName")); 
            }
        	db.connectClose();
            statement.close();
            resultSet.close();	
       
    		
    	}catch (Exception e) {
    		System.out.println(e.getMessage());
		} 
    	
    }
    
       @FXML
       void find_cBox_Click(MouseEvent event) {
    	   if(find_cBox.getSelectionModel().getSelectedItem()!=null) {
       		if(find_cBox.getSelectionModel().getSelectedIndex()==0) {
       			start_date.setVisible(true);
       			examName_cBox.setVisible(false);
       			userSearch_text.setVisible(false);
           	}
       		else if(find_cBox.getSelectionModel().getSelectedIndex()==1) {
       			userSearch_text.setVisible(true);
       			start_date.setVisible(false);
       			examName_cBox.setVisible(false);
               	}
       		else if(find_cBox.getSelectionModel().getSelectedIndex()==2) {
       			examName_cBox.setVisible(true);
       			userSearch_text.setVisible(false);
       			start_date.setVisible(false);
               	}
           	}
           	
           
       }

    @FXML
    void resultFind_button_Click(ActionEvent event) {
    	if(find_cBox.getSelectionModel().getSelectedItem()!=null) {
    		if(find_cBox.getSelectionModel().getSelectedIndex()==0) {
    			
        	query="SELECT studentName,studentSurname,teacherName,examName,lessonName,correctQuestion,wrongQuestion,point,loginDate FROM result INNER JOIN exam ON result.examID=exam.examID INNER JOIN teacher ON teacher.teacherID=exam.teacherID INNER JOIN student ON student.studentID=result.studentID INNER JOIN lesson ON lesson.lessonID=exam.lessonID WHERE loginDate='"+start_date.getValue().toString()+"'";
        	loadData();}
    		else if(find_cBox.getSelectionModel().getSelectedIndex()==1) {
            	query="SELECT studentName,studentSurname,teacherName,examName,lessonName,correctQuestion,wrongQuestion,point,loginDate FROM result INNER JOIN exam ON result.examID=exam.examID INNER JOIN teacher ON teacher.teacherID=exam.teacherID INNER JOIN student ON student.studentID=result.studentID INNER JOIN lesson ON lesson.lessonID=exam.lessonID WHERE studentName='"+userSearch_text.getText().trim()+"'";
            	loadData();}
    		else if(find_cBox.getSelectionModel().getSelectedIndex()==2) {
            	query="SELECT studentName,studentSurname,teacherName,examName,lessonName,correctQuestion,wrongQuestion,point,loginDate FROM result INNER JOIN exam ON result.examID=exam.examID INNER JOIN teacher ON teacher.teacherID=exam.teacherID INNER JOIN student ON student.studentID=result.studentID INNER JOIN lesson ON lesson.lessonID=exam.lessonID WHERE examName='"+examName_cBox.getSelectionModel().getSelectedItem().toString()+"'";
            	loadData();}
        	}
        	
        	else {
        		 uyari_label.setVisible(true);
        	}
    }

    @FXML
    void resultGet_button_Click(ActionEvent event) {
    	if(username_text.getText().trim()!="" & password_text.getText().trim()!="") {
    		userInfo();
    	
    	query="SELECT studentName,studentSurname,teacherName,examName,lessonName,correctQuestion,wrongQuestion,point,loginDate FROM result INNER JOIN exam ON result.examID=exam.examID INNER JOIN teacher ON teacher.teacherID=exam.teacherID INNER JOIN student ON student.studentID=result.studentID INNER JOIN lesson ON lesson.lessonID=exam.lessonID WHERE exam.teacherID="+teacherID;
    	loadData();
    	find_cBox.setDisable(false);
    	find_cBox.getItems().clear();
    	find_cBox.getItems().add("Sınav Giriş Tarihi");
    	find_cBox.getItems().add("Öğrenci Adı");
    	find_cBox.getItems().add("Sınav Adı");
    	comboBoxLoad(examName_cBox);
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
