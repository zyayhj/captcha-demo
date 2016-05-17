package com.touclick.captcha.service;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.touclick.captcha.exception.TouclickException;
import com.touclick.captcha.http.HttpClient;
import com.touclick.captcha.http.Response;
import com.touclick.captcha.model.Parameter;
import com.touclick.captcha.model.Status;
import com.touclick.captcha.util.TouclickUtil;

public class TouClick implements Serializable {
    private static final Logger LOGGER = Logger.getLogger(TouClick.class);

    private static final long serialVersionUID = -176092625883595547L;
    private static final String HTTP = "http://";
    private static final String POSTFIX = ".touclick.com/sverify.touclick";

    private HttpClient client = new HttpClient();

    /**
     * 请求二次验证, 服务端验证
     *
     * @param checkCode 一次验证携带的参数,与一次验证相同.(非必需,可为"")
     * @param checkKey  一次验证返回的checkAddress
     * @param token     一次验证返回的token
     * @param pubKey    公钥
     * @param priKey    私钥
     * @return Status   返回类型
     * @throws TouclickException
     */
    public Status check(String checkCode, String checkKey, String token,String pubKey,String priKey) throws TouclickException {
        return this.check(checkCode, checkKey, token, pubKey, priKey, "", "");
    }

    /**
     * 请求二次验证, 服务端验证
     *
     * @param checkCode 一次验证携带的参数,与一次验证相同.(非必需,可为"")
     * @param checkKey  一次验证返回的checkAddress
     * @param token     一次验证返回的token
     * @param pubKey    公钥
     * @param priKey    私钥
     * @param userName  请求用户名 用于统计分析
     * @param userId    请求用户id 用于统计分析
     * @return Status    返回类型
     * @throws TouclickException
     */
    public Status check(String checkCode, String checkKey, String token,String pubKey,String priKey, String userName, String userId) throws TouclickException {
        if (checkCode == null
                || checkKey == null || "".equals(checkKey)
                || pubKey == null || "".equals(pubKey)
                || priKey == null || "".equals(priKey)
                || token == null || "".equals(token)) {
            throw new TouclickException("参数有误");
        }
        List<Parameter> params = new ArrayList<Parameter>();
        params.add(new Parameter("ckcode", checkCode));
        params.add(new Parameter("i", token));
        params.add(new Parameter("b", pubKey));
        try {
            params.add(new Parameter("ip", InetAddress.getLocalHost().getHostAddress()));
        } catch (UnknownHostException e) {

        }
        params.add(new Parameter("un", userName));
        params.add(new Parameter("ud", userId));
        String sign = TouclickUtil.buildMysign(params, priKey);
        StringBuilder url = new StringBuilder();
        url.append(HTTP).append(checkKey).append(POSTFIX);
        params.add(new Parameter("sign", sign));
        Response response = null;
        System.out.println(url);
        try {
            response = client.get(url.toString(), params);
        } catch (TouclickException e1) {
            LOGGER.error(e1.getMessage());
        }
        ObjectMapper mapper = new ObjectMapper();
        if (response != null) {
            Status status = null;
            try {
                status = mapper.readValue(response.getInfo(), Status.class);
                return status;
            } catch (Exception e) {
                LOGGER.error("transfer json error ..", e);
            }
            return new Status(Status.STATUS_JSON_TRANS_ERROR, Status.getCause(Status.STATUS_JSON_TRANS_ERROR));
        }
        return new Status(Status.STATUS_HTTP_ERROR, Status.getCause(Status.STATUS_HTTP_ERROR));
    }

}
