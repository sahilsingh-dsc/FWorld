package com.example.fwprld.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fwprld.R;
import com.example.fwprld.ui.fragments.DiscoverFragment;
import com.example.fwprld.ui.fragments.FragComments;
import com.example.fwprld.ui.fragments.FragRecommends;
import com.example.fwprld.ui.fragments.MomentFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class ActPopDetail extends AppCompatActivity implements View.OnClickListener,TabLayout.OnTabSelectedListener{
TextView tvBack,contact,refund,terms,privacy;
RelativeLayout rlBack;
    public TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_popdetails);
        InitCompo();
        Listner();
        setTab();

    }

    private void Listner() {
        rlBack.setOnClickListener(this);
        tvBack.setOnClickListener(this);
        tabLayout.setOnTabSelectedListener(this);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                tabLayout.setScrollPosition(position,0f,true);
//                tabLayout.s
//                highLightCurrentTab(position); // for tab change
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


    }

    public void InitCompo()
    {
        tvBack=findViewById(R.id.tv_back);
        rlBack=findViewById(R.id.rl_back);
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);

        viewPager = (ViewPager) findViewById(R.id.pager);

    }
   public void  setTab()
    {
        tabLayout.addTab(tabLayout.newTab().setText("Comments"));
        tabLayout.addTab(tabLayout.newTab().setText("Recommended"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        //Creating our pager adapter
        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
      switch (v.getId())
      {
          case R.id.rl_back:
          case R.id.tv_back:
              finish();
              break;
      }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        tab.getPosition();
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    public class Pager extends FragmentStatePagerAdapter {

        //integer to count number of tabs
        int tabCount;

        //Constructor to the class
        public Pager(FragmentManager fm, int tabCount) {
            super(fm);
            //Initializing tab count
            this.tabCount= tabCount;
        }

        //Overriding method getItem
        @Override
        public Fragment getItem(int position) {
            //Returning the current tabs
            switch (position) {
                case 0:
                    FragComments tab1 = new FragComments();
                    return tab1;
                case 1:
                    FragRecommends tab2 = new FragRecommends();
                    return tab2;
                default:
                    return null;
            }
        }

        //Overriden method getCount to get the number of tabs
        @Override
        public int getCount() {
            return tabCount;
        }
    }
//    private void highLightCurrentTab(int position) {
//        for (int i = 0; i < tabLayout.getTabCount(); i++) {
//            TabLayout.Tab tab = tabLayout.getTabAt(i);
//            assert tab != null;
//            tab.setCustomView(null);
//            tab.setCustomView(adapter.getTabView(i));
//        }
//    }
}
