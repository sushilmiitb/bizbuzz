package com.bizbuzz.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bizbuzz.web.RegistrationController;

@Aspect
@Component
public class AspectLogging {
  private static final Logger logger = LoggerFactory.getLogger(AspectLogging.class);

  //  //@Pointcut("within(com.bizbuzz..*)")
  //  @Pointcut("execution(* com.bizbuzz..*(..))")
  //  public void allMethods(){}
  //  



  @Around("execution(* com.bizbuzz.web..*(..)) || execution(* com.bizbuzz.service..*(..)) || execution(* com.bizbuzz.repository..*(..)) || execution(* com.bizbuzz.model..*(..))")
  public Object aroundBizbuzzMethods(ProceedingJoinPoint pjp) throws Throwable {
    String packageName = pjp.getSignature().getDeclaringTypeName();
    String methodName = pjp.getSignature().getName();
    long start = System.currentTimeMillis();
    if(!pjp.getSignature().getName().equals("initBinder")) {
      logger.debug("Entering method [" + packageName + "." + methodName +  "]");
    }
    Object output = pjp.proceed();
    long elapsedTime = System.currentTimeMillis() - start;
    if(!methodName.equals("initBinder")) {
      logger.debug("Exiting method [" + packageName + "." + methodName + "]; exec time (ms): " + elapsedTime);
    }
    return output;
  }


  //  @Before("allMethods()")
  //  public void beforeAdvice(JoinPoint jp) throws Throwable{
  //    logger.debug("Method entered");
  //    //long start = System.currentTimeMillis();
  //    //Object obj = pjp.proceed();
  //    //long elapsedTime = System.currentTimeMillis() - start;
  //    //logger.debug("Method execution time: "+elapsedTime+" ms");
  //    //return obj;
  //  }
  //  
  //  @After("allMethods()")
  //  public void afterAdvice(JoinPoint jp) throws Throwable{
  //    logger.debug("Method exit");
  //  }

}
