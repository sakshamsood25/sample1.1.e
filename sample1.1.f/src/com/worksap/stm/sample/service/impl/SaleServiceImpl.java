package com.worksap.stm.sample.service.impl;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worksap.stm.sample.dao.spec.SaleDao;
import com.worksap.stm.sample.dto.SaleDto;
import com.worksap.stm.sample.entity.SaleEntity;
import com.worksap.stm.sample.entity.SaleFetchEntity;
import com.worksap.stm.sample.exception.ServiceException;
import com.worksap.stm.sample.service.spec.SaleService;

@Service
public class SaleServiceImpl implements SaleService {

	@Autowired
	private SaleDao saleDao;

	@Override
	public String getBy(int saleId) throws ServiceException {
		SaleDto sale = null;
		try {
			sale = saleDao.getBy(saleId);
		} catch (IOException e) {
			throw new ServiceException("Cannot find sale for sale Id: "
					+ saleId, e);
		}
		String name = null;
		if (sale != null) {
			name = sale.getName();
		}
		return name;
	}
	@Override
	public SaleEntity getBy(SaleFetchEntity entity)
			throws ServiceException {
		try {
			int saleSize = saleDao.getTotalCount();
			List<SaleDto> saleData = saleDao.getAll(
					entity.getStart(), entity.getLength());
			return new SaleEntity(entity.getDraw(), saleSize, saleSize,
					saleData);
		} catch (IOException e) {
			throw new ServiceException("Could not search offices", e);
		}
	}
}
