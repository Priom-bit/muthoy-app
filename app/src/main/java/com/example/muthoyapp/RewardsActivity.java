package com.example.muthoyapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
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

public class RewardsActivity extends AppCompatActivity {

    private String selectedTopicName = "";

    //volley variables
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();



        setContentView(R.layout.activity_rewards);

        TextView btn = findViewById(R.id.rewardhistoryid);
        Button button = findViewById(R.id.redeemid);
        TextView points = findViewById(R.id.totalpoints);



        final LinearLayout mathquiz = findViewById(R.id.mathquizlayout);
        final LinearLayout spinandwin = findViewById(R.id.spinandwinlayout);
        final LinearLayout scratchandwin = findViewById(R.id.scratchandwinlayout);

        mRequestQueue = Volley.newRequestQueue(RewardsActivity.this);

        mStringRequest = new StringRequest(Request.Method.GET,
                getBaseUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String success = jsonObject.getString("success");
                    String message = jsonObject.getString("message");

                    if (success.equals("0")) {
                        Toast.makeText(RewardsActivity.this,message,Toast.LENGTH_SHORT).show();

                        finish();
                        Intent intent=new Intent(RewardsActivity.this,RewardsActivity.class);
                        startActivity(intent);
                    }
                    if (success.equals("1")) {

//                                mProgress.setVisibility(View.GONE);
                        Toast.makeText(RewardsActivity.this,message,Toast.LENGTH_SHORT).show();
                       // String value = message;
                        points.setText(message);
                    }



                } catch (JSONException e) {

//                            mProgress.setVisibility(View.GONE);
                    Toast.makeText(RewardsActivity.this,e.toString(),Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

//                        mProgress.setVisibility(View.GONE);
                Toast.makeText(RewardsActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

            }
        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
////                Map<String, String> params = new HashMap<>();
////                params.put("name",userid.getText().toString());
////                params.put("password",userpassword.getText().toString());
//
//
////                return params;
//            }
        };

        mStringRequest.setShouldCache(false);
        mRequestQueue.add(mStringRequest);


        scratchandwin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RewardsActivity.this, ScratchWinActivity.class);
                startActivity(intent);
            }
        });

        spinandwin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RewardsActivity.this, SpinWinActivity.class);
                startActivity(intent);
            }
        });

        mathquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName = "mathquiz";
                Intent intent=new Intent(RewardsActivity.this, MathQuizActivity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RewardsActivity.this,RedeemActivity.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RewardsActivity.this, RewardsHistoryActivity.class);
                startActivity(intent);
            }
        });
    }
    private String getBaseUrl (){
        return "http://muthoyapp.riyacash.xyz/api/mathquiz.php";
    }
}