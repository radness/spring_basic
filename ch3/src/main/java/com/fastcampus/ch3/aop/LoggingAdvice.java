package com.fastcampus.ch3.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAdvice {
	@Around("execution(* com.fastcampus.ch3.aop.MyMath.add*(..))")	// pointcut - 부가 기능이 담긴 메서드 작성
	public Object methodCallLog(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		System.out.println("<<[start] " + pjp.getSignature().getName() + Arrays.toString(pjp.getArgs()));
		
		Object result = pjp.proceed(); // target의 메서드를 호출

		System.out.println("result = " + result);
		System.out.println("[end]>> " + (System.currentTimeMillis() - start) + "ms");
		
		return result;
	}
}
