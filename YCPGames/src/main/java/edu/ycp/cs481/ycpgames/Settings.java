package edu.ycp.cs481.ycpgames;

/**
 * Created by waffles on 10/4/13.
 */
public class Settings {
    //singleton is cool
    //and seems useful here
    private static Settings ourInstance = new Settings();

    public static Settings getInstance() {
        return ourInstance;
    }

    private Settings() {

    }
}
