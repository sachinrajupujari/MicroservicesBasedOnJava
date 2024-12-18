package com.investTech.services.hazelcastmemberservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapStoreConfig;
import com.hazelcast.config.MapStoreConfig.InitialLoadMode;
import com.hazelcast.config.MaxSizeConfig;
import com.hazelcast.config.cp.CPSubsystemConfig;
import com.hazelcast.config.cp.FencedLockConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.context.SpringManagedContext;
import com.investTech.services.hazelcastmemberservice.mapstore.AccountMapStore;

@Configuration
public class HazelcastMemberCacheConfig {

	@Bean
    public Config hazelcastConfig(){
		
		
		ManagementCenterConfig manCenterCfg = new ManagementCenterConfig();
		manCenterCfg.setEnabled(true).setUrl("http://localhost:8090/hazelcast-mancenter");
		
		AccountMapStore accountMapStore= new AccountMapStore();
		MapStoreConfig mapStoreConfig= new MapStoreConfig();
		mapStoreConfig.setEnabled(true).setClassName("com.investTech.services.hazelcastmemberservice.mapstore.AccountMapStore");
		mapStoreConfig.setInitialLoadMode(InitialLoadMode.EAGER);
		mapStoreConfig.setImplementation(accountMapStore);
		//configure MapStore as write-behind by setting the write-delay-seconds property to a value bigger than 0
		mapStoreConfig.setWriteDelaySeconds(0);
		
		CPSubsystemConfig cpSubsystemConfig=new CPSubsystemConfig();
		cpSubsystemConfig.setCPMemberCount(3);
		cpSubsystemConfig.addLockConfig(new FencedLockConfig("non-reentrant-lock", 1));
		cpSubsystemConfig.setSessionHeartbeatIntervalSeconds(1);
		cpSubsystemConfig.setSessionTimeToLiveSeconds(5);
		cpSubsystemConfig.setMissingCPMemberAutoRemovalSeconds(10); 
		
		Config config=new Config();
		config.setManagedContext(managedContext());
		config.setManagementCenterConfig(manCenterCfg).setCPSubsystemConfig(cpSubsystemConfig)
                .addMapConfig(new MapConfig().setName("accounts")
                		.setMaxSizeConfig(new MaxSizeConfig(300,MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                		.setEvictionPolicy(EvictionPolicy.LRU)
	            		.setBackupCount(2)
                		.setTimeToLiveSeconds(2000)
                		.setMapStoreConfig(mapStoreConfig))
                
                .addMapConfig(new MapConfig().setName("loandata")
                		.setMaxSizeConfig(new MaxSizeConfig(300,MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                		.setEvictionPolicy(EvictionPolicy.LRU)
                		.setTimeToLiveSeconds(2000));
		//Setting three more members
		HazelcastInstance hz1 = Hazelcast.newHazelcastInstance(config);
		HazelcastInstance hz2 = Hazelcast.newHazelcastInstance(config);
		HazelcastInstance hz3 = Hazelcast.newHazelcastInstance(config);
	     return config;      
	}
	
	 @Bean
	    public SpringManagedContext managedContext() {
	        return new SpringManagedContext();
    }
	 
	 
		
}
