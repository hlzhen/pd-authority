package com.waner.pd.xss;

import com.waner.pd.xss.converter.XssStringJsonDeserializer;
import com.waner.pd.xss.filter.XssFilter;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * XSS 跨站攻击自动配置
 */
@EnableConfigurationProperties(XssConfigurationProperties.class)
public class XssAuthConfiguration {

    private XssConfigurationProperties xssConfigurationProperties;

    public XssAuthConfiguration(XssConfigurationProperties xssConfigurationProperties) {
        this.xssConfigurationProperties = xssConfigurationProperties;
    }


    /**
     * 配置跨站攻击 反序列化处理器
     * @return
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer2() {
        return builder -> builder
                .deserializerByType(String.class, new XssStringJsonDeserializer(xssConfigurationProperties));
    }


    /**
     * 配置跨站攻击过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean(new XssFilter());
        filterRegistration.addUrlPatterns("/*");
        filterRegistration.setOrder(xssConfigurationProperties.getFilterOrder());

        Map<String, String> initParameters = new HashMap<>(2);
        String excludes = StringUtils.join(xssConfigurationProperties.getExcludeUrl().toArray(), ",");
        initParameters.put("excludes", excludes);
        initParameters.put("isIncludeRichText", xssConfigurationProperties.getIncludeRichText());
        filterRegistration.setInitParameters(initParameters);
        return filterRegistration;
    }
}
