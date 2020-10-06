package com.bilyoner.assignment.couponapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@Configuration
public class HazelcastCacheConfig {

	public static final String EVENTS = "EVENTS";
	public static final String EVENT_LIST = "EVENT_LIST";
	public static final String COUPONS = "COUPONS";
	public static final String COUPONS_BY_STATUS = "COUPONS_BY_STATUS";

	@Bean
	public HazelcastInstance createHazelcastInstance() {
		return Hazelcast.newHazelcastInstance(createConfig());
	}

	public Config createConfig() {
		Config config = new Config();
		config.addMapConfig(mapConfigForKey(EVENTS));
		config.addMapConfig(mapConfigForKey(EVENT_LIST));
		config.addMapConfig(mapConfigForKey(COUPONS));
		config.addMapConfig(mapConfigForKey(COUPONS_BY_STATUS));
		return config;
	}

	private MapConfig mapConfigForKey(String key) {
		MapConfig mapConfig = new MapConfig(key);
		mapConfig.setTimeToLiveSeconds(3600);
		mapConfig.setMaxIdleSeconds(3600);
		return mapConfig;
	}

}
