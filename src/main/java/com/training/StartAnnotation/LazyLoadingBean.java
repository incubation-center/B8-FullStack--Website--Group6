package com.training.StartAnnotation;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class LazyLoadingBean {

	public LazyLoadingBean() {
		System.out.println("LazyLoadingBean object created ..");
	}
}
