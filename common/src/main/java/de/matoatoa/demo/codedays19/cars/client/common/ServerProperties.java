package de.matoatoa.demo.codedays19.cars.client.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Jan Hauer (EXXETA AG)
 */
@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "de.matoatoa.demo.codedays19.cars.server")
public class ServerProperties {
    /**
     * Base url for cars server
     */
    private String url = "http://localhost:9001";

    /**
     * user name for cars server
     */
    private String user = "admin";

    /**
     * password for cars server
     */
    private String password = "secret";
}
