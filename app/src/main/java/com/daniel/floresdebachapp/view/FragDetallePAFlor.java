package com.daniel.floresdebachapp.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class FragDetallePAFlor
        extends FragmentStatePagerAdapter {

    private List<FragmentDetalle> listaFlores;

    public FragDetallePAFlor(FragmentManager fm, List<FragmentDetalle> flores) {
        super(fm);
        this.listaFlores = flores;
    }

    @Override
    public Fragment getItem(int position) {
        return listaFlores.get(position);
    }

    @Override
    public int getCount() {
        return listaFlores.size();
    }
}
