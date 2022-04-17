package com.example.muthoyapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SpinWinActivity extends AppCompatActivity {
    ImageView wheeling;
    String[] sectors = {"1","2","3","4","5","6","7","8","9","10","11","12"};
    TextView textView;
    Dialog myDialog;
    TextView SpinLeft;


    //volley variables
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().setTitle("Spin and Win");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_spin_win);
        wheeling = findViewById(R.id.wheel);
        myDialog = new Dialog(this);
        textView = findViewById(R.id.txtv);
        SpinLeft = findViewById(R.id.SpinLeft);


        Collections.reverse(Arrays.asList(sectors ));

        show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        show();
    }

    public void SpinNow(View view){

        Random rr = new Random();
        int degree = rr.nextInt(360);

        RotateAnimation rotateAnimation = new RotateAnimation(0,degree + 720,
                RotateAnimation.RELATIVE_TO_SELF,0.5f,
                RotateAnimation.RELATIVE_TO_SELF,0.5f);

        rotateAnimation.setDuration(3000);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new DecelerateInterpolator());

        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

//                if (Spin_left<=0){
//                    Intent intent = new Intent(SpinWinActivity.this,RewardsActivity.class);
//                    startActivity(intent);
//                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                show();

                CalculatePoint(degree);



            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        wheeling.startAnimation(rotateAnimation);
    }
    public void CalculatePoint(int degree){
        int initialPoint=0;
        int endPoint=30;
        int i = 0;
        String res = null;

        do {
            if (degree > initialPoint && degree < endPoint){
                res = sectors[i++];

            }
            initialPoint += 30; endPoint += 30;
            i++;
        }while (res == null);

        textView.setText(res);
        result(res);
        myDialog.setContentView(R.layout.activity_win);
        TextView button = myDialog.findViewById(R.id.pop_id);
        Button btn =myDialog.findViewById(R.id.btnok);

        button.setText(res+" Points");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpinWinActivity.this,SpinWinActivity.class);
                startActivity(intent);
            }
        });

        myDialog.show();


    }

    private void result(String reslt) {
        mRequestQueue = Volley.newRequestQueue(SpinWinActivity.this);
        String result= reslt;

        mStringRequest = new StringRequest(Request.Method.POST,
                getBaseUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String success = jsonObject.getString("success");
                    String message = jsonObject.getString("message");

//                    if (success.equals("0")) {
//                        //Toast.makeText(SpinWinActivity.this,message,Toast.LENGTH_SHORT).show();
//
//
//                    }
//                    if (success.equals("1")) {
//
////                                mProgress.setVisibility(View.GONE);
//                        Toast.makeText(SpinWinActivity.this,message,Toast.LENGTH_SHORT).show();
//
//                    }



                } catch (JSONException e) {

//                            mProgress.setVisibility(View.GONE);
                   // Toast.makeText(SpinWinActivity.this,e.toString(),Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

//                        mProgress.setVisibility(View.GONE);
               // Toast.makeText(SpinWinActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("result",result);



                return params;
            }
        };

        mStringRequest.setShouldCache(false);
        mRequestQueue.add(mStringRequest);
    }
    private void show() {
        mRequestQueue = Volley.newRequestQueue(SpinWinActivity.this);

        mStringRequest = new StringRequest(Request.Method.GET,
                getBaseUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String success = jsonObject.getString("success");
                    String message = jsonObject.getString("message");

                    if (success.equals("0")) {
                        Toast.makeText(SpinWinActivity.this,message,Toast.LENGTH_SHORT).show();

//                        finish();
//                        Intent intent=new Intent(SpinWinActivity.this,RewardsActivity.class);
//                        startActivity(intent);
                    }
                    if (success.equals("1")) {

//                                mProgress.setVisibility(View.GONE);
                        //Toast.makeText(SpinWinActivity.this,message,Toast.LENGTH_SHORT).show();

                        SpinLeft.setText("spin left :"+message);


                    }



                } catch (JSONException e) {

//                            mProgress.setVisibility(View.GONE);
                    Toast.makeText(SpinWinActivity.this,e.toString(),Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

//                        mProgress.setVisibility(View.GONE);
                Toast.makeText(SpinWinActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

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
    }
    private String getBaseUrl (){
        return "http://muthoyapp.riyacash.xyz/api/SpinWin.php";
    }
}




//        myDialog = new Dialog(this);
//
//    }
//    public void SpinNow(View v){
//        Button btnOk;
//        myDialog.setContentView(R.layout.activity_win);
//        btnOk = (Button) myDialog.findViewById(R.id.btnok);
//        myDialog.show();

