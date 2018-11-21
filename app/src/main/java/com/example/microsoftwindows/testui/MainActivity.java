package com.example.microsoftwindows.testui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    Button btnLoggin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLoggin = (Button) findViewById(R.id.btn_login);
        btnLoggin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });

    }

    /** Called when the user taps the Send button */
    public void login(View view) {
        EditText username = (EditText) findViewById(R.id.login_username);
        String stringUsername = username.getText().toString();
        EditText password = (EditText) findViewById(R.id.login_password);
        String stringPassword = password.getText().toString();
        if (validateAccount(stringUsername, stringPassword) == true) {
            Intent intent = new Intent(this, ShowList.class);
            intent.putExtra(EXTRA_MESSAGE, "login");
            intent.putExtra("username", stringUsername);
            startActivity(intent);
        }
        else {
            Context context = getApplicationContext();
            CharSequence text = "Sai tên đăng nhập hoặc mật khẩu";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
    public boolean validateAccount(String username, String password){
//        if (username.equals("tmchi") && password.equals("12345678")){
        if (username.equals("") && password.equals("")){

            return true;
        }
        else {
            return false;
        }
    }
}
