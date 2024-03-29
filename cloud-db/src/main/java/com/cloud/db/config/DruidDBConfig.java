package com.cloud.db.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author xujiping
 * @date 2018/6/9 18:50
 */
@Configuration
@EnableTransactionManagement
public class DruidDBConfig {

    protected final Logger logger = LoggerFactory.getLogger(DruidDBConfig.class);

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    /**
     * @return
     * @Bean 声明其为Bean实例
     * @Primary 在同样的DataSource中，首先使用被标注的DataSource
     */
    @Bean
    @Primary
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(url);
        datasource.setDriverClassName(driverClassName);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setInitialSize(5);
        datasource.setMinIdle(5);
        datasource.setMaxActive(100);
        datasource.setMaxWait(60000);
        datasource.setTimeBetweenEvictionRunsMillis(60000);
        datasource.setMinEvictableIdleTimeMillis(300000);
        datasource.setValidationQuery("select 'x'");
        datasource.setValidationQueryTimeout(30000);
        datasource.setTestWhileIdle(true);
        datasource.setTestOnBorrow(false);
        datasource.setTestOnReturn(false);
        datasource.setPoolPreparedStatements(false);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(20);
        try {
            datasource.setFilters("stat,wall,slf4j");
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties("druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000");
        return datasource;
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "xjp32.");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*/assets/*,*.js,*.gif,*.jpg,*.png,*.css,*.ico,*.ttf," +
                "/swagger*,/druid/*");
        return filterRegistrationBean;
    }

}
