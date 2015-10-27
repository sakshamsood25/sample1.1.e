package com.worksap.stm.sample.dao.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.worksap.stm.sample.dao.spec.SaleDao;
import com.worksap.stm.sample.dto.OfficeDto;
import com.worksap.stm.sample.dto.SaleDto;

@Repository
public class SaleDaoImpl implements SaleDao {


	private static final String FETCH_SALES = "SELECT * FROM SALE LIMIT ? OFFSET ?";

	private static final String SALE_TABLE_SIZE = "SELECT COUNT(*) FROM SALE";

	private static final String FETCH_BY_ID = "SELECT * FROM SALE WHERE id = ?";
	

	@Autowired
	private JdbcTemplate template;

	@Override
	public SaleDto getBy(int saleId) throws IOException {
		try {
			return template.queryForObject(FETCH_BY_ID, (rs, rownum) -> {
				return new SaleDto(rs.getInt("id"), rs.getString("name"));
			}, saleId);
		} catch (DataAccessException e) {
			throw new IOException(e);
		}
	}
	
	@Override
	public List<SaleDto> getAll(int start, int size) throws IOException {
		try {
			return template.query(FETCH_SALES, ps -> {
				ps.setInt(1, size);
				ps.setInt(2, start);
			}, (rs, rownum) -> {
				return new SaleDto(rs.getInt("id"), rs.getString("name"));
			});
		} catch (DataAccessException e) {
			throw new IOException(e);
		}
	}

	
	@Override
	public int getTotalCount() throws IOException {
		return template.queryForObject(SALE_TABLE_SIZE, (rs, rownum) -> {
			return rs.getInt(1);
		});
	}
	


}
