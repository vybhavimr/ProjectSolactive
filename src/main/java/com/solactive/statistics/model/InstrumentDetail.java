/**
 * 
 */
package com.solactive.statistics.model;

/**
 * @author Vybhavi
 *
 */

public class InstrumentDetail {

	private String instrumentId;
	private Double instrPrice;
	private Long timestamp;
	private double avg;
	private double max;
	private double min;
	private int count;

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getInstrumentId() {
		return instrumentId;
	}

	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	public Double getInstrPrice() {
		return instrPrice;
	}

	public void setInstrPrice(Double instrPrice) {
		this.instrPrice = instrPrice;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public InstrumentDetail() {
	}
}
