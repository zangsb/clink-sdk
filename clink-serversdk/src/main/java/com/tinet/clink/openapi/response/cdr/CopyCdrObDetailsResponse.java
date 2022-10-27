package com.tinet.clink.openapi.response.cdr;

import com.tinet.clink.openapi.model.CdrObDetailModel;
import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;

/**
 * 数据同步外呼通话记录明细响应
 *
 * @author huwk
 * @date 2018/10/24
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  CopyCdrObDetailsResponse extends PagedResponse {

    /**
     * 游标 id
     */
    private String scrollId;

    /**
     * 通话记录详情列表
     */
    private List<CdrObDetailModel> cdrObDetails;

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    public List<CdrObDetailModel> getCdrObDetails() {
        return cdrObDetails;
    }

    public void setCdrObDetails(List<CdrObDetailModel> cdrObDetails) {
        this.cdrObDetails = cdrObDetails;
    }
}
