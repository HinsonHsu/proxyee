package com.github.monkeywie.proxyee.intercept.mock;

import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptPipeline;
import com.github.monkeywie.proxyee.intercept.common.FullResponseIntercept;
import com.github.monkeywie.proxyee.util.FileUtil;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;

import java.nio.charset.Charset;

public class MockFullResponseIntercept extends FullResponseIntercept {


    /**
     * 匹配到的响应会解码成FullResponse
     */
    public boolean match(HttpRequest httpRequest, HttpResponse httpResponse,
                         HttpProxyInterceptPipeline pipeline) {
        String uri = httpRequest.uri();
        System.out.println(uri);
        if (uri.contains("interact/api/v2/show")) {
            return true;
        }
        return false;
    }

    /**
     * 拦截并处理响应
     */
    public void handelResponse(HttpRequest httpRequest, FullHttpResponse httpResponse,
                               HttpProxyInterceptPipeline pipeline) {
        //打印原始响应信息
        System.out.println(httpResponse.toString());
        System.out.println(httpResponse.content().toString(Charset.defaultCharset()));
        //修改响应头和响应体
        httpResponse.headers().set("handel", "edit head");
                    /*int index = ByteUtil.findText(httpResponse.content(), "<head>");
                    ByteUtil.insertText(httpResponse.content(), index, "<script>alert(1)</script>");*/
        httpResponse.content().clear();
        httpResponse.content().writeBytes(FileUtil.readFile("mock/act_v2_show.json").getBytes());
    }
}
