package com.github.com.PassCody.SFTPCrawler.SFTP;

import java.util.Properties;

public class CrawlerProperties {

    private Properties config;

    public CrawlerProperties() {
        this.config = new Properties();
    }

    public void addConfigPropertie(String key, String value) {
        this.config.put(key, value);
    }
    public void removeConfigPropertie(String key, String value) {
        this.config.remove(key, value);
    }


    public Properties getConfig() {
        return this.config;
    }
}
