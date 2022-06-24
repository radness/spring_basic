package com.fastcampus.ch3.diCopy3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.google.common.reflect.ClassPath;

public class Main3 {
	public static void main(String[] args) throws Exception {
		AppContext ac = new AppContext();
		Car car = (Car) ac.getBean("car");
		Engine engine = (Engine) ac.getBean("engine");
		System.out.println("car = " + car);
		System.out.println("engine = " + engine);
	}
}

// 클래스들을 자바 Bean으로 등록
@Component class Car {}
@Component class Truck extends Car {}
@Component class SportsCar extends Car {}
@Component class Engine {}

class AppContext {
	Map map; // 객체 저장소

	public AppContext() {
		map = new HashMap();
		doComponentScan();
	}

	// @Component 어노테이션이 붙은 객체들을 저장
	private void doComponentScan() {
		// 1. 패키지내의 클래스 목록을 가져온다.
		try {
			ClassLoader classLoader = AppContext.class.getClassLoader();
			ClassPath classPath = ClassPath.from(classLoader);

			Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.fastcampus.ch3.diCopy3");

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

	Object getBean(String key) {
		return map.get(key);
	}
}