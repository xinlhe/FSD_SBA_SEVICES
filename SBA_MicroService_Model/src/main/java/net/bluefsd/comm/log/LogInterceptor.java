package net.bluefsd.comm.log;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogInterceptor {
	Logger log = LoggerFactory.getLogger(LogInterceptor.class);

	@Around("net.bluefsd.comm.log.LogPointCut.log()")
	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
		String className = joinPoint.getTarget().getClass().getName();
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		String methodName = method.getName();

		log.debug("Before " + className + "." + methodName + "() is invoked! ");
		Object rval = joinPoint.proceed();
		log.debug("After " + className + "." + methodName + "() is invoked! ");

		return rval;
	}
}