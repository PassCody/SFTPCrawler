package com.github.com.PassCody.SFTPCrawler;

import com.github.com.PassCody.SFTPCrawler.Crawler.Crawler;
import com.github.com.PassCody.SFTPCrawler.SFTP.*;

public class Main {
    private Crawler crawler;

    public Main () {
        this.crawler = new Crawler(new SFTPSession);
    }

    public static void main(String[] args) {
        new Main();
    }
}
