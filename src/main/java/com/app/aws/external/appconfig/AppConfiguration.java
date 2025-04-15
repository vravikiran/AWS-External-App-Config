package com.app.aws.external.appconfig;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import com.app.aws.external.entities.Constants;

import jakarta.annotation.PostConstruct;

@Configuration
public class AppConfiguration {
	@Autowired
	ConfigurableEnvironment environment;
	@Autowired
	AppConfigPropertySource appConfigPropertySource;

	@PostConstruct
	public void init() {
		appConfigPropertySource.init();
		MutablePropertySources propertySources = environment.getPropertySources();
		if (!propertySources.contains(Constants.DYNAMIC_PROPERTIES_SOURCE_NAME)) {
			Map<String, Object> dynamicProperties = new HashMap<>();
			Object[] names = appConfigPropertySource.getPropertyNames();
			for (Object name : names) {
				dynamicProperties.put(name.toString(), appConfigPropertySource.getProperty(name.toString()));
			}
			propertySources
					.addFirst(new MapPropertySource(Constants.DYNAMIC_PROPERTIES_SOURCE_NAME, dynamicProperties));
		}
	}

	@Bean
	DataSource dataSource() {
		String url = appConfigPropertySource.getProperty(Constants.DATASOURCE_URL).toString();
		String username = appConfigPropertySource.getProperty(Constants.DATASOURCE_USERNAME).toString();
		String password = appConfigPropertySource.getProperty(Constants.DATASOURCE_PASSWORD).toString();
		return DataSourceBuilder.create().url(url).username(username).password(password).build();
	}
}
