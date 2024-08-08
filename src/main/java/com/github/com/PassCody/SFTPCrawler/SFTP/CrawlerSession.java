package com.github.com.PassCody.SFTPCrawler.SFTP;


import com.jcraft.jsch.Session;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Channel;

public class CrawlerSession {

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
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }
    }
    public void disconnect() {
        this.session.disconnect();
    }

    public Channel openChannel(String channelType){
        try {
           return this.session.openChannel(channelType);
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }
    }
}
