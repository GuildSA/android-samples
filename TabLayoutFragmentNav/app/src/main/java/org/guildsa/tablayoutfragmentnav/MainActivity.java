package org.guildsa.tablayoutfragmentnav;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabPagerAdapter mTabPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mTabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        // Increase this limit if we add more tabs! Otherwise offscreen pages may release their fragments.
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mTabPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class TabPagerAdapter extends FragmentPagerAdapter {

        private final List<String> tabTitles = new ArrayList<String>() {{
            add("Tab 1");
            add("Tab 2");
            add("Tab 3");
        }};

        private List<Fragment> tabs = new ArrayList<>();

        public TabPagerAdapter(FragmentManager fm) {
            super(fm);

            initializeTabs();
        }

        private void initializeTabs() {

            tabs.add(HostFragment.newInstance(new Tab1Fragment()));
            tabs.add(HostFragment.newInstance(new Tab2Fragment()));
            tabs.add(HostFragment.newInstance(new Tab3Fragment()));
        }

        @Override
        public Fragment getItem(int position) {
            return tabs.get(position);
        }

        @Override
        public int getCount() {
            return tabs.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles.get(position);
        }
    }

    @Override
    public void onBackPressed() {

        if(!BackStackFragment.handleBackPressed(getSupportFragmentManager())) {
            super.onBackPressed();
        }
    }

    public void openNewContentFragment(Fragment fragment) {

        HostFragment hostFragment = (HostFragment) mTabPagerAdapter.getItem(mViewPager.getCurrentItem());
        hostFragment.replaceFragment(fragment, true);
    }
}