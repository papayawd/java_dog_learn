package encrypt;

import java.security.MessageDigest;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * SHA1工具类（不可逆）
 * 使用 SHA1 方法进行加密<br/>
 * SHA1方法加密是不可逆的，不能解密，要想解密就必须使用暴力解密<br/>
 * <p/>
 * 方法中的 res 参数：原始的数据<br/>
 * 方法中的 key 参数：密钥，可以随便写<br/>
 */
public class SHA1Util {
    public static final String SHA1 = "SHA1";
    public static final String HmacSHA1 = "HmacSHA1";
    public static final String charset = null; // 编码格式；默认null为GBK

    private static SHA1Util instance;

    public SHA1Util() {
    }

    // 单例
    public static SHA1Util getInstance() {
        if (instance == null) {
            synchronized (SHA1Util.class) {
                if (instance == null) {
                    instance = new SHA1Util();
                }
            }
        }
        return instance;
    }

    /**
     * 使用 SHA1 方法加密（无密码）
     */
    private String parseByte2HexStr(byte buf[]) {
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
            MessageDigest md = MessageDigest.getInstance(SHA1);
            return parseByte2HexStr(md.digest(resBytes));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String encode(String res) {
        try {
            MessageDigest md = MessageDigest.getInstance(SHA1);
            byte[] resBytes = charset == null ? res.getBytes() : res.getBytes(charset);
            return parseByte2HexStr(md.digest(resBytes));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用 SHA1 方法加密（可以设密码）


    public String encode(String res, String key) {
        try {
            SecretKey sk = null;
            if (key == null) {
                KeyGenerator kg = KeyGenerator.getInstance(HmacSHA1);
                sk = kg.generateKey();
            } else {
                byte[] keyBytes = charset == null ? key.getBytes() : key.getBytes(charset);
                sk = new SecretKeySpec(keyBytes, HmacSHA1);
            }
            Mac mac = Mac.getInstance(HmacSHA1);
            mac.init(sk);
            byte[] result = mac.doFinal(res.getBytes());
            return parseByte2HexStr(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    */

}