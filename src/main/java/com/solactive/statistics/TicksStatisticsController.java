/**
 * 
 */
package com.solactive.statistics;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.solactive.statistics.model.InstrumentDetail;
import com.solactive.statistics.service.TicksStatisticsService;

/**
 * @author Vybhavi
 *
 */

@RestController
public class TicksStatisticsController {
	@Autowired
	private TicksStatisticsService ticksStatisticsService;
	
	@POST
	@RequestMapping(value="/ticks")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response sendTick(@RequestBody InstrumentDetail request) {
		if((System.currentTimeMillis() - request.getTimestamp()) > (60000)) {		
		return Response.status(204).build();
		}
		else {
			ticksStatisticsService.addInstrumentDetail(request);
			return Response.status(201).build();
		}
	}

	@GET
	@RequestMapping(value="/statistics")
	@Produces({MediaType.APPLICATION_JSON})
	public InstrumentDetail returnAllStatistics() {
		return ticksStatisticsService.getAllInstrumentDetails();
	}
	
	@GET
	@RequestMapping(value="/statistics/{instrId}")
	@Produces({MediaType.APPLICATION_JSON})
	public InstrumentDetail returnStatisticForOne(@PathVariable("instrId") String instrId) {
		return ticksStatisticsService.getInstrumentDetail(instrId);
	}
}
