package com.tinet.clink.cc.response.ws;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinet.clink.core.response.ResponseModel;

/**
 * @author libin
 * @date 2022-04-12 6:22 下午
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthTokenResponse extends ResponseModel {

    private String authToken;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
