package com.github.com.PassCody.SFTPCrawler.SFTP;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.util.Properties;

public class SFTPSession extends SFTPLoginData {
    private CrawlerClient client;
    private CrawlerSession session;
    private CrawlerProperties config;
    private CrawlerChannel channel;

    public SFTPSession() {
        super();
        this.client = new CrawlerClient();
        if (!super.getSFTP_LOGIN_KEYSTORE().equals("")) {
            this.client.setClientIdentity(super.getSFTP_LOGIN_KEYSTORE());
        }
        this.session = new CrawlerSession(super.getSFTP_LOGIN_USER(), super.getSFTP_LOGIN_HOST(), Integer.parseInt(super.getSFTP_LOGIN_PORT()), this.client);
        this.session.setSessionPassword(super.getSFTP_LOGIN_PASSWORD());
        this.config = new CrawlerProperties();
        this.config.addConfigPropertie("StrictHostKeyChecking", "no");
        this.session.conifgerSession(this.config);
    }

    public void connectToSFTP() {
        this.session.connect();
        this.channel = new CrawlerChannel(this.getCrawlerSession(), super.getSFTP_CHANNEL_TYPE());
    }
    public void disconnectFromSFTP() {
        this.channel.exitChannels();
        this.session.disconnect();
    }

    public CrawlerSession getCrawlerSession() {
        return this.session;
    }

    public CrawlerClient getCrawlerClient() {
        return this.client;
    }

    public CrawlerChannel getCrawlerChannel() {
        return this.channel;
    }
}