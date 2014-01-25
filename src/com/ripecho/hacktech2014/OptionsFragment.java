package com.ripecho.hacktech2014;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class OptionsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.draw_preferences);
    }
}