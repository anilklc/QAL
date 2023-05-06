package Models;

public class tableView {
  String announcementName;
  String announcementUsername;
  String announcementDate;
  String announcementType;
  String announcementText;
  public tableView(String announcementName,String announcementUsername,String announcementDate,String announcementType,String announcementText) {
	  this.announcementName=announcementName;
	  this.announcementUsername=announcementUsername;
	  this.announcementDate=announcementDate;
	  this.announcementType=announcementType; 
	  this.announcementText=announcementText; 
}
public String getAnnouncement() {
	return announcementText;
}
public void setAnnouncement(String announcementText) {
	this.announcementText = announcementText;
}
public String getAnnouncementName() {
	return announcementName;
}
public void setAnnouncementName(String announcementName) {
	this.announcementName = announcementName;
}
public String getAnnouncementUsername() {
	return announcementUsername;
}
public void setAnnouncementUsername(String announcementUsername) {
	this.announcementUsername = announcementUsername;
}
public String getAnnouncementDate() {
	return announcementDate;
}
public void setAnnouncementDate(String announcementDate) {
	this.announcementDate = announcementDate;
}
public String getAnnouncementType() {
	return announcementType;
}
public void setAnnouncementType(String announcementType) {
	this.announcementType = announcementType;
}
  

  
  
}
