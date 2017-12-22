package cn.com.ssm.admin.component.aop;

import com.ivymei.system.common.web.aop.CommonApiResponseAopHelper;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ResponseBodyAop {

	private Logger log = Logger.getLogger(getClass());

	@Pointcut("@annotation(org.springframework.web.bind.annotation.ResponseBody)")
	public void responseBody() {
		// System.out.println("我是一个切入点");
	}

	@Around("responseBody()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		Object res = CommonApiResponseAopHelper.point(pjp);
		return res;
	}

}
