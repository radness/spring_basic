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

// Ŭ�������� �ڹ� Bean���� ���
@Component class Car {}
@Component class Truck extends Car {}
@Component class SportsCar extends Car {}
@Component class Engine {}

class AppContext {
	Map map; // ��ü �����

	public AppContext() {
		map = new HashMap();
		doComponentScan();
	}

	// @Component ������̼��� ���� ��ü���� ����
	private void doComponentScan() {
		// 1. ��Ű������ Ŭ���� ����� �����´�.
		try {
			ClassLoader classLoader = AppContext.class.getClassLoader();
			ClassPath classPath = ClassPath.from(classLoader);

			Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.fastcampus.ch3.diCopy3");

			// 2. �ݺ������� ũ������ �ϳ��� �о�ͼ� @Component�� �پ��ִ��� Ȯ��
			for (ClassPath.ClassInfo classInfo : set) {
				Class clazz = classInfo.load();
				Component component = (Component) clazz.getAnnotation(Component.class);

				// 3. @Component�� �پ������� ��ü�� �����ϼ� map�� ����
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