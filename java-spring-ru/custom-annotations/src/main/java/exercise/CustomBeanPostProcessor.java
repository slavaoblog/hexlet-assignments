package exercise;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import exercise.calculator.CalculatorImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// BEGIN
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {

    private final Logger LOGGER = LoggerFactory.getLogger(CustomBeanPostProcessor.class);

    @Autowired
    private ApplicationContext applicationContext;

//    private Map<String, String> beans = new HashMap<>();

    private Map<String, Class> annotatedBeans = new HashMap<>();
    private Map<String, String> loggingLevels = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
        throws BeansException {

//        Class<?> clazz = bean.getClass();
//
//        if (clazz.isAnnotationPresent(Inspect.class)) {
//            String level = clazz.getAnnotation(Inspect.class).level();
//
//            System.out.println("found class with inspect! LOGGER level: " + level);
//            beans.put(beanName, level);
//        }
//        return bean;

        if (bean.getClass().isAnnotationPresent(Inspect.class)) {
            String level = bean.getClass().getAnnotation(Inspect.class).level();

            annotatedBeans.put(beanName, bean.getClass());
            loggingLevels.put(beanName, level);
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {

//        if (beans.containsKey(beanName)) {
//            String lvl = beans.get(beanName);
//        return Proxy.newProxyInstance(
//                Inspect.class.getClassLoader(),
//                Inspect.class.getInterfaces(),
//                new InvocationHandler() {
//                    @Override
//                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                        if (lvl.equals("info")) {
//                            LOGGER.info("Was called method: " + method.getName() + " with arguments: " + Arrays.toString(args));
//                        }
//                        else {
//                            LOGGER.info("Was called method: " + method.getName() + " with arguments: " + Arrays.toString(args));
//                        }
//                        return 10;
//                    }
//                }
//        );
//        }
//        return bean;
//    }

        if (!annotatedBeans.containsKey(beanName)) {
            return bean;
        }
        Class beanClass = annotatedBeans.get(beanName);
        String level = loggingLevels.get(beanName);

        return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), (proxy, method, args) -> {
                    String message = String.format(
                            "Was called method: %s() with arguments: %s",
                            method.getName(),
                            Arrays.toString(args)
                    );

                    if (level.equals("info")) {
                        LOGGER.info(message);
                    } else {
                        LOGGER.debug(message);
                    }

                    return method.invoke(bean, args);
                }
        );
    }
}
// END
