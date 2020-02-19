package br.com.okfx.tasklist.api;

import br.com.okfx.tasklist.api.config.property.TaskListApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(TaskListApiProperty.class)
public class TaskListApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskListApiApplication.class, args);
    }

}
