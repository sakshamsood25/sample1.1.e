package com.worksap.stm.sample.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.worksap.stm.sample.service.spec.NotifManagerService;
import com.worksap.stm.sample.dao.impl.Notif;



	
@Controller
public class NotifManagerController {
	
	NotifManagerService notifmanagerservice=new NotifManagerService();
	
	
	// page
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/addnotifs")
	public String officemanagement() {
		return "notif";
	 

	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/addnotifs/notifs",method = RequestMethod.GET,headers="Accept=application/json")
	@ResponseBody
	public List<Notif> getAllNotifs() {	 
	  List<Notif> notifs=notifmanagerservice.getAllNotifs();
	  return notifs;
	
	 }
	 
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseBody
	 @RequestMapping(value="addnotifs/notifs/archive/{notifIds}",method = RequestMethod.POST,headers="Accept=application/json")
	 public List<Notif> archiveAllNotifs(@PathVariable int[] notifIds) {	
		 for(int i=0;i<notifIds.length;i++){
			 notifmanagerservice.archiveNotif(notifIds[i]);	
			 
		 }
	  List<Notif> notifs=notifmanagerservice.getAllNotifs();
	  return notifs;
	
	 }
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseBody
	 @RequestMapping(value="addnotifs/notifs/{notifId}/{notifStatus}",method = RequestMethod.POST,headers="Accept=application/json")
	 public List<Notif> changeNotifStatus(@PathVariable int notifId,@PathVariable String notifStatus) throws ParseException {	
		 notifmanagerservice.changeNotifStatus(notifId,notifStatus);		 
		 return notifmanagerservice.getAllNotifs();
		 
	 }
	
}