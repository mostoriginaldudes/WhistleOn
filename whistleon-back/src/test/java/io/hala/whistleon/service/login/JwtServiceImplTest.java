package io.hala.whistleon.service.login;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;



class JwtServiceImplTest {

    @Test
    void saltValueTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(MockBean.class);
        MockBean mockBean = ac.getBean(MockBean.class);
        Assertions.assertThat(mockBean.saltValue).isNotNull();
    }

    @Component
    @PropertySource("classpath:application-dev.properties")
    static class MockBean{
        @Value("${jwt.salt.value}")
        public String saltValue;
    }


}
