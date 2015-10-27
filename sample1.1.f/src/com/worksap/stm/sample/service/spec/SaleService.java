package com.worksap.stm.sample.service.spec;


import com.worksap.stm.sample.entity.SaleEntity;
import com.worksap.stm.sample.entity.SaleFetchEntity;
import com.worksap.stm.sample.exception.ServiceException;

public interface SaleService {
	String getBy(int saleId) throws ServiceException;

	SaleEntity getBy(SaleFetchEntity entity) throws ServiceException;

}
