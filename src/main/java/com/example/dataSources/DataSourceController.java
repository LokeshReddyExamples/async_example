package com.example.dataSources;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataSourceController {
	
	// created for testing  
    @GetMapping("/sites")
    public ResponseEntity<List> sites() throws InterruptedException{
    	
    	DataBean site1 = new DataBean("site_1");
    	DataBean site2 = new DataBean("site_2");
    	DataBean site3 = new DataBean("site_3");
    	DataBean site4 = new DataBean("site_4");
    	
    	//Thread.sleep(5000);
    	
    	List<DataBean> list = Arrays.asList(site1,site2,site3,site4);
    	ResponseEntity ResponseEntity = new ResponseEntity<>(list,HttpStatus.OK);
    	
    	return ResponseEntity;
    }
    
    // created for testing 
    @GetMapping("/organization") 
    public ResponseEntity<List> organization() throws InterruptedException{
    	
    	ResponseEntity<DataBean> response = null;
    	DataBean org1 = new DataBean("org_1");
    	DataBean org2 = new DataBean("org_2");
    	DataBean org3 = new DataBean("org_3");
    	DataBean org4 = new DataBean("org_4");
    	List<DataBean> list = Arrays.asList(org1,org2,org3,org4);
    	
    	//Thread.sleep(2000);
    	
    	ResponseEntity ResponseEntity = new ResponseEntity<>(list,HttpStatus.OK);
        return ResponseEntity;
    }
    
    // created for testing 
    @GetMapping("/exceptionService")
    public ResponseEntity<Object> investores(){
        return new ResponseEntity<Object>("exception",HttpStatus.NO_CONTENT);
    } 

}
