package org.bambrikii.examples.mocking;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.mockStatic;

@Slf4j
public class MockTest {
    @Test
    public void shouldMockClass() {
        // given
        try (var myStaticUtilsMockedStatic = mockStatic(MyStaticUtils.class)) {
            myStaticUtilsMockedStatic
                    .when(() -> MyStaticUtils.method1(eq("str1")))
                    .then((Answer<String>) invocationOnMock -> "str2");

            myStaticUtilsMockedStatic
                    .when(() -> MyStaticUtils.method1(eq("str2")))
                    .thenCallRealMethod();

            // when
            assertThat(MyStaticUtils.method1("str1"))
                    .as("should be equal")
                    .isEqualTo("str2");

            assertThat(MyStaticUtils.method1("str2"))
                    .as("should be equal")
                    .isEqualTo("result=str2");
        }
    }

    @Test
    public void shouldMockConstructor() {
        // given
        try (var cls1MockedConstructor = mockConstruction(Cls1.class, (cls1, context) -> {
            List<?> arguments = context.arguments();
            log.info("arguments: " + arguments);
        })) {
            var list = cls1MockedConstructor.constructed();

            // when
            log.info("list " + list);
            log.info("cls1 " + new Cls1("str1"));
        }
    }


    static class Cls1 {
        private final String arg1;

        public Cls1(String arg1) {
            this.arg1 = arg1;
        }
    }
}
