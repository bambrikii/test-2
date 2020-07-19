package org.bambrikii.examples.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.weaving.LoadTimeWeaverAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.stereotype.Service;

/**
 * https://howtodoinjava.com/spring-core/spring-bean-life-cycle/
 */
@Service
public class MyAwareBean implements ApplicationContextAware, //
		ApplicationEventPublisherAware, //
		BeanClassLoaderAware, //
		BeanFactoryAware, //
		BeanNameAware, //
		LoadTimeWeaverAware, //
		MessageSourceAware, //
		NotificationPublisherAware, //
		ResourceLoaderAware //
{

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoadTimeWeaver(LoadTimeWeaver loadTimeWeaver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBeanName(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub

	}

}
