package com.github.com.PassCody.SFTPCrawler.SFTP;


import com.jcraft.jsch.Session;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Channel;

public class CrawlerSession {

    public boolean isConnected = false;
    private Session session;

    public CrawlerSession(String username, String host, int port, CrawlerClient client) {
        try {
            this.session = client.getClient().getSession(username, host, port);
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSessionPassword(String password){
        this.session.setPassword(password);
    }

    public void conifgerSession(CrawlerProperties config) {
        this.session.setConfig(config.getConfig());
    }

    public void connect() {
        try {
            this.session.connect();
            this.isConnected = true;
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }
    }
    public void disconnect() {
        if (isConnected) {
            this.session.disconnect();
            this.isConnected = false;
        }
    }

    public Channel openChannel(String channelType){
        try {
           return this.session.openChannel(channelType);
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }
    }
}
