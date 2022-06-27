package com.fastcampus.ch3;

import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component("engine") class Engine {}	// <bean id="engine" class="com.fastcampus.ch3.Engine" />
@Component class SuperEngine extends Engine {}
@Component class TurboEngine extends Engine {}
@Component class Door {}
@Component
class Car {
	@Value("red") String color;
	@Value("100") int oil;
//	@Autowired		// byType
//	@Qualifier("superEngine")
	@Resource(name="superEngine")	// byName - 이름으로만 찾는다.
	Engine engine;	// byType - 1. 타입으로 먼저 검색, 2. 여러개면 이름으로 검색 - engine, superEngine, turboEngine
	@Autowired Door[] doors;

	public Car() {}		// 기본 생성자는 필수로 만들어준다.
	public Car(String color, int oil, Engine engine, Door[] doors) {
		super();
		this.color = color;
		this.oil = oil;
		this.engine = engine;
		this.doors = doors;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getOil() {
		return oil;
	}

	public void setOil(int oil) {
		this.oil = oil;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public Door[] getDoors() {
		return doors;
	}

	public void setDoors(Door[] doors) {
		this.doors = doors;
	}

	@Override
	public String toString() {
		return "Car [color=" + color + ", oil=" + oil + ", engine=" + engine + ", doors=" + Arrays.toString(doors)
				+ "]";
	}
}

public class SpringDiTest {
	public static void main(String[] args) {
		ApplicationContext ac = new GenericXmlApplicationContext("config.xml");

//		Car car = (Car) ac.getBean("car");			// byName 아래와 같은 문장
		Car car = ac.getBean("car", Car.class); // byName
		Car car2 = (Car) ac.getBean(Car.class); // byType
		
//		Engine engine = (Engine) ac.getBean("engine");	// byName
//		Engine engine = (Engine) ac.getBean(Engine.class);	// byType - 같은 타입이 3개여서 에러가 난다.
//		Engine engine = (Engine) ac.getBean("superEngine");	// byName
//		Door door = (Door) ac.getBean("door");
		
//		car.setColor("red");
//		car.setOil(100);
//		car.setEngine(engine);
//		car.setDoors(new Door[] { (Door) ac.getBean("door"), ac.getBean("door", Door.class) });

		System.out.println("car = " + car);
		
//		System.out.println("engine = " + engine);

	}

}
