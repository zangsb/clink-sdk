//
//  MineViewController.m
//  mobileCMS
//
//  Created by 赵言 on 2019/12/11.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "MineConfigViewController.h"
#import "MineInfoModel.h"
#import "LoginModel.h"
#import "MineTextTableCellModel.h"
#import "CustomStylesViewController.h"

#import "MineTextTableCell.h"
#import "MineSwitchTableViewCell.h"
#import "superCustomStylesModel.h"

#import "MineConfigInputView.h"


@interface MineConfigViewController () <UITableViewDelegate, UITableViewDataSource>

@property (nonatomic, weak) BaseTableView *tableView;
@property (nonatomic, strong) NSMutableArray <NSMutableArray <MineTextTableCellModel *>*>*cellDataSource;

@property (weak, nonatomic) UITextField *textField;
@property (nonatomic, strong) NSMutableArray <superCustomStylesModel *>*dataSource;

@property (nonatomic, strong) NSArray                * headDataSource;

@end

@implementation MineConfigViewController
- (void)setupSubviews {
    [super setupSubviews];
    self.navigationItem.title = @"自定义样式";
    self.headDataSource = @[@"对话区域配置", @"输入框配置", @"吐司提示配置", @"相册导航栏配置"];
    self.tableView = [self setupTableView];
    [self bindViewModel];
    

}

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(configSwitchChange:) name:kTOSClientDemoConfigSwitchChange object:nil];
    
//    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(videoMessageReceiveNotification:) name:KVisitorVideoReady object:nil];
//
//    [[NSNotificationCenter defaultCenter] addObserver:self
//                                             selector:@selector(kVideoBothCallHangupNotification:)
//                                                 name:kVideoBothCallHangup
//                                               object:nil];
}

-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
}



- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    [self.tableView reloadData];
}

- (void)bindViewModel {
    UIBarButtonItem *resetConfigButton = [[UIBarButtonItem alloc] initWithTitle:@"恢复默认" style:UIBarButtonItemStylePlain target:self action:@selector(resetConfigClickEvent)];
    [resetConfigButton setTintColor:[UIColor colorWithRed:0.263f green:0.522f blue:255.0f alpha:1.0f]];
    
    self.navigationItem.rightBarButtonItem = resetConfigButton;
    
//    self.cellDataSource[0][0].value = @"经典样式";
//    NSDictionary *infoDictionary = [[NSBundle mainBundle] infoDictionary];
//    CFShow((__bridge CFTypeRef)(infoDictionary));
//    NSString *app_Version = [infoDictionary by_ObjectForKey:@"CFBundleShortVersionString"];
//
//    NSInteger clientType = [LoginModel loginModel].clientType.integerValue;
//    if (clientType == 1 || clientType == 2) {

//        self.cellDataSource[2][2].value = [LoginModel loginModel].softPhoneSwitch.integerValue == 1 ? @"1" : @"0";
//    } else {
//        self.cellDataSource[2][0].value = app_Version?:@"";
//    }
    
}

#pragma mark - configSwitchChange

- (void)configSwitchChange:(NSNotification *)notification {
    
    MineTextTableCellModel *model = (MineTextTableCellModel *)notification.object;
    for (int i = 0; i < self.cellDataSource.count; i++) {
        NSArray * nArray = self.cellDataSource[i];
        for (MineTextTableCellModel * nModel in nArray) {
            if ([nModel.title isEqualToString:model.title]) {
                nModel.value = model.value;
                break;
            }
        }
    }
    /// 更新沙盒路径下的配置数据
    [self upDataPlist];
    
}

- (void)upDataPlist {
    
    NSMutableArray * mArray = [NSMutableArray array];
    for (NSArray * nArray in self.cellDataSource) {
        NSMutableArray * subArray = [NSMutableArray array];
        for (MineTextTableCellModel * nModel in nArray) {
            NSMutableDictionary * dict = [self dicFromObject:nModel];
            [subArray addObject:dict];
        }
        [mArray addObject:subArray];
    }
    NSLog(@"解析后的数据：%@", mArray);
    [self writePlist:[mArray mutableCopy]];
    
}

/// model转化为字典
- (NSMutableDictionary *)dicFromObject:(NSObject *)object {
    NSMutableDictionary *dic = [NSMutableDictionary dictionary];
    unsigned int count;
    objc_property_t *propertyList = class_copyPropertyList([object class], &count);
 
    for (int i = 0; i < count; i++) {
        objc_property_t property = propertyList[i];
        const char *cName = property_getName(property);
        NSString *name = [NSString stringWithUTF8String:cName];
        /// valueForKey返回的数字和字符串都是对象
        NSObject *value = [object valueForKey:name];
 
        if ([value isKindOfClass:[NSString class]] || [value isKindOfClass:[NSNumber class]]) {
            /// string , bool, int ,NSinteger
            [dic setObject:value forKey:name];
 
        } else if ([value isKindOfClass:[NSArray class]] || [value isKindOfClass:[NSDictionary class]]) {
            /// 字典或字典
            [dic setObject:[self arrayOrDicWithObject:(NSArray*)value] forKey:name];
 
        } else if (value == nil) {
            /// null
            [dic setObject:@"" forKey:name];
        } else {
            //model
            [dic setObject:[self dicFromObject:value] forKey:name];
        }
    }
 
    return [dic copy];
}

/// 将可能存在model数组转化为普通数组
- (id)arrayOrDicWithObject:(id)origin {
    if ([origin isKindOfClass:[NSArray class]]) {
        //数组
        NSMutableArray *array = [NSMutableArray array];
        for (NSObject *object in origin) {
            if ([object isKindOfClass:[NSString class]] || [object isKindOfClass:[NSNumber class]]) {
                //string , bool, int ,NSinteger
                [array addObject:object];
 
            } else if ([object isKindOfClass:[NSArray class]] || [object isKindOfClass:[NSDictionary class]]) {
                //数组或字典
                [array addObject:[self arrayOrDicWithObject:(NSArray *)object]];
 
            } else {
                //model
                [array addObject:[self dicFromObject:object]];
            }
        }
 
        return [array copy];
 
    } else if ([origin isKindOfClass:[NSDictionary class]]) {
        //字典
        NSDictionary *originDic = (NSDictionary *)origin;
        NSMutableDictionary *dic = [NSMutableDictionary dictionary];
        for (NSString *key in originDic.allKeys) {
            id object = [originDic objectForKey:key];
 
            if ([object isKindOfClass:[NSString class]] || [object isKindOfClass:[NSNumber class]]) {
                //string , bool, int ,NSinteger
                [dic setObject:object forKey:key];
 
            } else if ([object isKindOfClass:[NSArray class]] || [object isKindOfClass:[NSDictionary class]]) {
                //数组或字典
                [dic setObject:[self arrayOrDicWithObject:object] forKey:key];
 
            } else {
                //model
                [dic setObject:[self dicFromObject:object] forKey:key];
            }
        }
 
        return [dic copy];
    }
 
    return [NSNull null];
}

/// 获取沙盒路径
- (NSString *)customStylesPlist {
    ///获取本地沙盒Document路径
    NSArray *documentPath = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    NSString *path = [documentPath objectAtIndex:0];
    ///在Document路径下拼接文件名字
    NSString *plistPath = [path stringByAppendingPathComponent:@"userCustomStyles/userCustomStylesDataSource"];
    return plistPath;
}


/// 写入字典
- (void)writePlist:(NSArray *)array {
    ///在Document路径下拼接文件名字
    NSString *plistPath = [self customStylesPlist];

    NSFileManager *fileManager = [NSFileManager defaultManager];
    ///检测plistPath路径是否存在
    BOOL isFile = [fileManager fileExistsAtPath:plistPath];
    if (isFile) {
        /// 文件存在 删除之后 写入
        [fileManager removeItemAtPath:plistPath error:nil];
    }
    else {
        /// 如果文件不存在需要创建文件
        BOOL isCreatDir = [fileManager createDirectoryAtPath:plistPath withIntermediateDirectories:YES attributes:nil error:nil];
        if (!isCreatDir) {
            NSLog(@"create 自定义样式Plist failed");
        }
        else {
            NSLog(@"创建自定义样式Plist成功！");
        }
    }
    NSError * error;
    BOOL isSuccess = [array writeToURL:[NSURL fileURLWithPath:plistPath] error:&error];
    
    if (isSuccess) {
        NSLog(@"写入成功!");
    }
    else {
        NSLog(@"写入失败! : %@", error);
    }
}

#pragma mark - UITableViewDataSource
- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return self.cellDataSource.count;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return self.cellDataSource[section].count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    BaseTableViewCell *cell = [BaseTableViewCell cellXibWithTableView:tableView reuseIdentifie:self.cellDataSource[indexPath.section][indexPath.row].cellType];
//    if (indexPath.section == 1) {
//        MineMenuBarTableCell *menuBarCell = (MineMenuBarTableCell *)cell;
//        self.menuBarCell = menuBarCell;
//        @weakify(self);
//        [menuBarCell setDidClickNoticeBtn:^{
//            @strongify(self);
//            NoticeWMPageController *noticeVC = [[NoticeWMPageController alloc] init];
//            [self.navigationController pushViewController:noticeVC animated:YES];
//        }];
//    }
    return cell;
}

- (void)tableView:(UITableView *)tableView willDisplayCell:(UITableViewCell *)cell forRowAtIndexPath:(NSIndexPath *)indexPath {
    [((BaseTableViewCell *)cell) setWithModel:self.cellDataSource[indexPath.section][indexPath.row]];
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    MineTextTableCellModel *model = self.cellDataSource[indexPath.section][indexPath.row];
    switch (indexPath.section) {
        case 0: {
            switch (indexPath.row) {
                case 0: {
                    @weakify(self);
                    CustomStylesViewController *customStylesVC = [[CustomStylesViewController alloc] initWithNibName:[CustomStylesViewController className] bundle:nil];
                    customStylesVC.themeClickEvent = ^(NSString * _Nonnull theme) {
                        @strongify(self);
                        model.value = theme;
                        /// 更新沙盒路径下的配置数据
                        [self upDataPlist];
                        [self.tableView reloadData];
                    };
                    [self.navigationController pushViewController:customStylesVC animated:YES];
                    break;
                }
                case 1: {
                    [self showInputViewModel:model];
                    break;
                }
                    
                default:
                    break;
            }
            break;
        }
        case 1: {
            switch (indexPath.row) {
                case 0: {
                    [self showInputViewModel:model];
                    break;
                }
                case 1: {
                    [self showInputViewModel:model];
                    break;
                }
                case 2: {
                    [self showInputViewModel:model];
                    break;
                }
                case 3: {
                    MineConfigInputView * inputView = [[MineConfigInputView alloc] initWithFrame:(CGRectMake(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT))];
                    inputView.titleString = model.title;
                    inputView.textString = model.value.length ? model.value : @"";
                    inputView.tipString = @"不超过 12 个字";
                    inputView.textMaxLength = 12;
                    inputView.isRegex = NO;
                    inputView.tipTextColor = [UIColor colorWithHexString:@"#8C8C8C"];
                    inputView.action = ^(NSString * _Nonnull string) {
                        model.value = string;
                        [self.tableView reloadData];
                    };
                    [inputView show];
                    break;
                }
                    
                default:
                    break;
            }
            break;
        }
        case 2: {
            switch (indexPath.row) {
                case 1: {
                    [self showInputViewModel:model];
                    break;
                }
                default:
                    break;
            }
            break;
        }
        case 3: {
            [self showInputViewModel:model];
            break;
        }
            
            
        default:
            break;
    }
//    if ([model.title isEqualToString:@"聊天窗口UI样式"]) {
//        CustomStylesViewController *customStylesVC = [[CustomStylesViewController alloc] initWithNibName:[CustomStylesViewController className] bundle:nil];
//        [self.navigationController pushViewController:customStylesVC animated:YES];
//    } else if ([model.title isEqualToString:@"时间色值"]) {
//        MineConfigInputView * inputView = [[MineConfigInputView alloc] initWithFrame:(CGRectMake(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT))];
//        inputView.titleString = model.title;
//        inputView.tipString = @"6 位数色值";
//        inputView.textMaxLength = 7;
//        inputView.isRegex = YES;
//        inputView.tipTextColor = [UIColor colorWithHexString:@"#8C8C8C"];
//        inputView.action = ^(NSString * _Nonnull string) {
//            model.value = string;
//            [self.tableView reloadData];
//        };
//        [inputView show];
//        __weak CustomAlertView *customAlertView = [self addCustomAlertViewWithTitle:@"修改手机号" placeholder:model.value];
//        @weakify(self);
//        [customAlertView setDidClickDetermineBtn:^{
//            @strongify(self);
//            if ([self.textField.text regexValidate:kPhoneNumber]) {
//                [customAlertView hidenView];
//                self.clientBindTelVM.bindTel = self.textField.text;
//                [self.clientBindTelVM requestData];
//                [TRLoadingView showLoadingAddTo:self.view animated:YES];
//            } else {
//                [customAlertView endEditing:YES];
//                [self showErrorView:@"请输入正确的手机号码"];
//            }
//        }];
//    }
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    return 45.f;
}

- (UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section {
    UIView * headView = [[UIView alloc] initWithFrame:(CGRectMake(0, 0, SCREEN_WIDTH, 30))];
    headView.backgroundColor = kHexColor(0xFFFFFF);
    UILabel * label = [[UILabel alloc] initWithFrame:(CGRectMake(20, 0, headView.width-20, 30))];
    label.textColor = kHexColor(0x8C8C8C);
    label.font = [UIFont systemFontOfSize:14];
    label.text = self.headDataSource[section];
    [headView addSubview:label];
    return headView;
}

- (void)showInputViewModel:(MineTextTableCellModel *)model {
    
    MineConfigInputView * inputView = [[MineConfigInputView alloc] initWithFrame:(CGRectMake(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT))];
    inputView.titleString = model.title;
    inputView.textString = model.value.length ? model.value : @"#";
    inputView.tipString = @"6 位数色值";
    inputView.textMaxLength = 7;
    inputView.isRegex = YES;
    inputView.tipTextColor = [UIColor colorWithHexString:@"#8C8C8C"];
    @weakify(self);
    inputView.action = ^(NSString * _Nonnull string) {
        @strongify(self);
        model.value = string;
        /// 更新沙盒路径下的配置数据
        [self upDataPlist];
        [self.tableView reloadData];
    };
    [inputView show];
    
}

#pragma mark - 初始化
- (BaseTableView *)setupTableView {
    BaseTableView *tableView = [[BaseTableView alloc] initWithFrame:CGRectMake(0.f, 0, kWindowWidth, kWindowHeight - kBottomBarHeight - kTabBarHeight) style:(UITableViewStyleGrouped)];
    [self.view addSubview:tableView];
    tableView.delegate = self;
    tableView.dataSource = self;
    tableView.sectionHeaderHeight = 30.f;
    tableView.sectionFooterHeight = 7.f;
    tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
    return tableView;
}

- (NSMutableArray *)cellDataSource {
    if (!_cellDataSource) {

        _cellDataSource = [NSMutableArray arrayWithObjects:[NSMutableArray array], [NSMutableArray array], [NSMutableArray array], [NSMutableArray array], nil];
//        _cellDataSource = [NSMutableArray array];
        NSString * plistPath = [self customStylesPlist];
        NSFileManager *fileManager = [NSFileManager defaultManager];
        ///检测plistPath路径是否存在
        BOOL isFile = [fileManager fileExistsAtPath:plistPath];
        /// 如果存在就读取配置的Plist数据
        if (isFile) {
            
            NSMutableArray * array = [NSMutableArray arrayWithContentsOfURL:[NSURL fileURLWithPath:plistPath]];
            NSLog(@"读取沙盒配置:%@", array);
            for (int i = 0; i < array.count; i++) {
                NSArray * nArray = array[i];
                for (NSDictionary * dic in nArray) {
                    [_cellDataSource[i] addObject:[MineTextTableCellModel yy_modelWithJSON:dic]];
                }
            }
        }
        else {
            /// 获取文件路径
            NSString *path = [[NSBundle mainBundle] pathForResource:@"superCustomStylesDataSource" ofType:@"plist"];
            NSMutableArray * array = [NSMutableArray arrayWithContentsOfFile:path];
            for (int i = 0; i < array.count; i++) {
                NSArray * nArray = array[i];
                NSLog(@"每组的数据：%@", nArray);
                for (NSDictionary * dic in nArray) {
                    [_cellDataSource[i] addObject:[MineTextTableCellModel yy_modelWithJSON:dic]];
                }
            }
            NSLog(@"读取默认的文件配置：%@", _cellDataSource);
            
            /// 如果文件不存在需要创建文件
            BOOL isCreatDir = [fileManager createDirectoryAtPath:plistPath withIntermediateDirectories:YES attributes:nil error:nil];
            if (!isCreatDir) {
                NSLog(@"create 自定义样式Plist failed");
            }
            else {
                NSLog(@"创建自定义样式Plist成功！");
            }
            
        }
//        NSArray *section0 = [NSArray arrayWithObjects:
//                             @{
//                                 @"cellType": [MineTextTableCell className],
//                                 @"title": @"聊天窗口UI样式",
//                                 @"value": @"",
//                                 @"selectionStyle": @(UITableViewCellSelectionStyleGray),
//                                 @"accessoryType": @(UITableViewCellAccessoryDisclosureIndicator)},
//                             @{
//                                 @"cellType": [MineTextTableCell className],
//                                 @"title": @"时间色值",
//                                 @"value": @"",
//                                 @"selectionStyle": @(UITableViewCellSelectionStyleGray),
//                                 @"accessoryType": @(UITableViewCellAccessoryDisclosureIndicator)},
//                             @{
//                                 @"cellType": [MineSwitchTableViewCell className],
//                                 @"title": @"客服/机器人昵称显示",
//                                 @"value": @"0",
//                                 @"selectionStyle": @(UITableViewCellSelectionStyleNone),
//                                 @"accessoryType": @(UITableViewCellAccessoryNone)},
//                             @{
//                                 @"cellType": [MineSwitchTableViewCell className],
//                                 @"title": @"访客昵称显示",
//                                 @"value": @"1",
//                                 @"selectionStyle": @(UITableViewCellSelectionStyleNone),
//                                 @"accessoryType": @(UITableViewCellAccessoryNone)},
//                             @{
//                                 @"cellType": [MineSwitchTableViewCell className],
//                                 @"title": @"客服/机器人头像显示",
//                                 @"value": @"1",
//                                 @"selectionStyle": @(UITableViewCellSelectionStyleNone),
//                                 @"accessoryType": @(UITableViewCellAccessoryNone)},
//                             @{
//                                 @"cellType": [MineSwitchTableViewCell className],
//                                 @"title": @"访客头像显示",
//                                 @"value": @"0",
//                                 @"selectionStyle": @(UITableViewCellSelectionStyleNone),
//                                 @"accessoryType": @(UITableViewCellAccessoryNone)}, nil];
//
//        NSArray *section1 = [NSArray arrayWithObjects:
//                             @{
//                                 @"cellType": [MineTextTableCell className],
//                                 @"title": @"输入框背景色值",
//                                 @"value": @"",
//                                 @"selectionStyle": @(UITableViewCellSelectionStyleGray),
//                                 @"accessoryType": @(UITableViewCellAccessoryDisclosureIndicator)},
//                             @{
//                                 @"cellType": [MineTextTableCell className],
//                                 @"title": @"输入区分割线",
//                                 @"value": @"",
//                                 @"selectionStyle": @(UITableViewCellSelectionStyleGray),
//                                 @"accessoryType": @(UITableViewCellAccessoryDisclosureIndicator)},
//                             @{
//                                 @"cellType": [MineTextTableCell className],
//                                 @"title": @"语音按钮色值",
//                                 @"value": @"",
//                                 @"selectionStyle": @(UITableViewCellSelectionStyleGray),
//                                 @"accessoryType": @(UITableViewCellAccessoryDisclosureIndicator)},
//                             @{
//                                 @"cellType": [MineTextTableCell className],
//                                 @"title": @"输入框暗文提示语",
//                                 @"value": @"",
//                                 @"selectionStyle": @(UITableViewCellSelectionStyleGray),
//                                 @"accessoryType": @(UITableViewCellAccessoryDisclosureIndicator)}, nil];
//
//        NSArray *section2 = [NSArray arrayWithObjects:
//                             @{
//                                 @"cellType": [MineSwitchTableViewCell className],
//                                 @"title": @"吐司提示背景色值",
//                                 @"value": @"",
//                                 @"selectionStyle": @(UITableViewCellSelectionStyleNone),
//                                 @"accessoryType": @(UITableViewCellAccessoryDisclosureIndicator)},
//                             @{
//                                 @"cellType": [MineTextTableCell className],
//                                 @"title": @"吐司提示文字色值",
//                                 @"value": @"",
//                                 @"selectionStyle": @(UITableViewCellSelectionStyleNone),
//                                 @"accessoryType": @(UITableViewCellAccessoryDisclosureIndicator)}, nil];
//
//        NSArray *section3 = [NSArray arrayWithObjects:
//                             @{
//                                 @"cellType": [MineTextTableCell className],
//                                 @"title": @"相册导航栏背景色值",
//                                 @"value": @"",
//                                 @"selectionStyle": @(UITableViewCellSelectionStyleNone),
//                                 @"accessoryType": @(UITableViewCellAccessoryDisclosureIndicator)},
//                             @{
//                                 @"cellType": [MineTextTableCell className],
//                                 @"title": @"相册导航栏文字色值",
//                                 @"value": @"",
//                                 @"selectionStyle": @(UITableViewCellSelectionStyleNone),
//                                 @"accessoryType": @(UITableViewCellAccessoryDisclosureIndicator)}, nil];
//
//
//        [_cellDataSource addObject:[NSMutableArray array]];
//
//        for (NSDictionary *dic in section0) {
//            [_cellDataSource[0] addObject:[MineTextTableCellModel yy_modelWithJSON:dic]];
//        }
//
//        for (NSDictionary *dic in section1) {
//            [_cellDataSource[1] addObject:[MineTextTableCellModel yy_modelWithJSON:dic]];
//        }
//
//        for (NSDictionary *dic in section2) {
//            [_cellDataSource[2] addObject:[MineTextTableCellModel yy_modelWithJSON:dic]];
//        }
//
//        for (NSDictionary *dic in section3) {
//            [_cellDataSource[3] addObject:[MineTextTableCellModel yy_modelWithJSON:dic]];
//        }
        
        
        
        
    }
    return _cellDataSource;
}

- (void)resetConfigClickEvent{
    NSLog(@"resetConfigClickEvent");
    //弹窗
    UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"恢复默认配置" message:@"确认要恢复默认配置吗，恢复后已修改的配置项将不会保存。" delegate:self cancelButtonTitle:@"取消" otherButtonTitles:@"确定", nil];
    
    [alertView show];
}

- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex//点击弹窗按钮后
{
    NSLog(@"buttonIndex:%ld",(long)buttonIndex);
 
    if (buttonIndex == 0) {//取消
        NSLog(@"取消");
    }else if (buttonIndex == 1){//确定
        NSLog(@"确定");
        [self resetConfigParams];
    }
}

-(void)resetConfigParams{
//    /// 获取文件路径
//    NSString *path = [[NSBundle mainBundle] pathForResource:@"superCustomStylesDataSource" ofType:@"plist"];
//    // plist 只记录默认值
//    NSArray <NSArray *>*array = [NSArray arrayWithContentsOfFile:path];
//    NSLog(@"默认配置文件 : %@", array);
    [self.cellDataSource removeAllObjects];
    self.cellDataSource = [NSMutableArray arrayWithObjects:[NSMutableArray array], [NSMutableArray array], [NSMutableArray array], [NSMutableArray array], nil];
    /// 获取文件路径
    NSString *path = [[NSBundle mainBundle] pathForResource:@"superCustomStylesDataSource" ofType:@"plist"];
    NSMutableArray * array = [NSMutableArray arrayWithContentsOfFile:path];
    for (int i = 0; i < array.count; i++) {
        NSArray * nArray = array[i];
        NSLog(@"每组的数据：%@", nArray);
        for (NSDictionary * dic in nArray) {
            [_cellDataSource[i] addObject:[MineTextTableCellModel yy_modelWithJSON:dic]];
        }
    }
    /// 更新沙盒路径下的配置数据
    [self upDataPlist];
    [DomainNameSave shareDomainNameSave].index = 0;
    [self.tableView reloadData];
    
    
//    self.dataSource = [[NSArray modelArrayWithClass:[superCustomStylesModel class] json:array] mutableCopy];
//    NSLog(@"data source = %@",self.dataSource);
//
//    superCustomStylesModel *model = self.dataSource[0];
//    NSLog(@"model = %@", [model yy_modelToJSONObject]);
    
//    [TOSKitCustomInfo shareCustomInfo].Chat_time_textColor = [self colorWithHexString:model.Chat_time_textColor alpha:1.f];
//    [TOSKitCustomInfo shareCustomInfo].Chat_tosRobotName_enable = model.Chat_tosRobotName_enable;
//    [TOSKitCustomInfo shareCustomInfo].Chat_visitorName_enable = model.Chat_visitorName_enable;
//    [TOSKitCustomInfo shareCustomInfo].Chat_tosRobot_portrait_enable = model.Chat_tosRobot_portrait_enable;
//    [TOSKitCustomInfo shareCustomInfo].Chat_visitor_portrait_enable = model.Chat_visitor_portrait_enable;
//    [TOSKitCustomInfo shareCustomInfo].ChatBox_backGroundColor = [self colorWithHexString:model.ChatBox_backGroundColor alpha:1.0f];
//    [TOSKitCustomInfo shareCustomInfo].ChatBox_lineColor = [self colorWithHexString:model.ChatBox_lineColor alpha:1.0f];
//
//    [TOSKitCustomInfo shareCustomInfo].VoiceButton_textColor = [self colorWithHexString:model.VoiceButton_textColor alpha:1.0f];
//    [TOSKitCustomInfo shareCustomInfo].ChatBox_textview_placeholder = model.ChatBox_textview_placeholder;
//    [TOSKitCustomInfo shareCustomInfo].Toast_backGroundColor = [self colorWithHexString:model.Toast_backGroundColor alpha:1.0f];
//    [TOSKitCustomInfo shareCustomInfo].Toast_textColor = [self colorWithHexString:model.Toast_textColor alpha:1.0f];
//    [TOSKitCustomInfo shareCustomInfo].imagePicker_naviBgColor = [self colorWithHexString:model.imagePicker_naviBgColor alpha:1.0f];
//    [TOSKitCustomInfo shareCustomInfo].imagePicker_barItemTextColor = [self colorWithHexString:model.imagePicker_barItemTextColor alpha:1.0f];
}

- (UIColor *)colorWithHexString:(NSString *)colorStr alpha:(CGFloat)alpha {
    NSString *cString = [[colorStr stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]] uppercaseString];
        
    NSRange range;
    range.location = 0;
    range.length = 2;
    NSString *rString = [cString substringWithRange:range];
    
    range.location = 2;
    NSString *gString = [cString substringWithRange:range];
    
    range.location = 4;
    NSString *bString = [cString substringWithRange:range];
    
    
    unsigned int r, g, b;
    [[NSScanner scannerWithString:rString] scanHexInt:&r];
    [[NSScanner scannerWithString:gString] scanHexInt:&g];
    [[NSScanner scannerWithString:bString] scanHexInt:&b];
    
    return kRGBAColor(r, g, b, alpha);
}

@end
