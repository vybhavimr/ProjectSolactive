package com.solactive.statistics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import com.solactive.statistics.model.InstrumentDetail;
import com.solactive.statistics.model.InstrumentDetailBuilder;
import com.solactive.statistics.service.TicksStatisticsService;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/testApplicationContext.xml"})
@WebAppConfiguration
public class TicksStatisticsControllerTest {
 
    private MockMvc mockMvc;
 
    @Autowired
    private TicksStatisticsService ticksServiceMock;
    @Test
    public void findAll_TodosFoundStatistics() throws Exception {
    	InstrumentDetail first = new InstrumentDetailBuilder()
                .instrPrice(100.20)
                .instrumentId("Lorem ipsum")
                .timestamp(new Long(12342334))
                .build();
    	InstrumentDetail second = new InstrumentDetailBuilder()
                .instrPrice(870.20)
                .instrumentId("Reliance")
                .timestamp(new Long(564446555))
                .build();
 
        when(ticksServiceMock.getAllInstrumentDetails()).thenReturn(getTestStatistics(first,second));       
 
        mockMvc.perform(get("/api/statistics"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("APPLICATION_JSON_UTF8"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].avg", is(485.20)))
                .andExpect(jsonPath("$[0].max", is(870.20)))
                .andExpect(jsonPath("$[0].min", is(100.20)))
                .andExpect(jsonPath("$[0].count", is(2)));
 
        verify(ticksServiceMock, times(1)).getAllInstrumentDetails();
        verifyNoMoreInteractions(ticksServiceMock);
    }

    @Test
    public void findAll_TodosFoundStatisticForOne() throws Exception {
    	InstrumentDetail first = new InstrumentDetailBuilder()
                .instrPrice(100.20)
                .instrumentId("Lorem ipsum")
                .timestamp(new Long(12342334))
                .build();
    	InstrumentDetail second = new InstrumentDetailBuilder()
                .instrPrice(870.20)
                .instrumentId("Reliance")
                .timestamp(new Long(564446555))
                .build();
 
        when(ticksServiceMock.getInstrumentDetail("Reliance")).thenReturn(getTestStatistics(first,second));       
 
        mockMvc.perform(get("/api/statistics/Reliance"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("APPLICATION_JSON_UTF8"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].avg", is(870.20)))
                .andExpect(jsonPath("$[0].max", is(870.20)))
                .andExpect(jsonPath("$[0].min", is(870.20)))
                .andExpect(jsonPath("$[0].count", is(2)));
 
        verify(ticksServiceMock, times(1)).getAllInstrumentDetails();
        verifyNoMoreInteractions(ticksServiceMock);
    }

    @Test
    public void insertTicks() throws Exception {
    	InstrumentDetail first = new InstrumentDetailBuilder()
                .instrPrice(100.20)
                .instrumentId("Lorem ipsum")
                .timestamp(new Long(12342334))
                .build();
        Mockito.doThrow(new Exception()).when(ticksServiceMock).addInstrumentDetail(first);       
 
        mockMvc.perform(get("/api/ticks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("APPLICATION_JSON_UTF8"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].avg", is(870.20)))
                .andExpect(jsonPath("$[0].max", is(870.20)))
                .andExpect(jsonPath("$[0].min", is(870.20)))
                .andExpect(jsonPath("$[0].count", is(2)));
 
        verify(ticksServiceMock, times(1)).getAllInstrumentDetails();
        verifyNoMoreInteractions(ticksServiceMock);
    }
    
    
    	private InstrumentDetail getTestStatistics(InstrumentDetail first, InstrumentDetail second) {
		InstrumentDetail instr = new InstrumentDetail();
		instr.setAvg((first.getInstrPrice()+second.getInstrPrice())/2);
		if(first.getInstrPrice() > second.getInstrPrice()) {
		instr.setMax(first.getInstrPrice());
		instr.setMin(second.getInstrPrice());
		}
		else {
			instr.setMax(second.getInstrPrice());
			instr.setMin(first.getInstrPrice());
		}
		instr.setCount(2);
		return instr;
	}
}