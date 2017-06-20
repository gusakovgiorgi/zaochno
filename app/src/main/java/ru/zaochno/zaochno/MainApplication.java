package ru.zaochno.zaochno;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.config.Configuration;
import com.birbit.android.jobqueue.log.CustomLogger;
import com.birbit.android.jobqueue.scheduling.FrameworkJobSchedulerService;
import com.birbit.android.jobqueue.scheduling.GcmJobSchedulerService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.zaochno.zaochno.model.user.UserManager;

/**
 * Created by Anton_MoApps on 08.06.2017.
 */

public class MainApplication extends Application {
    private static MainApplication instance;
    private Retrofit retrofit;
    private JobManager jobManager;


    public MainApplication() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        UserManager.getInstance().initializeUser();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …

        httpClient.readTimeout(60, TimeUnit.SECONDS).connectTimeout(30,TimeUnit.SECONDS);

        // add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        retrofit=new Retrofit.Builder()
                .baseUrl("http://trainingsonl-ru.1gb.ru")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        getJobManager();
    }


    public Retrofit getRetrofit() {
        return retrofit;
    }

    private void configureJobManager() {
        Configuration.Builder builder = new Configuration.Builder(this)
                .customLogger(new CustomLogger() {
                    private static final String TAG = "JOBS";
                    @Override
                    public boolean isDebugEnabled() {
                        return false;
                    }

                    @Override
                    public void d(String text, Object... args) {
//                        Log.d(TAG, String.format(text, args));
                    }

                    @Override
                    public void e(Throwable t, String text, Object... args) {
//                        Log.e(TAG, String.format(text, args), t);
                    }

                    @Override
                    public void e(String text, Object... args) {
//                        Log.e(TAG, String.format(text, args));
                    }

                    @Override
                    public void v(String text, Object... args) {

                    }
                })
                .minConsumerCount(1)//always keep at least one consumer alive
                .maxConsumerCount(3)//up to 3 consumers at a time
                .loadFactor(3)//3 jobs per consumer
                .consumerKeepAlive(120);//wait 2 minute

        jobManager = new JobManager(builder.build());
    }

    public synchronized JobManager getJobManager() {
        if (jobManager == null) {
            configureJobManager();
        }
        return jobManager;
    }

    public static MainApplication getInstance() {
        return instance;
    }
}
