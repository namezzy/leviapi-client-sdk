package top.withlevi.leviapiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * Created on 9/21/2023 5:21 PM
 * <p>
 * 签名认证工具
 *
 * @author Levi
 */
public class SignUtils {

    /**
     *
     * @param body
     * @param secretKey
     * @return
     */

    public static String genSign(String body, String secretKey) {
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        String content = body + "-" + secretKey;
        return md5.digestHex(content);
    }

}
