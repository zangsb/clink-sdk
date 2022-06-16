package com.tinet.clink.openapi.kb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.request.kb.CreateStandardQuestionRequest;
import com.tinet.clink.openapi.request.kb.DeleteStandardQuestionRequest;
import com.tinet.clink.openapi.request.kb.UpdateStandardQuestionRequest;
import com.tinet.clink.openapi.response.kb.DeleteStandardQuestionResponse;
import com.tinet.clink.openapi.response.kb.StandardQuestionResponse;
import org.junit.Test;

/**
 * @author feizq
 * @date 2022/06/16
 **/
public class StandardQuestionTest extends KbAbstractTest{

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void createStandardQuestion() {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        CreateStandardQuestionRequest request = new CreateStandardQuestionRequest();
        request.setBotId("581926");
        request.setTitle("平台调用创建标准问2");
        request.setCategoryId(-1);

        try {
            StandardQuestionResponse response = client.getResponseModel(request);
            System.out.println(mapper.writeValueAsString(response.getStandardQuestion()));
            System.out.println(mapper.writeValueAsString(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateStandardQuestion() {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        UpdateStandardQuestionRequest request = new UpdateStandardQuestionRequest();
        request.setBotId("581926");
        request.setTitle("平台调用创建标准问234");
        request.setId(167189);
        request.setCategoryId(-1);

        try {
            StandardQuestionResponse response = client.getResponseModel(request);
            System.out.println(mapper.writeValueAsString(response.getStandardQuestion()));
            System.out.println(mapper.writeValueAsString(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteStandardQuestion() {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        DeleteStandardQuestionRequest request = new DeleteStandardQuestionRequest();
        request.setBotId("581926");
        request.setIds(new Integer[]{167189});

        try {
            DeleteStandardQuestionResponse response = client.getResponseModel(request);
            System.out.println(mapper.writeValueAsString(response.getResult()));
            System.out.println(mapper.writeValueAsString(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
