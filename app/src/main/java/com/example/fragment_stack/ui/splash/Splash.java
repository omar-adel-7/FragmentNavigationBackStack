package com.example.fragment_stack.ui.splash;

import android.content.Intent;
import android.os.Handler;

import com.example.fragment_stack.R;
import com.example.fragment_stack.ui.Main.MainActivity;
import com.example.fragment_stack.ui.splash.presenter.ISplashContract;
import com.example.fragment_stack.ui.splash.presenter.SplashPresenter;

import modules.basemvp.BaseAppCompatActivity;


public class Splash extends BaseAppCompatActivity<ISplashContract.ISplashPresenter> implements ISplashContract.ISplashView {
    private final int SPLASH_DISPLAY_LENGHT = 1000;
    Intent myIntent;

    @Override
    public int getLayoutResource() {
        return R.layout.act_splash;
    }

    @Override
    public int getExtraLayout() {
        return 0;
    }

    @Override
    public int getContainerID() {
        return 0;
    }

    @Override
    public void configureUI() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

              myIntent = new Intent(Splash.this, MainActivity.class);

                startActivity(myIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }

    @Override
    public ISplashContract.ISplashPresenter injectDependencies() {
        return new SplashPresenter(this, this);
    }


}