package com.produits.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties("mes-configurations")
@RefreshScope
public class ApplicationPropertiesConfiguration {
	private int limiteDeProduits;

}
