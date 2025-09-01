package com.view;

import androidx.viewpager.widget.ViewPager;

public interface PageIndicator extends ViewPager.OnPageChangeListener {
  void setViewPager(ViewPager paramViewPager);
  
  void setViewPager(ViewPager paramViewPager, int paramInt);
  
  void setCurrentItem(int paramInt);
  
  void setOnPageChangeListener(ViewPager.OnPageChangeListener paramOnPageChangeListener);
  
  void notifyDataSetChanged();
}


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/view/PageIndicator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */