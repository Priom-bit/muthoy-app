package com.example.muthoyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SigninActivity extends AppCompatActivity {

    EditText userid;
    EditText userpassword;
    Button button;

    //volley variables
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signin);

        TextView btn=findViewById(R.id.signupid);
        TextView forgetPass = findViewById(R.id.forgotpasswordid);
        button =findViewById(R.id.signinbtnid);

        userid = findViewById(R.id.usernameid);
        userpassword = findViewById(R.id.passwordid);

        button.setAlpha(0.5f);
        button.setEnabled(false);

        userid.addTextChangedListener(new TextFieldListener());
        userpassword.addTextChangedListener(new TextFieldListener());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRequestQueue =Volley.newRequestQueue(SigninActivity.this);

                mStringRequest = new StringRequest(Request.Method.POST,
                        getBaseUrl(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message");

                            if (success.equals("0")) {
                                Toast.makeText(SigninActivity.this,message,Toast.LENGTH_SHORT).show();

                                finish();
                                Intent intent=new Intent(SigninActivity.this,RewardsActivity.class);
                                startActivity(intent);
                            }
                            if (success.equals("1")) {

//                                mProgress.setVisibility(View.GONE);
                                Toast.makeText(SigninActivity.this,message,Toast.LENGTH_SHORT).show();
                            }



                        } catch (JSONException e) {

//                            mProgress.setVisibility(View.GONE);
                            Toast.makeText(SigninActivity.this,e.toString(),Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

//                        mProgress.setVisibility(View.GONE);
                        Toast.makeText(SigninActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> params = new HashMap<>();
                        params.put("name",userid.getText().toString());
                        params.put("password",userpassword.getText().toString());


                        return params;
                    }
                };

                mStringRequest.setShouldCache(false);
                mRequestQueue.add(mStringRequest);



            }

            });


        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SigninActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SigninActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });

    }

    class TextFieldListener implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (loginInfoOk()) {
                button.setAlpha(1.0f);
                button.setEnabled(true);
            }
            else {
                button.setAlpha(0.5f);
                button.setEnabled(false);
            }
        }
    }

    private boolean isFillupAllField()
    {
        if (userid.getText().toString().length() > 0 && userpassword.getText().toString().length()>0) {
            return true;
        }
        return false;
    }
    private boolean loginInfoOk() {
        if (isFillupAllField())
            return true;
        return false;
    }
    private String getBaseUrl (){
        return "http://muthoyapp.riyacash.xyz/api/login.php";
    }
}