package com.framework.springboot.yeoutside.xss;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.*;

/**
 * XXS 요청 Wrapper class
 * - Request에서 한 번 값을 가져온 뒤에는 다시 가져올 수 없기 때문에
 * - Request를 Wrapper에 담아서 값을 수정한 뒤 Request의 값을 Wrapper 클래스로 변경해 주어야 한다.
 */
public class XssRequestWrapper extends HttpServletRequestWrapper {

    public XssRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    public String[] getParameterValues(String parameter) {

        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = cleanXSS(values[i]);
        }
        return encodedValues;
    }

    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if (value == null) {
            return null;
        }
        return cleanXSS(value);
    }

    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null)
            return null;
        return cleanXSS(value);

    }

    private String cleanXSS(String value) {
        String returnVal = value;
        returnVal = returnVal.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        returnVal = returnVal.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
        returnVal = returnVal.replaceAll("'", "&#39;");
        returnVal = returnVal.replaceAll("eval\\((.*)\\)", "");
        returnVal = returnVal.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        returnVal = returnVal.replaceAll("script", "");
        returnVal = returnVal.replaceAll("iframe", "");
        returnVal = returnVal.replaceAll("embed", "");
        return returnVal;
    }
}
