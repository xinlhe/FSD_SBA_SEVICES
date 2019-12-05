package net.bluefsd.comm.log;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogPointCut {

	@Pointcut("execution(public * net.bluefsd..controller.*.*(..))") // the pointcut expression
	public void log() {
	}

}
