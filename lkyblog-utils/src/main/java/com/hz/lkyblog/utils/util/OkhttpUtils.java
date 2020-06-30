package com.hz.lkyblog.utils.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
public class OkhttpUtils {

    public static SSLContext getTrustAllContext() {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sslContext;
    }

    private static class TrustAnyTrustManager implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }

    public static X509TrustManager getX509TrustManager() {
        return new TrustAnyTrustManager();
    }

    private static final OkHttpClient client = new OkHttpClient.Builder()
            .sslSocketFactory(getTrustAllContext().getSocketFactory(), getX509TrustManager())
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    public static OkHttpClient getClient() {
        return client;
    }

    public static String createNonceStr30() {
        return UUID.randomUUID().toString().substring(0, 6);
    }

    public static boolean downLoadPic(String url, String fileName) {
        Request request = new Request.Builder().url(url).build();
        try {
            Response execute = client.newCall(request).execute();
            int i = writeFile(execute, fileName);
            if (i == 1) {
                return true;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static int writeFile(Response response, String fileName) {
        InputStream is = null;
        FileOutputStream fos = null;
        is = response.body().byteStream();
        String path = "/Users/lky/Desktop/ic";
        File file = new File(path, fileName);
        try {
            fos = new FileOutputStream(file);
            byte[] bytes = new byte[1024];
            int len = 0;
            //获取下载的文件的大小
            long fileSize = response.body().contentLength();
            long sum = 0;
            int porSize = 0;
            while ((len = is.read(bytes)) != -1) {
                fos.write(bytes);
//                sum += len;
//                porSize = (int) ((sum * 1.0f / fileSize) * 100);
//                Message message = handler.obtainMessage(1);
//                message.arg1 = porSize;
//                handler.sendMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.error("myTag", "下载成功");
        return 1;
    }

}
