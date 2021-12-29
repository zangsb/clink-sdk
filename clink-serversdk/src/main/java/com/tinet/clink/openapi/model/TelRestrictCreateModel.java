package com.tinet.clink.openapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author libin
 * @date 2021-12-15 3:57 下午
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TelRestrictCreateModel {

    /**
     * 呼叫限制类型 1:黑名单 2:白名单
     */
    private Integer restrictType;

    /**
     * 类型 1:呼入 2:外呼
     */
    private Integer type;

    /**
     * 电话号码
     */
    private String tel;

    /**
     * 电话号码类型 1:单个电话手机不加0固话加区号例如13409876543/01059222999 2:地区例如010/031 3:未知号码
     */
    private Integer telType;

    /**
     * 描述
     */
    private String description;

    public Integer getRestrictType() {
        return restrictType;
    }

    public void setRestrictType(Integer restrictType) {
        this.restrictType = restrictType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getTelType() {
        return telType;
    }

    public void setTelType(Integer telType) {
        this.telType = telType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
