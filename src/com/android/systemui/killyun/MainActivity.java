package com.android.systemui.killyun;

import java.util.Timer;
import java.util.TimerTask;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
public class MainActivity extends Activity {
	 	ImageView arc;
	    CircleProgress sector;
	    CircleProgress round;
	    ImageView mermeoryimage;
	    CircularProgressDrawable drawable;
	    ImageView QQ ,icon1,icon2,icon4,icon5,icon6 ;
	    LinearLayout app;
	    View.OnClickListener listener = new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            if (currentAnimation != null) {
	                currentAnimation.cancel();
	            }
	            sector= (CircleProgress) findViewById(R.id.traffic_image);
	            round= (CircleProgress) findViewById(R.id.battery_image);
	            QQ=(ImageView)findViewById(R.id.icon3);
	            icon1=(ImageView)findViewById(R.id.icon1);
	            icon2=(ImageView)findViewById(R.id.icon2);
	            icon6=(ImageView)findViewById(R.id.icon6);
	            icon4=(ImageView)findViewById(R.id.icon4);
	            icon5=(ImageView)findViewById(R.id.icon5);
	            app=(LinearLayout)findViewById(R.id.app);
	            final Animation operatingAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.app_out);  
				LinearInterpolator lin = new LinearInterpolator();  
				operatingAnim.setInterpolator(lin); 
	            if (operatingAnim != null && QQ != null && operatingAnim.hasStarted()) {  
			    	QQ.clearAnimation();  
			        QQ.startAnimation(operatingAnim);  
				}
	            switch (v.getId()) {
	                case R.id.traffic_image:
	                	sector.setType(CircleProgress.SECTOR);
	                	QQ.setVisibility(0);
	                    icon1.setVisibility(0);
	                    icon2.setVisibility(0);
	                    icon4.setVisibility(0);
	                    icon5.setVisibility(0);
	                    icon6.setVisibility(0);
	                    app.setVisibility(0);
	                    new AsyncTask<Integer, Integer, Integer>() {
	                        @Override
	                        protected Integer doInBackground(Integer... params) {
	                            for(int i=0;i<=60;i++){
	                                publishProgress(i);
	                                
		    	                    
	                                try {
	                                    Thread.sleep(10);
	                                } catch (InterruptedException e) {
	                                    e.printStackTrace();
	                                }
	                            }
	                            return null;
	                        }

	                        @Override
	                        protected void onProgressUpdate(Integer... values) {
	                            super.onProgressUpdate(values);
	                            sector.setmSubCurProgress(values[0]);
	                        }


	                    }.execute(0);
	                    break;
	                case R.id.battery_image:
	                	round.setType(CircleProgress.ROUND);

	                    new AsyncTask<Integer, Integer, Integer>() {
	                        @Override
	                        protected Integer doInBackground(Integer... params) {
	                            for(int i=0;i<=51;i++){
	                                publishProgress(i);
	                                try {
	                                    Thread.sleep(30);
	                                } catch (InterruptedException e) {
	                                    e.printStackTrace();
	                                }
	                            }
	                            return null;
	                        }

	                        @Override
	                        protected void onProgressUpdate(Integer... values) {
	                            super.onProgressUpdate(values);
	                            round.setmSubCurProgress(values[0]);
	                        }
	                    }.execute(0);
	                    break;
	                case R.id.clean:
	                    currentAnimation = prepareStyle3Animation();
	                    currentAnimation.start();
	                        	icon1.startAnimation(operatingAnim);
	    	                    icon2.startAnimation(operatingAnim);
	    	                    QQ.startAnimation(operatingAnim); 
	    	                    icon4.startAnimation(operatingAnim);
	    	                    icon5.startAnimation(operatingAnim);
	    	                    icon6.startAnimation(operatingAnim);
	    	                    QQ.setVisibility(4);
	    	                    icon1.setVisibility(4);
	    	                    icon2.setVisibility(4);
	    	                    icon4.setVisibility(4);
	    	                    icon5.setVisibility(4);
	    	                    icon6.setVisibility(4);
	    	                    app.setVisibility(4);
	    	                    
	                    break;
	               

	            }
	            
	        }
	    };

	    Animator currentAnimation;
	  
	    private Animator prepareStyle3Animation() {
	        AnimatorSet animation = new AnimatorSet();

	        ObjectAnimator progressAnimation = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.PROGRESS_PROPERTY, 0.75f, 0f);
	        progressAnimation.setDuration(1200);
	        progressAnimation.setInterpolator(new AnticipateInterpolator());

	        Animator innerCircleAnimation = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.CIRCLE_FILL_PROPERTY, 1f, 0f);
	        innerCircleAnimation.setDuration(1200);
	        innerCircleAnimation.setInterpolator(new AnticipateInterpolator());

	        ObjectAnimator invertedProgress = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.PROGRESS_PROPERTY, 0f, 0.75f);
	        invertedProgress.setDuration(1200);
	        invertedProgress.setStartDelay(1500);//进度条静止时间
	        
	        invertedProgress.setInterpolator(new OvershootInterpolator());

	        Animator invertedCircle = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.CIRCLE_FILL_PROPERTY, 0f, 1f);
	        invertedCircle.setDuration(1200);
	        invertedCircle.setStartDelay(1500);// 中间圆环静止时间
	        
	        invertedCircle.setInterpolator(new OvershootInterpolator());

	        animation.playTogether(progressAnimation, innerCircleAnimation, invertedProgress, invertedCircle);
	        return animation;
	    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
    }
    class EmptyAnimatorListener implements Animator.AnimatorListener {

        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {

        }
        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        mermeoryimage = (ImageView) findViewById(R.id.memory_image);
        sector = (CircleProgress) findViewById(R.id.traffic_image);
        round = (CircleProgress) findViewById(R.id.battery_image);
        arc = (ImageView)findViewById(R.id.clean);

        drawable = new CircularProgressDrawable(getResources().getDimensionPixelSize(R.dimen.drawable_ring_size),
                getResources().getColor(android.R.color.darker_gray),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_blue_dark));
        mermeoryimage.setImageDrawable(drawable);
        hookUpListeners();
    }
    private void hookUpListeners() {
        mermeoryimage.setOnClickListener(listener);
        sector.setOnClickListener(listener);
        round.setOnClickListener(listener);
        arc.setOnClickListener(listener);

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK)
           {  
               exitBy2Click();      //调用双击退出函数
           }
        return false;
    }
    private static Boolean isExit = false;
         
    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
         
        } else {
            finish();
            System.exit(0);
        }
    }
}
        