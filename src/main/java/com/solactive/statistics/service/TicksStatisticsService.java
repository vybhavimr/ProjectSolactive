/**
 * 
 */
package com.solactive.statistics.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.solactive.statistics.dao.TicksStatisticsDao;
import com.solactive.statistics.model.InstrumentDetail;

/**
 * @author Vybhavi
 *
 */
@Component
public class TicksStatisticsService {


	@Autowired
	private TicksStatisticsDao ticksStatisticsDao;
	
	public InstrumentDetail getInstrumentDetail(String id) {
		return ticksStatisticsDao.getInstrumentDetail(id);
	}
	
	public InstrumentDetail getAllInstrumentDetails() {
		return ticksStatisticsDao.getAllInstrumentDetails();
	}
	
	
	public void addInstrumentDetail(InstrumentDetail instrDetail) {
		ticksStatisticsDao.addInstrumentDetail(instrDetail);
	}
	
	public List<InstrumentDetail> getAllStatistics(){
		return null;
	}

	
}
