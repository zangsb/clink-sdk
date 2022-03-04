//
//  OnlineMessageSendManager.h
//  TIMClientLib
//
//  Created by apple on 2021/10/30.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TIMStatusDefine.h"
#import <TIMClientLib/TIMClientLib.h>

NS_ASSUME_NONNULL_BEGIN

@interface OnlineMessageSendManager : NSObject

+ (instancetype)sharedOnlineMessageSendManager;

#pragma mark  发送文字消息
- (void)sendTextMessageWithMessageStr:(NSString*)messageStr
                              success:(void (^)(MYHTIMMessage * timMessage))successBlock
                                error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

#pragma mark  发送图片消息
- (void)sendImageMessageWithImageData:(NSData *)imageData
                              success:(void (^)(NSString * messageId))successBlock
                                error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

#pragma mark  发送语音消息
- (void)sendVoiceMessageWithVoiceData:(NSData *)voiceData
                              success:(void (^)(NSString * messageId))successBlock
                                error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

#pragma mark  发送视频消息
- (void)sendVideoMessageWithVideoData:(NSData *)videoData
                              success:(void (^)(NSString * messageId))successBlock
                                error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

@end

NS_ASSUME_NONNULL_END
