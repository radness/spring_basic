package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.*;
import org.springframework.stereotype.*;

import javax.inject.*;
import java.util.*;

@Component
@Scope("prototype")
class Door {}
@Component class Engine {}
@Component class TurboEngine extends Engine {}
@Component class SuperEngine extends Engine {}

@Component
class Car {
    @Value("red") String color;
    @Value("100") int oil;
    @Autowired    Engine engine;
    @Autowired    Door[] doors;

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", oil=" + oil +
                ", engine=" + engine +
                ", doors=" + Arrays.toString(doors) +
                '}';
    }
}

public class ApplicationContextTest {
    public static void main(String[] args) {
        ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
//      Car car = ac.getBean("car", Car.class); // 타입을 지정하면 형변환 안해도됨. 아래의 문장과 동일
        Car car  = (Car) ac.getBean("car"); // 이름으로 빈 검색
        Car car2 = (Car) ac.getBean(Car.class);   // 타입으로 빈 검색
        System.out.println("car = " + car);
//        System.out.println("car2 = " + car2);

    }
}

/* [src/main/resources/config.xml]
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
    <context:annotation-config/>
    <context:component-scan base-package="com.fastcampus.ch3"/>
</beans>
*/

/* [실행결과]
car = Car{color='red', oil=100, engine=com.fastcampus.ch3.Engine@df6620a, doors=[com.fastcampus.ch3.Door@205d38da]}
car2 = Car{color='red', oil=100, engine=com.fastcampus.ch3.Engine@df6620a, doors=[com.fastcampus.ch3.Door@205d38da]}
ac.getBeanDefinitionNames() = [org.springframework.context.annotation.internalConfigurationAnnotationProcessor, org.springframework.context.annotation.internalAutowiredAnnotationProcessor, org.springframework.context.annotation.internalRequiredAnnotationProcessor, org.springframework.context.annotation.internalCommonAnnotationProcessor, org.springframework.context.event.internalEventListenerProcessor, org.springframework.context.event.internalEventListenerFactory, car, door, engine, superEngine, turboEngine]
ac.getBeanDefinitionCount() = 11
ac.containsBeanDefinition("car") = true
ac.containsBean("car") = true
ac.getType("car") = class com.fastcampus.ch3.Car
ac.isSingleton("car") = true
ac.isPrototype("car") = false
ac.isPrototype("door") = true
ac.isTypeMatch("car", Car.class) = true
ac.findAnnotationOnBean("car", Component.class) = @org.springframework.stereotype.Component(value="")
ac.getBeanNamesForAnnotation(Component.class) = [car, door, engine, superEngine, turboEngine]
ac.getBeanNamesForType(Engine.class) = [engine, superEngine, turboEngine]
*/