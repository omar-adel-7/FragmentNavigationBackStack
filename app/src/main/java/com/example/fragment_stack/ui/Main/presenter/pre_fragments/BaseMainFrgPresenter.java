package com.example.fragment_stack.ui.Main.presenter.pre_fragments;

import android.content.Context;
import android.support.v4.app.Fragment;


/**
 * Created by Net22 on 10/11/2017.
 */

public class BaseMainFrgPresenter implements
        IBaseMainFrgContract.IBaseMainFrgPresenter

{

    private final Context mContext;
    IBaseMainFrgContract.IBaseMainFrgView mView;

    public static String DATA_KEY="DATA_KEY";

    public BaseMainFrgPresenter(Context context, IBaseMainFrgContract.IBaseMainFrgView view) {
        mView = view;
        mContext = context;

    }


    @Override
    public void getData() {
        mView.displayData (((Fragment)mView).getArguments().getString(DATA_KEY));
    }
}