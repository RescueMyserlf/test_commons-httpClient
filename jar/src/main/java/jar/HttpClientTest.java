package jar;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

public class HttpClientTest {


    private final static String OPERATER_NAME = "【HTTP操作】";

    private final static int SUCCESS = 200;

    private final static String UTF8 = "UTF-8";

    private HttpClient client = new HttpClient();


    private String doGet(URL url) {
        long beginTime = System.currentTimeMillis();
        String respStr = "";
        try {
            System.out.println(OPERATER_NAME + "开始get通信，目标host:" + url);
            HttpMethod method = new GetMethod(url.toString());
            // 中文转码
            method.getParams().setContentCharset(UTF8);
            try {
                client.executeMethod(method);
            } catch (HttpException e) {

            	System.out.println(new StringBuffer("发送HTTP GET给\r\n").append(url)
                        .append("\r\nHTTP异常\r\n"));
            } catch (IOException e) {

            	System.out.println(new StringBuffer("发送HTTP GET给\r\n").append(url)
                        .append("\r\nIO异常\r\n"));
            }
            if (method.getStatusCode() == SUCCESS) {
                respStr = method.getResponseBodyAsString();
            }
            // 释放连接
            method.releaseConnection();

            System.out.println(OPERATER_NAME + "通讯完成，返回码：" + method.getStatusCode());
            System.out.println(OPERATER_NAME + "返回内容：" + method.getResponseBodyAsString());
            System.out.println(OPERATER_NAME + "结束..返回结果:" + respStr);
        } catch (Exception e) {
        	System.out.println(OPERATER_NAME);
        	System.out.println(e);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(OPERATER_NAME + "共计耗时:" + (endTime - beginTime) + "ms");

        return respStr;
    }
    
    public static void main(String[] args) throws MalformedURLException {
    	 URL url ;//= new URL("http://172.16.126.198:9999/serviceTest?p={	'func': 'SFJK_KHGL_SFZJXXXGCJ',	'req_data': {		'khh': '1000058',		'khmc': '胡宏',		'xb': '男',		'mz': '汉',		'csrq': '19910122',		'zjdz': '河北省唐山市玉田县孤树镇东辛庄村永盛街58号',		'zjbh': '310105197611130018',		'i_issued_depart': '玉日县公安局',		'zjqsrq': '20090524',		'zjjzrq': '20290726',		'i_id_front_url': 'http://202.103.39.205:7003/213546/20180627/158341/l2KfNDys.jpg',		'i_id_back_url': 'http://202.103.39.205:7003/213546/20180627/158342/AaeEXgwE.jpg',		'remark': '备注',		'czzd': 'IP=10.246.1.153;MAC=10C37B568410',		'blms': '2',		'fqr': '13009',		'fqqd': '2'	}}");
    	 //url = new URL("http://www.baidu.com");
    	 //url = new URL("http://172.16.126.198:9999/serviceTest?p={'func':'SFJK_KHGL_SFZJXXXGCJ','req_data':{'khh':'1000058','khmc':'huhong','xb':'man','mz':'han','csrq':'19910122','zjdz':'hebeisheng','zjbh':'310105197611130018','i_issued_depart':'yurixiangonganju','zjqsrq':'20090524','zjjzrq':'20290726','i_id_front_url':'http://202.103.39.205:7003/213546/20180627/158341/l2KfNDys.jpg','i_id_back_url':'http://202.103.39.205:7003/213546/20180627/158342/AaeEXgwE.jpg','remark':'beizhu','czzd':'IP=10.246.1.153;MAC=10C37B568410','blms':'2','fqr':'13009','fqqd':'2'}}");
    	 url = new URL("http://172.16.126.198:9999/serviceTest?p={'func':'QUERY_CXKHXX','serviceId':'1','req_data':{'khh':'1084220'}}");
    	 HttpClientTest hct = new HttpClientTest();
    	 System.out.println(hct.doGet(url));;
	}

    
}
