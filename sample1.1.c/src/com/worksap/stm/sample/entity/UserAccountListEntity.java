package com.worksap.stm.sample.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountListEntity {
	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	private List<UserAccountEntity> userAccountEntities;
}
