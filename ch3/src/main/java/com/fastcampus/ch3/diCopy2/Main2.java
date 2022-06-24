package com.fastcampus.ch3.diCopy2;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Main2 {
	public static void main(String[] args) throws Exception {
		AppContext ac = new AppContext();
		Car car = (Car) ac.getBean("car");
		Engine engine = (Engine) ac.getBean("engine");
		System.out.println("car = " + car);
		System.out.println("engine = " + engine);
	}
}

class Car {
}

class Truck extends Car {
}

class SportsCar extends Car {
}

class Engine {
}

class AppContext {
	Map map; // ��ü �����

	public AppContext() {
		try {
//			map = new HashMap();
//			map.put("car", new SportsCar());
//			map.put("engine", new Engine());

			Properties p = new Properties();
			p.load(new FileReader("config.txt"));

			// Properties�� ����� ������ Map�� ����
			map = new HashMap(p);

			for (Object key : map.keySet()) {
				Class clazz = Class.forName((String) map.get(key));
				map.put(key, clazz.newInstance());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	Object getBean(String key) {
		return map.get(key);
	}
}