package com.waner.pd.xss.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.waner.pd.xss.XssConfigurationProperties;
import com.waner.pd.xss.utils.XssUtils;

import java.io.IOException;
import java.util.Set;

/**
 * 过滤跨站脚本的 反序列化工具
 */
public class XssStringJsonDeserializer extends JsonDeserializer<String> {


    private XssConfigurationProperties xssConfigurationProperties;

    public XssStringJsonDeserializer(XssConfigurationProperties xssConfigurationProperties) {
        this.xssConfigurationProperties = xssConfigurationProperties;
    }

    @Override
    public String deserialize(JsonParser p, DeserializationContext dc) throws IOException, JsonProcessingException {
        if (!p.hasToken(JsonToken.VALUE_STRING)) {
            return null;
        }
        String value = p.getValueAsString();
        if (null == value || "".equals(value)) {
            return value;
        }
        Set<String> jsonParamsExcludeTags = xssConfigurationProperties.getJsonParamsExcludeTags();
        boolean flag = jsonParamsExcludeTags.stream().anyMatch((item) -> value.contains(item));
        if (flag) {
            return XssUtils.xssClean(value, null);
        }
        return value;
    }
}
