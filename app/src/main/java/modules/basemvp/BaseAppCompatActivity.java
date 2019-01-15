package modules.basemvp;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
//import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
//import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
//import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.EditText;

import com.example.fragment_stack.R;

import butterknife.ButterKnife;
import modules.general.utils.LanguageUtil;
import modules.general.utils.Prefs;

import static modules.general.utils.LanguageUtil.LanguageState;


public abstract class BaseAppCompatActivity<P extends Base.IPresenter>
        extends AppCompatActivity
        implements Base.IViewAct<P> {
    private P mPresenter;
    public String[] screen_types;
    public String current_lang = LanguageUtil.ENGLISH_LANGUAGE;

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            View v = getCurrentFocus();
//            if ( v instanceof EditText) {
//                Rect outRect = new Rect();
//                v.getGlobalVisibleRect(outRect);
//                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
//                    Log.e("focus", "touchevent");
//                    v.clearFocus();
//                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                }
//            }
//        }
//        return super.dispatchTouchEvent(event);
//    }


    @Override
    protected void attachBaseContext(Context newBase) {
        String lang_code = LanguageUtil.getAppLanguage(); //load it from SharedPref
        Context context = LanguageUtil.setAppLanguage(newBase, lang_code);
        super.attachBaseContext(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        onCreateOperation();
    }

    public void onCreateOperation() {
        setScreenTypes();
        setStatusBarColor();
        setOrientation();
        setLanguage();
        setContentView(getLayoutResource());
         setLayout();
        setPresenterConfigure();
    }
    public void setScreenTypes() {
        screen_types = getResources().getStringArray(R.array.screen_types);
    }
    public void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.status_bar_color));
        }
    }
    public void setOrientation() {
        if (getString(R.string.screen_type).equals(screen_types[0])) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else   {

            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            int height = size.y;


            if (width < height) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            } else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }

        }

    }
    public void setLanguage() {
        current_lang = Prefs.getString(LanguageState,LanguageUtil.ENGLISH_LANGUAGE);
        LanguageUtil.setAppLanguage(this,current_lang);
    }

    public void setLayout() {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup v = ((ViewGroup) findViewById(android.R.id.content));
        if (v != null) {
            if (getExtraLayout() > 0) {
                View extraView = layoutInflater.inflate(getExtraLayout(), null);
                ViewGroup contentView = (ViewGroup) v.findViewById(getContainerID());
                if (contentView != null)
                {
                    contentView.addView(extraView);
                }
                ButterKnife.bind(this, v);
            } else {
                ButterKnife.bind(this);
            }
        } else {
            ButterKnife.bind(this);
        }
    }


    public void setPresenterConfigure() {
        mPresenter = injectDependencies();
        if (getPresenter() == null) {
            throw new IllegalArgumentException("You must inject the " +
                    "dependencies before retrieving the presenter");
        } else {
            configureUI();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
     }

    @Override
    public P getPresenter() {
        return mPresenter;
    }


}
