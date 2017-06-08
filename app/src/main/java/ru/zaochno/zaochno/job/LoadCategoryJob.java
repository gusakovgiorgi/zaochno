package ru.zaochno.zaochno.job;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;

import retrofit2.Response;
import ru.zaochno.zaochno.MainApplication;
import ru.zaochno.zaochno.model.Category;
import ru.zaochno.zaochno.model.user.UserManager;
import ru.zaochno.zaochno.rest.category.CategoryAPI;
import ru.zaochno.zaochno.rest.category.CategoryGetData;
import ru.zaochno.zaochno.rest.category.CategorySendData;

/**
 * Created by Anton_MoApps on 08.06.2017.
 */

public class LoadCategoryJob extends Job {
    public static final int PRIORITY = 1;
    public CategoryAPI categoryAPI;


    public LoadCategoryJob() {
        super(new Params(PRIORITY).requireNetwork().persist());
    }

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {
        categoryAPI=MainApplication.getInstance().getRetrofit().create(CategoryAPI.class);
        if(UserManager.getInstance().getUser()!=null) {
            Response<CategoryGetData> response = categoryAPI.getThematics(new CategorySendData(UserManager.getInstance().getUser().getUserToken())).execute();
        }
    }

    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {

    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(@NonNull Throwable throwable, int runCount, int maxRunCount) {
        return null;
    }
}
