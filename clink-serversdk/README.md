# Clink-SDK
欢迎使用天润融通 “智慧服务平台” 开发者工具套件（SDK）。SDK 对 API 接口调用进行了封装，让您不用复杂编程即可实现 “智慧服务平台” 与自有系统的集成，管理 “智慧服务平台” 的数据配置、呼叫控制、通话记录等各种资源。

## 环境准备
和直接调用 API 接口一样，您也需要一个访问密钥：AccessKeyID/AccessKeySecret。 访问密钥对可以通过登录系统管理后台，在【系统管理-系统对接-接口密钥】模块自助生成。每个访问密钥对可以设置不同的接口访问控制权限。
目前，我们仅支持 Java 语言的 SDK，您需要一个基础的 Java 开发环境，JDK 版本 >= 1.6。

## 安装SDK 
如果您使用 Apache Maven 来管理 Java 项目，只需在项目的 pom.xml 中文件加入相应的依赖项即可
```
<dependency>
    <groupId>com.ti-net</groupId>
    <artifactId>clink-serversdk</artifactId>
    <version>2.0.45.1</version>
</dependency>
```

## 使用 SDK
以下代码示例展示了使用 SDK 完成服务调用的 3 个主要步骤：  
1. 创建并初始化 Client 实例。  
2. 创建 API 请求并设置参数。  
3. 发起请求并处理应答或异常  

```
package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import com.tinet.clink.cc.request.queue.ListQueuesRequest;
import com.tinet.clink.cc.response.queue.ListQueuesResponse;

public class Test {
    public static void main(String[] args) {
        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "<your-access-key-id>",          // AccessKeyId
                "<your-access-key-secret>");     // AccessKeySecret

        Client client = new Client(configuration);

        // 创建API请求并设置参数
        ListQueuesRequest request = new ListQueuesRequest();
        request.setLimit(10);
        request.setOffset(0);

        // 发起请求并处理应答或异常
        ListQueuesResponse response;
        ObjectMapper mapper = new ObjectMapper();

        try {
            response = client.getResponseModel(request);
            try {
                System.out.println(mapper.writeValueAsString(response.getQueues()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (ServerException e) {
            e.printStackTrace();
        }
    }
}
```
# Change Log
### V 2.0.15
```
1.增加httpClient线程池空闲线程回收机制
2.增加失败请求重试机制，最多重试三次
3.补全外呼接口遗漏参数
```
### V 2.0.16
```
1.记录查询接口-外呼通话记录-查询外呼通话记录列表、查询外呼通话记录详情、同步外呼通话记录，返回结果增加虚拟号码
2.记录查询接口-通话录音-下载通话录音文件、查询通话记录地址，增加wav合成文件获取
```

### V 2.0.17
```
1.短信发送接口-管理员和座席可以通过接口进行短信发送
```

### V 2.0.19
```
1.报表接口-呼叫中心-座席工作量报表
2.报表接口-呼叫中心-队列报表
```

### V 2.0.20
```
1.配置管理接口-队列设置-新增队列，增加sayCno、vipSupport属性设置
2.配置管理接口-队列设置-更新队列，增加sayCno、vipSupport属性设置
```
### V 2.0.21
```
1.报表接口-呼叫中心-座席状态统计报表
```

### V 2.0.22
```
1.呼叫控制管理接口-网上回呼-网上回呼
```

### V 2.0.23
```
1.增加工具类EncryptUtil
```

### V 2.0.24
```
1.查询呼入通话记录列表 返回值增加标签字段[tags]
2.查看呼入通话记录 返回值增加标签字段[tags]
3.同步呼入通话记录 返回值增加标签字段[tags]
4.查询外呼通话记录列表 返回值增加标签字段[tags]
5.查看外呼通话记录 返回值增加标签字段[tags]
6.同步外呼通话记录 返回值增加标签字段[tags]
```

### V 2.0.25
```
1.创建客户资料 增加外部id字段[externalId]
2.更新客户资料 增加外部id字段[externalId]
3.增加根据外部id更新客户资料[UpdateCustomerByExternalIdRequest]
```

### V 2.0.26
```
1.设置-语音导航设置-查询语音导航列表，修改因参数名称错误而导致的返回值丢失问题
2.设置-语音导航设置-查询语音导航节点列表，修改因参数名称错误而导致的返回值丢失问题
```

### V 2.0.28
```
1.报表接口-呼叫中心-中继报表-来电分析
2.报表接口-呼叫中心-中继报表-接听率分析
3.报表接口-呼叫中心-外呼报表-预览外呼
4.webcall请求接口增加参数clidType
5.记录查询接口-在线客服-会话记录查询
6.记录查询接口-在线客服-会话详情查询
7.记录查询接口-在线客服-留言记录查询
8.记录查询接口-在线客服-满意度记录查询
9.记录查询接口-在线客服-聊天记录查询
```
### V 2.0.32
```
1.记录查询接口-通话录音-增加获取通话详情录音地址接口
```

### V 2.0.33
```
1.工单接口-工单模块-增加工单创建接口
2.工单接口-工单模块-增加工单更新接口
3.工单接口-工单模块-增加工单模板列表查询接口
4.工单接口-工单模块-增加工单模板查询接口
```

### V 2.0.34
```
客户中心接口-客户资料模块-增加客户资料删除接口
```
### V 2.0.35
```
数据-质检记录-获取质检转写结果
```

### V 2.0.36
```
业务记录接口-业务记录-增加业务记录详情接口
```
### V 2.0.37
```
增值功能-增加手机号码状态检测接口
```
### V 2.0.38
```
数据-满意度记录-查询满意度记录列表 查询参数增加通话唯一标识[mainUniqueId]
数据-呼入通话记录-查询呼入通话记录列表 查询参数增加通话记录唯一标识、请求唯一标识[mainUniqueId、requestUniqueId]
数据-呼入通话记录-查询呼入通话记录 返回值增加标记字段[mark]
数据-呼入通话记录-同步呼入通话记录 返回值增加标记字段[mark]
数据-外呼通话记录-查询外呼通话记录列表 查询参数增加通话记录唯一标识、请求唯一标识[mainUniqueId、requestUniqueId]
数据-外呼通话记录-查询外呼通话记录 返回值增加标记字段[mark]
数据-外呼通话记录-同步外呼通话记录 返回值增加标记字段[mark]
```

### V 2.0.38.1
```
数据-呼入通话记录-查询呼入通话记录 返回值修改标记字段[mark]类型为Integer
数据-呼入通话记录-同步呼入通话记录 返回值修改标记字段[mark]类型为Integer
数据-外呼通话记录-查询外呼通话记录 返回值修改标记字段[mark]类型为Integer
数据-外呼通话记录-同步外呼通话记录 返回值修改标记字段[mark]类型为Integer
```

### V 2.0.39
```
在线客服-会话管理-访客发起会话接口
在线客服-会话管理-访客结束会话接口
在线客服-会话管理-访客完成满意度评价接口
在线客服-会话管理-访客退出排队接口
在线客服-会话管理-访客转人工接口
在线客服-会话管理-发消息给客服接口

```

### V 2.0.40
```

1.知识库管理接口-知识库模块-获取知识库列表
2.目录管理接口-知识库模块-获取目录列表
3.文章管理接口-知识库模块-获取文章列表
4.文章管理接口-知识库模块-获取文章详情
5.在线客服-会话管理-客服登录接口
6.在线客服-会话管理-客服登出接口
7.在线客服-会话管理-客服发起会话接口
8.在线客服-会话管理-客服结束会话接口
9.在线客服-会话管理-客服发起满意度评价接口
10.在线客服-会话管理-发消息给访客接口
11.在线客服-会话管理-客服撤回消息接口
```

### V 2.0.41
```
设置-置忙状态-查询置忙状态 增加接口
监控-座席状态监控 增加agentStatusDetails查询条件，返回增加agentStatusDetail字段
```

### V 2.0.43
```
电话-呼叫管理-下线 增加接口
工单-工单详情接口 增加通话记录id
```
### V 2.0.44
```
数据-呼入通话记录-查询呼入通话记录列表 返回增加statusCode字段
数据-呼入通话记录-查询呼入通话记录 返回增加statusCode字段
数据-呼入通话记录-同步呼入通话记录 返回增加statusCode字段

数据-外呼通话记录-查询呼入通话记录列表 返回增加statusCode字段
数据-外呼通话记录-查询呼入通话记录 返回增加statusCode字段
数据-外呼通话记录-同步呼入通话记录 返回增加statusCode字段
```

### V 2.0.47
```
设置-队列设置-查看队列详情，增加队列名称返回
```

### V 2.0.49
```
数据-呼入通话记录-查询呼入通话记录列表，增加座席号码参数
数据-外呼通话记录-查询外呼通话记录列表，增加座席号码参数
```

### V 2.0.50
```
设置，增加黑白名单设置
数据-置忙状态设置，增加添加置忙状态、删除置忙状态、更新置忙状态接口
```

### V 2.0.51
```
支持多ak/sk、多平台型使用场景
```
### V 2.0.52
```
设置-查看座席详情，增加crmId字段返回
```
### V 2.0.53
```
在线客服-监控-队列状态监控
```

### V 2.0.55
```
呼叫中心-监控-座席状态监控，返回增加bridgeCallCount字段
```

### V 2.0.56
```
设置-队列设置-查询队列成员，新增接口根据座席权限查询所属或全局队列空闲成员
```

### V 2.0.56
```
设置-队列设置-查询队列成员，新增接口根据座席权限查询所属或全局队列空闲成员
```


### V 2.0.57
```
1、呼叫中心-监控，新增接口 获取座席状态监控详情
2、设置-黑白名单设置-查询和添加黑白名单列表接口增加字段“过期时间”
3、新增工具类CommonUtils，根据接听状态转换对应的中文描述
4、设置-队列设置-查询队列成员，新增接口根据座席权限查询所属或全局队列空闲成员
5、数据-通话录音-下载通话详情录音文件，新增接口，下载通话详情录音文件 
```

### V 2.0.58
```
1、数据-呼入通话记录-查询呼入通话记录列表，补充返回字段
2、数据-外呼通话记录-查询外呼通话记录列表，补充返回字段
3、数据-满意度记录-查询满意度记录列表，补充返回字段
4、报表-座席工作量报表，补充返回字段
5、报表-队列报表，补充返回字段
6、新增 数据-日志记录-查询登录日志记录列表
7、新增 数据-日志记录-查询操作日志记录列表
8、新增 数据-日志记录-查询工作日志记录列表
9、新增 数据-日志记录-查询工作时长日志记录列表
10、业务记录-查询业务记录列表，补充返回字段callId
11、业务记录-查询业务记录详情，补充返回字段callId
```

### V 2.0.59
```
1、设置-全局设置-新增查询满意度配置
2、设置-座席设置-新增返回字段-满意度自动执行
3、在线客服-会话记录-查询会话详情
```

### V 2.0.60
```
1、数据-满意度记录-添加满意度调查记录
2、工具条token登录-获取authToken
3、工具条token登录-获取agentToken
4、座席监控查询接口——返回结果追加queuesMap (队列信息)
```

### V 2.0.61

```
监控-座席状态查询接口，补充返回字段队列信息
```

### V 2.0.63
```
1、在线客户-会话记录-查询会话详情-新增字段 
    （设备类型:deviceType 接待类:chatDurationType 会话结束原因:closeReason 会话结束前置状态:closeStatus）
2、在线客户-会话记录-同步会话详情-新增字段
     (最后一条消息发送者:lastSenderMessageType 机器人有效会话:botChatValid 机器人会话轮数:botRound 访客消息数:visitorChatCount 座席消息数:agentChatCount 机器人消息数:botChatCount 访客发送第一条消息的时间:visitorFirstMsgTime 访客发送最后一条消息的时间:visitorLastMsgTime 座席发送第一条消息的时间:agentFirstMsgTime 座席发送最后一条消息的时间:agentLastMsgTime)
```

### V 2.0.65

```
1、数据-呼入通话记录-查询呼入通话记录列表，补充返回字段“坐席响铃时间”
2、数据-呼入通话记录-查询呼入通话记录详情，补充返回字段“坐席响铃时间”
3、数据-呼入通话记录-查询呼入通话记录明细详情，补充返回字段“坐席响铃时间”
4、数据-呼入通话记录-查看呼入通话记录
5、新增 外呼任务-新增外呼任务
```

### V 2.0.66
```
1、新增 报表-呼入报表-按地区统计
2、新增 设置-座席设置-查看座席外显号码
```
### V 2.0.67
```
```

### V 2.0.68
```
1、新增 在线客户-会话记录-查询访客消息未读数
2、新增 报表-座席工作台首页数据
```

### V 2.0.68.1
```
1、优化异常处理逻辑
```


### V 2.0.69
```
1、竹间机器人知识库增加分类的增、删、改接口操作
2.工单接口中增加子表单以及级联多选字段
```

### V 2.0.70
```
1、监控-队列状态监控-增加监控范围参数
2、通话记录可以直接计算得出及时应答数
3、新增/更新话机增加主叫记忆字段
4、新增 在线客服-会话记录-同步满意度记录、请求参数增加会话id，可单独通过接口获取某条会话的满意度
5、新增 在线客服-会话记录-查询会话详情、返参增加满意度发起方式
```

### V 2.0.70.1
```
1、查看呼入通话记录追加返回"是否及时应答"字段
```

### V 2.0.70.2
```
1、新增 在线客服-会话记录-同步满意度记录、返参增加 "满意度发起方式" 字段
2、新增 在线客服-会话记录-查询会话详情、返参增加 "满意度发起方式" 字段
```

### V 2.0.71.1
```
1、业务系统-工单管理接口-获取工单详情 返回参数 工单详情表单字段model 追加 "taskId" 字段
```

### V 2.0.72
```
1、新增 业务系统-工单管理接口-查询工单字段列表接口
2、新增 业务系统-工单管理接口-更新工单字段选项值接口
```

### V 2.0.73
```
1、 话机设置-新增话机 请求参数追加网络防抖 "jittBuffer" 字段
2、 话机设置-修改话机 请求参数追加网络防抖 "jittBuffer" 字段
3、 话机设置-查询话机列表 返回参数追加网络防抖 "jittBuffer" 字段
4、 话机设置-查看话机详情 返回参数追加网络防抖 "jittBuffer" 字段
5、新增 业务系统-报表-工单整体统计报表
6、新增 业务系统-报表-工单座席情况统计报表
7、新增 业务系统-报表-工单座席组情况统计报表
8、新增 业务系统-工单管理接口-关闭工单接口
9、新增 业务系统-工单管理接口-完成工单接口
10、 新增 在线客服-会话记录-查询会话详情、返回参数增加 "满意度发起方式" 字段
```

### V 2.0.74
```
1、 新增 在线客服-API-报表-增加座席工作考勤报表
2、 新增 在线客服-会话记录-查询会话详情、返参增加 "会话发起方式","排队时间","会话类型","首次响应时长" 字段
3、 新增 在线客服-会话记录-同步会话记录、返参增加 "会话类型" 字段
```

### V 2.0.75
```
1、 新增 工单-工单管理接口-增加预制流历史表单更新接口
```
