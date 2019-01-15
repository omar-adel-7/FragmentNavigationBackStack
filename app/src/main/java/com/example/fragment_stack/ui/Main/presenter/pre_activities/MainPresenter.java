package com.example.fragment_stack.ui.Main.presenter.pre_activities;

import android.content.Context;
import androidx.fragment.app.Fragment;

import com.example.fragment_stack.ui.Main.MainActivity;
import com.example.fragment_stack.ui.Main.fragments.Fragment_Repeated;

import modules.general.utils.FragmentHelper;


/**
 * Created by Net22 on 10/11/2017.
 */

public class MainPresenter implements
        IMainContract.IMainPresenter, FragmentHelper.ActivityInterface

{

    private final Context mContext;
    IMainContract.IMainView mView;
    FragmentHelper fragmentHelper;


    public FragmentHelper getFragmentHelper() {
        return fragmentHelper;
    }


    public MainPresenter(Context context, IMainContract.IMainView view) {
        mView = view;
        mContext = context;
        fragmentHelper = new FragmentHelper(((MainActivity) context).getSupportFragmentManager()
                , ((MainActivity) context).getContainerFragmentID(), this);


    }


    @Override
    public void removeAllFragments() {

    }

    @Override
    public void removeAllFragmentsExceptOne(String fragmentTag) {
        fragmentHelper.removeAllFragmentsExceptOne(fragmentTag);

    }

    @Override
    public void removeFragment(String fragmentTag) {
        fragmentHelper.removeFragment(fragmentTag);
    }


    @Override
    public void openFragment(Fragment fragment) {
        if (fragmentHelper.getCurrentFragment() != null &&
                !fragment.getClass().getName().equalsIgnoreCase(Fragment_Repeated.class.getName())) {
            if (!fragmentHelper.getCurrentFragment().getClass().getName().equals(fragment.getClass().getName())) {
                fragmentHelper.showFragment(fragment,false);
            }
        } else {
            if (fragment.getClass().getName().equalsIgnoreCase(Fragment_Repeated.class.getName())) {
                fragmentHelper.showFragmentWithRepeat(fragment);
            } else {
                fragmentHelper.showFragment(fragment,false);
            }
        }


    }

    @Override
    public void switchActivityUIByFragment(Fragment fragment) {

    }

    @Override
    public void noBackFragments() {
        ((MainActivity) mContext).finish();
    }


}