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

public class Fragment4 extends BaseSupportFragment<BaseMainFrgPresenter> implements IBaseMainFrgContract.IBaseMainFrgView {

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
        ((MainActivity)getContainerActivity()).updateTitle("Fragment 4");
    }

    @Override
    public BaseMainFrgPresenter injectDependencies() {
        return new BaseMainFrgPresenter(getContainerActivity(), this);
    }

    @Override
    public void displayData(String data) {
        txtvData.setText(data);
    }

    public static Fragment4 newInstance(String data) {
        Fragment4 fragment4=new Fragment4();
        Bundle bundle=new Bundle();
        bundle.putString(DATA_KEY,data);
        fragment4.setArguments(bundle);
        return fragment4 ;
    }
}
