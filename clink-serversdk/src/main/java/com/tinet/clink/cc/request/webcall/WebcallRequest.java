package com.tinet.clink.cc.request.webcall;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.webcall.WebcallResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 * webcall请求
 *
 * @author wangll
 * @date 2020/04/02
 **/
public class WebcallRequest extends AbstractRequestModel<WebcallResponse> {

    /**
     * 客户电话，固话类型需要添加区号，手机类型不加 0，固话带分机以 “-” 分隔。
     * 如果企业开启号码隐藏功能，可从弹屏事件中获取customerNumberKey的值，进行外呼，多个直接用英文逗号’,’分隔
     */
    private String customerNumber;

    /**
     * 自定义请求id,如果传了这个参数那么将返回传入值，如果未传该值，那么系统将自动生成一个，一次接口请求造成的多次呼叫requestUniqueId相同
     */
    private String requestUniqueId;

    /**
     * 呼叫客户侧超时时间，取值范围 5-60s，默认 45s
     */
    private Integer customerTimeout;

    /**
     * 自动应答检查（传真机等），1.开启 0.不开启  默认为0
     */
    private Integer amd;

    /**
     * 延迟时长，延迟多少秒呼叫 参数取值范围：0<=delay<=60 默认为0
     */
    private Integer delay;

    /**
     * 当外显方式为0时表示指定的外显号码，当外显类型为1时表示指定的外呼标识
     */
    private String clid;

    /**
     * 回呼接通后进入的ivrId
     */
    private Integer ivrId;

    /**
     * 动态附带参数，参数会写入通道中，以便ivr中调用。参数名建议使用user_开头
     */
    private String params;

    /**
     * 外显方式，0：指定外显号码；1：指定外呼标识
     */
    private Integer clidType;

    public WebcallRequest() {
        super(PathEnum.Webcall.value(), HttpMethodType.POST);
    }


    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
        if (customerNumber != null) {
            putBodyParameter("customerNumber", customerNumber);
        }
    }

    public String getRequestUniqueId() {
        return requestUniqueId;
    }

    public void setRequestUniqueId(String requestUniqueId) {
        this.requestUniqueId = requestUniqueId;
        if (requestUniqueId != null) {
            putBodyParameter("requestUniqueId", requestUniqueId);
        }
    }

    public Integer getCustomerTimeout() {
        return customerTimeout;
    }

    public void setCustomerTimeout(Integer customerTimeout) {
        this.customerTimeout = customerTimeout;
        if (customerTimeout != null) {
            putBodyParameter("customerTimeout", customerTimeout);
        }
    }

    public Integer getAmd() {
        return amd;
    }

    public void setAmd(Integer amd) {
        this.amd = amd;
        if (amd != null) {
            putBodyParameter("amd", amd);
        }
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
        if (delay != null) {
            putBodyParameter("customerNumber", delay);
        }
    }

    public String getClid() {
        return clid;
    }

    public void setClid(String clid) {
        this.clid = clid;
        if (clid != null) {
            putBodyParameter("clid", clid);
        }
    }

    public Integer getIvrId() {
        return ivrId;
    }

    public void setIvrId(Integer ivrId) {
        this.ivrId = ivrId;
        if (ivrId != null) {
            putBodyParameter("ivrId", ivrId);
        }
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
        if (params != null) {
            putBodyParameter("params", params);
        }
    }

    public Integer getClidType() {
        return clidType;
    }

    public void setClidType(Integer clidType) {
        this.clidType = clidType;
        if (clidType != null) {
            putBodyParameter("clidType", clidType);
        }
    }

    @Override
    public Class<WebcallResponse> getResponseClass() {
        return WebcallResponse.class;
    }
}
