package edu.ycp.cs481.ycpgames;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;


public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
    }


    public static class SettingsFragment extends PreferenceFragment {

       @Override
        public void onCreate(Bundle savedInstanceState){
           super.onCreate(savedInstanceState);
           addPreferencesFromResource(R.xml.preferences);

       }
    }

}
