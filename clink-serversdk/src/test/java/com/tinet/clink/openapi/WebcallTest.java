package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.call.webcall.WebcallRequest;
import com.tinet.clink.openapi.response.call.webcall.WebcallResponse;

import org.junit.Ignore;
import org.junit.Test;

/**
 * webcall接口测试
 *
 * @author wangll
 * @date 2018/10/29
 **/
@Ignore
public class WebcallTest extends AbstractTest {

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 上线单元测试
     */
    @Test
    public void webcallTest() throws ServerException, ClientException, JsonProcessingException {

        WebcallRequest request = new WebcallRequest();
        request.setCustomerNumber("13716619459");
        request.setIvrId(148);
        request.expires(100);


        WebcallResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }

}
