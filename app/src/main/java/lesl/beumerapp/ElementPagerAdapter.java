package lesl.beumerapp;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

import lesl.beumerapp.models.Element;

public class ElementPagerAdapter extends FragmentStatePagerAdapter {
    private MainViewModel viewModel;

    public ElementPagerAdapter(FragmentManager fm, MainViewModel vm) {
        super(fm);
        viewModel = vm;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        viewModel.setCurrentElement(position);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new ElementFragment();
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return viewModel.getElementName(position);
    }
}
