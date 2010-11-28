package com.alpha.view;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.MediaController.MediaPlayerControl;

public class Mp3Split extends Activity implements MediaPlayerControl,MediaPlayer.OnCompletionListener, OnClickListener
{

	private MediaPlayer mp;
	private String source;

	@Override
	public void finish()
	{
		if (mp!=null)
		{
			mp.release();
			mp = null;
		}
		
		super.finish();
	}
@Override
protected void onCreate(Bundle savedInstanceState)
{
	super.onCreate(savedInstanceState);
	setContentView(R.layout.mp3_splitter);
	mp = null;
	this.source = "/sdcard/quran/a002.mp3";
	this.assignButton();
	
	
}

private void assignButton()
{
   ((ImageView)findViewById(R.id.mp3_play)).setOnClickListener(this);
}

private void play()
{
try{
	mp = new MediaPlayer(); 
	mp.setOnCompletionListener(this);
	mp.setDataSource(this.source);
	mp.prepare();
	mp.start();
	final Handler handler=new Handler();
	final Runnable playerTask = new Runnable() {
		public void run()
		{
			try{
				{
					int pos =mp.getCurrentPosition() , i = 0;
					/*
					while (posLecture[(sourate==40) ? 3 : sourate][i] < pos && i<posLecture[(sourate==40) ? 3 : sourate].length)
					{
						i++;
					}
					if (i>0)
					lv.setSelection(i-1);
					tv.setText("["+i+"]"+mp.getCurrentPosition());
					*/
			if (mp.isPlaying() && mp !=null)
			{
				handler.postDelayed(this, 500);
			}
		}
		
	} catch (Exception ex)
	{
		Log.e("sam",ex.getMessage(),ex);
	}
	}
};	  
  handler.postDelayed(playerTask, 100);         
  //new Thread(playerTask).start();

	} catch (Exception ex)
	{
		Log.d("sam", "error lecture Q = "+ex.getMessage());
	}
	
}


public boolean canPause()
{
	// TODO Auto-generated method stub
	return false;
}

public boolean canSeekBackward()
{
	// TODO Auto-generated method stub
	return false;
}

public boolean canSeekForward()
{
	// TODO Auto-generated method stub
	return false;
}

public int getBufferPercentage()
{
	// TODO Auto-generated method stub
	return 0;
}

public int getCurrentPosition()
{
	// TODO Auto-generated method stub
	return 0;
}

public int getDuration()
{
	// TODO Auto-generated method stub
	return 0;
}

public boolean isPlaying()
{
	return (mp!=null&&mp.isLooping());
}

public void pause()
{
	// TODO Auto-generated method stub
	
}

public void seekTo(int pos)
{
	// TODO Auto-generated method stub
	
}

public void start()
{
	// TODO Auto-generated method stub
	
}

public void onCompletion(MediaPlayer mp)
{
	mp.release();
	mp = null;
}

public void onClick(View v)
{
switch (v.getId())
{
case R.id.mp3_play: this.play(); break;

}


}
	
	
	
	
}
