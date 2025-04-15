package com.app.aws.external.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.aws.external.entities.AppInfo;

@RestController
@RequestMapping("/appdetail")
public class AppController {
	@Autowired
	AppInfo appInfo;
	
	@GetMapping
	public ResponseEntity<String> getAppDetails() {
		String appDetail = "APP NAME: "+appInfo.getAppName()+"\n"+"ORGANIZATION: "+appInfo.getOrg()+"\n"+"Location: "+appInfo.getLocation();
		return ResponseEntity.ok(appDetail);
	}
}
