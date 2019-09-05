package cl.go.sport.api.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanUtil implements ApplicationContextAware {

	private static ApplicationContext context;
	
	private static void setContext(ApplicationContext applicationContext){
		BeanUtil.context = applicationContext;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		BeanUtil.setContext(applicationContext);
	}
	
	public static <T> T getBean(Class<T> beanClass) {
        return BeanUtil.context.getBean(beanClass);
    }
}
