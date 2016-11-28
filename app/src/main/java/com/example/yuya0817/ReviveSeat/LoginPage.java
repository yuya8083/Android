package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginPage extends Activity {

    private String username,password;
    EditText editname,editpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        editname = (EditText)findViewById(R.id.username);
        editpass = (EditText)findViewById(R.id.password);

        Button button=(Button)findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = editname.getText().toString();
                password = editpass.getText().toString();

                Intent intent = new Intent(LoginPage.this, Top.class);
                startActivity(intent);
            }
        });

        Button button2=(Button)findViewById(R.id.register);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this, JoinConfirmation.class);//NewUserSet
                startActivity(intent);
            }
        });
    }
}
