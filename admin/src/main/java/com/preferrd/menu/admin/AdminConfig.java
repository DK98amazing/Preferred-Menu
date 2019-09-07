package com.preferrd.menu.admin;

import de.codecentric.boot.admin.server.config.AdminServerMarkerConfiguration;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminConfig {
    @Bean
    public AdminServerMarkerConfiguration.Marker marker() {
        AdminServerMarkerConfiguration adminServerMarkerConfiguration = new AdminServerMarkerConfiguration();
        AdminServerMarkerConfiguration.Marker marker = adminServerMarkerConfiguration.adminServerMarker();
        return marker;
    }
}
