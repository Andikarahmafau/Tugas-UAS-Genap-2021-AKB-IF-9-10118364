package com.example.tugas_akb_if9_10118364_andikarahmafauziah;

import android.view.ViewGroup;

public abstract class DrawerItem<T extends DrawerAdapter.ViewHolder>  {

    protected boolean isChecked;
    public abstract T createViewHolder(ViewGroup parent);

    public abstract void bindViewHolder(T Holder);

    public DrawerItem<T>setChecked(boolean isChecked){
        this.isChecked = isChecked;
        return this;

    }

    public boolean isChecked() {
        return isChecked;
    }

    public boolean isSelectable(){
        return true;
    }
}
