package edu.ycp.cs481.ycpgames;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends Activity {
    //declare instance of buttons
    private ImageButton TicTacToeButton;
    private ImageButton DotsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Get reference to TicTacToeButton
        TicTacToeButton = (ImageButton)findViewById(R.id.TicTacToeButton);
        //Set listener for TicTacToeButton
        TicTacToeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //When TicTacToe button is pressed:
                //Placeholder "Toast" till TicTacToe is implemented
                Toast.makeText(MainActivity.this, R.string.TicTacToe, Toast.LENGTH_SHORT).show();
            }
        });
        //Get reference to DotsButton
        DotsButton = (ImageButton)findViewById(R.id.DotsButton);
        //Set listener for DotsButton
        DotsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //When dots button is pressed:
                //Placeholder "Toast" till Dots is implemented
                Toast.makeText(MainActivity.this, R.string.Dots, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
