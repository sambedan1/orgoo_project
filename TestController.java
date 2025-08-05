package com.tuespotsolutions.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.tuespotsolutions.models.Test;

@RestController
@CrossOrigin("*")
@RequestMapping("/test")
public class TestController {

	@PostMapping("/")
	public String getResponse(HttpServletRequest httpServletRequest) {
		System.out.println("line no 23 : "+httpServletRequest.toString());
		return "api working";
	}
	
	@PostMapping("/request")
	public Test sendRequest(@RequestBody Test test) {
		System.out.println("line no 29 : "+test.toString());
		return test;
	}
	
	@PostMapping("/json")
	public String sendJsonObject(@RequestBody JsonObject jsonObject) {
		System.out.println("line no 35 : "+jsonObject.toString());
		return "JsonObject test";
	}
	
	@PostMapping("/stringdata")
	public String sendJsonObject(@RequestBody String json) {
		System.out.println("line no 41 : "+json.toString());
		return json;
	}
	
	@PostMapping("/stringmap")
	public  String sendJsonObjectMap(@RequestBody Map<String, Object> json) {
		System.out.println("line no 47 : "+json);
		return "Map test";
	}
	
	@PostMapping("/object")
	public  String sendObject(@RequestBody Object json) {
		System.out.println("line no 47 : "+json);
		return "Object test";
	}
}
