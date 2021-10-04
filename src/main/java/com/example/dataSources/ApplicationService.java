package com.example.dataSources;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApplicationService {

	private static RestTemplate restTemplate = new RestTemplate();

	private static  List<DataBean>  createHttp(String url) {
		List<DataBean> response = restTemplate.getForObject(url, List.class);
		return response;
	}


	private static  CompletableFuture<Object> sequence(List<CompletableFuture<List<DataBean>>> futures) {
		CompletableFuture<Void> allDoneFuture =
				CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
		return allDoneFuture.thenApply(v ->
		futures.stream().
		map(future -> {
			return future.thenApply(fuObject -> {
				fuObject.size(); // added for exception scenerio
				return fuObject;
			}).exceptionally(ex -> Arrays.asList(new DataBean("exception"))).join();
		}).collect(Collectors.toList()));
	}


	public CompletableFuture<Object> loadAsync(List<String> topSites) {
			List<CompletableFuture<List<DataBean>>> futures = topSites.stream()
				.map(site -> CompletableFuture.supplyAsync(() -> createHttp(site)))
				.collect(Collectors.toList());
		CompletableFuture<Object> allDoneFutureNew = sequence(futures);
		return allDoneFutureNew;
	}


}