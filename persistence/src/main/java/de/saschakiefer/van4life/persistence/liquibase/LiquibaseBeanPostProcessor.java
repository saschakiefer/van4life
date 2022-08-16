package de.saschakiefer.van4life.persistence.liquibase;

import java.util.function.Consumer;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import liquibase.integration.spring.SpringLiquibase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LiquibaseBeanPostProcessor implements BeanPostProcessor {

	private final Consumer<SpringLiquibase> consumer;

	@Override
	public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
		if (bean instanceof SpringLiquibase) {
			consumer.accept((SpringLiquibase) bean);
		}
		return bean;
	}
}
