package com.payment.seffaf;

import com.payment.seffaf.model.Customer;
import com.payment.seffaf.utils.AuditorAwareImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication
public class SeffafApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SeffafApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SeffafApplication.class);
    }


    @Bean
    public AuditorAware<Customer> auditorAware() {
        return new AuditorAwareImpl();
    }

}
