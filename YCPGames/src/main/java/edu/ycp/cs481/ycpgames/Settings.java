package edu.ycp.cs481.ycpgames;

/**
 * Created by waffles on 10/4/13.
 */
public class Settings {
	private static final String TAG = "YCPGamesSettings";
    private Boolean singlePlayer = true;
    private int difficulty = 0;
    private int girdSize = 3;
    /*
    singleton is cool
    and seems useful here
    */
    private static Settings ourInstance = new Settings();

    public static Settings getInstance() {
        return ourInstance;
    }

    private Settings() {

    }
    /*
     *method to see if it is a single player or multiplayer game
     * returns true if singleplayer
     */
    public boolean isSinglePlayer(){
        return singlePlayer;
    }
    /*
     *method to set singleplayer vs multiplayer
     */
    public void setSinglePlayer(Boolean SP){
        singlePlayer = SP;
    }

    /*
     *getter and setter for difficulty
     */
    public int getDifficulty(){
        return difficulty;
    }
    public void setDifficulty(int d){
        difficulty = d;
    }

    /*
     * getter and setter for gridsize
     *
     */
    public int getGirdSize(){
        return girdSize;
    }
    public void setGirdSize(int g){
        girdSize = g;
    }
}
