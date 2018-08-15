package com.example.yinlian.tariff.index;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * AES 加密工具类
 * @author shanjie
 * @date 2018/06/25 09:36
 */
public class AesUtil {


    /**
     * 算法/模式/补码方式
     */
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    /**
     * 补码内容
     */
    public static final String IV_VALUE = "0123456789abcdef";
    /**
     * 默认编码
     */
    private static final String DEFAULT_CHARSET = "utf-8";


    public static String encrypt(String sourceContext,String sourceKey){
        return encrypt(sourceContext,sourceKey,IV_VALUE);
    }


    /**
     * 加密
     * @param sourceContext 内容明文
     * @param sourceKey 密钥
     * @param iv 偏移量
     * @return 加密内容
     */
    public static String encrypt(String sourceContext,String sourceKey,String iv){
        if(sourceKey == null){
//            logger.info("Key为空");
            return null;
        }
        if(sourceKey.length() != 16){
//            logger.info("Key长度不是16位");
            return null;
        }
        try {
            byte[] encryptResult = AES_CBC_Encrypt(sourceContext.getBytes(DEFAULT_CHARSET),sourceKey.getBytes(DEFAULT_CHARSET),iv.getBytes(DEFAULT_CHARSET));
            if(encryptResult != null){
                return parseByte2HexStr(encryptResult).toUpperCase();
            }else{
                return null;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密
     * @param sourceContext 内容密文
     * @param sourceKey 密钥
     * @param iv 偏移量
     * @return 解密内容
     */
    public static String decrypt(String sourceContext,String sourceKey,String iv){
        if(sourceKey == null){
//            logger.info("Key为空");
            return null;
        }
        if(sourceKey.length() != 16){
//            logger.info("Key长度不是16位");
            return null;
        }

        try {
            byte[] decryptResult = AES_CBC_Decrypt(sourceContext.getBytes(DEFAULT_CHARSET),sourceKey.getBytes(DEFAULT_CHARSET),iv.getBytes(DEFAULT_CHARSET));
            if(decryptResult!=null){
                return new String(decryptResult);
            }else{
                return null;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密
     *
     * @param sSrc 内容
     * @param sKey 密钥
     * @return 加密内容
     */
    private static byte[] AES_CBC_Encrypt(byte[] sSrc, byte[] sKey, byte[] iv) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(sKey, "AES");
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(iv));
            return cipher.doFinal(sSrc);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 解密
     *
     * @param sSrc 内容
     * @param sKey 密钥
     * @return 解密内容
     */
    private static byte[] AES_CBC_Decrypt(byte[] sSrc, byte[] sKey, byte[] iv) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(sKey, "AES");
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(iv));
            return cipher.doFinal(sSrc);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
//        String sKey = "0123456789abcdef";
//        String sSrc = "{a:b}";
//
//        logger.info("需要加密的内容是" + sSrc);
//        try {
//            //加密
//            byte[] encrypted = AesUtil.AES_CBC_Encrypt(sSrc.getBytes("utf-8"), sKey.getBytes("utf-8"), IV_VALUE.getBytes("utf-8"));
//            String text = parseByte2HexStr(encrypted).toUpperCase();
//            logger.info("text = " +text);
//
//            //解密
//            byte[] decryptedRequest = parseHexStr2Byte(text);
//            logger.info("decryptedRequest = "+ decryptedRequest);
//            byte[] decrypted = AesUtil.AES_CBC_Decrypt(decryptedRequest, sKey.getBytes("utf-8"), IV_VALUE.getBytes("utf-8"));
//            logger.info("解密的字符串：" + new String(decrypted));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        AesUtil.encrypt("","d12fa7e992fa4ef3");

    }

    /**
     * 将二进制转换成十六进制
     *
     * @param buf
     * @return
     */
    private static String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将十六进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

}
