package com.lewei.production.web;

import com.lewei.production.annotation.SystemLog;
import com.lewei.production.model.User;
import com.lewei.production.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 日志切面
 *
 * 
 */
@Aspect
public class LogAspect {

	@Resource
	private LogService logService;

//	 and !execution(* com.szsdn.wm.controller.admin.LogController.*(..))
	@After("execution(* com.szsdn.wm.controller..*(..)) && !execution(* com.szsdn.wm.controller.customer..*(..))")
	public void after(JoinPoint joinPoint) throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
//		if (request.getSession().getAttribute("user") instanceof CUser) {
//			request.getSession().removeAttribute("user");
//		}
//		Enumeration params = request.getParameterNames();
		User user = (User) request.getSession().getAttribute("user");
		String description= getControllerMethodDescription(joinPoint);
		String module= getControllerMethodModule(joinPoint);
		if(module != null){
			logService.save(user.getId(), description, module,joinPoint.getSignature().getName(),request.getRequestURI());
		}
	}

	/**
	 * 
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public static String getControllerMethodDescription(JoinPoint joinPoint)
			throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class<?> targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";

		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class<?>[] params = method.getParameterTypes();
				if (params.length == arguments.length) {
					SystemLog log = method
							.getAnnotation(SystemLog.class);
					if (log != null) {
						description = log.description();
					}
					break;
				}
			}
		}
		return description;
	}
	
	/**
	 * 
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public static String getControllerMethodModule(JoinPoint joinPoint)
			throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class<?> targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String module = null;

		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class<?>[] params = method.getParameterTypes();
				if (params.length == arguments.length) {
					SystemLog log = method
							.getAnnotation(SystemLog.class);
					if (log != null) {
						module = log.module()[0].getValue();
					}
					break;
				}
			}
		}
		return module;
	}
}
