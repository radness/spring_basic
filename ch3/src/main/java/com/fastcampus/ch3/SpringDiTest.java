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
	@Resource(name="superEngine")	// byName - �̸����θ� ã�´�.
	Engine engine;	// byType - 1. Ÿ������ ���� �˻�, 2. �������� �̸����� �˻� - engine, superEngine, turboEngine
	@Autowired Door[] doors;

	public Car() {}		// �⺻ �����ڴ� �ʼ��� ������ش�.
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

//		Car car = (Car) ac.getBean("car");			// byName �Ʒ��� ���� ����
		Car car = ac.getBean("car", Car.class); // byName
		Car car2 = (Car) ac.getBean(Car.class); // byType
		
//		Engine engine = (Engine) ac.getBean("engine");	// byName
//		Engine engine = (Engine) ac.getBean(Engine.class);	// byType - ���� Ÿ���� 3������ ������ ����.
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
