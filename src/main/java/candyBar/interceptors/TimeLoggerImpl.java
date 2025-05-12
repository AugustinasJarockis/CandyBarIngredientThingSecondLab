package candyBar.interceptors;

import javax.interceptor.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@TimeLogger
@Interceptor
public class TimeLoggerImpl implements Serializable {
    @AroundInvoke
    public Object logging(InvocationContext ctx)
            throws Exception {
        System.out.println("Start: " + LocalDateTime.now());
        Object result = ctx.proceed();
        System.out.println("End: " + LocalDateTime.now());
        return result;
    }
}