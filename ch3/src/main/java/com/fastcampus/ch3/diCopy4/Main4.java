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
		Car car = (Car) ac.getBean("car"); // byName���� ��ü�� �˻�
		Door door = (Door) ac.getBean(Door.class); // byType���� ��ü�� �˻�
		Engine engine = (Engine) ac.getBean("engine");

		// �������� ��ü�� ����
//		car.engine = engine;
//		car.door = door;

		System.out.println("car = " + car);
		System.out.println("door = " + door);
		System.out.println("engine = " + engine);
	}
}

// Ŭ�������� �ڹ� Bean���� ���
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
	Map map; // ��ü �����

	public AppContext() {
		map = new HashMap();
		doComponentScan();
		doAutowired();
	}

	private void doAutowired() {
		/* map�� ����� ��ü�� iv(instance variable)�߿� @Autowired�� �پ� ������
		 * map���� iv�� Ÿ�Կ� �´� ��ü�� ã�Ƽ� ����(��ü�� �ּҸ� iv�� ����)
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

	// @Component ������̼��� ���� ��ü���� ����
	private void doComponentScan() {
		// 1. ��Ű������ Ŭ���� ����� �����´�.
		try {
			ClassLoader classLoader = AppContext.class.getClassLoader();
			ClassPath classPath = ClassPath.from(classLoader);

			Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.fastcampus.ch3.diCopy4");

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