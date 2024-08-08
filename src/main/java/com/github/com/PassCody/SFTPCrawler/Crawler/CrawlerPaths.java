package com.github.com.PassCody.SFTPCrawler.Crawler;

public class CrawlerPaths {

    private String CRAWLER_LOCAL_PATH   = "D:\\Temp\\SFTPCrawler\\Test";
    private String CRAWLER_REMTOE_PATH  = "/home/Crawler/Test";

    public  String getCRAWLER_LOCAL_PATH() {
        if (!this.CRAWLER_LOCAL_PATH.equals("")) {
            return this.CRAWLER_LOCAL_PATH;
        }
        return "";
    }
    public  String getCRAWLER_REMTOE_PATH() {
        if (!this.CRAWLER_REMTOE_PATH.equals("")) {
            return this.CRAWLER_REMTOE_PATH;
        }
        return "";
    }
}
