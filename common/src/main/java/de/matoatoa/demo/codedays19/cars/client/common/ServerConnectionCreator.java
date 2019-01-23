package de.matoatoa.demo.codedays19.cars.client.common;

import org.springframework.cloud.service.AbstractServiceConnectorCreator;
import org.springframework.cloud.service.ServiceConnectorConfig;

public class ServerConnectionCreator extends AbstractServiceConnectorCreator<ServerConfig, ServerInfo> {
    @Override
    public ServerConfig create(ServerInfo serverInfo, ServiceConnectorConfig serviceConnectorConfig) {
        return new ServerConfig(serverInfo.getUrl(), serverInfo.getUserName(), serverInfo.getPassword());
    }
}
