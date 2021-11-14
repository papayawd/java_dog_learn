package encrypt;

import java.security.MessageDigest;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class MD5Util {

    public static final String MD5 = "MD5";
    public static final String HmacMD5 = "HmacMD5";
    public static final String charset = null; // 编码格式；默认null为GBK

    private static MD5Util instance;

    public MD5Util() {
    }

    // 单例
    public static MD5Util getInstance() {
        if (instance == null) {
            synchronized (MD5Util.class) {
                if (instance == null) {
                    instance = new MD5Util();
                }
            }
        }
        return instance;
    }

    /**
     * 使用 MD5 方法加密（无密码）
     */
    public String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
    public String encode(byte[] resBytes) {
        try {
            MessageDigest md = MessageDigest.getInstance(MD5);
            return parseByte2HexStr(md.digest(resBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String encode(String res) {
        try {
            MessageDigest md = MessageDigest.getInstance(MD5);
            byte[] resBytes = charset == null ? res.getBytes() : res.getBytes(charset);
            return parseByte2HexStr(md.digest(resBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用 MD5 方法加密（可以设密码）
     */
    /**
    public String encode(String res, String key) {
        try {
            SecretKey sk = null;
            if (key == null) {
                KeyGenerator kg = KeyGenerator.getInstance(HmacMD5);
                sk = kg.generateKey();
            } else {
                byte[] keyBytes = charset == null ? key.getBytes() : key.getBytes(charset);
                sk = new SecretKeySpec(keyBytes, HmacMD5);
            }
            Mac mac = Mac.getInstance(HmacMD5);
            mac.init(sk);
            byte[] result = mac.doFinal(res.getBytes());
            return BASE64Util.getInstance().encode(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    **/

}