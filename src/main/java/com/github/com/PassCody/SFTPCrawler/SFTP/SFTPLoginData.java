package com.github.com.PassCody.SFTPCrawler.SFTP;

public class SFTPLoginData {

    private final String SFTP_LOGIN_USER      = "";
    private final String SFTP_LOGIN_PASSWORD  = "";
    private final String SFTP_LOGIN_HOST      = "";
    private final String SFTP_LOGIN_PORT      = "";
    private final String SFTP_LOGIN_KEYSTORE  = "";
    private final String SFTP_CHANNEL_TYPE    = "";

    public String getSFTP_LOGIN_USER(){
        if (!this.SFTP_LOGIN_USER.equals("")) {
            return this.SFTP_LOGIN_USER;
        }
        return "";
    }
    public String getSFTP_LOGIN_PASSWORD() {
        if (!this.SFTP_LOGIN_PASSWORD.equals("")) {
            return this.SFTP_LOGIN_PASSWORD;
        }
        return "";
    }
    public String getSFTP_LOGIN_HOST() {
        if (!this.SFTP_LOGIN_HOST.equals("")) {
            return this.SFTP_LOGIN_HOST;
        }
        return "";
    }
    public String getSFTP_LOGIN_PORT() {
        if (!this.SFTP_LOGIN_PORT.equals("")) {
            return this.SFTP_LOGIN_PORT;
        }
        return "";
    }
    public String getSFTP_LOGIN_KEYSTORE() {
        if (!this.SFTP_LOGIN_KEYSTORE.equals("")) {
            return this.SFTP_LOGIN_KEYSTORE;
        }
        return "";
    }
    public String getSFTP_CHANNEL_TYPE() {
        if (!this.SFTP_CHANNEL_TYPE.equals("")) {
            return this.SFTP_CHANNEL_TYPE;
        }
        return "";
    }

}
