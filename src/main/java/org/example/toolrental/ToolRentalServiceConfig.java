package org.example.toolrental;

import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@ComponentScan
@EnableAutoConfiguration
@EntityScan(basePackages = "org.example.toolrental.data.entity")
public class ToolRentalServiceConfig {


    @Bean
    public RulesEngine rulesEngine() {
        return new DefaultRulesEngine();
    }

}