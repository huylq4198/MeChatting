package com.huylq.mechatting.config.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(basePackages = "com.huylq.mechatting.repository.htx", entityManagerFactoryRef = "htxEntityManager", transactionManagerRef = "htxTransactionManager")
public class HuyXauTraiDatasourceConfig {

    @Autowired
    Environment env;
//
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "app.datasource.htx")
//    public DataSourceProperties htxDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean
//    @Primary
//    public DataSource htxDataSource() {
//        return htxDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
//    }

    @Bean
    @Primary
    public DataSource htxDataSource() {
        return DataSourceBuilder.create()
                .driverClassName(env.getProperty("app.datasource.hxt.driver-class-name"))
                .url(env.getProperty("app.datasource.hxt.url"))
                .username(env.getProperty("app.datasource.hxt.username"))
                .password(env.getProperty("app.datasource.hxt.password"))
                .build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean htxEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(htxDataSource());
        em.setPackagesToScan("com.huylq.mechatting.entity.htx");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.database-platform"));
//        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
//        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    @Primary
    public PlatformTransactionManager htxTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(htxEntityManager().getObject());
        return transactionManager;
    }
}
