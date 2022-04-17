package com.example.muthoyapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.anupkumarpanwar.scratchview.ScratchView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ScratchWinActivity extends AppCompatActivity {
    private ScratchView scratchView;
    Dialog myDialog;
    TextView Value;
    TextView scratchleft;

    //volley variables
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().setTitle("Scratch and Win");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_scratch_win);
        scratchView= findViewById(R.id.scratchView);
        myDialog = new Dialog(this);
        Value = findViewById(R.id.value);
        scratchleft = findViewById(R.id.scratchleft);

        Value.setText("250");

        show();

       scratchView.setRevealListener(new ScratchView.IRevealListener() {

           @Override
           public void onRevealed(ScratchView scratchView) {


               result(Value.toString());
               Intent intent = new Intent(ScratchWinActivity.this, ScratchWin1Activity.class);
               intent.putExtra("Points", Value.getText().toString());

               startActivity(intent);
           }

           @Override
           public void onRevealPercentChangedListener(ScratchView scratchView, float percent) {
               if (percent >= 0.5) {
                   Log.d("Reveal Percentage", "onRevealPercentChangedListener: " + String.valueOf(percent));
               }
           }
       });
    }


    @Override
    protected void onResume() {
        super.onResume();
        show();

    }


    private void result(String reslt) {
               mRequestQueue = Volley.newRequestQueue(ScratchWinActivity.this);
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
               mRequestQueue = Volley.newRequestQueue(ScratchWinActivity.this);

               mStringRequest = new StringRequest(Request.Method.GET,
                       getBaseUrl(), new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {

                       try {
                           JSONObject jsonObject = new JSONObject(response);

                           String success = jsonObject.getString("success");
                           String message = jsonObject.getString("message");

                           if (success.equals("0")) {
                               Toast.makeText(ScratchWinActivity.this,message,Toast.LENGTH_SHORT).show();

//                        finish();
//                        Intent intent=new Intent(SpinWinActivity.this,RewardsActivity.class);
//                        startActivity(intent);
                           }
                           if (success.equals("1")) {

//                                mProgress.setVisibility(View.GONE);
                               //Toast.makeText(SpinWinActivity.this,message,Toast.LENGTH_SHORT).show();

                               scratchleft.setText("Scratch left :"+message);


                           }



                       } catch (JSONException e) {

//                            mProgress.setVisibility(View.GONE);
                           Toast.makeText(ScratchWinActivity.this,e.toString(),Toast.LENGTH_SHORT).show();

                       }

                   }
               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {

//                        mProgress.setVisibility(View.GONE);
                       Toast.makeText(ScratchWinActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

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
        return "http://muthoyapp.riyacash.xyz/api/scratchwin.php";
    }
    }



