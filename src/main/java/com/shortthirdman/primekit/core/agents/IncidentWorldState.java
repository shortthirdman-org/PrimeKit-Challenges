package com.shortthirdman.primekit.core.agents;

/**
 * @param serviceDegraded whether service is degraded
 * @param recentDeployment whether deployment is recent
 * @param serviceHealthy whether service is healthy
 * @param oncallPaged whether service is on-call paged
 * @param restartAttempted whether service has been attempted restart
 * @param severity the level of {@link Severity} of the incident
 */
public record IncidentWorldState(boolean serviceDegraded,
                                 boolean recentDeployment,
                                 boolean serviceHealthy,
                                 boolean oncallPaged,
                                 boolean restartAttempted,
                                 Severity severity) {

    /**
     * Initialize incident with whether service is healthy
     * @param healthy flag to set if service is healthy or not
     * @return the initialized {@link IncidentWorldState}
     */
    public IncidentWorldState withServiceHealthy(boolean healthy) {
        return new IncidentWorldState(serviceDegraded, recentDeployment, healthy,
                oncallPaged, restartAttempted, severity);
    }
}
