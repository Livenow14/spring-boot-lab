package com.livenow.springcore.scan;

import com.livenow.springcore.SpringCoreApplication;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberFieldService {
    /**
     * TODO: core > scan 패키지 내에 있는 클래스를 스프링 빈으로 등록하기
     */
    @Test
    void componentScan() {
        ApplicationContext context = getApplicationContext();
        TeamDao dao = context.getBean("teamDao", TeamDao.class);
        assertThat(dao).isNotNull();

        TeamService service = context.getBean("teamService", TeamService.class);
        assertThat(service).isNotNull();
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
