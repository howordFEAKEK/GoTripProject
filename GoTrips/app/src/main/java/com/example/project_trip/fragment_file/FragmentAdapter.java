package com.example.project_trip.fragment_file;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.project_trip.fragment_file.CusmaidFragment;
import com.example.project_trip.fragment_file.LocalFragment;
import com.example.project_trip.fragment_file.MainFragment;

public class FragmentAdapter extends FragmentStateAdapter {

    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1 :
                return new MainFragment();
            case 2 :
                return new CusmaidFragment();

        }

        return new LocalFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
