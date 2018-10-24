package com.example.fragment_stack.ui.Main.fragments;

import android.os.Bundle;
import android.widget.TextView;

import com.example.fragment_stack.R;
import com.example.fragment_stack.ui.Main.MainActivity;
import com.example.fragment_stack.ui.Main.presenter.pre_fragments.BaseMainFrgPresenter;
import com.example.fragment_stack.ui.Main.presenter.pre_fragments.IBaseMainFrgContract;

import butterknife.BindView;
import modules.basemvp.BaseSupportFragment;

import static com.example.fragment_stack.ui.Main.presenter.pre_fragments.BaseMainFrgPresenter.DATA_KEY;

public class Fragment2 extends BaseSupportFragment<BaseMainFrgPresenter> implements IBaseMainFrgContract.IBaseMainFrgView {

    @BindView(R.id.txtv_data)
    TextView txtvData;

    @Override
    public int getLayoutResource() {
        return R.layout.frg_base_main;
    }

    @Override
    public void configureUI() {
        getPresenter().getData();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getContainerActivity()).updateTitle("Fragment 2");
    }

    @Override
    public BaseMainFrgPresenter injectDependencies() {
        return new BaseMainFrgPresenter(getContainerActivity(), this);
    }

    @Override
    public void displayData(String data) {
        txtvData.setText(data);
    }

    public static Fragment2 newInstance(String data) {
        Fragment2 fragment2=new Fragment2();
        Bundle bundle=new Bundle();
        bundle.putString(DATA_KEY,data);
        fragment2.setArguments(bundle);
        return fragment2 ;
    }
}
