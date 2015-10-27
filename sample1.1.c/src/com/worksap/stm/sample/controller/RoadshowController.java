package com.worksap.stm.sample.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.worksap.stm.sample.entity.RoadshowEntity;
import com.worksap.stm.sample.entity.RoadshowFetchEntity;
import com.worksap.stm.sample.service.spec.RoadshowService;

@Controller
public class RoadshowController {

	@Autowired
	private RoadshowService roadshowService;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/roadshows", method = RequestMethod.POST)
	@ResponseBody
	public RoadshowEntity getRoadshows(
			@RequestBody RoadshowFetchEntity roadshowFetchEntity) {
		return roadshowService.getBy(roadshowFetchEntity);
	}

	// page
		@PreAuthorize("hasAuthority('ADMIN')")
		@RequestMapping(value = "/roadshowmanagement")
		public String roadshowmanagement() {
			return "roadshow-management";
		}
}
