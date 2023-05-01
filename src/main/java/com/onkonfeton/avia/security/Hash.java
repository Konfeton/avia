package com.onkonfeton.avia.security;

public class Hash {
    public static String encrypt(String input){
        int hash = 0;
        for (int i = 0; i < input.length(); i++) {
            byte byte_of_data = (byte)input.charAt(i);
            hash = (hash << 4) + byte_of_data;
            int h1 = hash & 0xf0000000;
            if (h1 != 0)
            {
                hash = ((hash ^ (h1 >> 24)) & (0xfffffff));
            }
        }

        return String.valueOf(hash);
    }
}
