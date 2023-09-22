package com.study.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Configuration;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
@MapperScan(basePackages = {PgDataSourceConfig.PACKAGE}, sqlSessionFactoryRef = "postgreSqlSessionFactory")
public class PgDataSourceConfig {

    /**
     * dao 所在的包
     */
    public static final String PACKAGE = "com.*.mapper";

    /**
     * mapper所在目录
     */
    private static final String MAPPER_LOCATION = "classpath*:mapper/*.xml";


    @Bean(name = "postgreSqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db-postgresql")
    public DruidDataSource mysqlDruidDataSource(){
        return DruidDataSourceBuilder.create().build();
    }


    @Bean(name = "postgreSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("postgreSqlDataSource") DruidDataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(resolver.getResources(PgDataSourceConfig.MAPPER_LOCATION));
        return factoryBean.getObject();
    }


    @Bean(name = "postgreSqlTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("postgreSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
