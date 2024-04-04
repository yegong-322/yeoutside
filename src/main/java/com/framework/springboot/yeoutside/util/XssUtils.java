package com.framework.springboot.yeoutside.util;

import org.apache.commons.text.StringEscapeUtils;

/**
 * Xss Util class
 */
public class XssUtils {

    /**
     * 태그 치환
     * @param value 문자열
     */
    public static String charEscape(String value) {

        return value == null ? value : StringEscapeUtils.escapeHtml4(value);
    }

}
