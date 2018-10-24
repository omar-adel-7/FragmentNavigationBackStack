package com.example.fragment_stack.ui.Main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.fragment_stack.R;
import com.example.fragment_stack.ui.Main.fragments.Fragment1;
import com.example.fragment_stack.ui.Main.fragments.Fragment2;
import com.example.fragment_stack.ui.Main.fragments.Fragment3;
import com.example.fragment_stack.ui.Main.fragments.Fragment4;
import com.example.fragment_stack.ui.Main.fragments.Fragment_Repeated;
import com.example.fragment_stack.ui.Main.fragments.Fragment_with_click;
import com.example.fragment_stack.ui.Main.presenter.pre_activities.IMainContract;
import com.example.fragment_stack.ui.Main.presenter.pre_activities.MainPresenter;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import butterknife.BindView;
import modules.general.ui.parentview.ParentActivity;
import modules.general.ui.utils.general_listeners.ITitleListener;

public class MainActivity extends ParentActivity<MainPresenter> implements IMainContract.IMainView {


    @BindView(R.id.main_lnr_content)
    LinearLayout mainLnrContent;
    @BindView(R.id.main_bnv_bottombar)
    BottomNavigationViewEx mainBnvBottombar;
    @BindView(R.id.main_lnr_bottomservices)
    LinearLayout mainLnrBottomservices;

    @Override
    public int getExtraLayout() {
        return R.layout.act_main;
    }

    public int getContainerFragmentID() {
        return R.id.main_lnr_content;
    }

    @Override
    public Fragment getCurrentFragment() {
         return  getMainPresenter().getFragmentHelper().getCurrentFragment();
    }



    @Override
    public void configureUI() {
        super.configureUI();
        disableDrawerSwipe();
          getCsTitle().setTitleListener(new ITitleListener() {
            @Override
            public void onBackPressed() {
                MainActivity.this.onBackPressed();
            }

             @Override
             public void onMenuPressed() {

             }

         });

        mainBnvBottombar.enableItemShiftingMode(false);
        mainBnvBottombar.enableShiftingMode(false);
        mainBnvBottombar.setIconsMarginTop(0);
        mainBnvBottombar.setTextSize(12);
          mainBnvBottombar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switchTab(item.getItemId());
                return false;
            }
        });


       mainBnvBottombar.setCurrentItem(0);
    }

    public void updateTitle(String title) {
         getCsTitle().updateTitle(title);
    }


    @Override
    public void switchTab(int itemId ) {
        MenuItem item = mainBnvBottombar.getMenu().findItem(itemId);
        clearBottomNavigation();
        mainBnvBottombar.setIconTintList(mainBnvBottombar.getMenuItemPosition(item), getResources().getColorStateList(
                R.color.colorPrimary));
        mainBnvBottombar.setTextTintList(mainBnvBottombar.getMenuItemPosition(item),
                getResources().getColorStateList(R.color.colorPrimary));

            if (itemId == R.id.main_navigation_1) {
                removeAllFragmentsExceptOne(Fragment1.class.getName());

                Fragment1 fragment1=Fragment1.newInstance("fragment 1 from bottom tabs");
                openFragment(fragment1);

            } else if (itemId == R.id.main_navigation_with_click) {
                removeFragment(Fragment_Repeated.class.getName());
                Fragment_with_click fragmentWithClick=Fragment_with_click.newInstance("fragment click from bottom tabs");
                openFragment(fragmentWithClick);
            }

            else if (itemId == R.id.main_navigation_2) {
                Fragment2 fragment2=Fragment2.newInstance("fragment 2 from bottom tabs");
                openFragment(fragment2);

            } else if (itemId == R.id.main_navigation_3) {
                Fragment3 fragment3=Fragment3.newInstance("fragment 3 from bottom tabs");
                openFragment(fragment3);

            } else if (itemId == R.id.main_navigation_4) {
                Fragment4 fragment4=Fragment4.newInstance("fragment 4 from bottom tabs");
                openFragment(fragment4);
            }

    }


    public void clearBottomNavigation() {
        int count = mainBnvBottombar.getItemCount();
        for (int i = 0; i < count; i++) {
            mainBnvBottombar.setIconTintList(i, getResources().getColorStateList(R.color.menu_icontint_color));
            mainBnvBottombar.setTextTintList(i, getResources().getColorStateList(R.color.menu_text_color));
        }
    }

    public MainPresenter getMainPresenter() {
        return ((MainPresenter) getPresenter());
    }

    @Override
    public MainPresenter injectDependencies() {
        return new MainPresenter(this, this);
    }

    @Override
    public void onSaveInstanceState(Bundle oldInstanceState) {
        super.onSaveInstanceState(oldInstanceState);
        oldInstanceState.clear();
    }


    @Override
    public void onBackPressed() {
         if(getMainPresenter().getFragmentHelper().getCurrentFragment()!=null)
        {
            if(getMainPresenter().getFragmentHelper().getCurrentFragment().getClass().getName().equals(
                    Fragment1.class.getName()))
            {
                finish();
            }
            else {
                onBacksetSelectedTab(getMainPresenter().getFragmentHelper().backFragmentsTag());
            }
        }
        else
        {
            finish();
        }

    }

    public void onBacksetSelectedTab(String fragmentTag)
    {


        if(fragmentTag.equals(Fragment1.class.getName()))
        {
            mainBnvBottombar.setCurrentItem(0);
        }
        else
        if(fragmentTag.equals(Fragment_with_click.class.getName()))
        {
            mainBnvBottombar.setCurrentItem(1);
        }
        else
        if(fragmentTag.equals(Fragment_Repeated.class.getName()))
        {
            clearBottomNavigation();
        }

        else
        if(fragmentTag.equals(Fragment2.class.getName()))
        {

            mainBnvBottombar.setCurrentItem(2);

        }
        else if(fragmentTag.equals(Fragment3.class.getName()))
        {
            mainBnvBottombar.setCurrentItem(3);
        }

        else if(fragmentTag.equals(Fragment4.class.getName()))
        {
            mainBnvBottombar.setCurrentItem(4);
        }


    }

    public void selectMenuItemId(int menuItemId) {
        MenuItem item = mainBnvBottombar.getMenu().findItem( menuItemId);
             clearBottomNavigation();
            mainBnvBottombar.setIconTintList(mainBnvBottombar.getMenuItemPosition(item), getResources().getColorStateList(
                    R.color.colorPrimary));
            mainBnvBottombar.setTextTintList(mainBnvBottombar.getMenuItemPosition(item),
                    getResources().getColorStateList(R.color.colorPrimary));
            }

    public void removeFragment(String fragmentTag) {
        getMainPresenter().removeFragment(fragmentTag);
    }

    public void removeAllFragmentsExceptOne(String fragmentTag) {
        getMainPresenter().removeAllFragmentsExceptOne(fragmentTag);
    }


    public void openFragment(Fragment fragment) {
        getMainPresenter().openFragment(fragment);
    }



}
