package test;

public class Test {
	
	public static void main(String[] args) {
		String url = "http://www.baidu.com";
		System.out.println(HttpClientPoolUtil.doGet(url, null, null));;
	}

}
