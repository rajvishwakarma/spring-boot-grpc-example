package com.sample.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MethodAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodAspect.class);

	public static final String POINTCUT_MAPPER = "execution(* com.sample.*.*.*(..))";

	@Pointcut(POINTCUT_MAPPER)
	protected void loggingOperation(){
	}


	@Around("loggingOperation()")
	public Object aroundController(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		String name = signature.getDeclaringTypeName()+"."+signature.getName();
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		Object result = joinPoint.proceed();
		stopWatch.stop();
		LOGGER.info("Method "+name+" execution lasted:"+stopWatch.getLastTaskTimeMillis()+" ms");
		return result;
	}

	@AfterReturning(pointcut = "loggingOperation()",returning = "result")
	public void afterReturningController(JoinPoint joinPoint, Object result) {
		LOGGER.info("+++ Result +++");
	}

	@AfterThrowing(pointcut = "loggingOperation()", throwing = "error")
	public void afterThrowingController(JoinPoint joinPoint, Throwable error) {
		LOGGER.info("Exception : " + error);
		LOGGER.info("--- ERROR ---");
	}
}