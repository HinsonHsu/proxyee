package com.github.monkeywie.proxyee.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class FileUtil {

    public static String readFile(String path) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            String str;
            while ((str = in.readLine()) != null) {
                sb.append(str).append("\n");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return sb.toString();
    }

}
