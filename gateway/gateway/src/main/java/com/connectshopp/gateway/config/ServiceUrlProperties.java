package com.connectshopp.gateway.config;

import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "connectshop")
public class ServiceUrlProperties {

    private Map<String, String> services;

    public Map<String, String> getServices() {
        return services;
    }

    public void setServices(Map<String, String> services) {
        this.services = services;
    }

    public String getServiceUrl(String service) {
        if (services == null) {
            return null;
        }
        return services.get(service);
    }
}
