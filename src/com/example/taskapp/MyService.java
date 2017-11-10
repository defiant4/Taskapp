package com.example.taskapp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	private Camera camera;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override  
    public void onStart(Intent intent, int startid) {  
		
		Context context = this;
		PackageManager pm = context.getPackageManager();

		// if device support camera
		if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			Log.e("err", "Device has no camera!");
			return;
		}

		camera = Camera.open();
		final Parameters p = camera.getParameters();
		
		
		p.setFlashMode(Parameters.FLASH_MODE_TORCH);
		camera.setParameters(p);
		camera.startPreview();

    }  
	
	
	 @Override
	    public void onDestroy() {
	        
		 Context context = this;
		 PackageManager pm = context.getPackageManager();

			// if device support camera
			if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
				Log.e("err", "Device has no camera!");
				return;
			}

			camera = Camera.open();
			final Parameters p = camera.getParameters();
			
			
			
			p.setFlashMode(Parameters.FLASH_MODE_OFF);
			camera.setParameters(p);
			camera.stopPreview();
	    }


}
