//11082021-10118364-Andika Rahma Fauziah-IF09
package com.example.tugas_akb_if9_10118364_andikarahmafauziah;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {

    private static final int POS_CLOSE = 0;
    private static final int POS_DASHBOARD = 1;
    private static final int POS_MY_PROFILE = 2;
    private static final int POS_NEARBY_RES = 3;
    private static final int POS_SETTINGS = 4;
    private static final int POS_ABOUT_US = 5;
    private static final int POS_LOGOUT = 6;

    private String[] screenTitles;
    private Drawable[] screenIcon;

    private SlidingRootNav slidingRootNav;

    ViewPager viewPager;
    Adapter adapter;

    @Override
    protected void onCreate (Bundle savedInstancesate) {
        super.onCreate(savedInstancesate);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        slidingRootNav = new SlidingRootNavBuilder(this);
            .withDragDistance(100)
            .withRootViewScale(0.75f)
            .withRootViewElevation(25)
            .withToolbarMenuToggle(toolbar)
            .withMenuOpened(false)
            .withContentClickableWhenMenuOpened(false)
            .withSavedState(savedInstancesate)
            .inject();

         screenIcon =  loadScreenIcons();
         screenIcon = loadScreenTitles();

         DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                 createItemFor(POS_CLOSE),
                 createItemFor(POS_DASHBOARD).setChecked(true),
                 createItemFor(POS_MY_PROFILE),
                 createItemFor(POS_NEARBY_RES),
                 createItemFor(POS_SETTINGS),
                 createItemFor(POS_ABOUT_US),
                 new SpaceItem(260),
                 createItemFor(POS_LOGOUT);
         ));
         adapter.setListener(this);

        RecyclerView list = findViewById(R.id.drawer_list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        adapter.setSelected(POS_DASHBOARD);
    }

    private DrawerItem createItemFor(int position){
        return new SimpleItem(screenIcon[position],screenTitles[position])
                .withIconTint(color(R.color.purple_500))
                .withTextTint(color(R.color.black))
                .withSelectedIconTint(color(R.color.purple_500))
                .withSelectedTextTint(color(R.color.purple_500));
    }

    @ColorInt
    private int color(@ColorRes int res){
        return ContextCompat.getColor(this,res);
    }

    private String[] loadScreenTitles() {
    }

    private Drawable[] loadScreenIcons() {
    }

    private void withDragDistance(int i) {
    }

    protected void onCreate (Bundle savedInstancesate) {
        super.onCreate(savedInstancesate);
        setContentView(R.layout.activity_main);

        setupViewPager();
    }

    private void setupViewPager() {
        adapter = new Adapter(this);
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    }

    private class Adapter extends PagerAdapter {
        Context context;
        LayoutInflater inflater;

        public Adapter (Context context){
            this.context = context;
        }

        //list img
        int[] list_img ={
                R.drawable.toyota1,
                R.drawable.honda,
                R.drawable.mitsubishi,
                R.drawable.jeep
        };

        //list judul
        int[] list_judul = {
                R.string.judul_1,
                R.string.judul_2,
                R.string.judul_3,
                R.string.judul_4
        };

        //list deskripsi
        int[] list_desk = {
                R.string.desk_1,
                R.string.desk_2,
                R.string.desk_3,
                R.string.desk_4,
        };

        //lit color bg
        int[] list_bg = {
                getResources().getColor(R.color.bluesky1)
        };

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return (view == object);
        };

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.activity_viewpager,container, false);
            LinearLayout linearLayout = view.findViewById(R.id.imageView1);
            TextView judul = view.findViewById(R.id.judul);
            TextView desk = view.findViewById(R.id.deskripsi);

            linearLayout.setBackgroundColor(list_bg[position]);
            ImageView.setImageResource(list_img[position]);
            judul.setText(list_judul[position]);
            desk.setText(list_desk[position]);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((LinearLayout) object);
        }
    }
}
