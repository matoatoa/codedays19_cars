package de.matoatoa.demo.codedays19.cars.client.common;

import lombok.Getter;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Jan Hauer (EXXETA AG)
 */
@Getter
public class ServerConfig {

    public static final String DEFAULT_URL = "http://localhost:9001";

    /**
     * Base url for cars server
     */
    private final String url;

    /**
     * user name for cars server
     */
    private final String user;

    /**
     * password for cars server
     */
    private final String password;

    public ServerConfig(String url, String user, String password) {
        this.url = Optional.ofNullable(url).orElse(ServerConfig.DEFAULT_URL);
        this.user = Objects.requireNonNull(user);
        this.password = Objects.requireNonNull(password);
    }
}
