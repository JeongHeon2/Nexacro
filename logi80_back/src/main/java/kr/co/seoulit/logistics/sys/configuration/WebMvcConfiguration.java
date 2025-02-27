package kr.co.seoulit.logistics.sys.configuration;

import java.nio.charset.Charset;

import jakarta.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.seoulit.logistics.sys.interceptor.LoggerInterceptor;
import kr.co.seoulit.logistics.sys.interceptor.LoginInterceptor;
import kr.co.seoulit.logistics.sys.interceptor.XplatformInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer{

	// 인터셉터를 등록하는 기능
	// 모드 경로는 / /*에 적용
	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(new LoginInterceptor())
				.excludePathPatterns("/swagger-ui/index.html")
				.excludePathPatterns("/swagger-resources/**")
				.excludePathPatterns("/swagger-ui.html")
				.excludePathPatterns("/*logout*")
				.excludePathPatterns("/*login*")
				.excludePathPatterns("/error")
				.addPathPatterns("/")
				.addPathPatterns("/*");

		registry.addInterceptor(new LoggerInterceptor());
		registry.addInterceptor(new XplatformInterceptor());

		WebMvcConfigurer.super.addInterceptors(registry);
	}

	// 2개의 빈은 인코딩 관련.
	// 이 필터는 요청과 응답의 문자 인코딩을 UTF-8로 설정
	// setForceEncoding(true)로 설정하여 요청과 응답의 인코딩을 강제로 UTF-8로 적용
	@Bean
	public Filter characterEncodingFilter(){
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();    //CharacterEncodingFilter는 스프링이 제공하는 클래스로 웹에서 주고받는 데이터의 헤더값을 UTF-8로 인코딩 해줌.
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);  //기본값은 false로 설정되어 있음.

		return characterEncodingFilter;
	}

	// 응답 본문의 문자열을 UTF-8로 인코딩해 처리.
	// StringHttpMessageConverter를 사용해 문자열 데이터를 UTF-8로 변환
	@Bean
	public HttpMessageConverter<String> responseBodyConverter(){
		return new StringHttpMessageConverter(Charset.forName("UTF-8"));
	}

	@Bean
	public MultipartResolver multipartResolver() {
		StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
		return multipartResolver;
	}
}
