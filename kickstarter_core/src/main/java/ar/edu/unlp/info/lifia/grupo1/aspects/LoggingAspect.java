package ar.edu.unlp.info.lifia.grupo1.aspects;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;


@Aspect
public class LoggingAspect {
	
	private static Logger logger = Logger.getLogger(LoggingAspect.class);
	
	/**
	 * Logs any exception thrown from a service.
	 * Outputs exception name, thrower method, method arguments and full method specification
	 * @param joinPoint
	 * @param error
	 */
	@AfterThrowing(
			pointcut = "execution(* ar.edu.unlp.info.lifia.grupo1.services.interfaces.*.*(..))",
			throwing= "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
		Signature signature = joinPoint.getSignature();
	    String methodName = signature.getName();
	    String methodSpecification = signature.toString();
	    String arguments = Arrays.toString(joinPoint.getArgs());
	    
		logger.error(
				"\n*************************************************************" +
				"\nEXCEPTION CAUGHT: " + error +
				"\nThrower method: " + methodName + 
				"\nWith arguments: " + arguments + 
				"\nFull method specification: " + methodSpecification + 
				"\n*************************************************************");
    }
}
