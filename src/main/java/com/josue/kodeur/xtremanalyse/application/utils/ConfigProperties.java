package com.josue.kodeur.xtremanalyse.application.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author JosueKodeur
 */

@Data
@ConfigurationProperties("xtrem")
public final class ConfigProperties {
    private String secret;
    private Long tokenExpire;
}
