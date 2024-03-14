package org.kenuki.landingserver.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Data
@Configuration
@ConfigurationProperties("storage")
public class ImageStorageConfiguration {
    private String absolute_path;
}
