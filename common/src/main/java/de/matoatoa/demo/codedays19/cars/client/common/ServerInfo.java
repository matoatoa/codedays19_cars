package de.matoatoa.demo.codedays19.cars.client.common;

import org.springframework.cloud.service.UriBasedServiceInfo;

import java.util.Optional;

public class ServerInfo extends UriBasedServiceInfo {

    public ServerInfo(String id, String uriString) {
        super(id, uriString);
    }

    public String getUrl() {
        return getScheme() + "://" + getHost() + Optional.ofNullable(getPath()).map("/"::concat).orElse("");
    }
}
