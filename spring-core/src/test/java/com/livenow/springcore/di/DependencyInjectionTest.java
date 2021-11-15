package com.livenow.springcore.di;

import com.livenow.springcore.SpringCoreApplication;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class DependencyInjectionTest {
    /**
     * TODO: 생성자 주입을 통해 StationConstructorService에 StationRepository 의존성을 주입하기
     */
    @Test
    void constructorInjection() {
        ApplicationContext context = getApplicationContext();
        MemberConstructorService service = context.getBean("memberConstructorService", MemberConstructorService.class);
        assertThat(service.sayGump()).isEqualTo("Gump");
    }

    /**
     * TODO: 필드 주입을 통해 StationConstructorService에 StationRepository 의존성을 주입하기
     */
    @Test
    void autowiredInjection() {
        ApplicationContext context = getApplicationContext();
        MemberFieldService service = context.getBean("memberFieldService", MemberFieldService.class);
        assertThat(service.sayGump()).isEqualTo("Gump");
    }

    /**
     * TODO: setter 주입을 통해 StationConstructorService에 StationRepository 의존성을 주입하기
     */
    @Test
    void setterInjection() {
        ApplicationContext context = getApplicationContext();
        MemberSetterService service = context.getBean("memberSetterService", MemberSetterService.class);
        assertThat(service.sayGump()).isEqualTo("Gump");
    }

    /**
     * HelloApplication > @SpringBootApplication 설정을 통해 이미 ComponentScan 설정되어있음
     */
    private ApplicationContext getApplicationContext() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringCoreApplication.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println("beanDefinitionNames: " + Arrays.toString(beanDefinitionNames));
        return context;
    }
}
