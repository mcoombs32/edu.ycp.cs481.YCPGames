package edu.ycp.cs481.ycpgames;

import android.util.Log;

/**
 * Created by waffles on 10/4/13.
 */
public class Settings {
	private static final String TAG = "YCPGamesSettings";
	private Boolean singlePlayer = false;
	private int difficulty = 0;
	private int gridHeight = 5;
    private int gridWidth = 5;

	/*
	singleton is cool
	and seems useful here
	*/
	private static Settings ourInstance = new Settings();

	public static Settings getInstance() {
		return ourInstance;
	}

	private Settings() {
		Log.d(TAG, "Settings singleton object created");
	}

	public boolean isSinglePlayer() {
		return singlePlayer;
	}

	public void setSinglePlayer(Boolean SP) {
		singlePlayer = SP;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int d) {
		difficulty = d;
	}

    public int getGridHeight() {return gridHeight;}
    public int getGridWidth() {return gridWidth;}

    public void setGridHeight(int g){this.gridHeight = g;}
    public void setGridWidth(int g){this.gridWidth = g;}



}
