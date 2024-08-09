package com.github.com.PassCody.SFTPCrawler.Crawler;

import com.github.com.PassCody.SFTPCrawler.SFTP.CrawlerChannel;

import java.io.File;

public class FileUploader {

    private String path;

    public FileUploader(CrawlerChannel channel, String localPath, String pathAddition, String remotePath) {
        this.path = localPath + "\\" + pathAddition;
        File directory = new File(this.path);
        File[] child = directory.listFiles();
        for (int i = 0; i < child.length; i++) {
            System.out.println("Uploading File: " + child[i].getAbsolutePath() + " to " + remotePath);
            channel.uploadFile(child[i].getAbsolutePath(), remotePath);
            System.out.println("Uploading is finished");
        }
    }
}
