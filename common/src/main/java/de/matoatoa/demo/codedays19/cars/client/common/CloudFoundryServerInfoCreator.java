package de.matoatoa.demo.codedays19.cars.client.common;

import org.springframework.cloud.cloudfoundry.CloudFoundryServiceInfoCreator;
import org.springframework.cloud.cloudfoundry.Tags;

import java.util.Map;

public class CloudFoundryServerInfoCreator extends CloudFoundryServiceInfoCreator<ServerInfo> {
    public CloudFoundryServerInfoCreator() {
        super(new Tags(), "cars");
    }

    @Override
    public ServerInfo createServiceInfo(Map<String, Object> serviceData) {
        return new ServerInfo(getId(serviceData), getUriFromCredentials(getCredentials(serviceData)));
    }
}
