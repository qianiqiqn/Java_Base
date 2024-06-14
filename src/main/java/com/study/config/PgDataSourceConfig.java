package com.study.config;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author wenqianqian
 *
 * basePackages 配置mapper接口位置
 */
@Configuration
@MapperScan(basePackages = "com.sutpc.dao", sqlSessionFactoryRef = "pgSqlSessionFactory")
public class PgDataSourceConfig {

    private static final String MAPPER_LOCATION = "classpath:mappers/*.xml";

    /**
     * 数据源
     */
    @Primary
    @Bean(name = "pgDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.pg")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 事务管理器
     */
    @Primary
    @Bean(name = "pgTransactionManager")
    public PlatformTransactionManager dataSourceTransactionManager(@Qualifier("pgDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * session工厂
     */
    @Primary
    @Bean(name = "pgSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("pgDataSource") DataSource dataSource)
            throws Exception {
        MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean ();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(PgDataSourceConfig.MAPPER_LOCATION));
        return sessionFactoryBean.getObject();
    }
}
