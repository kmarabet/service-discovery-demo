package com.altoros.config;

import com.altoros.ApplicationInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
//@PropertySource("classpath:config.properties")
//todo remove if not needed but coud be needed if ApplicationInfo.artifactId set is implemented during start time
public class CommonConfig {

    /*@Bean
    public ApplicationInfo applicationInfo(){
        return new ApplicationInfo();
    }*/
}