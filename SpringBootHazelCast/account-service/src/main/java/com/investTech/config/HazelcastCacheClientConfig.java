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
		
		clientConfig.getNetworkConfig().addAddress("localhost");
		//192.168.0.102
		//clientConfig.setProperty("hazelcast.client.heartbeat.interval", "70000");
		//clientConfig.setProperty("hazelcast.client.heartbeat.timeout", "20000");
		clientConfig.setProperty("hazelcast.client.invocation.timeout.seconds", "120");
		clientConfig.getNetworkConfig().setConnectionAttemptLimit(10);
		clientConfig.getNetworkConfig().setConnectionAttemptPeriod(5000);
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
			return HazelcastClient.newHazelcastClient(clientConfig);
		}
	}
	

}