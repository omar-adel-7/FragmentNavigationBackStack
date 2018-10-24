package modules.general.ui.custom_views.NavigationViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.fragment_stack.R;

import butterknife.ButterKnife;


/**
 * Created by Net22 on 9/14/2017.
 */

public class Menu extends LinearLayout {



    public Menu(Context context) {
        super(context);
        initView(context);
        doWork();
    }


    public Menu(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        doWork();
    }

    public Menu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        doWork();

    }

    private void initView(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.menu, this, true);
        ButterKnife.bind(this);  //true also


    }

    private void doWork() {


    }

}
