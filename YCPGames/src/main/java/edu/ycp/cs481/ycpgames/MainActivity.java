package edu.ycp.cs481.ycpgames;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.Intent;


public class MainActivity extends Activity {
	//define debug string
	private static final String TAG = "YCPGamesActivity";
	//declare instance of buttons
	protected ImageButton TicTacToeButton;
	protected ImageButton DotsButton;
	protected ImageButton CheckersButton;
	protected ImageButton SettingsButton;
	Settings settings = Settings.getInstance();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d(TAG, "onCreate() called");

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
		//Get references to main menu buttons
		TicTacToeButton = (ImageButton) findViewById(R.id.TicTacToeButton);
		DotsButton = (ImageButton) findViewById(R.id.DotsButton);
		CheckersButton = (ImageButton) findViewById(R.id.CheckersButton);
		SettingsButton = (ImageButton) findViewById(R.id.SettingsButton);

		//Set listeners for main menu buttons
		TicTacToeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TicTacToeActivity.class);
                startActivity(intent);

            }
        });
		DotsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this, DotsActivity.class);
                startActivity(intent);
			}
		});
		CheckersButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this, CheckersActivity.class);
                startActivity(intent);

			}
		});
		SettingsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(intent);
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
