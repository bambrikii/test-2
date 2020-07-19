package org.bambrikii.examples.spring.lifecycle;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ //
		MyBeanFactoryPostProcessor.class, //
		MySampleBean.class, //
		MyBeanPostProcessor.class, //
})
public class SpringLifecycleMain {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringLifecycleMain.class, args);
		ExitCodeGenerator exitCodeGenerators = new ExitCodeGenerator() {

			@Override
			public int getExitCode() {
				return 0;
			}
		};
		SpringApplication.exit(context, exitCodeGenerators);
	}
}
