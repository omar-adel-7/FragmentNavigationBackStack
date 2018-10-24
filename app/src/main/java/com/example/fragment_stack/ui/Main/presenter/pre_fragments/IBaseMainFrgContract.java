package com.example.fragment_stack.ui.Main.presenter.pre_fragments;


import modules.basemvp.Base;


/**
 * Created by Net22 on 9/28/2017.
 */

public interface IBaseMainFrgContract {


    public interface IBaseMainFrgView {
        void displayData(String data);
    }

    public interface IBaseMainFrgPresenter extends Base.IPresenter {
        void getData();
     }
}