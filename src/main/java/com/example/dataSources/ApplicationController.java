package com.example.dataSources;

import java.util.ArrayList;
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
    List<String> topSites = new ArrayList<>();
    	
    	//for (int i=0;i<25;i++) {
    		topSites.add("http://localhost:8084/sites"); 
    		topSites.add("http://localhost:8084/organization");
    		topSites.add("http://localhost:8084/nocontent");
    		topSites.add("http://localhost:8084/timeOut");
    		topSites.add("http://localhost:8084/exception");
    	//}
    		
    	CompletableFuture<Object> allDoneFutureNew = applicationService.loadAsync(topSites);
    	return allDoneFutureNew;
    }
}