package com.github.com.PassCody.SFTPCrawler.Crawler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermissions;

public class CrawlerFile {

    public String changePermissions(String fullFileName) {
        String returnValue = "";
        try {
            if (fullFileName.contains("\\")) {
                File file = new File(fullFileName);
                boolean success = file.setReadable(true, false) &&
                        file.setWritable(true, false) &&
                        file.setExecutable(true, false);
                returnValue = success ? "File permissions changed successfully." : "Failed to change file permissions.";
            } else {
                // Handle POSIX (Linux, macOS) file permissions
                Path path = Paths.get(fullFileName);
                Files.setPosixFilePermissions(path, PosixFilePermissions.fromString("rwxrwxrwx"));
                returnValue = "File permissions changed successfully.";
            }
        } catch (IOException e) {
            returnValue = "Failed to change file permissions: " + e.getMessage();
        }
        return returnValue;
    }

    public String renameFile(String oldName, String newName) {
        System.out.println("Old Filename: " + oldName);
        System.out.println("New Filename: " + newName);
        File oldFile = new File(oldName);
        File newFile = new File(newName);
        if (!oldFile.exists()) {
            return "File to rename does not exist.";
        }
        if (newFile.exists()) {
            return "A file with the new name already exists.";
        }
        System.out.println("Starting rename " + oldName + " to " + newFile);
        if (oldFile.renameTo(newFile)) {
            return "File renamed successfully.";
        } else {
            return "File couldn't be renamed.";
        }
    }

    public String deleteFile(String fullFileName) {
        File file = new File(fullFileName);
        System.out.println("Starting to delete " + fullFileName);
        if (!file.exists()) {
            return "File does not exist.";
        }
        if (file.delete()) {
            return "File deleted successfully.";
        } else {
            return "File couldn't be deleted.";
        }
    }
}
