package com.xazhao.datasource.config;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * MyBatis Configuration
 *
 * @Description Created on 2024/08/10.
 * @Author Zhao.An
 */

@Slf4j
@Configuration
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@EnableConfigurationProperties(MybatisProperties.class)
public class MybatisConfiguration implements Provider {

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private MybatisProperties properties;

    /**
     * MyBatis适配多数据源
     *
     * @return DatabaseIdProvider 数据库厂商标识
     */
    @Bean
    public DatabaseIdProvider getDatabaseIdProvider() {
        DatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
        Properties properties = new Properties();
        properties.put(MYSQL, "mysql");
        properties.put(ORACLE, "oracle");
        properties.setProperty(POSTGRESQL, "postgresql");
        databaseIdProvider.setProperties(properties);
        log.info("MyBatis adaptation multiple data sources configuration is successful");
        return databaseIdProvider;
    }

    /**
     * 实例化SqlSessionFactory
     *
     * @param dataSource DataSource
     * @return SqlSessionFactory
     * @throws Exception Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        // 数据源
        sessionFactory.setDataSource(dataSource);
        // 数据厂商
        sessionFactory.setDatabaseIdProvider(getDatabaseIdProvider());
        // Mybatis全局配置文件
        sessionFactory.setConfigLocation(
                new DefaultResourceLoader().getResource(properties.getConfigLocation()));
        // MyBatis映射文件所在的位置，指的是编写SQL的xml位置
        sessionFactory.setMapperLocations(applicationContext.getResources(properties.getMapperLocations()));
        // 实体类所在的包
        sessionFactory.setTypeAliasesPackage(properties.getTypeAliasesPackage());
        log.info("SqlSessionFactory instantiate the success");
        return sessionFactory.getObject();
    }

}
