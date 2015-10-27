package com.worksap.stm.sample.dao.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.worksap.stm.sample.dao.spec.RoadshowDao;
import com.worksap.stm.sample.dto.OfficeDto;
import com.worksap.stm.sample.dto.RoadshowDto;

@Repository
public class RoadshowDaoImpl implements RoadshowDao {


	private static final String FETCH_ROADSHOWS = "SELECT * FROM ROADSHOW LIMIT ? OFFSET ?";

	private static final String ROADSHOW_TABLE_SIZE = "SELECT COUNT(*) FROM ROADSHOW";

	private static final String FETCH_BY_ID = "SELECT * FROM ROADSHOW WHERE id = ?";
	

	@Autowired
	private JdbcTemplate template;

	@Override
	public RoadshowDto getBy(int roadshowId) throws IOException {
		try {
			return template.queryForObject(FETCH_BY_ID, (rs, rownum) -> {
				return new RoadshowDto(rs.getInt("id"), rs.getString("name"));
			}, roadshowId);
		} catch (DataAccessException e) {
			throw new IOException(e);
		}
	}
	
	@Override
	public List<RoadshowDto> getAll(int start, int size) throws IOException {
		try {
			return template.query(FETCH_ROADSHOWS, ps -> {
				ps.setInt(1, size);
				ps.setInt(2, start);
			}, (rs, rownum) -> {
				return new RoadshowDto(rs.getInt("id"), rs.getString("name"));
			});
		} catch (DataAccessException e) {
			throw new IOException(e);
		}
	}

	
	@Override
	public int getTotalCount() throws IOException {
		return template.queryForObject(ROADSHOW_TABLE_SIZE, (rs, rownum) -> {
			return rs.getInt(1);
		});
	}
	


}
