package ru.zaochno.zaochno.drawer;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.widget.ImageView;

/**
 * Created by notbl on 6/11/2017.
 */

public class DrawerListViewItem {
    private Drawable imageView;
    private String title;

    public DrawerListViewItem(Drawable imageView, String title) {
        this.imageView = imageView;
        this.title = title;
    }

    public Drawable getImageView() {
        return imageView;
    }

    public void setImageView(Drawable imageView) {
        this.imageView = imageView;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
