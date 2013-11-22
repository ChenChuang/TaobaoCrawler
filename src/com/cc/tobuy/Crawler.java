package com.cc.tubuy;

public class Crawler {

	private static String testUrl = "http://gw.api.tbsandbox.com/router/rest";
	
    protected static String appkey = "1021550148";
    protected static String secret = "sandboxb88d662a099374674db02dcc6";
    
    protected static String url = testUrl;
    
    ItemcatsGetter mItemcatsGetter = new ItemcatsGetter();
    ItempropsGetter mItempropsGetter = new ItempropsGetter();
}
