package com.example.netmall.home.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.example.netmall.R;

public class WelcomeActivity extends AppCompatActivity {

    private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
