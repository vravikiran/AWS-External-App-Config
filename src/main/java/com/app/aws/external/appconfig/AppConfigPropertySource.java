package com.app.aws.external.appconfig;

import java.util.Properties;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;

import com.app.aws.external.entities.Constants;

import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.appconfigdata.AppConfigDataClient;
import software.amazon.awssdk.services.appconfigdata.model.GetLatestConfigurationRequest;
import software.amazon.awssdk.services.appconfigdata.model.GetLatestConfigurationResponse;
import software.amazon.awssdk.services.appconfigdata.model.StartConfigurationSessionRequest;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.AssumeRoleRequest;
import software.amazon.awssdk.services.sts.model.AssumeRoleResponse;
import software.amazon.awssdk.services.sts.model.Credentials;

@Component
public class AppConfigPropertySource {

	private Properties properties;

	public Object[] getPropertyNames() {
		Object[] names = this.properties.keySet().toArray();
		return names;
	}

	public Object getProperty(String name) {
		return this.properties.get(name);
	}

	public void init() {
		AssumeRoleRequest roleRequest = AssumeRoleRequest.builder().roleArn(Constants.roleARN)
				.roleSessionName(Constants.roleSessionName).build();
		StsClient stsClient = StsClient.builder().build();
		AssumeRoleResponse roleResponse = stsClient.assumeRole(roleRequest);
		Credentials awsBasicCredentials = roleResponse.credentials();
		AppConfigDataClient client = AppConfigDataClient.builder()
				.credentialsProvider(
						StaticCredentialsProvider.create(AwsSessionCredentials.create(awsBasicCredentials.accessKeyId(),
								awsBasicCredentials.secretAccessKey(), awsBasicCredentials.sessionToken())))
				.build();
		StartConfigurationSessionRequest startConfigurationSessionRequest = StartConfigurationSessionRequest.builder()
				.applicationIdentifier(Constants.appIdentifier).environmentIdentifier(Constants.envIdentifier)
				.configurationProfileIdentifier(Constants.profileIdentifier).build();
		String sessionToken = client.startConfigurationSession(startConfigurationSessionRequest)
				.initialConfigurationToken();
		GetLatestConfigurationRequest latestConfigurationRequest = GetLatestConfigurationRequest.builder()
				.configurationToken(sessionToken).build();
		GetLatestConfigurationResponse latestConfigurationResponse = client
				.getLatestConfiguration(latestConfigurationRequest);
		byte[] buffer = latestConfigurationResponse.configuration().asByteArray();
		processYamlContent(buffer);
	}

	private void processYamlContent(byte[] byteBuffer) {
		YamlPropertiesFactoryBean bean = new YamlPropertiesFactoryBean();

		bean.setResources(new ByteArrayResource(byteBuffer));

		this.properties = bean.getObject();
	}
}
