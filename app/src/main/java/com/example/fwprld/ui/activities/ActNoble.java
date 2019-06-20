package com.example.fwprld.ui.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.fwprld.R;
import com.example.fwprld.ui.fragments.FragChildNobleCount;
import com.example.fwprld.ui.fragments.FragChildNobleDuke;
import com.example.fwprld.ui.fragments.FragChildNobleKing;
import com.example.fwprld.ui.fragments.FragChildNobleViscount;

public class ActNoble extends FragmentActivity implements View.OnClickListener,TabLayout.OnTabSelectedListener{
    TextView tvBack;
    public TabLayout tabLayout;
//    public ViewPager viewPager;
    public FrameLayout viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_noble);
        initCompo();
        Listner();
        AddItem();
        SetDefalutFrag();
    }

    private void SetDefalutFrag() {

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.pager, new FragChildNobleKing())
                .commit();
        tabLayout.setOnTabSelectedListener(this);
    }

    private void Listner() {
        tvBack.setOnClickListener(this);
        tabLayout.setOnTabSelectedListener(this);
    }

    private void initCompo() {
        tvBack=findViewById(R.id.tv_back);

        tabLayout = (TabLayout)findViewById(R.id.tabLayout_noble);
//        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager = (FrameLayout)findViewById(R.id.pager);

    }
    public void AddItem()
    {
        tabLayout.addTab(tabLayout.newTab().setText("King"));
        tabLayout.addTab(tabLayout.newTab().setText("Duke"));
        tabLayout.addTab(tabLayout.newTab().setText("Count"));
        tabLayout.addTab(tabLayout.newTab().setText("Viscount"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //Creating our pager adapter
//        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());
//
//        //Adding adapter to pager
//        viewPager.setAdapter(adapter);
//        viewPager.setEnabled(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        startActivity(new Intent(getApplicationContext(),SettingActivity.class));
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv_back:
                finish();
                break;
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
//        viewPager.setCurrentItem(tab.getPosition());
        Fragment fragment = null;

        switch (tab.getPosition()) {

            case 0:
                fragment = new FragChildNobleKing();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.pager, fragment)
                        .commit();
                break;

            case 1:
                fragment = new FragChildNobleDuke();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.pager, fragment)
                        .commit();
//                fragment = new MyStoriesFragment();
//                getChildFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.pager, fragment)
//                        .commit();
                break;

            case 2:
                fragment = new FragChildNobleCount();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.pager, fragment)
                        .commit();
//                fragment = new F_ArtistFragment();
//                getChildFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.pager, fragment)
//                        .commit();
                break;

            case 3:
                fragment = new FragChildNobleViscount();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.pager, fragment)
                        .commit();
//                fragment = new DuetFragment();
//                getChildFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.pager, fragment)
//                        .commit();

                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

/*
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
                case 2:
                    FragComments tab3 = new FragComments();
                    return tab3;
                case 3:
                    FragRecommends tab4 = new FragRecommends();
                    return tab4;
                default:
                    return null;
            }
        }

        //Overriden method getCount to get the number of tabs
        @Override
        public int getCount() {
            return tabCount;
        }
    }*/
}
