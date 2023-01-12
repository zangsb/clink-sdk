//
//  MessageViewController.m
//  TOSClientKitDemo
//
//  Created by 言 on 2022/7/5.
//

#import "MessageViewController.h"
#import "STBaseWebViewController.h"
#import "BaiduMapViewController.h"

@interface MessageViewController ()

@end

@implementation MessageViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    [self.navigationController setNavigationBarHidden:NO animated:animated];
}

/// 扩展面板，自定义按钮事件 （需要在子类实现这个方法）
- (void)didClinkCustomExtendBoardItemAction:(TOSKitExtendBoardItemModel *)item {
    
//    if (item.type == TOSChatBoxExtendBoardTypeCustom &&
//        item.index == 10006) {
//        [self.navigationController pushViewController:[[BaiduMapViewController alloc] init] animated:YES];
//    }
}

- (void)tinet_textMessageClickAction:(TinetClickTextMessageEventType)eventType userInfo:(NSDictionary *)userInfo {
    /// 超链接点击事例
    if (eventType == TinetClickEventTypeUrl) {
        STBaseWebViewController * webView = [[STBaseWebViewController alloc] init];
        webView.urlString = [NSString stringWithFormat:@"%@", userInfo[@"content"]];
        [self.navigationController pushViewController:webView animated:YES];
    }
    
    if (eventType == TinetClickMiniProgramCard) {
        STBaseWebViewController * webView = [[STBaseWebViewController alloc] init];
        webView.urlString = [NSString stringWithFormat:@"%@", userInfo[@"content"][@"pagepath"]?:@""];
        [self.navigationController pushViewController:webView animated:YES];
    }
    
    if (eventType == TinetClickLogisticsCard) {
        STBaseWebViewController * webView = [[STBaseWebViewController alloc] init];
        webView.urlString = [NSString stringWithFormat:@"%@", userInfo[@"content"][@"orderLink"]?:@""];
        [self.navigationController pushViewController:webView animated:YES];
    }
    
    [self showErrorView:[NSString stringWithFormat:@"%ld = %@",eventType,userInfo]];
}

@end
