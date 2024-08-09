package com.github.com.PassCody.SFTPCrawler.Crawler;

import com.github.com.PassCody.SFTPCrawler.SFTP.CrawlerSession;
import com.github.com.PassCody.SFTPCrawler.SFTP.SFTPSession;
import com.jcraft.jsch.ChannelSftp;

import java.io.File;

public class Crawler extends CrawlerPaths{

    private SFTPSession sftp;
    private Unziper unziper;
    private Ziper ziper;
    private CrawlerFile cFile = new CrawlerFile();
    private FileUploader uploader;

    public Crawler(SFTPSession sftp) {
        super();
        sftp = new SFTPSession();
        sftp.connectToSFTP();
        for (ChannelSftp.LsEntry entry : sftp.getCrawlerChannel().readRemotePath(super.getCRAWLER_REMTOE_PATH())) {
            String newRemotePath = "";
            String fileName = entry.getFilename();
            if (fileName.toLowerCase().contains(".zip") || fileName.toLowerCase().contains(".gz") ) {
                if (super.getCRAWLER_REMTOE_PATH().contains("/")) {
                    newRemotePath = super.getCRAWLER_REMTOE_PATH()+"/"+fileName;
                } else {
                    newRemotePath = super.getCRAWLER_REMTOE_PATH()+"\\"+fileName;
                }
                sftp.getCrawlerChannel().downloadFile(super.getCRAWLER_LOCAL_PATH(), newRemotePath);
                this.unziper = new Unziper(fileName, super.getCRAWLER_LOCAL_PATH(), "Unpacking");
                this.ziper  = new Ziper(fileName, super.getCRAWLER_LOCAL_PATH(), "Unpacking", "newZIPFiles");
            }
        }
        this.uploader = new FileUploader(sftp.getCrawlerChannel(),super.getCRAWLER_LOCAL_PATH(), "newZIPFiles", super.getCRAWLER_REMTOE_PATH()+"/NewFiles/");
        sftp.disconnectFromSFTP();
    }
}
