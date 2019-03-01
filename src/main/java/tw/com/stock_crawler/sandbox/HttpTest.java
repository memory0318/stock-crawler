package tw.com.stock_crawler.sandbox;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpTest {

	public static void main(String[] args) throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("http://www.twse.com.tw/exchangeReport/STOCK_DAY?response=json&date=20181213&stockNo=2426")
				.build();
		try (Response response = client.newCall(request).execute()) {
			System.out.println(response.body().string());
		}
	}
}
