package phieulong.springbootsample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import phieulong.springbootsample.controller.Home;

@SpringBootTest
class SpringbootSampleApplicationTests {

    @Autowired
    private Home home;


    @Test
    void test_dependency_injection() {
        assert home != null;
    }
}
