package com.vanniktech.emoji.sample;

import android.support.v4.view.ViewPager;
import android.view.View;


public final class PageTransformer implements ViewPager.PageTransformer {
  private static final float MIN_SCALE = 0.9f;
  private static final float MIN_ALPHA = 0.1f;

  @Override public void transformPage(final View page, final float position) {
    if (position < -1) {  // [-Infinity,-1)
      // This page is way off-screen to the left.
      page.setAlpha(0);
    } else if (position <= 1) { // [-1,1]
      page.setScaleX(Math.max(MIN_SCALE, 1 - Math.abs(position)));
      page.setScaleY(Math.max(MIN_SCALE, 1 - Math.abs(position)));
      page.setAlpha(Math.max(MIN_ALPHA, 1 - Math.abs(position)));
    } else {  // (1,+Infinity]
      // This page is way off-screen to the right.
      page.setAlpha(0);
    }
  }
}
