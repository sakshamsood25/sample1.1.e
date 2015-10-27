package com.worksap.stm.sample.service.impl;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worksap.stm.sample.dao.spec.RoadshowDao;
import com.worksap.stm.sample.dto.RoadshowDto;
import com.worksap.stm.sample.entity.RoadshowEntity;
import com.worksap.stm.sample.entity.RoadshowFetchEntity;
import com.worksap.stm.sample.exception.ServiceException;
import com.worksap.stm.sample.service.spec.RoadshowService;

@Service
public class RoadshowServiceImpl implements RoadshowService {

	@Autowired
	private RoadshowDao roadshowDao;

	@Override
	public String getBy(int roadshowId) throws ServiceException {
		RoadshowDto roadshow = null;
		try {
			roadshow = roadshowDao.getBy(roadshowId);
		} catch (IOException e) {
			throw new ServiceException("Cannot find roadshow for roadshow Id: "
					+ roadshowId, e);
		}
		String name = null;
		if (roadshow != null) {
			name = roadshow.getName();
		}
		return name;
	}
	@Override
	public RoadshowEntity getBy(RoadshowFetchEntity entity)
			throws ServiceException {
		try {
			int roadshowSize = roadshowDao.getTotalCount();
			List<RoadshowDto> roadshowData = roadshowDao.getAll(
					entity.getStart(), entity.getLength());
			return new RoadshowEntity(entity.getDraw(), roadshowSize, roadshowSize,
					roadshowData);
		} catch (IOException e) {
			throw new ServiceException("Could not search offices", e);
		}
	}
}
