package com.nealma.framework.aspect;

import com.nealma.framework.annocation.SystemDaoLayerLog;
import com.nealma.framework.annocation.SystemServiceLayerLog;
import com.nealma.framework.annocation.SystemWebLayerLog;
import com.nealma.framework.exception.SensitiveWordsExecption;
import com.nealma.framework.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * Created by neal.ma on 5/25/16.
 */
@Aspect
@Component
@EnableAspectJAutoProxy
public class SystemLogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemLogAspect.class);

    @Pointcut("@annotation(com.nealma.framework.annocation.SystemWebLayerLog)")
    public void webLayer() {

    }

    @Pointcut("@annotation(com.nealma.framework.annocation.SystemServiceLayerLog)")
    public void serviceLayer() {

    }

    @Pointcut("@annotation(com.nealma.framework.annocation.SystemDaoLayerLog)")
    public void daoLayer() {

    }

    @Before("webLayer()")
    public void doBeforeForWeb(JoinPoint joinPoint) throws SensitiveWordsExecption {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userinfo");
        //请求的IP
        String ip = request.getRemoteAddr();
        Object[] os = joinPoint.getArgs();
        if (os != null) {
            for (Object o : os) {
                LOGGER.debug("o = {}", o);
            }
        }
        try {
            getWebLayerMethodDescription(joinPoint);
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    @Before("daoLayer()")
    public void doBeforeForDao(JoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //请求的IP
        String ip = request.getRemoteAddr();
        Object[] os = joinPoint.getArgs();
        if (os != null) {
            for (Object o : os) {
                LOGGER.debug("o = {}", o);
            }
        }
        try {
            getDaoMthodDescription(joinPoint);
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    /**
     * 异常通知 用于拦截service层记录异常日志
     *
     * @param joinPoint
     * @param e
     */
//    @AfterThrowing(pointcut = "serviceLayer()", throwing = "e")
//    public synchronized void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpSession session = request.getSession();
//        //读取session中的用户
//        User user = (User) session.getAttribute("userinfo");
//        //获取请求ip
//        String ip = request.getRemoteAddr();
//        Object[] os = joinPoint.getArgs();
//        if (os != null) {
//            for (Object o : os) {
//                LOGGER.debug("o = {}", o);
//            }
//        }
//        try {
//            getServiceMthodDescription(joinPoint);
//        } catch (Exception e1) {
////            e1.printStackTrace();
//        }
//    }

    /**
     * 获取注解中对方法的描述信息 用于web层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static String getWebLayerMethodDescription(JoinPoint joinPoint) throws Exception {
        LOGGER.info("[ WebLayer ]");
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemWebLayerLog.class).description();
                    break;
                }
            }
        }
        LOGGER.info("[ Web Layer ] : {}", description);
        return description;
    }

    /**
     * 获取注解中对方法的描述信息 用于Service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static String getServiceMthodDescription(JoinPoint joinPoint)
            throws Exception {
        LOGGER.info("[ ServiceLayer ]");
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemServiceLayerLog.class).description();
                    break;
                }
            }
        }
        LOGGER.info("[ ServiceLayer ] : {}", description);
        return description;
    }

    /**
     * 获取注解中对方法的描述信息 用于Dao层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static String getDaoMthodDescription(JoinPoint joinPoint)
            throws Exception {
        LOGGER.info("[ DaoLayer ]");
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemDaoLayerLog.class).description();
                    break;
                }
            }
        }

//        DataSource annotation = o.getClass().getDeclaredMethod(methodName, joinPoint.getSignature().).getAnnotation(DataSource.class);
//        if (null!=annotation) {
//            DataSourceContextHolder.setDataSourceType(annotation.name());
//        }else{
//            annotation=o.getClass().getAnnotation(DataSource.class);
//            if(null!=annotation){
//                DataSourceContextHolder.setDataSourceType(annotation.name());
//            }
//        }
        LOGGER.info("[ DaoLayer ] : {}", description);
        return description;
    }
}
