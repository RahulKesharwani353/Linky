package com.example.linky;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sawolabs.androidsdk.Sawo;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_b);
        new Sawo(
                LoginActivity.this,
                "791343ff-7968-4fab-85d7-e2cbfea8235b", // your api key
                "614a181909e2ab20815667fc1tyloGlRHYTKqIj89zgbMqDu"  // your api key secret
        ).login(
                "email", // can be one of 'email' or 'phone_number_sms'
                Dashboard.class.getName()  // Callback class name
        );
        finish();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}