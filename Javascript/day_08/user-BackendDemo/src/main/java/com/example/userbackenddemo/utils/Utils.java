package com.example.userbackenddemo.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Utils {


    public String genaratePassword(int count){
        return RandomStringUtils.random(count,false,true);
    }

    public static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }
    public static boolean checkFileExtension(String fileExtention){
        List<String> fileExtentions = Arrays.asList("png","jpg","jpeg");
        return fileExtentions.contains(fileExtention);
    }
}
