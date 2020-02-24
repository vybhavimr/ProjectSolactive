package com.solactive.statistics;

import javax.ws.rs.Path;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Path("/error")
public class StatisticsErrorController implements ErrorController {

	@Override	
	@ResponseBody
	public String getErrorPath() {
		return "No Mapping Found";
	}
}