package com.SySTomateAlgo.TomateAlgo.Config;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.hibernate.Hibernate;
import org.springframework.context.annotation.Bean;

public class JacksonConfig {
    @Bean
    public Hibernate5Module hibernate5Module(){
        Hibernate5Module module = new Hibernate5Module();

        module.disable(Hibernate5Module.Feature.FORCE_LAZY_LOADING);
        return module;
    }
}
