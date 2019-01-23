package de.matoatoa.demo.codedays19.cars.client.common;

import org.springframework.boot.autoconfigure.condition.ConditionalOnCloudPlatform;
import org.springframework.boot.cloud.CloudPlatform;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ConditionalOnCloudPlatform(CloudPlatform.CLOUD_FOUNDRY)
public class CloudConfiguration extends AbstractCloudConfig {
    @Bean
    @Primary
    public ServerConfig serverConfigCloud() {
        return connectionFactory().service(ServerConfig.class);
    }
}