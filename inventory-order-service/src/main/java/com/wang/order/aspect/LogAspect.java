package com.wang.order.aspect;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LogAspect {
    public LogAspect() {

        System.out.println("Common LogAspect");
    }

    private final static Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 定义一个切点
     */
    @Pointcut("execution(public * com.wang..*Controller.*(..))")//所有com子包下所有的controller的所有方法
    public void controllerPointcut() {
    }

    @Before("controllerPointcut()")
    public void doBefore(JoinPoint joinPoint) {
        //日志流水号
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        MDC.put("LOG_ID", String.valueOf(snowflake));
    }
}