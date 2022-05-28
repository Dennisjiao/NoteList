package main.java.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
 
@Aspect
public class TrackCounter {
	
        
    //定义切点
    @Pointcut("execution(* main.java.bean.CompactDisc.playTrack(int)) && args(trackNumber)")
    public void trackPlayed(int trackNumber){}
 
  //在切点前执行
    @Before("trackPlayed(trackNumber)")
    public void before(int trackNumber){
        System.out.println("Before Advice,Track id:"+trackNumber);        
    }
    //在切点后执行
    @After("trackPlayed(trackNumber)")
    public void after(int trackNumber){
        System.out.println("After Advice,Track id:"+trackNumber);  
        System.out.println("\n");
    }
  //定义切点
    @Pointcut("execution(* main.java.bean.CompactDisc.play(..))")
    public void play(){}
    
    //环绕通知
    @Around("play()")
    public void playAdvice(ProceedingJoinPoint jp)
    {
    	try{
    		System.out.println("Around advice:before method called");
    		jp.proceed();
    		System.out.println("Around advice:after method called");
    	}
    	catch(Throwable e)
    	{
    		System.out.println("Around advice error");
    	}
    }
    
    //定义切点
    @Pointcut("execution(* main.java.bean.CompactDisc.throwExceptionTest(..))")
    public void throwExceptionTest(){}
    
    @AfterThrowing("throwExceptionTest()")
    public void afterThrowingAdvice(JoinPoint point){
    	String methodName = point.getSignature().getName();
    	System.out.println("AfterThrowing is triggered by method "+methodName);
    }

}