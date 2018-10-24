package modules.general.ui.custom_views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fragment_stack.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import modules.general.ui.utils.general_listeners.ITitleListener;

public class CustomTitle extends LinearLayout {
    @BindView(R.id.customtitle_btn_back)
    ImageView btnBack;
    @BindView(R.id.custom_title_rl_main)
    RelativeLayout custom_title_rl_main;
    @BindView(R.id.customtitle_btn_menu)
    ImageView btnMenuMain;


    @BindView(R.id.customtitle_txt_title)
    TextView txtTitle;
    ITitleListener titleListener ;

    public CustomTitle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_title, this);
        ButterKnife.bind(this);
    }

    public ImageView getBtnMenuMain() {
        return btnMenuMain;
    }

    public void setTitleListener(ITitleListener titleListener) {
        this.titleListener = titleListener;
        btnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomTitle.this.titleListener.onBackPressed();
            }
        });
        btnMenuMain.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomTitle.this.titleListener.onMenuPressed();
            }
        });

    }

    public void updateTitle(String title) {
        txtTitle.setText(title);
    }

    public void hideCustomTitle() {
        setVisibility(GONE);
    }

    public void showCustomTitle() {
        setVisibility(VISIBLE);
    }


    public void showMenu() {
        btnMenuMain.setVisibility(VISIBLE);
    }

    public void hideMenu() {
        btnMenuMain.setVisibility(GONE);
    }


    public void hideMenuAndBack() {
        btnMenuMain.setVisibility(GONE);
        btnBack.setVisibility(GONE);
    }

}