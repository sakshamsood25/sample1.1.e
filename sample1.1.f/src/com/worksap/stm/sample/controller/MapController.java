package com.worksap.stm.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MapController {


	// page
		@PreAuthorize("hasAuthority('ADMIN')")
		@RequestMapping(value = "/map")
		public String quickstart() {
			return "map";
		}
}
