package com.framework.springboot.yeoutside.xss;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * XXS 방어 Filter class
 */
@WebFilter("/*")
public class XSSFilter implements Filter {

    /**
     * 웹 컨테이너(톰캣)이 시작될 때 필터 최초 한 번 인스턴스 생성
     */
    public void init(FilterConfig filterConfig) throws SecurityException, ServletException {

        Filter.super.init(filterConfig);
    }

    /**
     * 클라이언트의 요청 시, 전/후 처리를 수행하며, FilterChain를 통해 전달함
     * - Http Body 부분을 가져와 값이 있을 경우에만 ObjectMapper를 사용해 파싱 해주고 태그를 치환
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

//        XssRequestWrapper xssRequestWrapper = new XssRequestWrapper((HttpServletRequest) servletRequest);
//        String body = requestDataByte(xssRequestWrapper);
//
//        if(!StringUtils.isEmpty(body)) {
//            Map<String, Object> jsonObject = new ObjectMapper().readValue(body, HashMap.class);
//            Map<String, Object> customJsonObject = new HashMap<>();
//
//            jsonObject.forEach((key, value) -> customJsonObject.put(key, XssUtils.charEscape(value.toString())));
//            xssRequestWrapper.resetInputStream(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(customJsonObject).getBytes());
//        }

//        filterChain.doFilter(xssRequestWrapper, servletResponse);  // 해당 메소드를 기점으로 request, response가 나눠짐

        filterChain.doFilter(new XssRequestWrapper((HttpServletRequest) servletRequest), servletResponse);
    }

    /**
     * 필터 인스턴스 종료
     */
    public void destroy() {

        Filter.super.destroy();
    }

    private String requestDataByte(HttpServletRequest request) throws IOException {

        byte[] rawData = new byte[128];
        InputStream inputStream = request.getInputStream();

        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }
}