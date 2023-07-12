package com.bagoes.login;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyFragmentAdapter extends FragmentPagerAdapter
{
    private BeritaFragment _beritaFragment = new BeritaFragment();
    private Context _context;
    private ECommerceFragment _eCommerceFragment = new ECommerceFragment();
    private int _tabCount;
    private KampusFragment _kampusFragment = new KampusFragment();

    public MyFragmentAdapter(Context context, FragmentManager fragmentManager, int tabCount){
        super(fragmentManager);

        _context = context;
        _tabCount = tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                return _eCommerceFragment;
            case 1:
                return _beritaFragment;
            case 2:
                return _kampusFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return _tabCount;
    }
}
