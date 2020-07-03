package com.taobao.utils;

/**
 * Defines constants.
 */
public class Constants {

	public static final String EXTRA_ID_NEW_TAB = "EXTRA_ID_NEW_TAB";
	public static final String EXTRA_ID_URL = "EXTRA_ID_URL";
	public static final String EXTRA_ID_TITLE = "EXTRA_ID_TITLE";
	public static final String EXTRA_ID_MSG = "EXTRA_ID_MSG";
	public static final String EXTRA_ID_PUSH_TITLE = "EXTRA_ID_PUSH_TITLE";
	public static final String EXTRA_ID_OPEN = "EXTRA_ID_OPEN";
	public static final String EXTRA_ID_OPEN_TYPE = "EXTRA_ID_OPEN_TYPE";
	public static final String EXTRA_ID_OPEN_REQUESTCODE = "EXTRA_ID_OPEN_REQUESTCODE";
	public static final String EXTRA_ID_FULLPATH = "EXTRA_ID_FULLPATH";
	public static final String EXTRA_ID_MULITMSG = "EXTRA_ID_MULITMSG";
	public static final String EXTRA_ID_UID = "EXTRA_ID_UID";

	public static final String EXTRA_ID_HISTORY_ID = "EXTRA_ID_HISTORY_ID";
	public static final String EXTRA_ID_BOOKMARK_ID = "EXTRA_ID_BOOKMARK_ID";
	public static final String EXTRA_ID_BOOKMARK_URL = "EXTRA_ID_BOOKMARK_URL";
	public static final String EXTRA_ID_BOOKMARK_TITLE = "EXTRA_ID_BOOKMARK_TITLE";
	public static final String EXTRA_ID_BOOKMARK_TABINDEX = "EXTRA_ID_BOOKMARK_TABINDEX";

	public static final String EXTRA_ID_GESTURE_ID = "EXTRA_ID_GESTURE_ID";
	public static final String EXTRA_ID_GESTURE_URL = "EXTRA_ID_GESTURE_URL";
	public static final String EXTRA_ID_GESTURE_TITLE = "EXTRA_ID_GESTURE_TITLE";

	public static final String EXTRA_ID_CLOUD_TABINDEX = "EXTRA_ID_CLOUD_TABINDEX";
	
	public static final String EXTRA_ID_WALL_ID = "EXTRA_ID_WALL_ID";
	public static final String EXTRA_ID_WALL_TYPE = "EXTRA_ID_WALL_TYPE";
	public static final String EXTRA_ID_WALL_PATH = "EXTRA_ID_WALL_PATH";

	public static final String EXTRA_SAVED_URL = "EXTRA_SAVED_URL";
	
	public static final String EXTRA_ID_UPDATE_DOWN_NOTIFICATION = "EXTRA_ID_UPDATE_DOWN_NOTIFICATION";
	
	public static final String EXTRA_ID_HUODONG_ISLINK = "EXTRA_ID_HUODONG_ISLINK";
	public static final String EXTRA_ID_HUODONG_STEP = "EXTRA_ID_HUODONG_STEP";
	public static final String EXTRA_ID_HUODONG_NAME = "EXTRA_ID_HUODONG_NAME";
	public static final String EXTRA_ID_HUODONG_MOBILE = "EXTRA_ID_HUODONG_MOBILE";
	
	public static final int BOOKMARK_THUMBNAIL_WIDTH_FACTOR = 70;
	public static final int BOOKMARK_THUMBNAIL_HEIGHT_FACTOR = 60;

	/**
	 * serverapi
	 */

	public static final String URL_STAT_SERVER_HOST = "http://stat.browser.congzheli.com/";
	public static final String URL_STAT_POST_HOST = "http://update.huohou.com/api/v3";
	public static final String URL_AD_SERVER_HOST = "http://www.unionbrowser.com/";
	public static final String URL_M_HUOHOU_HOST = "http://m.huohou.cn/";
	
	public static final String URL_API_SERVER_HOST = URL_M_HUOHOU_HOST+"api/";	
	public static final String URL_API_SERVER_HOST2 = URL_M_HUOHOU_HOST+"api2/";	

	public static final String URL_HUOHOU_GAME_HOST = "http://agame.huohou.com/";
	public static final String URL_HUOHOU_WEB = "http://m.huohou.com/";
	public static final String URL_HUOHOU_WEIBO = "http://weibo.com/huohou1";
	public static final String URL_HUOHOU_EMAIL = "mailto:marketing@huohou.com";
	public static final String URL_TJ_POST_HOST = URL_STAT_SERVER_HOST+"android/v3";
	public static final String URL_WBCODE = "1000616"; // 1000612

	/**
	 * Specials urls.
	 */
	public static final String URL_ABOUT_BLANK = "about:blank";
	public static final String URL_ABOUT_START = "about:start";
	public static final String URL_ABOUT_GAME = "about:game";
	public static final String URL_ABOUT_MSG = "about:msg";
	public static final String URL_JAVASCRIPT = "javascript:";
	
	public static final String URL_SINA_IP_CITY = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json";
	public static final String URL_ABOUT_CLOUD_USERPAGE = "about:cloud#6";
	public static final String URL_ACTION_SEARCH = "action:search?egn=";
	public static final String URL_GOOGLE_MOBILE_VIEW = "http://www.google.com/gwt/x?u=%s";
	public static final String URL_GOOGLE_MOBILE_VIEW_NO_FORMAT = "http://www.google.com/gwt/x?u=";
	public static final String URL_BAIDU_MOBILE_VIEW = "http://gate.baidu.com/tc?from=opentc&tn=74039078_dg&src=%s";
	public static final String URL_BAIDU_MOBILE_VIEW_NO_FORMAT = "http://gate.baidu.com/tc?from=opentc";
	public static final String URL_BAIDU_TRANSLATE = "http://translate.baiducontent.com/transpage?tn=74039078_dg&source=url&from=auto&to=auto&query=[*]";
	public static final String URL_BAIDU_TRANSLATE_REFERER="http://fanyi.baidu.com/transpage?query="; 
	public static final String URL_BASE_DIR = "file:///android_asset/startpage/";
	public static final String URL_MIMETYPE = "text/html";
	public static final String URL_UTF_8 = "UTF-8";
	public static final String URL_GB2312 = "GB2312";
	public static final String URL_HTTP = "http://";
	public static final String URL_GAME_HOME_URL = URL_HUOHOU_GAME_HOST + "html/top-index_pt-1.html";
	public static final String URL_GAME_HOME_JSON_URL = URL_HUOHOU_GAME_HOST + "index_json.php";
	public static final String URL_GAME_DOWNLOAD_NOTIFY_URL = URL_HUOHOU_GAME_HOST + "extends/dlnotify.php";
	public static final String URL_NAVHTM = URL_BASE_DIR + "nav.htm";

	/**
	 * Search urls.
	 */
	public static String URL_SEARCH_GOOGLE = "http://www.google.com/search?ie=UTF-8&sourceid=navclient&gfns=1&q=%s";
	public static String URL_SEARCH_BAIDU = "http://www.baidu.com/s?mobile_se=1&tn=74039078_dg&word=%s";
	public static final String URL_SEARCH_TAOBAO = "http://r.m.taobao.com/s?p=mm_40308607_4230170_14150082&q=%s";
	public static final String URL_SEARCH_CIBA = "http://wap.iciba.com/cword/%s";
	public static final String URL_SEARCH_SOGOU = "http://wap.sogou.com/web/searchList.jsp?keyword=%s";
	public static final String URL_SEARCH_360 = "http://m.so.com/s?src=huohou&q=%s";
	
	public static final String URL_SEARCH_IMG_BAIDU = "http://wap.baidu.com/img?tn=bdwis&bd_page_type=1&word=%1$s#!/search/%1$s/0";   //tn=74039078_dg (no work)
	public static final String URL_SEARCH_IMG_360 = "http://m.image.so.com/i?src=huohou&q=%s";
	public static final String URL_SEARCH_IMG_SOGOU = "http://m.sogou.com/pic/searchList.jsp?keyword=%s";
	
	public static final String URL_SEARCH_VIDEO_SOKU = "http://www.soku.com/m/y/video?q=%s";
	public static final String URL_SEARCH_VIDEO_BAIDU = "http://m.video.baidu.com/#search=%s";
	public static final String URL_SEARCH_VIDEO_SOGOU = "http://m.tv.sohu.com/so?wd=%s";
	
	public static final String URL_SEARCH_NOVEL_EASOU = "http://book.easou.com/ta/search.m?q=%s";
	public static final String URL_SEARCH_NOVEL_YICHA = "http://book.yicha.cn/cnovel/sch.do?site=2145963982&key=%s";
	public static final String URL_SEARCH_NOVEL_QIDIAN = "http://sosu.qidian.com/searchresult.aspx?keyword=%s";
	public static final String URL_SEARCH_NOVEL_BAIDU = "http://duokoo.baidu.com/novel/?pageid=Gnfa8lrl&bd_page_type=1&word=%s";
	
	public static final String URL_SEARCH_SHOPPING_TAOBAO = "http://r.m.taobao.com/s?p=mm_40308607_4230170_14150082&q=%s";
	public static final String URL_SEARCH_SHOPPING_JD = "http://m.jd.com/ware/search.action?keyword=%s";
	public static final String URL_SEARCH_SHOPPING_DANGDANG = "http://m.dangdang.com/touch/search.php?keyword=%s";
	public static final String URL_SEARCH_SHOPPING_ETAO = "http://m.etao.com/#!/search/search.php?q=%s";

	public static final String URL_ID_HUOHOU_GAME = URL_HUOHOU_GAME_HOST + "html/appinfo-appinfo-%s.html";
	public static final String URL_VOICE_REDIRECT = "http://redirect.wiseie.com/vos?f=huohoubrowser&k=%s";
	public static final String URL_YIDA_SEARCH = "http://27.115.86.197:1081/search?pid=12&s=";	
	public static final String URL_IN_HUOHOU_DOMAIN_PATTERN = "^http://[\\w\\W]*?\\.huohou\\.com/[\\w\\W]*";
	public static final String EXTRA_ID_LAST_PUSH_MSG_ID = "EXTRA_ID_LAST_PUSH_MSG_ID";

	/**
	 * User agents.
	 */
	public static final String USER_AGENT_DEFAULT = "";
	public static final String USER_AGENT_IPHONE = "Mozilla/5.0 (iPhone; CPU iPhone OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A403 Safari/8536.25";
	public static final String USER_AGENT_IPAD = "Mozilla/5.0 (iPad; CPU OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A403 Safari/8536.25";
	public static final String USER_AGENT_DESKTOP = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/536.26 (KHTML, like Gecko) Chrome/7.0.517.44 Safari/8536.25";

	/**
	 * Preferences.
	 */
	public static final String PREFERENCES_GENERAL_HOME_PAGE = "GeneralHomePage";
	public static final String PREFERENCES_GENERAL_SEARCH_URL = "GeneralSearchUrl";
	public static final String PREFERENCES_GENERAL_SWITCH_TABS_METHOD = "GeneralSwitchTabMethod";
	public static final String PREFERENCES_GENERAL_BARS_DURATION = "GeneralBarsDuration";
	public static final String PREFERENCES_GENERAL_BUBBLE_POSITION = "GeneralBubblePosition";
	public static final String PREFERENCES_SHOW_FULL_SCREEN = "GeneralFullScreen";
	public static final String PREFERENCES_SHOW_TOAST_ON_TAB_SWITCH = "GeneralShowToastOnTabSwitch";

	public static final String PREFERENCES_UI_VOLUME_KEYS_BEHAVIOUR = "GeneralVolumeKeysBehaviour";

	public static final String PREFERENCES_DEFAULT_ZOOM_LEVEL = "DefaultZoomLevel";

	public static final String PREFERENCES_BROWSER_HISTORY_SIZE = "BrowserHistorySize";
	public static final String PREFERENCES_BROWSER_ENABLE_JAVASCRIPT = "BrowserEnableJavascript";
	public static final String PREFERENCES_BROWSER_ENABLE_IMAGES = "BrowserEnableImages";
	public static final String PREFERENCES_BROWSER_USE_WIDE_VIEWPORT = "BrowserUseWideViewPort";
	public static final String PREFERENCES_BROWSER_LOAD_WITH_OVERVIEW = "BrowserLoadWithOverview";
	public static final String PREFERENCES_BROWSER_SHAKE_TO_VOICE = "BrowserShakeToVoice";
	public static final String PREFERENCES_BROWSER_OPEN_NEW_WINDOW = "BrowserOpenNewWindow";
	public static final String PREFERENCES_BROWSER_ENABLE_FORM_DATA = "BrowserEnableFormData";
	public static final String PREFERENCES_BROWSER_ENABLE_PASSWORDS = "BrowserEnablePasswords";
	public static final String PREFERENCES_BROWSER_ENABLE_COOKIES = "BrowserEnableCookies";
	public static final String PREFERENCES_BROWSER_USER_AGENT = "BrowserUserAgent";
	public static final String PREFERENCES_BROWSER_ENABLE_PLUGINS_ECLAIR = "BrowserEnablePluginsEclair";
	public static final String PREFERENCES_BROWSER_ENABLE_PROXY_SETTINGS = "BrowserEnableProxySettings";
	public static final String PREFERENCES_BROWSER_ENABLE_PLUGINS = "BrowserEnablePlugins";
	public static final String PREFERENCES_BROWSER_RESTORE_LAST_PAGE = "PREFERENCES_BROWSER_RESTORE_LAST_PAGE";
	public static final String PREFERENCES_BROWSER_DOUBLEFINGER ="BrowserDoubleFinger";
	public static final String PREFERENCES_BROWSER_SENDBUGREPORTS ="BrowserSendBugReports";
	public static final String PREFERENCES_BROWSER_SET_DEFAULT_BROWSER ="SetDefaultBrowser";
	public static final String PREFERENCES_BROWSER_SHOW_FLOAT_WINDOW ="ShowFloatWindow";
	public static final String PREFERENCES_BROWSER_FONT_SIZE = "BrowserFontSize";
	public static final String PREFERENCES_BROWSER_WIFI_UPDATE = "BrowserWifiUpdate";
	public static final String PREFERENCES_BROWSER_USER_AGENT_ID = "BrowserUserAgentId";
	public static final String PREFERENCES_BROWSER_PRIVACY_INPUT = "BrowserPrivacyInput";
	public static final String PREFERENCES_BROWSER_PRIVACY_RECENT = "BrowserPrivacyRecent";
	public static final String PREFERENCES_BROWSER_PRIVACY_HISTORY = "BrowserPrivacyHistory";
	public static final String PREFERENCES_BROWSER_PRIVACY_ACCOUNT = "BrowserPrivacyAccount";
	public static final String PREFERENCES_BROWSER_PRIVACY_CACHE = "BrowserPrivacyCache";
	public static final String PREFERENCES_BROWSER_PRIVACY_COOKIES = "BrowserPrivacyCookies";
	public static final String PREFERENCES_BROWSER_PUSH_MSG = "BrowserPushMsg";

	public static final String PREFERENCES_PRIVACY_CLEAR_CACHE_ON_EXIT = "PrivacyClearCacheOnExit";
	public static final String PREFERENCES_ADBLOCKER_ENABLE = "AdBlockerEnable";
	public static final String PREFERENCES_BOOKMARKS_SORT_MODE = "BookmarksSortMode";
	public static final String PREFERENCES_LAST_VERSION_CODE = "LastVersionCode";
	public static final String PREFERENCES_NIGHTMODE = "PREFERENCES_NIGHTMODE";

	public static final String PREFERENCES_START_PAGE_SHOW_CONGZHELI = "StartPageEnableCongzheli";
	public static final String PREFERENCES_START_PAGE_SHOW_SEARCH = "StartPageEnableSearch";
	public static final String PREFERENCES_START_PAGE_SHOW_BOOKMARKS = "StartPageEnableBookmarks";
	public static final String PREFERENCES_START_PAGE_SHOW_HISTORY = "StartPageEnableHistory";
	public static final String PREFERENCES_START_PAGE_BOOKMARKS_LIMIT = "StartPageBookmarksLimit";
	public static final String PREFERENCES_START_PAGE_HISTORY_LIMIT = "StartPageHistoryLimit";

	public static final String PREFERENCES_COMM_UUID = "PREFERENCES_COMM_UUID";
	public static final String PREFERENCES_COMM_LOGOID = "PREFERENCES_COMM_LOGOID";
	public static final String PREFERENCES_COMM_NAVTOPID = "PREFERENCES_COMM_NAVTOPID";
	public static final String PREFERENCES_COMM_NAVSITEID = "PREFERENCES_COMM_NAVSITEID";
	public static final String PREFERENCES_COMM_ADID = "PREFERENCES_COMM_ADID";
	public static final String PREFERENCES_COMM_PUSHID = "PREFERENCES_COMM_PUSHID";
	public static final String PREFERENCES_COMM_GAMEID = "PREFERENCES_COMM_GAMEID";
	public static final String PREFERENCES_COMM_NAVIDS = "PREFERENCES_COMM_NAVIDS";
	public static final String PREFERENCES_COMM_PRODUCTID = "PREFERENCES_COMM_PRODUCTID";
	public static final String PREFERENCES_COMM_CHANNEL = "PREFERENCES_COMM_CHANNEL";
	public static final String PREFERENCES_COMM_ADVALUE = "PREFERENCES_COMM_ADVALUE";
	public static final String PREFERENCES_COMM_GAMEVALUE = "PREFERENCES_COMM_GAMEVALUE";
	public static final String PREFERENCES_COMM_MSG_ID = "PREFERENCES_COMM_MSG_ID";
	
	public static final String LAST_COMM_MSG = "LAST_COMM_MSG";
	
	public static final String PREFERENCES_COMM_OPEN_TIME = "PREFERENCES_COMM_OPEN_TIME";
	
	public static final String UI_WALL_ID = "UI_WALL_ID";
	public static final String UI_WALL_PATH = "UI_WALL_PATH";
	public static final String UI_WALL_TYPE = "UI_WALL_TYPE";
	public static final String UI_BOTTOMBAR_ID = "UI_BOTTOMBAR_ID";
	public static final String UI_BOTTOMBARMODE_ID = "UI_BOTTOMBARMODE_ID";
	public static final String GESTURELIB = "gestures";
	public static final String GESTUREINI = "gesture.ini";
	public static final String GESTURESECTIONS = "sites";
	public static final String UPDATE_IGNORE_THIS_VER = "UPDATE_IGNORE_THIS_VER";
	public static final String ORIENTATION  = "ORIENTATION";
	public static final String NOHISTORY  = "NOHISTORY";
	public static final String BOOKMARK_UPTIME = "BOOKMARK_UPTIME";
	public static final String NINENAV_UPTIME = "NINENAV_UPTIME";
	public static final String GESTURE_UPTIME = "GESTURE_UPTIME";
	public static final String CFG_UPTIME = "CFG_UPTIME";
	public static final String WALL_USE_TIPS = "WALL_USE_TIPS";
	public static final String HUODONG_SHOW = "HUODONG_SHOW";	
	
	public static final String USER_LOGIN_INFO  = "USER_LOGIN_INFO";
	
	public static final String PREFERENCES_GAME_JSON_UPDATEVER = "GameJsonUpdateVer";

	public static final String PREFERENCE_BOOKMARKS_DATABASE = "PREFERENCE_BOOKMARKS_DATABASE";
	public static final String PREFERENCE_NAV_ITEM = "PREFERENCE_NAV_ITEM_";
	public static final String AD_STR = "AD_STR";
	public static final String AD_UPID = "AD_UPID";
	public static final String AD_UP_TIME = "AD_UP_TIME";
	public static final String PUSH_ID = "PUSH_ID";
	public static final String PUSH_TIME = "PUSH_TIME";
	public static final String PUSH_NEWS_ID = "PUSH_NEWS_ID";
	public static final String PUSH_TASK_ID = "PUSH_TASK_ID";
	public static final String NEW_WINDOW_NAVGATE = "NEW_WINDOW_NAVGATE";
	public static final String EXIT_CLEAR_HISTORY = "EXIT_CLEAR_HISTORY";
	public static final String EXIT_DONT_PROMPT = "EXIT_DONT_PROMPT";
	public static final String SERVICE_CMD_STR= "cmd";
	
	public static final String PLG_LIST = "PLG_LIST";

	public static final String COMM_LAYOUT_BOOKMARK_TAG = "comm_bookmarks_history";
	public static final String COMM_LAYOUT_MAP_NEAR_TAG = "comm_map_near";
	public static final String COMM_LAYOUT_LOADERROR_TAG = "comm_load_error";
	public static final String APKFILEEXT = ".apk";
	public static final int SDCARD_TYPE=0;
	public static final int MEMORY_TYPE=1;
	public static final String STR_HUOHOU_BROWSER = "huohoubrowser";
	public static final String STR_SCREENSHOTS = "screenshots";
	public static final String STR_PROPERTIES = "properties";
	public static final String INFO_APP = "app";
	public static final String INFO_OUTPUT = "output";
	public static final String INFO_LOGO = "logo";
	public static final String INFO_NAVTOP = "navtop";
	public static final String INFO_NAVSITE = "navsite";
	public static final String INFO_AD = "ad";
	public static final String INFO_PUSH = "push";
	public static final String INFO_GAME = "game";
	public static final String INFO_CHANNEL = "channel";
	public static final String INFO_PRODUCT = "product";
	public static final String INFO_UUID = "uuid";
	public static final String INFO_MAC = "mac";
	public static final String INFO_IMEI = "imei";
	public static final String INFO_TEL = "tel";
	public static final String INFO_SIMID = "simid";
	public static final String INFO_IMSI = "imsi";
	public static final String INFO_SDK = "sdk";
	public static final String INFO_MODEL = "model";
	public static final String INFO_RELEASE = "release";
	public static final String INFO_IP = "ip";
	public static final String INFO_NAVID = "navid";
	
	public static final String JSON_OBJ_APP="app";
	public static final String JSON_OBJ_LOGO="logo";
	public static final String JSON_OBJ_NAVTOP="navtop";
	public static final String JSON_OBJ_NAVSITE="navsite";
	public static final String JSON_OBJ_AINFO="ainfo";
	public static final String JSON_OBJ_PINFO="pinfo";
	public static final String JSON_OBJ_OPTION="option";
	public static final String JSON_OBJ_GAME="game";
	public static final String JSON_OBJ_NAVMAXID="navmaxid";
	
	public static final String FIRST_NAV_VOS="FIRST_NAV_VOS";
	public static final String FIRST_NAV_URL_VOS="FIRST_NAV_URL_VOS";
	public static final String FIRST_NAV_URL_NAV="FIRST_NAV_URL_NAV";
	
	public static final String API_JSON_OBJECT="object";
	public static final String API_JSON_TIME="time";
	public static final String API_JSON_SIGN="sign";
	public static final String API_JSON_AID="aid";
	public static final String API_JSON_OID="olduid";
	public static final String API_JSON_USERDATA="userdata";
	public static final String API_JSON_RET_MSG="msg";
	public static final String API_JSON_RET_MSG_STATUS="status";
	public static final String API_JSON_RET_MESSAGE="message";
	public static final String API_JSON_RET_AMOUNT="amount";
	public static final String API_JSON_RET_DATA="data";
	public static final int API_HD_ID=1;
	public static final int API_HD_TWC_ID=2;
	public static final String SYSWLAN0PATH = "sys/class/net/wlan0/address";
	public static final String SYSETH0PATH = "sys/class/net/eth0/address";
	public static final String HD_TWC_LAST_GUESS = "HD_TWC_LAST_GUESS";
	public static final String HD_TWC_GUESS_RED = "HD_TWC_GUESS_RED";
	public static final String HD_TWC_IS_SHARE = "HD_TWC_IS_SHARE";
	public static final String HD_TWC_FLOAT_X = "HD_TWC_FLOAT_X";
	public static final String HD_TWC_FLOAT_Y = "HD_TWC_FLOAT_Y";
	public static final String HD_TWC_ALLROW = "HD_TWC_ALLROW";
	public static final String HD_TWC_GUESS_ITEM="HD_TWC_GUESS_ITEM";
	public static final String HD_TWC_SHARE_TYPE="HD_TWC_SHARE_TYPE";
	public static final String HD_TWC_FRAGMENT_ID="HD_TWC_FRAGMENT_ID";
//	public static final String API_VOICE_APP_ID=SpeechConstant.APPID+"=538ee03f";
	public static final String STR_API_YIDA_DOMAIN="domain";
	public static final String STR_API_YIDA_INTENT="intent";
	
	public static int LOADING_PAGESIZE = 10;//Ӧ�������б�ÿ�μ��ص�����
	
	/**
	 * Methods.
	 */

	/**
	 * Initialize the search url "constants", which depends on the user local.
	 * 
	 * @param context
	 *            The current context.
	 */
//	public static void initializeConstantsFromResources(Context context) {
//		URL_SEARCH_BAIDU = context.getResources().getString(R.string.Constants_SearchUrlBaidu);
//		URL_SEARCH_GOOGLE = context.getResources().getString(R.string.Constants_SearchUrlGoogle);
//	}
	
	/**
	 * Ӧ������(AppCenterActivity)
	 * 
	 */
	public static final String APPDATA_FILE_PARISED_ITEM = "PARISED";
	public static final String APPDATA_FILE_RECOMMEND = "RECOMMEND";
	public static final String APPDATA_FILE_HOT = "HOT";
	public static final String APPDATA_FILE_NEW = "NEW";
	public static final String APPDATA_ASSETS_PATH= "appplg/APPDATA";
	public static final int APP_DETAIL_REQUEST_CODE= 11;
	public static final int APP_DETAIL_RESULT_CODE_1= 1;//����
	public static final int APP_DETAIL_RESULT_CODE_2= 2;//���
	
	public static final int WALL_PAPER_LOAD_PAGESIZE= 12;//���
	public static final int WALL_PAPER_RECOMMEND_CLSID= 1;//�Ƽ���ֽ
	public static final int WALL_PAPER_HUOHOU_BABY_CLSID= 9;//��ﱦ��
	
	
	public static final int API_CODE_GET_AUTH_COUD = 1; 
	public static final int API_CODE_TEST_AUTH_COUD = 2; 
	public static final int API_CODE_PASSWORD = 3; 
	public static final int API_CODE_LOGIN = 4; 
	public static final int API_CODE_GET_USERINFO = 5; 
	public static final int API_CODE_POST_USERHEAD = 6; 
	public static final String MOBILE= "mobile";
	public static final String USER_NICK= "nick";
	public static final String USER_SIGNATURE = "signature";
	
	
	public static final String PAGEINDEX = "pageindex";
	public static final String PAGESIZE = "pagesize";
	public static final int API_CODE_POST_PAY = 7;
	public static final int API_CODE_POST_CHARGE = 8;
	public static final int API_CODE_MODIFY_USER_INFO = 9;
	public static final int API_CODE_DEVICE_INFO = 12;
	public static final int API_GET_CARD_INFO = 14;
	public static final int API_GET_BIND_AUTH_CUDE = 15;
	public static final int API_GET_EXP = 16;
	
	//long main card
	public static final String CARD_MODEL = "card_model_";
	public static final String CARD_TODAY_HOT_NEWS_STRING = CARD_MODEL+"2";
	public static final String CARD_HOT_VIDEO_STRING = CARD_MODEL+"3";
	public static final String CARD_BAIDU_NEWS_STRING = CARD_MODEL+"4";
	public static final String CARD_HOT_MODULE_STRING = CARD_MODEL+"5";
	public static final String CARD_JOKE_STRING = CARD_MODEL+"6";
	public static final String CARD_GAME_STRING = CARD_MODEL+"7";
	public static final String CARD_NOVEL_STRING = CARD_MODEL+"8";
	public static final String CARD_MOST_VISITED_SIRING = CARD_MODEL+"13";
	public static final String CARD_HOT_WEB_SITE_STRING = CARD_MODEL+"9";
	public static final String CARD_TOOLS_STRING = CARD_MODEL+"10";
	public static final String CARD_ADV= CARD_MODEL+"11";

	
}
