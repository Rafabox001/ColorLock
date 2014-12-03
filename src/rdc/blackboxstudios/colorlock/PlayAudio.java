package rdc.blackboxstudios.colorlock;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class PlayAudio extends Service {
	private static final String LOGCATColorlock = null;
	MediaPlayer objPlayer;

	public void onCreate() {
		super.onCreate();
		Log.d(LOGCATColorlock, "Service Started!");
		objPlayer = MediaPlayer.create(this, R.raw.lock);
	}

	public int onStartCommand(Intent intent, int flags, int startId) {
		objPlayer.start();
		Log.d(LOGCATColorlock, "Media Player started!");
		if (objPlayer.isLooping() != true) {
			Log.d(LOGCATColorlock, "Problem in Playing Audio");
		}
		return 1;
	}

	public void onStop() {
		objPlayer.stop();
		objPlayer.release();
	}

	public void onPause() {
		objPlayer.stop();
		objPlayer.release();
	}

	public void onDestroy() {
		objPlayer.stop();
		objPlayer.release();
	}

	@Override
	public IBinder onBind(Intent objIndent) {
		return null;
	}
}
