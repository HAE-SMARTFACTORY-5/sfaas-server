package com.hae5.sfaas.common.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.hae5.sfaas.*")
public class MapperSessionConfig {

    @Value("${mybatis.config-locations}")
    private String CONFIG_LOCATION;

    @Value("${mybatis.mapper-locations}")
    private String MAPPER_LOCATIONS;

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setVfs(SpringBootVFS.class);
        Resource[] res = new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATIONS);
        sessionFactory.setMapperLocations(res);
        Resource configRes = new PathMatchingResourcePatternResolver().getResource(CONFIG_LOCATION);
        sessionFactory.setConfigLocation(configRes);
        return sessionFactory.getObject();
    }

}