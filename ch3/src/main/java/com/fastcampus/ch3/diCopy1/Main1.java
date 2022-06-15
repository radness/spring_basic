package com.fastcampus.ch3.diCopy1;

import java.io.FileReader;
import java.util.Properties;

public class Main1 {
	public static void main(String[] args) throws Exception {
		Car car = getCar();
		System.out.println("car = " + car);
		
		Car car2 = (Car)getObject("car");
		System.out.println("car2 = " + car2);
		
		Engine engine = (Engine)getObject("engine");
		System.out.println("engine = " + engine);
	}

	static Car getCar() throws Exception {
		Properties p = new Properties();
		p.load(new FileReader("config.txt"));

		Class clazz = Class.forName(p.getProperty("car"));

		return (Car)clazz.newInstance();
	}
	
	static Object getObject(String key) throws Exception {
		Properties p = new Properties();
		p.load(new FileReader("config.txt"));

		Class clazz = Class.forName(p.getProperty(key));

		return clazz.newInstance();
	}
}

class Car {}
class Truck extends Car {}
class SportsCar extends Car {}
class Engine {}