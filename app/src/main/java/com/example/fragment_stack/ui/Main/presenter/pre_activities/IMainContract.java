package com.example.fragment_stack.ui.Main.presenter.pre_activities;


import androidx.fragment.app.Fragment;

import modules.basemvp.Base;


/**
 * Created by Net22 on 9/28/2017.
 */

public interface IMainContract {


    public interface IMainView {
        void switchTab(int menuItemId);
     }

    public interface IMainPresenter extends Base.IPresenter {



        void removeAllFragments();
        void removeAllFragmentsExceptOne(String fragmentTag);
        void removeFragment(String fragmentTag);

        void openFragment(Fragment fragment);


     }
}