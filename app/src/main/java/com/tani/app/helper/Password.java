package com.tani.app.helper;

import java.security.MessageDigest;
import java.util.Arrays;

public class Password {

    public static String sha1(String text)  {
        try {
            if (text == null) text = "";
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(text.getBytes("UTF-8"), 0, text.length());
            byte[] sha1hash = digest.digest();
            return convertToHex(sha1hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static String sort(String string) {
        char[] chars = string.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
