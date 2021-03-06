package com.example.fragment_stack.ui.Main.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fragment_stack.R;
import com.example.fragment_stack.ui.Main.MainActivity;
import com.example.fragment_stack.ui.Main.presenter.pre_fragments.BaseMainFrgPresenter;
import com.example.fragment_stack.ui.Main.presenter.pre_fragments.IBaseMainFrgContract;

import butterknife.BindView;
import modules.basemvp.BaseSupportFragment;

import static com.example.fragment_stack.ui.Main.presenter.pre_fragments.BaseMainFrgPresenter.DATA_KEY;

public class Fragment_with_click extends BaseSupportFragment<BaseMainFrgPresenter> implements IBaseMainFrgContract.IBaseMainFrgView {

    @BindView(R.id.btn_data)
    Button btnData ;

    @Override
    public int getLayoutResource() {
        return R.layout.frg_with_click_repeated;
    }

    @Override
    public void configureUI() {
        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_Repeated fragment_repeated=Fragment_Repeated.newInstance("repeated from fragment with click");
                ((MainActivity)getContainerActivity()).openFragment(fragment_repeated);
            }
        });
        getPresenter().getData();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getContainerActivity()).updateTitle("Fragment with click");
    }

    @Override
    public BaseMainFrgPresenter injectDependencies() {
        return new BaseMainFrgPresenter(getContainerActivity(), this);
    }

    @Override
    public void displayData(String data) {
        btnData.setText(data);

    }
    public static Fragment_with_click newInstance(String data) {
        Fragment_with_click fragment3=new Fragment_with_click();
        Bundle bundle=new Bundle();
        bundle.putString(DATA_KEY,data);
        fragment3.setArguments(bundle);
        return fragment3 ;
    }

}
