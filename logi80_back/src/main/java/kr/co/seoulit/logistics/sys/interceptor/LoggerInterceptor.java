package kr.co.seoulit.logistics.sys.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoggerInterceptor implements HandlerInterceptor {
   private final Log log = LogFactory.getLog(getClass());

   // 이 인터셉터는 모든 경로에 대해 로깅 관련 작업 처리를 하는 인터셉터이다.
   // 이는 모든 웹 요청이 들어왔을때 로그를 남기거나 추적하는 역할을 한다.

   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      log.debug("======================================          START         ======================================");
      log.debug(" Request URI \t:  " + request.getRequestURI());
      // true를 반환하여 요청이 계속 진행될 수 있도록 합니다.

      System.out.println("\nLoggerInterceptor - preHandle: 요청 시작 - " + request.getRequestURI() + "\n");

      return true;

      // preHandle() 컨트롤러 실행 전에 수행
   }

   @Override
   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
      log.debug("======================================           END          ======================================\n");

      // postHandle(); 컨트롤러 수행 후 결과를 뷰로 보내기 전에 수행
   }

   @Override
   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
      if (ex != null) {
         log.debug("Exception \t:  " + ex.getMessage());
      }
      log.debug("======================================           END          ======================================\n");

      // afterCompletion 뷰의 작업까지 완료된 후 수행
   }
}