package ncu.jinxiu.config.util;

public interface Constant {

    /*
    * 微信相关
     */
    // 请求的网址
    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";
    // 你的appid
    public static final String WX_LOGIN_APPID = "wxdd190f93ac28696b";
    // 你的密匙
    public static final String WX_LOGIN_SECRET = "b3b11a55d005052080071a25a723a7a7";
    // 固定参数
    public static final String WX_LOGIN_GRANT_TYPE = "authorization_code";

    /*
    * 腾讯地图
     */
    //key
    public static  final String  QQ_MAP_KEY = "XXOBZ-PKQWU-26BVI-4RPT3-LV5HQ-OIBQF";
    //计算坐标距离
    public static final String QQ_MAP_DISTANCE = "https://apis.map.qq.com/ws/distance/v1/?parameters";
    //搜索城市id
    public static final String QQ_MAP_SEARCH = "https://apis.map.qq.com/ws/district/v1/search";
    //获取行政划分
    public static final String QQ_MAP_DISTRICT = "https://apis.map.qq.com/ws/district/v1/getchildren";
    //地址转坐标
    public static final String QQ_MAP_TO = "https://apis.map.qq.com/ws/geocoder/v1/";

    /*
    *  图灵机器人
     */
    //api
    public static final String REBOOT_URL = "http://openapi.tuling123.com/openapi/api/v2";
    //apikey
    public static final String REBOOT_KEY = "469f6180d21646f8b99bd96797c11ece";

    //百度地图
    public static final String BAIDU_MAP_TO = "http://api.map.baidu.com/geocoding/v3/";
    public static final String BAIDU_MAP_AK = "qimDWkoOflbIZd01GrBOGwrfDNlwUj8S";

}
