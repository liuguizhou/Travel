package com.travel.liuyun.network;

import java.io.*;

/**
 * User: Ryan
 * Date: 11-3-17
 * Time: 上午10:38
 */
public class FormFile {
    /* 文件名称 */
    private String fileName;
    /* 字段名称*/
    private String name;
    /* 内容类型 */
    private String contentType = "application/octet-stream"; //需要查阅相关的资料
    private File file;

    @SuppressWarnings({"ResultOfMethodCallIgnored"})
    public FormFile(String name, String fileName) throws IOException {
        fileName.replace('\\', '/');
        if (fileName.indexOf('/') != -1) {
            this.fileName = fileName.substring(fileName.lastIndexOf('/') + 1);
        } else {
            this.fileName = fileName;
        }
        file = new File(fileName);
        contentType = parseContentType(this.fileName);
        this.name = name;
    }

    private static String parseContentType(String filname) {
        if (filname != null && filname.matches("(?i).*?\\.(png|jpg|gif|bmp)")) {
            return "image/" + filname.substring(filname.lastIndexOf(".") + 1).toLowerCase();
        } else {
            return "application/octet-stream";
        }
    }

    public InputStream getInputStream() {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getFileName() {
        return fileName;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }
}
