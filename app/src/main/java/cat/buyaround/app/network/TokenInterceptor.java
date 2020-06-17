package cat.buyaround.app.network;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import cat.buyaround.app.auth.UserManager;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class TokenInterceptor implements Interceptor {
    private UserManager userManager;

    @Inject
    public TokenInterceptor(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = userManager.getToken();
        if (userManager.hasSession()) {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", token)
                    .build();

            return chain.proceed(request);
        }

        return chain.proceed(chain.request());
    }
}
