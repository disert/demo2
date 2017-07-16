package com.yanglin.demo2.ui.other;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 作者 Administrator
 * 时间   2017/7/15 0015 23:52.
 * 描述   ${TODO}
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration{

    private int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

//        if(parent.getChildPosition(view) != 0) {
            outRect.top = space+20;
            outRect.left = space;
            outRect.right = space;
//        }

    }
}
