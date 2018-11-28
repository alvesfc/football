package br.org.ehandler.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {ExceptionConfig.class})
public class ExceptionConfig {
}
