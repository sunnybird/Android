package com.example.jinlong.dailyartical.util;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JinLong on 2015/2/9.
 */
public class Decrypt {
    public static Decrypt decrypt = null ;

    public static Decrypt getInstance(){
        if(decrypt == null){
            decrypt = new  Decrypt();
        }
        return decrypt;
    }
    private  Decrypt(){

    }
    public  byte[] decrypt(InputStream input,String key,int index )throws Exception{

        char keychar = key.charAt(index);
        int n = 0;
        byte[] bytebuffer = null ;

        if(input != null) {
            bytebuffer = new byte[input.available()];
            while ((n = (input.read(bytebuffer))) != -1) {

                for (int i = 0; i < n; i++) {
                    bytebuffer[i] = (byte) (bytebuffer[i] ^ keychar);
                }

            }

        }
        return bytebuffer;
    }
}
