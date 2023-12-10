package tue.student.iot.group18.gym2go;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Util {

    public static String getMD5Str(String str) {
        byte[] digest = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            digest  = md5.digest(str.getBytes("utf-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //
        String md5Str = new BigInteger(1, digest).toString(16);
        return md5Str;
    }


    public static long currentTime(){
        long time = new Date().getTime();
        return time;
    }


    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String intToHex(int[] bytes) {
        char[] hexArray = "0123456789abcdef".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }


    public static int[] toUnsignedByte(byte[] data){
        int unsigned_data[] = new int[data.length];

        for(int i = 0; i < data.length; i++){
            unsigned_data[i] = Byte.toUnsignedInt(data[i]);
        }
        return unsigned_data;
    }


    public static byte[] hexStringToByteArray(String hexString) {
        int length = hexString.length();

        // Check if the length is even
        if (length % 2 != 0) {
            throw new IllegalArgumentException("Hex string must have an even length.");
        }

        // Create a byte array with half the length of the hex string
        byte[] byteArray = new byte[length / 2];

        // Convert each pair of hex characters to a byte
        for (int i = 0; i < length; i += 2) {
            String pair = hexString.substring(i, i + 2);
            byteArray[i / 2] = (byte) Integer.parseInt(pair, 16);
        }

        return byteArray;
    }

    public static String byteArrayToHexString(byte[] byteArray) {
        StringBuilder hexString = new StringBuilder(2 * byteArray.length);

        // Convert each byte to its hexadecimal representation in lowercase
        for (byte b : byteArray) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }
}
