package edu.ycp.cs481.ycpgames;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends Activity {
	//define debug string
	private static final String TAG = "YCPGamesActivity";
	//declare instance of buttons
	protected ImageButton TicTacToeButton;
	protected ImageButton DotsButton;
	protected ImageButton CheckersButton;
	protected ImageButton SettingsButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d(TAG, "onCreate() called");

		//Get references to main menu buttons
		TicTacToeButton = (ImageButton) findViewById(R.id.TicTacToeButton);
		DotsButton = (ImageButton) findViewById(R.id.DotsButton);
		CheckersButton = (ImageButton) findViewById(R.id.CheckersButton);
		SettingsButton = (ImageButton) findViewById(R.id.SettingsButton);

		//Set listeners for main menu buttons
		TicTacToeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.d(TAG, "TicTacToeButton Pressed");
				//When TicTacToe button is pressed:
				//Placeholder "Toast" till TicTacToe is implemented
				Toast.makeText(MainActivity.this, R.string.TicTacToe, Toast.LENGTH_SHORT).show();
				//TODO: link to TicTacToe
			}
		});
		DotsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.d(TAG, "DotsButton Pressed");
				//When dots button is pressed:
				//Placeholder "Toast" till Dots is implemented
				Toast.makeText(MainActivity.this, R.string.Dots, Toast.LENGTH_SHORT).show();
				//TODO: link to Dots
			}
		});
		CheckersButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.d(TAG, "CheckersButton Pressed");
				//When Checkers button is pressed:
				//Placeholder "Toast" till Checkers is implemented
				Toast.makeText(MainActivity.this, R.string.Checkers, Toast.LENGTH_SHORT).show();
				//TODO: link to Checkers
			}
		});
		SettingsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.d(TAG, "SettingsButton Pressed");
				//When Settings button is pressed:
				//Placeholder "Toast" till Settings is implemented
				Toast.makeText(MainActivity.this, R.string.Settings, Toast.LENGTH_SHORT).show();
				//TODO: link to settings
			}
		});


	}

	@Override
	public void onStart() {
		super.onStart();
		Log.d(TAG, "onStart() called");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d(TAG, "onPause() called");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "onResume() called");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.d(TAG, "onStop() called");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy()");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
