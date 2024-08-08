package com.github.com.PassCody.SFTPCrawler.Crawler;

import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipInputStream;

public class Ziper {

    private String source = "";
    private String destination = "";
    private String sourceFolder = "";

    public Ziper(String fileName, String preSource, String folder, String destinationFolder) {
        this.sourceFolder = folder;
        try {
            if (preSource.contains("\\")) {
                this.source = preSource + "\\" + this.sourceFolder;
                this.destination = preSource + "\\" + destinationFolder + "\\" + fileName;
                System.out.println("Start generating new ZIP File.");
                this.repackZipFile(this.source, this.destination);
                System.out.println("New ZIP File is generated.");
                this.removeFileFromZip(this.destination, folder+"/");
            } else {
                this.source = preSource + "/" + this.sourceFolder;
                this.destination = preSource + "/" + destinationFolder + "/" + fileName;
                repackGzFile(this.source, this.destination);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void repackZipFile(String sourceDir, String zipFilePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zipOut = new ZipOutputStream(fos)) {
            File fileToZip;
            if ((fileToZip = new File(sourceDir)).getName().equals(this.sourceFolder)) {
                zipFile(fileToZip, fileToZip.getName(), zipOut);
            }
        }
    }

    private void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/") && !this.sourceFolder.equals(fileToZip.getName())) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        ZipEntry zipEntry = null;
        try (FileInputStream fis = new FileInputStream(fileToZip)) {
            if (fileName.contains("/")) {
                fileName = fileName.replace(this.sourceFolder + "/", "");
                zipEntry = new ZipEntry(fileName);
            } else {
                fileName = fileName.replace(this.sourceFolder + "\\", "");
                zipEntry = new ZipEntry(fileName);
            }
            zipOut.putNextEntry(zipEntry);
            fis.transferTo(zipOut);

        }
    }

    private void removeFileFromZip(String zipFilePath, String fileToRemove) throws IOException {
        File tempFile = File.createTempFile("tempZip", ".zip");
        tempFile.deleteOnExit();
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath));
             ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(tempFile))) {
            ZipEntry entry;
            boolean fileFound = false;
            while ((entry = zis.getNextEntry()) != null) {
                System.out.println("Processing entry: " + entry.getName());
                if (entry.getName().equals(fileToRemove)) {
                    fileFound = true;
                    System.out.println("File to remove found: " + entry.getName());
                    continue;
                }
                zos.putNextEntry(new ZipEntry(entry.getName()));
                byte[] buffer = new byte[1024];
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
            }
            if (!fileFound) {
                System.out.println("File not found in the ZIP: " + fileToRemove);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        File originalFile = new File(zipFilePath);
        if (!originalFile.delete()) {
            throw new IOException("Could not delete original ZIP file");
        }
        if (!tempFile.renameTo(originalFile)) {
            throw new IOException("Could not rename temp ZIP file");
        }
        System.out.println("File removed successfully from ZIP.");
    }

    private void repackGzFile(String sourceFilePath, String gzFilePath) throws IOException {
        File inputFile = new File(sourceFilePath.replace("_", "."));
        File outputFile = new File(gzFilePath);
        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile);
             GzipCompressorOutputStream gos = new GzipCompressorOutputStream(fos)) {
            fis.transferTo(gos);
        }
    }
}
