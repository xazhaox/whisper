package com.xazhao.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Datasource Configuration
 *
 * @Description Created on 2024/08/09.
 * @Author Zhao.An
 */

@Slf4j
@Configuration
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class DatasourceConfiguration {

    public static final String PREFIX = "spring.datasource.druid";

    /**
     * 通过Druid创建数据源，将配置文件中的spring.datasource.druid前缀的相关属性自动绑定到这个数据源上
     *
     * @return DataSource
     */
    @Bean
    @ConfigurationProperties(prefix = DatasourceConfiguration.PREFIX)
    public DataSource dataSource() {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        log.info("DataSource the creation is successful");
        return dataSource;
    }

    /**
     * 配置Spring事务管理器
     *
     * @param dataSource DataSource
     * @return DataSourceTransactionManager
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}
