package com.example.b_building;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends Activity
{
	private static final long SPLASH_DELAY_MILLIS = 2000;
	private ImageView imageView;
	private AnimationDrawable animationDrawable;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_PROGRESS);
		// final View view = View.inflate(this, R.layout.welcome, null);
		setContentView(R.layout.welcome);
		/*
		 * AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
		 * aa.setDuration(2000); view.startAnimation(aa);
		 * aa.setAnimationListener(new AnimationListener() {
		 * 
		 * @Override public void onAnimationStart(Animation animation) { // TODO
		 * Auto-generated method stub
		 * 
		 * }
		 * 
		 * @Override public void onAnimationRepeat(Animation animation) { //
		 * TODO Auto-generated method stub
		 * 
		 * }
		 * 
		 * @Override public void onAnimationEnd(Animation animation) { // TODO
		 * Auto-generated method stub // goHome(); new Thread(new Runnable() {
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub
		 * MainActivity.g = new Graph(SplashActivity.this, 0); goHome(); }
		 * }).start(); } });
		 */
		/*
		 * {             @Override             public void
		 * onAnimationEnd(Animation arg0) {                 redirectTo();
		 *             }             @Override             public void
		 * onAnimationRepeat(Animation animation) {}             @Override
		 *             public void onAnimationStart(Animation animation) {}
		 *      
		 *                                                                      
		 *         }); /*new Handler().postDelayed(new Runnable() { public void
		 * run() { goHome(); } }, SPLASH_DELAY_MILLIS);
		 */
		imageView = (ImageView) findViewById(R.id.welImage);
//		animationDrawable = (AnimationDrawable) getResources().getDrawable(R.drawable.framebyframe);
//		imageView.setBackgroundDrawable(animationDrawable);
		imageView.setBackgroundResource(R.drawable.framebyframe);  
        
        animationDrawable = (AnimationDrawable) imageView.getDrawable();  
          
        animationDrawable.start();  
          
        /*new Handler(){  
            public void handleMessage(android.os.Message msg) {  
              if(msg.what==1){  
                  Intent intent = new Intent(StartActivity.this,NextActivity.class);  
                  startActivity(intent);  
              }  
            };  
        }.sendEmptyMessageDelayed(1, 7000);*/
		if (MainActivity.g == null)
		{
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					// TODO Auto-generated method stub
					MainActivity.g = new Graph(SplashActivity.this, 0);
					goHome();
				}
			}).start();
		}
		else
		{
			new Handler().postDelayed(new Runnable()
			{
				public void run()
				{
					goHome();
				}
			}, SPLASH_DELAY_MILLIS);
		}
	}

	private void goHome()
	{
		Intent intent = new Intent(SplashActivity.this, MainActivity.class);
		SplashActivity.this.startActivity(intent);
		SplashActivity.this.finish();
	}
}
