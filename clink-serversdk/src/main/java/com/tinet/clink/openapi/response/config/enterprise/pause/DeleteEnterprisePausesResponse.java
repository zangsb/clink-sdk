package com.tinet.clink.openapi.response.config.enterprise.pause;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinet.clink.openapi.model.EnterprisePauseDeleteModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @author libin
 * @date 2021-12-14 5:36 下午
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeleteEnterprisePausesResponse extends ResponseModel {

    private EnterprisePauseDeleteModel enterprisePause;

    public EnterprisePauseDeleteModel getEnterprisePause() {
        return enterprisePause;
    }

    public void setEnterprisePause(EnterprisePauseDeleteModel enterprisePause) {
        this.enterprisePause = enterprisePause;
    }
}
