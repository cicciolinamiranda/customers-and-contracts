package com.g4s.javelin.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.g4s.javelin.config.properties.AbstractDbConfigProperties;

@EnableTransactionManagement
@Configuration
@EnableJpaRepositories("com.g4s.javelin.data.repository")
public class DbConfiguration {
    @Autowired
    private AbstractDbConfigProperties properties;

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        props.put("hibernate.hbm2ddl.auto", "update");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setDataSource(dataSource());
        factory.setPersistenceUnitName("defaultPersistenceUnit");
        factory.setPackagesToScan("com.g4s.javelin.data.model");
        factory.setJpaProperties(props);
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

    /*
     * (non-javadoc)
     *
     * Use Tomcat 7 DBCP because GAE restricts usage on
     * java.lang.management.ManagementFactory
     *
     */
    @Bean
    @Profile("!test")
    public DataSource dataSource() {
        BasicDataSource datasource = new BasicDataSource();
        datasource.setDriverClassName(properties.getDriverClassName());
        datasource.setUrl(properties.getUrl());
        datasource.setUsername(properties.getUsername());
        datasource.setPassword(properties.getPassword());
        //CSOFF: MagicNumber
        datasource.setMaxActive(12);
        datasource.setMaxIdle(12);
        datasource.setMinIdle(12);
        datasource.setInitialSize(12);
        //CSON: MagicNumber
        datasource.setTestOnBorrow(true);
        datasource.setValidationQuery("SELECT 1");
        return datasource;
    }
}
