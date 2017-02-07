package edu.cust.course.Course.common.webservice.spring_ws.endpoint;


import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import edu.cust.course.Course.common.webservice.spring_ws.Country;
import edu.cust.course.Course.common.webservice.spring_ws.Currency;
import edu.cust.course.Course.common.webservice.spring_ws.GetCountryRequest;
import edu.cust.course.Course.common.webservice.spring_ws.GetCountryResponse;



@Endpoint
public class UserEndpoint {
	private static final String NAMESPACE_URI = "http://spring_ws.webservice.common.Course.course.cust.edu/";
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
		GetCountryResponse response = new GetCountryResponse();
		 Country poland = new Country();
	        poland.setName("Poland-" + request.getName());
	        poland.setCapital("Warsaw");
	        poland.setCurrency(Currency.PLN);
	        poland.setPopulation(38186860);
	        response.setCountry(poland);
	        return response;
    }
}