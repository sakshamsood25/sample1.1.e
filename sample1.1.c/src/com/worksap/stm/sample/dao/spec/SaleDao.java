package com.worksap.stm.sample.dao.spec;

import java.io.IOException;
import java.util.List;
import com.worksap.stm.sample.dto.SaleDto;

public interface SaleDao {
	int getTotalCount() throws IOException;
	SaleDto getBy(int saleId) throws IOException;
	List<SaleDto> getAll(int start, int size) throws IOException;

}
