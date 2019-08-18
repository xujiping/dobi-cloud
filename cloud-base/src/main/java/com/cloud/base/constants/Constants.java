package com.cloud.base.constants;

import java.io.File;

/**
 * 公共静态类
 *
 * @author xujiping
 * @date 2018/6/8 16:59
 */
public interface Constants {

     String RESOURCE_SERVER_NAME = "chapter17-server";
     String INVALID_CLIENT_DESCRIPTION = "客户端验证失败，如错误的client_id/client_secret。";

    /**
     * 路径分隔符
     */
     String PATH_SEPARATOR = File.separator;
//     String PATH_SEPARATOR = "/";

    /**
     * auth code 超时时间（毫秒）
     */
     long CODE_EXPIRE_IN = 360000;

    /**
     * auth code redis key 前缀
     */
     String CODE_REDIS_KEY_PREFIX = "code_";

    /**
     * auth token 超时时间（毫秒）
     */
     long TOKEN_EXPIRE_IN = 360000;
     String REDIS_USER_TOKEN = "user_token";

     String SALT = "123456";

    /**
     * 用户福利详情：1收入 2支出
     */
     int WEAL_DETAIL_INCOME = 1;
     int WEAL_DETAIL_EXPEND = 2;

    /**
     * 用户福利详情类别：1金币 2零钱 3福豆
     */
     int WEAL_DETAIL_TYPE_GOLD = 1;
     int WEAL_DETAIL_TYPE_COIN = 2;
     int WEAL_DETAIL_TYPE_BEAN = 3;

    /**
     * 0不可用,1未付款,2已付款,3未发货,4已发货,5已签收,6退货申请,7退货中,8已退货,9取消交易
     */
     int ORDER_STATUS_DISABLED = 0;
     int ORDER_STATUS_NOT_PAY = 1;
     int ORDER_STATUS_PAYED = 2;
     int ORDER_STATUS_NOT_YET_SHIPPED = 3;
     int ORDER_STATUS_DELIVERED = 4;
     int ORDER_STATUS_SIGNED = 5;
     int ORDER_STATUS_RETURN_REQUEST = 6;
     int ORDER_STATUS_RETURNING = 7;
     int ORDER_STATUS_RETURN_FINISH = 8;
     int ORDER_STATUS_CANCEL = 9;
     int ORDER_STATUS_FINISH = 10;

    /**
     * 订单结算状态：1货到付款、2分期付款、在线结算
     */
     int ORDER_SETTLEMENT_STATUS_PAY_ON_DELIVERY = 1;
     int ORDER_SETTLEMENT_STATUS_INSTALLMENT = 2;
     int ORDER_SETTLEMENT_STATUS_FINISHED = 3;

    /**
     * 状态（normal：正常、audit：待审核、pass：通过、failed：未通过、block：冻结）
     */
     String STAT_ALL = "all";
     String STAT_NORMAL = "normal";
     String STAT_AUDIT = "audit";
     String STAT_PASS = "pass";
     String STAT_FAILED = "failed";
     String STAT_BLOCK = "block";

    /**
     * 状态：0不可用 1正常 2待初始化
     */
     int STATUS_UNENABLE = 0;
     int STATUS_NORMAL = 1;
     int STATUS_NOT_INIT = 2;


    /**
     * redis专辑信息
     */
     String REDIS_CLIENT_RELEASE = "client_release";

    /**
     * 头像文件存储所属子文件夹
     */
     String HEADER_FILE_PATH = "/header/";

    /**
     * 组织类型（个人、媒体、国家机构、企业、其他组织）
     */
     String ORIGIN_TYPE_PERSON = "个人";
     String ORIGIN_TYPE_MEDIA = "媒体";
     String ORIGIN_TYPE_COUNTRY = "国家机构";
     String ORIGIN_TYPE_COMPANY = "企业";
     String ORIGIN_TYPE_OTHER = "其他组织";

    /**
     * 是否同意
     */
     String AGREE = "true";
     String NOT_AGREE = "false";

    /**
     * 是否精品：1精品 0普通
     */
     int BOUTIQUE = 1;
     int NO_BOUTIQUE = 0;

    /**
     * 图片后缀
     */
     String PNG_SUFFIX = "png";

    /**
     * 短视频后缀
     */
     String SHORT_VIDEO_SUFFIX = "mp4";

    /**
     * 图集的目录
     */
     String ATLAS_PATH = "atlas";

    /**
     * 短视频目录
     */
     String SHORT_VIDEO_PATH = "shortVideo";

    /**
     * 分类
     */
     String CATEGORY_ALL = "all";
     String CATEGORY_ATLAS = "atlas";
     String CATEGORY_VIDEO = "video";

    /**
     * 等级
     */
     int LEVEL_1 = 1;
     int LEVEL_2 = 2;

    /**
     * 资源服务器ID
     */
     String RESOURCE_ID = "res-server";

    /**
     * 头部字段
     */
     String HEADER_TOKEN = "ucToken";
     String HEADER_ACCOUNT_ID = "ucAccountId";
     String HEADER_PLATFORM = "platform";


    /**
     * 渠道类型
     */
    int CHANNEL_TYPE_WX = 1;
}
