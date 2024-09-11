package com.xazhao.pay.aspect;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.xazhao.pay.constant.PayConventions;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Spring Aop，定义切面，支持支付前置/后置操做
 *
 * @Description Created on 2024/08/28.
 * @Author Zhao.An
 */

@Slf4j
@Aspect
@Component
public class PaySurroundAspect implements PayConventions {

    /**
     * 配置切入点，@Pointcut切入点表达式，使用@PayAspect注解标识的方法都会进行切入，也可以用通配符配置具体切入的方法名
     */
    @Pointcut("@annotation(com.xazhao.pay.annotation.PayAspect)")
    public void payAspectPointCut() {
    }

    /**
     * 环绕通知，
     *
     * @param joinPoint 切入点
     * @return Object
     * @throws Throwable Throwable
     */
    @Around("payAspectPointCut()")
    public Object interceptorSurround(ProceedingJoinPoint joinPoint) throws Throwable {

        String payType = null;
        // 方法参数
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            Map<String, Object> argMap = BeanUtil.beanToMap(arg);
            payType = String.valueOf(argMap.get(PAY_TYPE));
        }

        log.info("Aspect环绕通知开始，支付平台为 {}.", payType);

        // 连接点返回结果
        Object result = null;

        // 前置通知方法
        beforePay(joinPoint);

        try {
            log.info("开始处理PayAspect注解所标注的目标方法.");

            // 执行目标方法，即执行使用@PayAspect注解标注的方法
            result = joinPoint.proceed();

            log.info("处理目标方法完毕，PayAspect所在标注位置为 {} 类中 {} 方法.",
                    joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());

        } catch (Throwable e) {
            // 执行异常通知方法
            afterThrowingPay(joinPoint, e);

        }

        // 后置通知方法
        afterPay(joinPoint);

        return result;
    }

    /**
     * 前置通知
     *
     * @param joinPoint 目标类连接点对象
     */
    private void beforePay(JoinPoint joinPoint) {

        String argJson = getArgsJson(joinPoint);

        log.info("{} 方法前置通知，Json参数为 {}.", joinPoint.getSignature().getName(), argJson);
    }

    /**
     * 后置通知
     *
     * @param joinPoint 目标类连接点对象
     */
    private void afterPay(JoinPoint joinPoint) {

        String argJson = getArgsJson(joinPoint);

        log.info("{} 方法后置通知，Json参数为 {}.", joinPoint.getSignature().getName(), argJson);

        log.info("Aspect环绕通知结束.\n");
    }

    /**
     * 后置异常通知
     *
     * @param joinPoint 目标类连接点对象
     * @param throwable 异常信息
     */
    private void afterThrowingPay(JoinPoint joinPoint, Throwable throwable) {

        String argJson = getArgsJson(joinPoint);

        log.info("{} 方法后置异常通知，Json参数为 {}.", joinPoint.getSignature().getName(), argJson);
    }

    /**
     * 获取目标方法参数
     *
     * @param joinPoint 目标类连接点对象
     * @return Json类型的参数
     */
    private static String getArgsJson(JoinPoint joinPoint) {
        String argJson = null;
        // 方法参数
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            Map<String, Object> argMap = BeanUtil.beanToMap(arg);
            argJson = JSONUtil.toJsonStr(argMap);
        }

        return argJson;
    }
}
