package com.worksap.stm.sample.dao.impl;

public class Notif {
	
	private int notif_id;
	 private String notif_name;
	 private String notif_description; 
	 private String notif_priority;
	 private String notif_status;
	 private String notif_time;
	 public int getNotifId() {
	  return notif_id;
	 }
	 public void setNotifId(int notifId) {
	  this.notif_id = notifId;
	 }
	 public String getNotifName() {
	  return notif_name;
	 }
	 public void setNotifName(String notifName) {
	  this.notif_name = notifName;
	 }
	 public String getNotifDescription() {
	  return notif_description;
	 }
	 public void setNotifDescription(String notifDescription) {
	  this.notif_description = notifDescription;
	 }
	 
	 
	 public String getNotifStatus() {
			return notif_status;
		}
	 
	public void setNotifStatus(String notifStatus) {
			this.notif_status = notifStatus;
		}
	public String getNotif_time() {
		return notif_time;
	}
	public void setNotif_time(String notif_time) {
		this.notif_time = notif_time;
	}
	  
	 @Override
	 public String toString() {
	  return "Notif [notif_id=" + notif_id + ", notif_name=" + notif_name
	    + ", notif_description=" + notif_description + ", notif_priority="
	    + notif_priority +",notif_status="+notif_status+ "]";
	 }


}
