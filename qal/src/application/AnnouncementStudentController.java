package application;


import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import DB.DBHelper;
import Models.tableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class AnnouncementStudentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<tableView,String> announcementDate_clm;

    @FXML
    private Label announcementDate_label;

    @FXML
    private TextField announcementDate_text;

    @FXML
    private TableColumn<tableView,String> announcementName_clm;

    @FXML
    private Label announcementName_label;

    @FXML
    private TextField announcementName_text;

    @FXML
    private TableColumn<tableView,String> announcementType_clm;

    @FXML
    private TableColumn<tableView,String> announcementUsername_clm;

    @FXML
    private Label announcement_label1;

    @FXML
    private TextArea announcement_textarea;

    @FXML
    private Label info;

    @FXML
    private Label info1;

    @FXML
    private Label name_surname_label;

    @FXML
    private TextField name_surname_text;
    
    @FXML
    private TableView<tableView> tableview;
  

    DBHelper db=new DBHelper();
    PreparedStatement statement;
    ResultSet resultSet;
    String query;
    tableView tbl=null;
    ObservableList<tableView> tblList=FXCollections.observableArrayList();
    
    private void refreshTable() {
  	  try {
  		    db.connectOpen();
  	        query="SELECT * FROM announcement WHERE announcementType=0 OR announcementType=2";
  	        statement=db.connection.prepareStatement(query);
     		resultSet=statement.executeQuery();
     		tblList.clear();
           while(resultSet.next()) {
          	 
          	 tblList.add(new tableView(resultSet.getString("announcementName"),resultSet.getString("announcementUsername"),resultSet.getString("announcementDate"),resultSet.getString("announcementType"),resultSet.getString("announcementText")));
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
      	announcementName_clm.setCellValueFactory(new PropertyValueFactory<>("announcementName"));
      	announcementUsername_clm.setCellValueFactory(new PropertyValueFactory<>("announcementUsername"));
      	announcementDate_clm.setCellValueFactory(new PropertyValueFactory<>("announcementDate"));
      	announcementType_clm.setCellValueFactory(new PropertyValueFactory<>("announcementType"));

      	
      }
      
      void nameSurnameLoad(String username,String query,String columnName,String columnName2) {
    	  
    	    	try {
    	    		db.connectOpen();
    	        	statement=db.connection.prepareStatement(query);
    	        	statement.setString(1,username);
    	        	resultSet=statement.executeQuery();
    	        	while (resultSet.next())
    	            { name_surname_text.setText(resultSet.getString(columnName)+" "+resultSet.getString(columnName2));
    	            }
    	        	db.connectClose();
    	            statement.close();
    	            resultSet.close();	
    	       
    	    		
    	    	}catch (Exception e) {
    	    		System.out.println(e.getMessage());
    			}
    	  
    	  
    	  
      }
      
      @FXML
      void tableview_Click(MouseEvent event) {
      	tbl=tableview.getSelectionModel().getSelectedItem();
      	announcementName_text.setText(tbl.getAnnouncementName());
      	announcementDate_text.setText(tbl.getAnnouncementDate());
      	nameSurnameLoad(tbl.getAnnouncementUsername(),"SELECT adminUsername,adminName,adminSurname FROM announcement INNER JOIN admin ON announcement.announcementUsername = admin.adminUsername WHERE announcementUsername=?","adminName" ,"adminSurname");
      	nameSurnameLoad(tbl.getAnnouncementUsername(),"SELECT teacherUsername,teacherName,teacherSurname FROM announcement INNER JOIN teacher ON announcement.announcementUsername = teacher.teacherUsername WHERE announcementUsername=?","teacherName" ,"teacherSurname");
      	announcement_textarea.setText(tbl.getAnnouncement());
      }

      @FXML
      void announcementRefresh_button_Click(ActionEvent event) {
    	  loadData();
      }
      
    @FXML
    void initialize() {
       loadData();

    }

}
