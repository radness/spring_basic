package com.fastcampus.app;


import java.lang.reflect.Method;

public class PrivateMethodCall {
	public static void main(String[] args) throws Exception {
//		Hello hello = new Hello();
//		hello.main();	// private 라서 외부 호출 불가

		// Reflection API를 사용 - 클래스 정보를 얻고 다룰 수 있는 강력한 기능 제공
		// java.lang.reflect 패키지 사용
		Class helloClass = Class.forName("com.fastcampus.ch2.Hello");
		Hello hello = (Hello) helloClass.newInstance(); // Class 객체가 가진 정보로 객체 생성
		Method main = helloClass.getDeclaredMethod("main");
		main.setAccessible(true); // private 인 main() 메서드를 호출가능하게 한다.

		main.invoke(hello);

	}
}
