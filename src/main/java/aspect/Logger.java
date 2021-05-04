package aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {
    //@AfterReturning: Sau moi hanh dong return cua cac ham trong con troller thi no se join va Class Logger.
    @AfterReturning(pointcut = "within(controller.*)", returning = "result")
    public void log(JoinPoint joinPoint,Object result){
        System.out.println("===================> START LOG <===========================");
        String nameClass = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        System.out.println(nameClass+"."+method);
        System.out.println(result.toString());
    }
}
