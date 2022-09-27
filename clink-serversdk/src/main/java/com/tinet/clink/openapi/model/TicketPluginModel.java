package com.tinet.clink.openapi.model;


/**
 * @Description 工单插件model
 * @Author LinYang
 * @Date 2022/7/21
 * @Version 1.0
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  TicketPluginModel {

    /**
     * 插件名称
     */
    private String name;

    /**
     * 工单模板ID
     */
    private Integer workflowId;

    /**
     * 插件链接url
     */
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Integer workflowId) {
        this.workflowId = workflowId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}