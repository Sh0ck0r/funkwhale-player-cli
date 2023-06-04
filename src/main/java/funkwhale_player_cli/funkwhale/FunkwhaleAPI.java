package funkwhale_player_cli.funkwhale;

import okhttp3.*;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;

public class FunkwhaleAPI {

    private static volatile FunkwhaleAPI instance;
    private OkHttpClient client;
    private MediaType mediaType;
    private RequestBody body;
    private Request request;
    private Response response;

    public static FunkwhaleAPI getInstance() {
        FunkwhaleAPI localInstance = instance;
        if (localInstance == null) {
            synchronized (FunkwhaleAPI.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new FunkwhaleAPI();
                }
            }
        }
        return localInstance;
    }

    private FunkwhaleAPI() {
        client = new OkHttpClient();
    }

    @Nullable
    public String get(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new RuntimeException("Request failed with code: " + response.code());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
