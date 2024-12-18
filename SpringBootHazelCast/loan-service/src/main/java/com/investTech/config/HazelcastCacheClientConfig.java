package com.investTech.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;


@Component
class CacheClient {

	@Bean
	public ClientConfig clientConfig() {
		
		ClientConfig clientConfig = new ClientConfig();
		
		clientConfig.getNetworkConfig().addAddress("10.42.1.44")
									   .setConnectionAttemptLimit(5)
									   .setConnectionAttemptPeriod(5000);
		
		clientConfig.setProperty("hazelcast.client.heartbeat.interval", "60000")
					.setProperty("hazelcast.client.heartbeat.timeout", "20000")
					.setProperty("hazelcast.client.invocation.timeout.seconds", "120");
		
		return clientConfig;
	}
	
	/**
	 * <P>Temporary. Spring Boot should soon autoconfigure
	 * the {@code HazelcastInstance} bean.
	 * </P>
	 */
	@Configuration
	@ConditionalOnMissingBean(HazelcastInstance.class)
	static class HazelcastClientConfiguration {
		
		@Bean
		public HazelcastInstance hazelcastInstance(ClientConfig clientConfig) {
			return clientConfig != null ? HazelcastClient.newHazelcastClient(clientConfig): 
				HazelcastClient.newHazelcastClient();
		}
	}
	

}