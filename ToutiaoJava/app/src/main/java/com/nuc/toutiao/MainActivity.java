package com.nuc.toutiao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.nuc.toutiao.fragment.redianFragment;
import com.nuc.toutiao.fragment.shehuiFragment;
import com.nuc.toutiao.fragment.taizhouFragment;
import com.nuc.toutiao.fragment.tuijianFragment;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.mainViewPager);
        tabLayout = findViewById(R.id.mainTabLayout);

        viewPager2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position) {
                    case 0:
                        return new tuijianFragment();
                    case 1:
                        return new redianFragment();
                    case 2:
                        return new taizhouFragment();
                    default:
                        return new shehuiFragment();
                }
            }

            @Override
            public int getItemCount() {
                return 4;
            }
        });

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("推荐");
                        break;
                    case 1:
                        tab.setCustomView(R.layout.myredian);
                        break;
                    case 2:
                        tab.setText("泰州");
                        break;
                    case 3:
                        tab.setText("社会");
                        break;
                }
            }
        }).attach();

    }
}
