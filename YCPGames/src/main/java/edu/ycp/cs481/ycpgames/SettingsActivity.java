package edu.ycp.cs481.ycpgames;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;


public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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
       public void onSharedPreferenceChanged(SharedPreferences sp, String key){

       }
    }

}
