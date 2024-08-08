package com.github.com.PassCody.SFTPCrawler.SFTP;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;

public class CrawlerChannel {

    Channel channel;
    ChannelSftp sftpChannel;

    public CrawlerChannel(CrawlerSession session, String channelType){
        this.channel = session.openChannel(channelType);
        this.sftpChannel = (ChannelSftp) channel;
    }

    public void exitChannels() {
        this.channel.disconnect();
        this.sftpChannel.disconnect();
    }

    public void uploadFile(String localFile, String remoteFile) {
        try {
            this.sftpChannel.put(localFile, remoteFile);
        } catch (SftpException e) {
            throw new RuntimeException(e);
        }
    }

    public void downloadFile(String localFile, String remoteFile) {
        try {
            this.sftpChannel.get(remoteFile, localFile);
        } catch (SftpException e) {
            throw new RuntimeException(e);
        }
    }

}
