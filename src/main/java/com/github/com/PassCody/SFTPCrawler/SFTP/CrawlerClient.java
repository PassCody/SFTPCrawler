package com.github.com.PassCody.SFTPCrawler.SFTP;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;

public class CrawlerClient {

    private JSch client;

    public CrawlerClient() {
        this.client = new JSch();
    }

    public JSch getClient() {
        return this.client;
    }

    public void setClientIdentity(String identity) {
        try {
            this.getClient().addIdentity(identity);
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }
    }
}
