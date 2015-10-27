package com.worksap.stm.sample.service.spec;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.worksap.stm.sample.dao.impl.Notif;
import com.worksap.stm.sample.utils.DBUtility;
public class NotifManagerService {
 
 private Connection connection;

 public NotifManagerService() {
  connection = DBUtility.getConnection();
 }

 public void addNotif(Notif notif) {
  try {
   PreparedStatement preparedStatement = connection
     .prepareStatement("insert into notif_list(notif_name,notif_description,notif_status,notif_archived,notif_time) values (?, ?, ?,?,?,?,?)");
   System.out.println("Notif:"+notif.getNotifName());
   preparedStatement.setString(1, notif.getNotifName());
   preparedStatement.setString(2, notif.getNotifDescription());   
   preparedStatement.setString(3, notif.getNotifStatus());
   preparedStatement.setInt(4,0);
   Date dt = new Date();

   SimpleDateFormat sdf = 
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

   String currentTime = sdf.format(dt);
   preparedStatement.setString(5,currentTime);
   preparedStatement.executeUpdate();

  } catch (SQLException e) {
   e.printStackTrace();
  }
 }
 
 public void archiveNotif(int notifId) {
  try {
   PreparedStatement preparedStatement = connection
     .prepareStatement("update notif_list set notif_archived=true where notif_id=?");
   // Parameters start with 1
   preparedStatement.setInt(1, notifId);
   preparedStatement.executeUpdate();

  } catch (SQLException e) {
   e.printStackTrace();
  }
 }
 
 public void updateNotif(Notif notif) throws ParseException {
  try {
   PreparedStatement preparedStatement = connection
     .prepareStatement("update notif_list set notif_name=?, notif_description=?, notif_status=?" +
       "where notif_id=?");
   preparedStatement.setString(1, notif.getNotifName());
   preparedStatement.setString(2, notif.getNotifDescription());
 
   preparedStatement.setString(3, notif.getNotifStatus());
   preparedStatement.setInt(4, notif.getNotifId());
   Date dt = new Date();

   SimpleDateFormat sdf = 
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

   String currentTime = sdf.format(dt);
   preparedStatement.setString(5,currentTime);
   preparedStatement.executeUpdate();

  } catch (SQLException e) {
   e.printStackTrace();
  }
 }
 
 public void changeNotifStatus(int notifId,String status) throws ParseException {
   try {
    PreparedStatement preparedStatement = connection
      .prepareStatement("update notif_list set notif_status=? where notif_id=?");
    preparedStatement.setString(1,status);
    preparedStatement.setInt(2, notifId);
    preparedStatement.executeUpdate();

   } catch (SQLException e) {
    e.printStackTrace();
   }
  }


 public List<Notif> getAllNotifs() {
  List<Notif> notifs = new ArrayList<Notif>();
  try {
   Statement statement = connection.createStatement();
   ResultSet rs = statement.executeQuery("select * from notif_list where notif_archived=0");
   while (rs.next()) {
    Notif notif = new Notif();
    notif.setNotifId(rs.getInt("notif_id"));
    notif.setNotifName(rs.getString("notif_name"));
    notif.setNotifDescription(rs.getString("notif_description"));    
    notif.setNotifStatus(rs.getString("notif_status"));
    notif.setNotif_time(rs.getString("notif_time"));
    notifs.add(notif);
   }
  } catch (SQLException e) {
   e.printStackTrace();
  }

  return notifs;
 }
 
 public Notif getNotifById(int notifId) {
  Notif notif = new Notif();
  try {
   PreparedStatement preparedStatement = connection.
     prepareStatement("select * from notif_list where notif_id=?");
   preparedStatement.setInt(1, notifId);
   ResultSet rs = preparedStatement.executeQuery();
   
   if (rs.next()) {
     notif.setNotifId(rs.getInt("notif_id"));
     notif.setNotifName(rs.getString("notif_name"));
     notif.setNotifDescription(rs.getString("notif_description"));    
     
     notif.setNotifStatus(rs.getString("notif_status"));
     notif.setNotif_time(rs.getString("notif_time"));
   }
  } catch (SQLException e) {
   e.printStackTrace();
  }

  return notif;
 }
}