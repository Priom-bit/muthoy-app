package com.example.muthoyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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

public class SignupActivity extends AppCompatActivity {

   private  EditText U_name;
   private EditText U_no;
   private EditText U_pass;
    Button btn;

    TextView sign_in;

    //volley variables
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btn =findViewById(R.id.signupbtnid);
        U_name = findViewById(R.id.username);
        U_no = findViewById(R.id.phone);
        U_pass = findViewById(R.id.password);
        sign_in = findViewById(R.id.signinid);

        btn.setAlpha(0.5f);
        btn.setEnabled(false);

        U_name.addTextChangedListener(new TextFieldListener());
        U_no.addTextChangedListener(new TextFieldListener());
        U_pass.addTextChangedListener(new TextFieldListener());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mRequestQueue =Volley.newRequestQueue(SignupActivity.this);

                mStringRequest = new StringRequest(Request.Method.POST,
                        getBaseUrl(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message");

                            if (success.equals("1")) {
                                Toast.makeText(SignupActivity.this,message,Toast.LENGTH_SHORT).show();

                                finish();
                                Intent intent=new Intent(SignupActivity.this,SigninActivity.class);
                                startActivity(intent);
                            }
                            if (success.equals("0")) {

//                                mProgress.setVisibility(View.GONE);
                                Toast.makeText(SignupActivity.this,message,Toast.LENGTH_SHORT).show();
                            }
                            if (success.equals("2")) {

//                                mProgress.setVisibility(View.GONE);
                                Toast.makeText(SignupActivity.this,message,Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {

//                            mProgress.setVisibility(View.GONE);
                            Toast.makeText(SignupActivity.this,e.toString(),Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

//                        mProgress.setVisibility(View.GONE);
                        Toast.makeText(SignupActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> params = new HashMap<>();
                        params.put("name",U_name.getText().toString());
                        params.put("password",U_pass.getText().toString());
                        params.put("phone",U_no.getText().toString());

                        return params;
                    }
                };

                mStringRequest.setShouldCache(false);
                mRequestQueue.add(mStringRequest);



            }
        });

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent=new Intent(SignupActivity.this,SigninActivity.class);
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
                btn.setAlpha(1.0f);
                btn.setEnabled(true);
            }
            else {
                btn.setAlpha(0.5f);
                btn.setEnabled(false);
            }
        }
    }
    private boolean isFillupAllField()
    {
        if (U_name.getText().toString().length() > 0 && U_no.getText().length()>0 && U_pass.getText().toString().length()>0) {
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
        return "http://muthoyapp.riyacash.xyz/api/register.php";
    }
}