package com.worksap.stm.sample.service.spec;

import com.worksap.stm.sample.entity.RoadshowEntity;
import com.worksap.stm.sample.entity.RoadshowFetchEntity;
import com.worksap.stm.sample.exception.ServiceException;

public interface RoadshowService {
	String getBy(int roadshowId) throws ServiceException;

	RoadshowEntity getBy(RoadshowFetchEntity entity) throws ServiceException;

}
