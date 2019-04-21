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

    // TODO: 4/21/19
    /**
     * notification tablosu oluşturup email gönderme ile ilgili işlemleri yapalım
     *
     * ekranlar için servislere karar ver
     *      admin için ödeme ekranı servisleri
     *      satıcı için ekran servisleri
     *
     * authentication
     * prod veritabanı tasarımı
     * */


//    @Bean
//    public DataSource dataSource() {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setInitializationFailTimeout(0);
//        dataSource.setMaximumPoolSize(5);
//        dataSource.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
//        dataSource.addDataSourceProperty("url", "jdbc:postgresql://127.0.0.1:5432/sampledb");
//        dataSource.addDataSourceProperty("user", "philipp");
//        dataSource.addDataSourceProperty("password", "test_pwd");
//        return dataSource;
//    }

//    private static Properties getDefaultProperties() {
//        Properties defaultProperties = new Properties();
//         Set sane Spring Hibernate properties:
//        defaultProperties.put("spring.jpa.show-sql", "true");
//        defaultProperties.put("spring.jpa.hibernate.naming.physical-strategy", "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
//        defaultProperties.put("spring.datasource.initialize", "false");
//         Store Values on Delete:
//        defaultProperties.put("spring.jpa.properties.org.hibernate.envers.store_data_at_delete", "true");
//         Prevent JPA from trying to Auto Detect the Database:
//        defaultProperties.put("spring.jpa.database", "postgresql");
//         Prevent Hibernate from Automatic Changes to the DDL Schema:
//        defaultProperties.put("spring.jpa.hibernate.ddl-auto", "none");
//        return defaultProperties;
//    }


}
