package ex.random.hadoophdfs.common.utils;


import java.io.IOException;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName: EncryptUtils 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年8月19日 下午8:07:25 
 *
 */
public class EncryptUtils {
	private final static Logger logger = LoggerFactory.getLogger(EncryptUtils.class);
	
	private EncryptUtils() {
		
	}
	
	private static final String DEFAULT_ENCODING = "UTF-16LE";
	
	public static String encryptByAES(String str, String key) throws Exception {
        if (null == str || str.trim().length() < 1) {
            throw new Exception();
        }

        byte[] keybytes = hexToByte(key);
        byte[] ivbytes = new byte[16];

        SecretKeySpec skeySpec = new SecretKeySpec(keybytes, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivbytes);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

        byte[] encrypted1 = str.getBytes(DEFAULT_ENCODING);
        byte[] original = cipher.doFinal(encrypted1);
        String originalString = byte2hex(original);
        logger.info("[***加密成功" + new Date() + "***]");
        return originalString;

    }
	
	public static String decryptByAES(String str, String key) throws Exception {
        if (null == str || str.trim().length() < 1) {
        	throw new Exception();
        }

        byte[] keybytes = hexToByte(key);
        byte[] ivbytes = new byte[16];

        SecretKeySpec skeySpec = new SecretKeySpec(keybytes, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivbytes);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

        byte[] encrypted1 = hexToByte(str);
        byte[] original = cipher.doFinal(encrypted1);
        logger.info("[***解密成功" + new Date() + "***]");
        return new String(original, DEFAULT_ENCODING);
    }
	
	public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) hs = hs + "0" + stmp;
            else hs = hs + stmp;

        }
        return hs.toUpperCase();
    }
	
	public static byte[] hexToByte(String s) throws IOException {
        int i = s.length() / 2;
        byte abyte0[] = new byte[i];
        int j = 0;
        if (s.length() % 2 != 0)
            throw new IOException("hexadecimal string with odd number of characters");
        for (int k = 0; k < i; k++) {
            char c = s.charAt(j++);
            int l = "0123456789abcdef0123456789ABCDEF".indexOf(c);
            if (l == -1)
                throw new IOException("hexadecimal string contains non hex character");
            int i1 = (l & 0xf) << 4;
            c = s.charAt(j++);
            l = "0123456789abcdef0123456789ABCDEF".indexOf(c);
            i1 += l & 0xf;
            abyte0[k] = (byte) i1;
        }
        return abyte0;
    }
	
	public static void main(String[] args) throws Exception {
    	System.out.println(encryptByAES("jd_75c1f94e7e0d3", "EDA3D558B223AC4DCC3DB19675A7D324"));
    	System.out.println(decryptByAES("001CD10DC89E1ED9804B67E0B28860CAA4C76F0248CC5394E5B4F745862A097F5C645ABD5B26C9FE93055FF8C78882FD", 
    			"EDA3D558B223AC4DCC3DB19675A7D324"));
    }
	
}
