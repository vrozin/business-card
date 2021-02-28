package com.vrozin.businesscard.views.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.vrozin.businesscard.R;
import com.vrozin.businesscard.databinding.ActivityNavHostBinding;
import com.vrozin.businesscard.services.managers.ThemeManager;

public class ActivityNavHost extends AppCompatActivity
{
    // Views
    ActivityNavHostBinding activityNavHostBinding;

    // Misc
    private static final String  TAG = "***MainNavAct***";
    private NavController navController;
    private ThemeManager  themeManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        initViews();
        initNavigation();
    }

    private void initViews()
    {
        setTheme();

        activityNavHostBinding = ActivityNavHostBinding.inflate(getLayoutInflater());
        setContentView(activityNavHostBinding.getRoot());
    }

    private void setTheme()
    {
        themeManager = new ThemeManager(ActivityNavHost.this);
        themeManager.applySavedTheme();
    }

    private void initNavigation()
    {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(activityNavHostBinding.bottomNavigation, navController);
    }

    /*
    private void showUserSettingsMenu(View v)
    {
        ContextThemeWrapper contextThemeWrapper;

        if (themeManager.isDarkTheme())
            contextThemeWrapper = new ContextThemeWrapper(this, R.style.PopupDark);
        else
            contextThemeWrapper = new ContextThemeWrapper(this, R.style.PopupLight);

        MenuInflater menuInflater = new MenuInflater(contextThemeWrapper);
        PopupMenu popupMenu = new PopupMenu(contextThemeWrapper, activityNavHostBinding.bottomNavigation.findViewById(R.id.bottom_nav_user_settings));

        menuInflater.inflate(R.menu.bottom_nav_user_settings, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(item ->
        {
            if (item.getItemId() == R.id.bottom_nav_user_settings_toggle_mode)
            {
                Log.d(TAG, "Changing theme!");
                themeManager.switchTheme();

                // Recreating the current activity to update the UI.
                // This action won't get rid of ViewModel, but "finish()"+"startActivity()" will.
                // We want to keep ViewModel since it stores currently selected fragment page and
                // we should start the redrawn activity with it.
                recreate();
            } else if (item.getItemId() == R.id.bottom_nav_user_settings_log_out)
            {
                mainNavigationHostViewModel.setCurrentLayoutPage(MainNavigationHostViewModel.LayoutPage.LOGOUT);
            }

            return false;
        });

        try
        {
            @SuppressWarnings("JavaReflectionMemberAccess")
            Field fMenuHelper = PopupMenu.class.getDeclaredField("mPopup");
            fMenuHelper.setAccessible(true);

            Object menuHelper = fMenuHelper.get(popupMenu);
            Objects.requireNonNull(menuHelper).getClass().getDeclaredMethod("setForceShowIcon", new Class[]{boolean.class}).invoke(menuHelper,true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        MenuItem menuItem = popupMenu.getMenu().findItem(R.id.bottom_nav_user_settings_toggle_mode);
        if (themeManager.isDarkTheme())
            menuItem.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.theme_dark_icon,null));
        else
            menuItem.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.theme_light_icon,null));

        popupMenu.show();
    }
     */
}
