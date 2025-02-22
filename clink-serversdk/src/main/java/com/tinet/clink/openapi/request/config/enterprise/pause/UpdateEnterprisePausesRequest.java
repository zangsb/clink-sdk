package com.tinet.clink.openapi.request.config.enterprise.pause;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.enterprise.pause.UpdateEnterprisePausesResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.Objects;

/**
 * @author libin
 * @date 2021-12-14 5:38 下午
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  UpdateEnterprisePausesRequest extends AbstractRequestModel<UpdateEnterprisePausesResponse> {

    private Integer id;

    private String pauseStatus;

    private Integer isRest;

    private Integer isDefault;

    public UpdateEnterprisePausesRequest() {
        super(PathEnum.UpdateEnterprisePauses.value(), HttpMethodType.POST);
    }

    @Override
    public Class<UpdateEnterprisePausesResponse> getResponseClass() {
        return UpdateEnterprisePausesResponse.class;
    }


    public void setPauseStatus(String pauseStatus) {
        this.pauseStatus = pauseStatus;
        if (Objects.nonNull(pauseStatus)) {
            putBodyParameter("pauseStatus", pauseStatus);
        }
    }

    public void setIsRest(Integer isRest) {
        this.isRest = isRest;
        if (Objects.nonNull(isRest)) {
            putBodyParameter("isRest", isRest);
        }
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
        if (Objects.nonNull(isDefault)) {
            putBodyParameter("isDefault", isDefault);
        }
    }

    public void setId(Integer id) {
        this.id = id;
        if (Objects.nonNull(id)) {
            putBodyParameter("id", id);
        }
    }

    public String getPauseStatus() {
        return pauseStatus;
    }

    public Integer getIsRest() {
        return isRest;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public Integer getId() {
        return id;
    }
}
