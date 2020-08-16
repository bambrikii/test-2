package org.bambrikii.examples.spring.aspect;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {MyAspectConfig.class})
public class MyAspectTest {
    @Autowired
    private MyAspectBean myAspectBean;

    @Test
    public void test1() {
        myAspectBean.shouldThrow();
    }
}
