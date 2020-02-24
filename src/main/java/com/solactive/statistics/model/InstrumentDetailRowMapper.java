/**
 * 
 */
package com.solactive.statistics.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @author Vybhavi
 *
 */
public class InstrumentDetailRowMapper implements RowMapper<InstrumentDetail> {
	public InstrumentDetail mapRow(ResultSet rs, int row) throws SQLException {
		InstrumentDetail instrDetail = new InstrumentDetail();
		instrDetail.setInstrumentId(rs.getString("id"));
		instrDetail.setInstrPrice(rs.getDouble("price"));
		instrDetail.setTimestamp(rs.getLong("givenTimestamp"));
		return instrDetail;
	}
}