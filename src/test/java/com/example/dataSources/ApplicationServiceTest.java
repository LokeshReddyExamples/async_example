package com.example.dataSources;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;

public class ApplicationServiceTest {
	
	ApplicationService service = new ApplicationService();
	List<String> topSites = new ArrayList<>();
	
	@Test
	public void loadAsyncTest() throws InterruptedException, ExecutionException{
		 
	    	
	     topSites.add("http://localhost:8080/sites"); 
	     topSites.add("http://localhost:8080/organization");
		 CompletableFuture<Object> obFuture = service.loadAsync(topSites);
		 List<DataBean> list = (List<DataBean>) obFuture.get();
		 System.out.println("normal case "+obFuture);
		 assertEquals(2, list.size());
		 
	}
	
	@Test
	public void loadAsyncExceptionTest() {
		 List<String> topSites = new ArrayList<>();
	    	
	    		topSites.add("http://localhost:8080/sites"); 
	    		topSites.add("http://localhost:8080/organization");
	    		topSites.add("http://localhost:8080/timeOut");

	    		CompletableFuture<Object> obFuture = service.loadAsync(topSites);
	    		Object resultObject = null;
	    		try {
	    			resultObject = obFuture.join();
	    		}catch (HttpClientErrorException e) {
	    			e.printStackTrace();
				}
	    		List<Object> list = (List)resultObject;
	    		assertEquals(3, list.size());
		 
	}

}
