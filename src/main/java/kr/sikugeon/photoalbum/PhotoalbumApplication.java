package kr.sikugeon.photoalbum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PhotoalbumApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml,"
            + "classpath:aws.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(PhotoalbumApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }

}
