package com.solactive.statistics.model;

import org.springframework.test.util.ReflectionTestUtils;

public class InstrumentDetailBuilder {
	
	 private InstrumentDetail model;

	    public InstrumentDetailBuilder() {
	        model = new InstrumentDetail();
	    }

	    public InstrumentDetailBuilder instrumentId(String instrumentId) {
	        ReflectionTestUtils.setField(model, "instrumentId", instrumentId);
	        return this;
	    }

	    public InstrumentDetailBuilder instrPrice(Double instrPrice) {
	    	ReflectionTestUtils.setField(model, "instrPrice", instrPrice);
	        return this;
	    }

	    public InstrumentDetailBuilder timestamp(Long timestamp) {
	    	ReflectionTestUtils.setField(model, "timestamp", timestamp);
	        return this;
	    }
	    public InstrumentDetailBuilder avg(int avg) {
	    	ReflectionTestUtils.setField(model, "avg", avg);
	        return this;
	    }

	    public InstrumentDetailBuilder max(int max) {
	    	ReflectionTestUtils.setField(model, "max", max);
	        return this;
	    }

	    public InstrumentDetailBuilder min(String min) {
	    	ReflectionTestUtils.setField(model, "min", min);
	        return this;
	    }

	    public InstrumentDetailBuilder count(String count) {
	    	ReflectionTestUtils.setField(model, "count", count);
	        return this;
	    }
	    
	    public InstrumentDetail build() {
	        return model;
	    }
}
