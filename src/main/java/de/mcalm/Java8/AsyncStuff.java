package de.mcalm.Java8;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsyncStuff {

	public static void main(String[] args) {
		CompletableFuture<String> vogella = getContent("http://www.vogella.com/index.html");
		CompletableFuture<Void> thenAccept = vogella.thenAccept(System.out::println);
//		CompletableFuture<String> web = getContent("http://www.web.de");
//		web.thenAccept(System.out::println);
		System.out.println("============= ENDE ==================");
	}

	private static CompletableFuture<String> getContent(String url) {

		CompletableFuture<String> responseFuture = new CompletableFuture<String>();
		Runnable r = new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				OkHttpClient client = new OkHttpClient();

				Request request = new Request.Builder().url(url).build();

				client.newCall(request).enqueue(new Callback() {
					@Override
					public void onFailure(Call call, IOException e) {
						responseFuture.cancel(true);
						e.printStackTrace();
					}

					@Override
					public void onResponse(Call call, final Response response) throws IOException {
						if (!response.isSuccessful()) {
							throw new IOException("Unexpected code " + response);
						} else {
							responseFuture.complete(Integer.toString(response.code()));
						}
					}
				});
			}
		};
		
		Thread thread = new Thread(r);
		thread.start();

		return responseFuture;
	}
}
