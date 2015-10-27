package com.worksap.stm.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.worksap.stm.sample.entity.SaleEntity;
import com.worksap.stm.sample.entity.SaleFetchEntity;
import com.worksap.stm.sample.service.spec.SaleService;

@Controller
public class SaleController {

	@Autowired
	private SaleService saleService;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/sales", method = RequestMethod.POST)
	@ResponseBody
	public SaleEntity getSales(
			@RequestBody SaleFetchEntity saleFetchEntity) {
		return saleService.getBy(saleFetchEntity);
	}

	// page
		@PreAuthorize("hasAuthority('ADMIN')")
		@RequestMapping(value = "/salemanagement")
		public String salemanagement() {
			return "sale-management";
		}
}