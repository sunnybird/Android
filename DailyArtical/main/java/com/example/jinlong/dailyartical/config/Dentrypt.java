package com.example.jinlong.dailyartical.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by JinLong on 2015/2/8.
 */
public class Dentrypt {
    public static void encrypt(String orginpath, String newpath, String key,
                               int index) throws IOException {

        File orginfile = new File(orginpath);
        File newfile = new File(newpath);

        char keychar = key.charAt(index);

        byte[] bytebuffer = new byte[512];
        int n = 0;

        FileInputStream input = new FileInputStream(orginfile);
        FileOutputStream output = new FileOutputStream(newfile);
        while ((n = (input.read(bytebuffer))) != -1) {

            for (int i = 0; i < n; i++) {

                bytebuffer[i] = (byte) (bytebuffer[i] ^ keychar);

            }
            output.write(bytebuffer);

        }
        if (input != null) {
            input.close();
        }
        if (output != null) {

            output.close();
        }

    }
}
