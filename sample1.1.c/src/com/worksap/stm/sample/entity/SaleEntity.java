package com.worksap.stm.sample.entity;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.worksap.stm.sample.dto.SaleDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleEntity {
	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	private List<SaleDto> sales;
}
