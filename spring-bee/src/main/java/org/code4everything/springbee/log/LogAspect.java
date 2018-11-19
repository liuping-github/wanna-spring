package org.code4everything.springbee.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.code4everything.boot.constant.StringConsts;
import org.code4everything.boot.log.AopLogUtils;
import org.code4everything.boot.service.LogService;
import org.code4everything.springbee.SpringBeeApplication;
import org.code4everything.springbee.domain.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author pantao
 * @since 2018/10/13
 **/
@Aspect
@Component
public class LogAspect {

    private final LogService<Log> logLogService;

    private final HttpServletRequest request;

    @Autowired
    public LogAspect(LogService<Log> logLogService, HttpServletRequest request) {
        this.logLogService = logLogService;
        this.request = request;
    }

    @Pointcut("@annotation(org.code4everything.boot.annotations.AopLog)")
    public void serviceAspect() {
        // do nothing
    }

    /**
     * 记录用户操作
     *
     * @param joinPoint {@link JoinPoint}
     */
    @Before("serviceAspect()")
    public void doBefore(JoinPoint joinPoint) {
        doAfterThrowing(joinPoint, null);
    }

    @AfterThrowing(pointcut = "serviceAspect()", throwing = "throwable")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable throwable) {
        if (Boolean.TRUE.equals(SpringBeeApplication.getBeeConfigBean().getShouldSaveLog())) {
            // 定义缓存键
            String key = request.getHeader(StringConsts.TOKEN) + Thread.currentThread().getId();
            AopLogUtils.saveLog(logLogService, key, joinPoint, throwable);
        }
    }
}
