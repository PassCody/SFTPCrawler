package com.github.com.PassCody.SFTPCrawler.SFTP;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import java.util.Vector;

public class CrawlerChannel {

    Channel channel;
    ChannelSftp sftpChannel;

    public CrawlerChannel(CrawlerSession session, String channelType){
        try {
            if (!session.isConnected) {
                throw new IllegalStateException("Session is not connected. Cannot open channel.");
            }
            this.channel = session.openChannel(channelType);
            this.sftpChannel = (ChannelSftp) channel;
            this.sftpChannel.connect();
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }
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
            System.out.println("Download successfully.");
        } catch (SftpException e) {
            throw new RuntimeException(e);
        }
    }

    public Vector<ChannelSftp.LsEntry> readRemotePath(String remotePath) {
        if (this.sftpChannel == null || !this.sftpChannel.isConnected()) {
            throw new IllegalStateException("SFTP-Channel ist nicht verbunden.");
        }
        Vector<ChannelSftp.LsEntry> fileList = new Vector<>();
        try {
            fileList = sftpChannel.ls(remotePath);
        } catch (SftpException e) {
            System.err.println("Fehler beim Lesen des Remote-Pfades: " + e.getMessage());
            System.err.println("Fehlercode: " + e.id);
            e.printStackTrace(); // Stacktrace für detaillierte Fehlersuche ausgeben
        }
        return fileList;
    }

}
