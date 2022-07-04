package com.waner.pd.dozer;

import com.github.dozermapper.core.Mapper;
import org.springframework.context.annotation.Bean;

/**
 * Created by Ale on 2022/7/1
 */
public class DozerAutoConfiguration {

    @Bean
    public DozerUtils getDozerUtils(Mapper mapper) {
        DozerUtils dozerUtils = new DozerUtils(mapper);
        return dozerUtils;
    }
}
