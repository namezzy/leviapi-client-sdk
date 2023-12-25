package top.withlevi.leviapiclientsdk.client;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

import top.withlevi.leviapiclientsdk.model.User;

import java.util.HashMap;
import java.util.Map;

import static top.withlevi.leviapiclientsdk.utils.SignUtils.genSign;

/**
 * Created on 9/21/2023 11:16 AM
 *
 * @author Levi
 */
public class LeviApiClient {

    private static final  String GATEWAY_HOST = "http://localhost:8090";

    private String accessKey;

    private String secretKey;

    public LeviApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name) {

        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);

        String result = HttpUtil.get(GATEWAY_HOST+"/api/name/", paramMap);
        System.out.println(result);
        return result;
    }


    public String getNameByPost(String name) {

        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);

        String result = HttpUtil.post(GATEWAY_HOST+"/api/name/", paramMap);
        System.out.println(result);
        return result;
    }


    /**
     * @param body 对密钥签名进行处理
     * @return
     */

    private Map<String, String> genHeaderMap(String body) {
        Map<String, String> hashMap = new HashMap<>();

        hashMap.put("accessKey", accessKey);
        // 不能直接发送
        // hashMap.put("secretKey", secretKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("body", body);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", genSign(body, secretKey));

        return hashMap;
    }


    public String getUsernameByPost(User user) {

        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST+"/api/name/user")
                .addHeaders(genHeaderMap(json))
                .body(json)
                .execute();
        System.out.println("Status Code: " + httpResponse.getStatus());
        String result = httpResponse.body();
        System.out.println(result);

        return result;
    }


}
