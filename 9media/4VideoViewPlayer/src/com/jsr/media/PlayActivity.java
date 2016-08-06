package com.jsr.media;

import java.io.File;
import android.app.Activity;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;
public class PlayActivity extends Activity {
;
	private AudioManager mAudioManager = null;
	private TextView playtime = null;
	private TextView durationTime = null;
	private TextView sound;
	private SeekBar seekbar = null;
	private SeekBar soundBar = null;
    private SurfaceView surfaceView;
    private File videofile;
    private MediaPlayer mediaPlayer;
    private Handler handler = null;
    private int position;
	private int currentPosition;
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.play);
        mediaPlayer = new MediaPlayer();
        playtime=(TextView)findViewById(R.id.playtime);
        durationTime=(TextView)findViewById(R.id.duration);
        sound=(TextView)findViewById(R.id.soundsize);
        surfaceView = (SurfaceView) this.findViewById(R.id.surfaceView);
       // surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//       surfaceView.getHolder().setFixedSize(320,240);	
        seekbar = (SeekBar)findViewById(R.id.seekbar);                        
        ButtonClickListener listener = new ButtonClickListener();
        ImageButton playbutton = (ImageButton) this.findViewById(R.id.playBtn);
       // playbutton.setAlpha(00000000);
        ImageButton pausebutton = (ImageButton) this.findViewById(R.id.pauseBtn);
        ImageButton resetbutton = (ImageButton) this.findViewById(R.id.resetBtn);
        ImageButton stopbutton = (ImageButton) this.findViewById(R.id.stopBtn);
        playbutton.setOnClickListener(listener);
        pausebutton.setOnClickListener(listener);
        resetbutton.setOnClickListener(listener);
        stopbutton.setOnClickListener(listener);
        seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {    		
    		@Override
    		public void onStopTrackingTouch(SeekBar seekBar) {  			
    		}   		
    		@Override
    		public void onStartTrackingTouch(SeekBar seekBar) {   			
    		}		
    		@Override
    		public void onProgressChanged(SeekBar seekBar, int progress,
    				boolean fromUser) {
    			if(fromUser)
    				mediaPlayer.seekTo(progress);
    		}
    	});   
    	soundBar = (SeekBar)findViewById(R.id.soundBar);
    	soundBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {   		
    		@Override
    		public void onStopTrackingTouch(SeekBar seekBar) {
    			// TODO Auto-generated method stub   			
    		}   		
    		@Override
    		public void onStartTrackingTouch(SeekBar seekBar) {
    			// TODO Auto-generated method stub    			
    		}   		
    		@Override
    		public void onProgressChanged(SeekBar seekBar, int progress,
    				boolean fromUser) {
    			// TODO Auto-generated method stub
    			if (fromUser){
    			
    				int ScurrentPosition = soundBar.getProgress();
    				mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, ScurrentPosition, 0);  	
    				
    			}
    		}
    	});
 
    }
    
    @Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
    	position = savedInstanceState.getInt("position");
    	String path = savedInstanceState.getString("path");
    	if(path!=null && !"".equals(path)){
    		videofile = new File(path);
    	}
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
    	outState.putInt("position", position);
    	if(videofile!=null)
    		outState.putString("path", videofile.getAbsolutePath());
    	
		super.onSaveInstanceState(outState);
	}



	private final class ButtonClickListener implements View.OnClickListener{
		private boolean pause;
		
		public void onClick(View v) {
			
				
				switch (v.getId()) {
				case R.id.playBtn:
					String filename = ListActivity.uri.toString();
						videofile = new File(filename);
						play();								
					break;
				case R.id.pauseBtn:
					if(mediaPlayer.isPlaying()){
						mediaPlayer.pause();
						pause = true;
					}else{
						if(pause){
							mediaPlayer.start();
							pause = false;
						}
					}
					break;
					
				case R.id.resetBtn:
					if(mediaPlayer.isPlaying()){
  					    mediaPlayer.seekTo(0);
						

					}
					else{
						play();

					}
					break;
				case R.id.stopBtn:
					if(mediaPlayer.isPlaying()) {
						
						mediaPlayer.stop();
					    mediaPlayer.release();
						 mediaPlayer=null;					
					}				
					break;
					default:
						break;
				}
			
		}    	
    }
	

	private void play()  {
		try{
			
        mediaPlayer.reset();
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mediaPlayer.setDisplay(surfaceView.getHolder());
		mediaPlayer.setDataSource(ListActivity.uri.toString());		
		setup();
		mediaPlayer.start();
	}catch(Exception e){
		System.out.println("play is wrong");
		}
	}
	
	public String toTime(int time) {

		time /= 1000;
		int minute = time / 60;

		int second = time % 60;	
		return String.format("%02d:%02d", minute, second);
	}
	public String toFotmat(int num){
		   
		   return String.format("%02d", num);
	   }
	
	private void setup(){	
		init();
		try {
			mediaPlayer.prepare();
			mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
				
				@Override
				public void onPrepared(final MediaPlayer mp) {
					seekbar.setMax(mp.getDuration());
					handler.sendEmptyMessage(1);
					playtime.setText(toTime(mp.getCurrentPosition()));
					durationTime.setText(toTime(mp.getDuration()));
					 mp.seekTo(currentPosition);
					
				     handler.sendEmptyMessage(2);
					 sound.setText(toFotmat(CurrentSound)+"/"+toFotmat(MaxSound));

				}
			});
		} catch (Exception e) {
			System.out.println("wrong");
		}
	}
	int MaxSound;
	int CurrentSound;
	private void init(){
		 handler = new Handler(){
			public void handleMessage(Message msg){
				super.handleMessage(msg);
				switch (msg.what) {
				case 1:
					if(mediaPlayer!=null)
						currentPosition = mediaPlayer.getCurrentPosition();
					
					seekbar.setProgress(currentPosition);
					playtime.setText(toTime(currentPosition));
					handler.sendEmptyMessage(1);
					break;
				case 2:
					mAudioManager=(AudioManager) PlayActivity.this.getSystemService(PlayActivity.AUDIO_SERVICE);
					MaxSound=mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
				    CurrentSound=mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
				    sound.setText(toFotmat(CurrentSound)+"/"+toFotmat(MaxSound));
					handler.sendEmptyMessage(2);
					break;
				default:
					break;
				}
				
			}
		};
	}


}
	
