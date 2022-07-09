package com.waner.pd.xss;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ale on 2022/7/9
 */
@ConfigurationProperties(prefix = "pd.auth.xss")
public class XssConfigurationProperties {

    /**
     * 过滤器排序
     */
    private int filterOrder = 1;

    /**
     * 是否包含富文本
     */
    private String includeRichText = "true";


    /**
     * 普通请求需排除的地址
     */
    private Set<String> excludeUrl = new HashSet<>();


    /**
     * 针对Json的请求需要自定义XSS攻击的标签
     */
    private Set<String> JsonParamsExcludeTags = new HashSet<>();

    public int getFilterOrder() {
        return filterOrder;
    }

    public void setFilterOrder(int filterOrder) {
        this.filterOrder = filterOrder;
    }

    public String getIncludeRichText() {
        return includeRichText;
    }

    public void setIncludeRichText(String includeRichText) {
        this.includeRichText = includeRichText;
    }

    public Set<String> getExcludeUrl() {
        return excludeUrl;
    }

    public void setExcludeUrl(Set<String> excludeUrl) {
        this.excludeUrl = excludeUrl;
    }

    public Set<String> getJsonParamsExcludeTags() {
        return JsonParamsExcludeTags;
    }

    public void setJsonParamsExcludeTags(Set<String> jsonParamsExcludeTags) {
        JsonParamsExcludeTags = jsonParamsExcludeTags;
    }
}
