package com.vrozin.businesscard.services.managers;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.StyleRes;

import com.vrozin.businesscard.R;

/**
 * Manages storing and switching between app themes.
 * Should be called before any activity inflates its views.
 */
public class ThemeManager
{
    private final static String APP_KEY   = "com.scentroid.sims2";
    private final static String THEME_KEY = "theme";
    private final SharedPreferences sharedPreferences;
    private final Context           context;
    private int                     currentTheme;

    public ThemeManager(Context context)
    {
        sharedPreferences = context.getSharedPreferences(APP_KEY, Context.MODE_PRIVATE);
        this.context      = context;
        currentTheme      = sharedPreferences.getInt(THEME_KEY, R.style.DarkTheme);
    }

    public void applySavedTheme()
    {
        currentTheme = sharedPreferences.getInt(THEME_KEY, R.style.DarkTheme);
        context.setTheme(currentTheme);
    }

    private void saveTheme(@StyleRes int theme)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt(THEME_KEY, theme);
        editor.apply();
    }

    public void switchTheme()
    {
        if (currentTheme == R.style.DarkTheme)
            saveTheme(R.style.LightTheme);
        else
            saveTheme(R.style.DarkTheme);
    }

    public boolean isDarkTheme()
    {
        return currentTheme == R.style.DarkTheme;
    }
}
