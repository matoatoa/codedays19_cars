package de.matoatoa.demo.codedays19.cars.client.common;

/**
 * @author Jan Hauer (EXXETA AG)
 */
class IncompleteConfigurationException extends RuntimeException {

    private static final String TEMPLATE = "Configuration for server connection failed due to missing values." +
            "\n\nProvided values:" +
            "\n\nurl:<%s>\nuser:<%s>\npassword:<%s>";

    IncompleteConfigurationException(ServerProperties serverProperties) {
        super(String.format(TEMPLATE, serverProperties.getUrl(), serverProperties.getUser(), serverProperties.getPassword()));
    }
}
