package com.github.com.PassCody.SFTPCrawler.Crawler;

import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Unziper {

    private String zipFilePath = "";
    private String destDir = "";

    public Unziper(String fileName, String localPath, String newFoldeName) {
        try {
            if (localPath.contains("\\")) {
                zipFilePath = localPath + "\\" + fileName;
                destDir = localPath + "\\" + newFoldeName;
            } else {
                zipFilePath = localPath + "/" + fileName;
                destDir = localPath + "/" + newFoldeName;
            }
            String fileNameToCheck = fileName.toLowerCase();
            System.out.println("Start Unziping " + zipFilePath + ".");
            if (fileNameToCheck.contains(".zip") || fileNameToCheck.contains(".rar") || fileNameToCheck.contains(".7z")) {
                this.unpackZipFile(zipFilePath, destDir);
            } else {
                this.unpackGzFile(zipFilePath, destDir);
            }
            System.out.println("Unziping of " + zipFilePath + " is finished.");
        } catch (IOException e) {
            try {
                throw new RuntimeException(e);
            } catch (RuntimeException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void unpackZipFile(String zipFilePath, String destDir) throws IOException {
        File dir = new File(destDir);
        if (!dir.exists()) dir.mkdirs();
        ZipFile zipFile = new ZipFile(zipFilePath);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            File entryDestination = new File(destDir, entry.getName());
            if (entry.isDirectory()) {
                entryDestination.mkdirs();
            } else {
                entryDestination.getParentFile().mkdirs();
                try (InputStream in = zipFile.getInputStream(entry);
                     OutputStream out = new FileOutputStream(entryDestination)) {
                    in.transferTo(out);
                }
            }
        }
    }

    private void unpackGzFile(String gzFilePath, String destDir) throws IOException {
        File inputFile = new File(gzFilePath);
        File outputFile = new File(destDir.replace("_", "."));
        try (FileInputStream fis = new FileInputStream(inputFile);
             GzipCompressorInputStream gis = new GzipCompressorInputStream(fis);
             FileOutputStream fos = new FileOutputStream(outputFile)) {
            gis.transferTo(fos);
        }
    }

    public String getZipFilePath() {
        return this.zipFilePath;
    }
    public String getDestDir() {
        return this.destDir;
    }
}
