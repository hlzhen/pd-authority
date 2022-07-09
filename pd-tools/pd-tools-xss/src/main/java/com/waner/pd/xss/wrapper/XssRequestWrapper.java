package com.waner.pd.xss.wrapper;

import com.waner.pd.xss.utils.XssUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.List;
import java.util.Map;


/**
 * 跨站攻击请求包装器
 */
@Slf4j
public class XssRequestWrapper extends HttpServletRequestWrapper {
    private List<String> ignoreParamValueList;

    public XssRequestWrapper(HttpServletRequest request, List<String> ignoreParamValueList) {
        super(request);
        this.ignoreParamValueList = ignoreParamValueList;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> requestMap = super.getParameterMap();
        for (Map.Entry<String, String[]> me : requestMap.entrySet()) {
            String[] values = me.getValue();
            for (int i = 0; i < values.length; i++) {
                values[i] = XssUtils.xssClean(values[i], this.ignoreParamValueList);
            }
        }
        return requestMap;
    }

    @Override
    public String[] getParameterValues(String paramString) {
        String[] sourceArrayParams = super.getParameterValues(paramString);
        if (sourceArrayParams == null) {
            return null;
        }
        int len = sourceArrayParams.length;
        String[] targetArrayParams = new String[len];
        for (int i = 0; i < len; i++) {
            targetArrayParams[i] = XssUtils.xssClean(sourceArrayParams[i], this.ignoreParamValueList);
        }
        return targetArrayParams;
    }

    @Override
    public String getParameter(String paramString) {
        String str = super.getParameter(paramString);
        if (str == null) {
            return null;
        }
        return XssUtils.xssClean(str, this.ignoreParamValueList);
    }

    @Override
    public String getHeader(String paramString) {
        String str = super.getHeader(paramString);
        if (str == null) {
            return null;
        }
        return XssUtils.xssClean(str, this.ignoreParamValueList);
    }
}
