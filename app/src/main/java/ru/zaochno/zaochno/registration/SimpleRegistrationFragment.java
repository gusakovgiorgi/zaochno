package ru.zaochno.zaochno.registration;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.zaochno.zaochno.MainActivity;
import ru.zaochno.zaochno.MainApplication;
import ru.zaochno.zaochno.R;
import ru.zaochno.zaochno.model.user.UserManager;
import ru.zaochno.zaochno.model.user.UserType;
import ru.zaochno.zaochno.rest.register.RegisterAPI;
import ru.zaochno.zaochno.rest.register.RegisterGetData;
import ru.zaochno.zaochno.rest.register.RegisterSendData;


/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleRegistrationFragment extends Fragment {

    private static final String EMPTY_FIELD_MESSSAGE = "пустая строка";
    private static final String patternString = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[x01-x08x0bx0cx0e-x1fx21x23-x5bx5d-x7f]|\\\\[x01-x09x0bx0cx0e-x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[x01-x08x0bx0cx0e-x1fx21-x5ax53-x7f]|\\\\[x01-x09x0bx0cx0e-x7f])+)\\])";

    // Create a Pattern object
    private Pattern pattern = Pattern.compile(patternString);
    private RegisterAPI registerAPI;

    @BindView(R.id.simpleRegistrationFragmentNameId)
    EditText name;
    @BindView(R.id.simpleRegistrationFragmentEmailId)
    EditText email;
    @BindView(R.id.simpleRegistrationFragmentPasswordId)
    EditText password;
    @BindView(R.id.simpleRegistrationFragmentPasswordRepeatId)
    EditText repeatPassword;
    @BindView(R.id.simpleRegistrationFragmentPhoneId)
    EditText phone;
    @BindView(R.id.simpleRegistrationFragmentRegionId)
    EditText region;
    @BindView(R.id.simpleRegistrationFragmentAgreeCheckboxId)
    CheckBox agreeCheckbox;
    @BindView(R.id.simpleRegistrationFragmentRegisterButtonId)
    Button registerAttempt;
    @BindView(R.id.registrationExtendedFragmentLoginProgressId)
    ProgressBar progressBar;
    @BindView(R.id.extendedRegistrationFragmentLoginFormId)
    View hidingView;


    public SimpleRegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_registration, container, false);

        ButterKnife.bind(this, view);

        registerAPI = MainApplication.getInstance().getRetrofit().create(RegisterAPI.class);


        return view;
    }

    @OnClick(R.id.simpleRegistrationFragmentRegisterButtonId)
    void loginAttempt() {

        boolean error = false;
        if (name.getText().toString().isEmpty()) {
            name.setError(EMPTY_FIELD_MESSSAGE);
            error = true;
        }
        if (!isEmailValid()) {
            error = true;
        }
        if (password.getText().toString().isEmpty()) {
            error = true;
            password.setError(EMPTY_FIELD_MESSSAGE);
        }
        if (repeatPassword.getText().toString().isEmpty()) {
            error = true;
            repeatPassword.setError(EMPTY_FIELD_MESSSAGE);
        } else if (!repeatPassword.getText().toString().equals(password.getText().toString())) {
            error = true;
            password.setError("Пароли не совпадают");
            repeatPassword.setError("Пароли не совпадают");
        }
        if (phone.getText().toString().isEmpty()) {
            error = true;
            phone.setError(EMPTY_FIELD_MESSSAGE);
        }
        if (region.getText().toString().isEmpty()) {
            error = true;
            region.setError(EMPTY_FIELD_MESSSAGE);
        }
        if (!agreeCheckbox.isChecked()) {
            error = true;
            agreeCheckbox.setError("Согласитесь с соглашением");
        }
        if (registerAttempt.getText().toString().isEmpty()) {
            error = true;
            registerAttempt.setError(EMPTY_FIELD_MESSSAGE);
        }

        if (!error) {
            sendDataToServer();
        }
    }

    private boolean isEmailValid() {
        String text = email.getText().toString();
        if (text.isEmpty()) {
            email.setError(EMPTY_FIELD_MESSSAGE);
            return false;
        }
        Matcher matcher = pattern.matcher(text);
        if (!matcher.matches()) {
            email.setError("Неправильный емайл");
            return false;
        }
        return true;
    }


    private void sendDataToServer() {
        showProgress(true);
        RegisterSendData data = new RegisterSendData();
        data.setType(UserType.PHYSICAL_USER);
        data.setName(name.getText().toString());
        data.setEmail(email.getText().toString());
        data.setPassword(password.getText().toString());
        data.setPhone(phone.getText().toString());
        data.setRegion(region.getText().toString());

        registerAPI.registerUser(data).enqueue(new Callback<RegisterGetData>() {
            @Override
            public void onResponse(Call<RegisterGetData> call, Response<RegisterGetData> response) {
                showProgress(false);
                if(response.body().getToken().isEmpty()){
                    showToast(response.body().getMessage());
                    return;
                }
                UserManager.getInstance().createUser(response.body().getToken());
                Intent mainActivityIntent=new Intent(getActivity(), MainActivity.class);
                startActivity(mainActivityIntent);
                getActivity().finish();
            }

            @Override
            public void onFailure(Call<RegisterGetData> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void showToast(String message){
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }

    private void showProgress(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        hidingView.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
    }

}
