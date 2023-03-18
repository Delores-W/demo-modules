package com.delores.medusa.utils;

import com.delores.medusa.exception.BaseMedusaException;
import com.delores.medusa.exception.TechnicalException;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author William
 * @date 4/29/21 6:33 PM
 * @description
 */
public class EncryptUtil {

    public static final String KEY_MD5 = "MD5";
    private static final String salt_MD5 = "Delores.Medusa";

    public static String encryptWithSalt(String in) {
        System.out.println("password: " + in);
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance(KEY_MD5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // add salt
        // String salt = String.valueOf(UUID.randomUUID());

        byte[] byteArray = (in + salt_MD5).getBytes();
        StringBuilder hexValue = new StringBuilder();
        if (md5 != null) {
            byte[] md5Bytes = md5.digest(byteArray);

            for (byte b : md5Bytes) {
                int val = (int) b & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
        }
        return hexValue.toString();
    }

    private static final String AES_ALG = "AES";

    private static final String charset = "utf-8";

    //AES算法
    private static final String AES_CBC_PCK_ALG = "AES/CBC/PKCS5Padding";

    private static final byte[] AES_IV = initIv(AES_CBC_PCK_ALG);

    /**
     * AES加密
     *
     * @param content
     * @param aesKey
     * @return
     * @throws Exception
     */
    public static String aesEncrypt(String content, String aesKey) throws BaseMedusaException {
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_PCK_ALG);

            IvParameterSpec iv = new IvParameterSpec(AES_IV);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(aesKey.getBytes(), AES_ALG), iv);
            byte[] encryptBytes = cipher.doFinal(content.getBytes(charset));
            return new String(Base64.encodeBase64(encryptBytes));
        } catch (Exception e) {
            e.printStackTrace();
            throw new TechnicalException("AES加密失败：Aescontent = " + content + "; charset = " + charset, e);
        }
    }

    /**
     * AES解密
     *
     * @param content 待解密串
     * @param key     密钥
     * @return
     * @throws Exception
     */
    public static String aesDecrypt(String content, String key) throws BaseMedusaException {
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_PCK_ALG);
            IvParameterSpec iv = new IvParameterSpec(initIv(AES_CBC_PCK_ALG));
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(),
                    AES_ALG), iv);

            byte[] cleanBytes = cipher.doFinal(Base64.decodeBase64(content.getBytes()));
            return new String(cleanBytes, charset);
        } catch (Exception e) {
            e.printStackTrace();
            throw new TechnicalException("AES解密失败：AES content = " + content + "; charset = " + charset, e);
        }
    }

    /**
     * 初始向量的方法, 全部为0. 这里的写法适合于其它算法,针对AES算法的话,IV值一定是128位的(16字节).
     *
     * @param fullAlg
     * @return
     * @throws GeneralSecurityException
     */
    private static byte[] initIv(String fullAlg) {
        try {
            Cipher cipher = Cipher.getInstance(fullAlg);
            int blockSize = cipher.getBlockSize();
            byte[] iv = new byte[blockSize];
            for (int i = 0; i < blockSize; ++i) {
                iv[i] = 0;
            }
            return iv;
        } catch (Exception e) {

            int blockSize = 16;
            byte[] iv = new byte[blockSize];
            for (int i = 0; i < blockSize; ++i) {
                iv[i] = 0;
            }
            return iv;
        }
    }


}
