package com.worksap.stm.sample.dao.spec;

import java.io.IOException;
import java.util.List;
import com.worksap.stm.sample.dto.RoadshowDto;

public interface RoadshowDao {
	int getTotalCount() throws IOException;
	RoadshowDto getBy(int roadshowId) throws IOException;
	List<RoadshowDto> getAll(int start, int size) throws IOException;

}
