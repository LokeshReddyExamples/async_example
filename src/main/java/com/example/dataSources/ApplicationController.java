package com.example.dataSources;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class ApplicationController {
    
	@Autowired
    private  ApplicationService applicationService;

   
    @GetMapping("/makesMultipleHttpCal")
    public CompletableFuture<Object>  execute()  {
    	List<String> topSites = Arrays.asList(
		        "http://localhost:8082/sites", "http://localhost:8082/organization");
    	CompletableFuture<Object> allDoneFutureNew = applicationService.loadAsync(topSites);
    	return allDoneFutureNew;
    }
}