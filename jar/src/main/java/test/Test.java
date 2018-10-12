package test;

import java.util.HashMap;
import java.util.Map;

public class Test {
	
	public static void main(String[] args) {
		String url = "http://172.16.126.198:9999/serviceTest";
		Map<String, String> parm = new HashMap<String, String>();
		//parm.put("p", "{'func':'SFJK_KHGL_SFZJXXXGCJ','req_data':{'khh':'1000058','khmc':'胡宏','xb':'男','mz':'汉','csrq':'19910122','zjdz':'河北省唐山市玉田县孤树镇东辛庄村永盛街58号','zjbh':'310105197611130018','i_issued_depart':'玉日县公安局','zjqsrq':'20090524','zjjzrq':'20290726','i_id_front_url':'http://202.103.39.205:7003/213546/20180627/158341/l2KfNDys.jpg','i_id_back_url':'http://202.103.39.205:7003/213546/20180627/158342/AaeEXgwE.jpg','remark':'备注','czzd':'IP=10.246.1.153;MAC=10C37B568410','blms':'2','fqr':'13009','fqqd':'2'}}");
		parm.put("p", "{'func':'SFJK_KHGL_EKHZLXG','req_data':{'khh':'93015032','khmc':'蔡青','sj':'18996510000','email':'newthon@163.com','dz':'测试地址','delxrxm':'第二联系人姓名','delxrdh':'18900001111','syr':'刘松','beneficiary_tel':'18900001111','beneficiary_id_no':'421023198709021111','sjkzr':'刘松','zydm':'6','mzdm':'1','gzdw':'长江证券','blcxjl':'0','credit_record':'没有','czzd':'IP=10.246.1.153;MAC=10C37B568410','blms':'2','fqr':'13009','fqqd':'2'}}");
		System.out.println(HttpClientPoolUtil.doGet(url, parm, null));;
	}

}
