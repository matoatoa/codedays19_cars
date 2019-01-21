package de.matoatoa.demo.codedays19.cars.client.common;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

/**
 * @author Jan Hauer (EXXETA AG)
 */
public class IncompleteConfigurationFailureAnalyzer extends AbstractFailureAnalyzer<IncompleteConfigurationException> {
    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, IncompleteConfigurationException cause) {
        return new FailureAnalysis(cause.getLocalizedMessage(), "Provide values for url, user and password.", cause);
    }
}
