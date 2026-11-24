package com.example.advancedapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.os.Bundle;

import com.example.advancedapplication.databinding.ActivityModeBinding;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class ModeActivity extends AppCompatActivity {

    private ActivityModeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityModeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        binding.viewPager.setAdapter(new PagerAdapter(this));

        ArrayList<String> tabs = new ArrayList<>();
        tabs.add(getString(R.string.first));
        tabs.add(getString(R.string.second));
        tabs.add(getString(R.string.third));

        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) -> {
            tab.setText(tabs.get(position));
        }).attach();

        binding.btnSetMode.setOnClickListener(view -> showPopupMenu());
    }

    private void showPopupMenu() {
        PopupMenu popupMenu = new PopupMenu(ModeActivity.this, binding.btnSetMode);
        popupMenu.getMenuInflater().inflate(R.menu.menu_mode, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(menuItem -> {
            int id = menuItem.getItemId();
            if (id == R.id.item_light) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                return true;
            } else if (id == R.id.item_night) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                return true;
            } else if (id == R.id.item_system) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                return true;
            }
            return false;
        });
        popupMenu.show();
    }


    @Override
    public void onBackPressed() {
        if (binding.viewPager.getCurrentItem() != 0) {
            binding.viewPager.setCurrentItem(0);
        } else {
            super.onBackPressed();
        }
    }

    public class PagerAdapter extends FragmentStateAdapter {

        public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0: return SimpleFragment.newInstance(getString(R.string.first));
                case 1: return SimpleFragment.newInstance(getString(R.string.second));
                default: return SimpleFragment.newInstance(getString(R.string.third));
            }
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}
