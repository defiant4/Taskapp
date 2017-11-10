package com.example.taskapp;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends Activity {
	 int batterychecking;
	

	private Camera camera;

	private Button button;

	@Override
	protected void onStop() {
		super.onStop();

		if (camera != null) {
			camera.release();
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		  button = (Button) findViewById(R.id.buttonFlashlight);
		
		BroadcastReceiver batteryLevel = new BroadcastReceiver() {
		    
			   public void onReceive(Context context, Intent intent) {
			    context.unregisterReceiver(this);
			    int currentLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
			    int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
			    int level= -1;
			    if (currentLevel >= 0 && scale > 0) {
			     level = (currentLevel * 100) / scale;
			    }
			    batterychecking=level;

				
						if (batterychecking>50) {
							
							stopService(new Intent(MainActivity.this, MyService.class));
				
							button.setEnabled(true);

						} else {

							startService(new Intent(MainActivity.this, MyService.class));
							
							button.setEnabled(false);

						}

			   }
			  };
			   
			  IntentFilter batteryLevelFilter = new IntentFilter(
			    Intent.ACTION_BATTERY_CHANGED);
			  registerReceiver(batteryLevel, batteryLevelFilter);
		

	}
	}