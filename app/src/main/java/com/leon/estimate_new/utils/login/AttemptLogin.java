package com.leon.estimate_new.utils.login;

import static com.leon.estimate_new.helpers.MyApplication.getApplicationComponent;
import static com.leon.estimate_new.helpers.MyApplication.getPreferenceManager;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.leon.estimate_new.R;
import com.leon.estimate_new.activities.MainActivity;
import com.leon.estimate_new.di.view_model.HttpClientWrapper;
import com.leon.estimate_new.enums.ProgressType;
import com.leon.estimate_new.enums.SharedReferenceKeys;
import com.leon.estimate_new.infrastructure.IAbfaService;
import com.leon.estimate_new.infrastructure.ICallback;
import com.leon.estimate_new.tables.LoginFeedBack;
import com.leon.estimate_new.utils.Crypto;
import com.leon.estimate_new.utils.CustomToast;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AttemptLogin extends AsyncTask<Activity, Activity, Void> {
    private final String username;
    private final String password;
    private final boolean isChecked;

    public AttemptLogin(String username, String password, boolean isChecked) {
        super();
        this.username = username;
        this.password = password;
        this.isChecked = isChecked;
    }

    @Override
    protected Void doInBackground(Activity... activities) {
        Retrofit retrofit = getApplicationComponent().NetworkHelperModel().getInstance();
        final IAbfaService iAbfaService = retrofit.create(IAbfaService.class);
        Call<LoginFeedBack> call = iAbfaService.login(username, password);
        activities[0].runOnUiThread(() ->
                HttpClientWrapper.callHttpAsync(call, ProgressType.SHOW.getValue(), activities[0],
                        new LoginCompleted(activities[0], isChecked, username, password),
                        new Incomplete(activities[0]),
                        new Error(activities[0])));
        return null;
    }
}

class LoginCompleted implements ICallback<LoginFeedBack> {
    private final Activity activity;
    private final boolean isChecked;
    private final String username;
    private final String password;

    public LoginCompleted(Activity activity, boolean isChecked, String username, String password) {
        this.activity = activity;
        this.isChecked = isChecked;
        this.username = username;
        this.password = password;
    }

    @Override
    public void execute(Response<LoginFeedBack> response) {
        LoginFeedBack loginFeedBack = response.body();
        if (loginFeedBack == null || loginFeedBack.access_token == null ||
                loginFeedBack.refresh_token == null ||
                loginFeedBack.access_token.isEmpty() ||
                loginFeedBack.refresh_token.isEmpty()) {
            new CustomToast().warning(activity.getString(R.string.error_is_not_match), Toast.LENGTH_LONG);
        } else {
//            List<String> cookieList = response.headers().values("Set-Cookie");
//            loginFeedBack.XSRFToken = (cookieList.get(1).split(";"))[0];
//            JWT jwt = new JWT(loginFeedBack.access_token);
//            loginFeedBack.displayName = jwt.getClaim("DisplayName").asString();
//            loginFeedBack.userCode = jwt.getClaim("UserCode").asString();
            savePreference(loginFeedBack, isChecked);
            Intent intent = new Intent(activity, MainActivity.class);
            activity.startActivity(intent);
            activity.finish();
        }
    }

    void savePreference(LoginFeedBack loginFeedBack, boolean isChecked) {
//        ISharedPreferenceManager sharedPreferenceManager = MyApplication.getApplicationComponent().SharedPreferenceModel();
//        sharedPreferenceManager
//                .putData(SharedReferenceKeys.DISPLAY_NAME.getValue(), loginFeedBack.displayName);
//        sharedPreferenceManager
//                .putData(SharedReferenceKeys.USER_CODE.getValue(), loginFeedBack.userCode);
        getPreferenceManager().putData(SharedReferenceKeys.TOKEN.getValue(), loginFeedBack.access_token);
        getPreferenceManager().putData(SharedReferenceKeys.REFRESH_TOKEN.getValue(), loginFeedBack.refresh_token);
        getPreferenceManager().putData(SharedReferenceKeys.XSRF.getValue(), loginFeedBack.XSRFToken);
        getPreferenceManager().putData(SharedReferenceKeys.USERNAME_TEMP.getValue(), username);
        getPreferenceManager().putData(SharedReferenceKeys.PASSWORD_TEMP.getValue(), Crypto.encrypt(password));
        if (isChecked) {
            getPreferenceManager().putData(SharedReferenceKeys.USERNAME.getValue(), username);
            getPreferenceManager().putData(SharedReferenceKeys.PASSWORD.getValue(), Crypto.encrypt(password));
        }
    }
}

