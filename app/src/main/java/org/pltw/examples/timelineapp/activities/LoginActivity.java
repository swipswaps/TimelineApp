package org.pltw.examples.timelineapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import org.pltw.examples.timelineapp.R;

/**
 * Created by pieperm on 2/28/17.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEdit, passwordEdit;
    private Button loginButton;
    private TextView loginLink;
    private CheckBox stayLoggedInCheckbox;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Backendless.initApp(this, MainActivity.APPLICATION_ID, MainActivity.SECRET_KEY, MainActivity.VERSION);

        usernameEdit = (EditText)findViewById(R.id.login_username_edit);
        passwordEdit = (EditText)findViewById(R.id.login_password_edit);
        stayLoggedInCheckbox = (CheckBox)findViewById(R.id.login_stay_logged_in_checkbox);
        loginButton = (Button)findViewById(R.id.login_button);
        loginLink = (TextView)findViewById(R.id.login_link);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(loginIntent);

                Backendless.UserService.login(
                        usernameEdit.getText().toString(),
                        passwordEdit.getText().toString(),
                        new AsyncCallback<BackendlessUser>() {
                            @Override
                            public void handleResponse(BackendlessUser response) {

                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                MainActivity.createAlertDialog(LoginActivity.this, "Login Failed", fault.getMessage());
                            }
                        }, stayLoggedInCheckbox.isChecked());


            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSignupActivity = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(goToSignupActivity);
            }
        });





    }

}
