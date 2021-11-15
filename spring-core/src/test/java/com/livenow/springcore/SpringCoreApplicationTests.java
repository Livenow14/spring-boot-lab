package com.livenow.springcore;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SpringCoreApplicationTests {

    @Test
    void contextLoads() {
        List<String> deployProfiles = Arrays.asList("deploy1", "deploy2");
        deployProfiles.toArray(String[]::new);
    }

}
