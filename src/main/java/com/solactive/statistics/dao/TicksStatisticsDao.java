/**
 * 
 */
package com.solactive.statistics.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.solactive.statistics.model.InstrumentDetail;
import com.solactive.statistics.model.InstrumentDetailRowMapper;

/**
 * @author Vybhavi
 *
 */
@Component
public class TicksStatisticsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional
	public InstrumentDetail getInstrumentDetail(String id) {
		List<InstrumentDetail> instrDetailRes = (List<InstrumentDetail>) jdbcTemplate.query(
				"select * from instrument_detail where id = ?", new Object[] { id }, new InstrumentDetailRowMapper());
		InstrumentDetail instrDetail = getInstrStatistics(instrDetailRes);
		return instrDetail;
	}

	@Transactional
	public InstrumentDetail getAllInstrumentDetails() {
		List<InstrumentDetail> instrDetailRes = (List<InstrumentDetail>) jdbcTemplate
				.query("SELECT * FROM instrument_detail", new InstrumentDetailRowMapper());
		InstrumentDetail instrDetail = getInstrStatistics(instrDetailRes);
		return instrDetail;
	}

	private InstrumentDetail getInstrStatistics(List<InstrumentDetail> instrDetailRes) {
		InstrumentDetail instrDetail = new InstrumentDetail();
		int count = 0;
		double sum = 0, high = 0, low = 0;
		for (InstrumentDetail t : instrDetailRes) {
			if (((System.currentTimeMillis()) - (t.getTimestamp())) > 60000) {
				continue;
			}
			count = count + 1;
			sum = sum + t.getInstrPrice();
			if (high < t.getInstrPrice()) {
				high = t.getInstrPrice();
			}
			if (low > t.getInstrPrice()) {
				low = t.getInstrPrice();
			}
		}
		if (count > 0) {
			instrDetail.setCount(count);
			instrDetail.setMax(high);
			instrDetail.setMin(low);
			instrDetail.setAvg((sum / count));
			return instrDetail;
		} else {
			System.out.println("There are no records with ticks within a minute");
			return null;
		}
	}

	@Transactional
	public void addInstrumentDetail(InstrumentDetail instrDetail) {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		simpleJdbcInsert.withTableName("instrument_detail");
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", instrDetail.getInstrumentId());
		parameters.put("price", instrDetail.getInstrPrice());
		parameters.put("givenTimestamp", instrDetail.getTimestamp());
		simpleJdbcInsert.execute(parameters);
	}
}
