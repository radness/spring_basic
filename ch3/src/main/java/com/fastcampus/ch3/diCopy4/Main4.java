package com.fastcampus.ch3.diCopy4;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.google.common.reflect.ClassPath;

public class Main4 {
	public static void main(String[] args) throws Exception {
		AppContext ac = new AppContext();
		Car car = (Car) ac.getBean("car"); // byName으로 객체를 검색
		Door door = (Door) ac.getBean(Door.class); // byType으로 객체를 검색
		Engine engine = (Engine) ac.getBean("engine");

		// 수동으로 객체를 연결
//		car.engine = engine;
//		car.door = door;

		System.out.println("car = " + car);
		System.out.println("door = " + door);
		System.out.println("engine = " + engine);
	}
}

// 클래스들을 자바 Bean으로 등록
@Component
class Car {
	@Autowired
	Engine engine;
	@Autowired
	Door door;

	@Override
	public String toString() {
		return "Car [engine=" + engine + ", door=" + door + "]";
	}

}

@Component
class Truck extends Car {
}

@Component
class SportsCar extends Car {
}

@Component
class Engine {
}

@Component
class Door {

}

class AppContext {
	Map map; // 객체 저장소

	public AppContext() {
		map = new HashMap();
		doComponentScan();
		doAutowired();
	}

	private void doAutowired() {
		/* map에 저장된 객체의 iv(instance variable)중에 @Autowired가 붙어 있으면
		 * map에서 iv의 타입에 맞는 객체를 찾아서 연결(객체의 주소를 iv에 저장)
		*/
		try {
			for (Object bean : map.values()) {
				for (Field field : bean.getClass().getDeclaredFields()) {
					if (field.getAnnotation(Autowired.class) != null)	// byType
						field.set(bean, getBean(field.getName()));	// car.engine = obj;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Component 어노테이션이 붙은 객체들을 저장
	private void doComponentScan() {
		// 1. 패키지내의 클래스 목록을 가져온다.
		try {
			ClassLoader classLoader = AppContext.class.getClassLoader();
			ClassPath classPath = ClassPath.from(classLoader);

			Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.fastcampus.ch3.diCopy4");

			// 2. 반복문으로 크랠스를 하나씩 읽어와서 @Component이 붙어있는지 확인
			for (ClassPath.ClassInfo classInfo : set) {
				Class clazz = classInfo.load();
				Component component = (Component) clazz.getAnnotation(Component.class);

				// 3. @Component가 붙어있으면 객체를 생성하서 map에 저장
				if (component != null) {
					String id = StringUtils.uncapitalize(classInfo.getSimpleName());
					map.put(id, clazz.newInstance());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	Object getBean(String key) { // byName
		return map.get(key);
	}

	Object getBean(Class clazz) { // byType
		for (Object obj : map.values()) {
			if (clazz.isInstance(obj)) {
				return obj;
			}
		}

		return null;
	}
}