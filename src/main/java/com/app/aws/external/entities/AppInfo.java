package com.app.aws.external.entities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppInfo {
	@Value("${app.name}")
	private String appName;
	@Value("${app.org}")
	private String org;
	@Value("${app.location}")
	private String location;

	public String getAppName() {
		return appName;
	}

	public String getOrg() {
		return org;
	}

	public String getLocation() {
		return location;
	}

	public AppInfo() {
		super();
	}

}
