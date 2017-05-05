package com.cfxy.compusnewsapp.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
/**
 * 
 * @author Ralap
 * created at 2017/5/3 19:47
 */
public class HorizontalScrollViewPager extends ViewPager {

	int startX;
	int startY;

	public HorizontalScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public HorizontalScrollViewPager(Context context) {
		super(context);
	}


	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX = (int) ev.getX();
			startY = (int) ev.getY();
			// 请求父控件及祖宗控件不要拦截事件
			getParent().requestDisallowInterceptTouchEvent(true);
			break;
		case MotionEvent.ACTION_MOVE:
			int endX = (int) ev.getX();
			int endY = (int) ev.getY();

			int dx = endX - startX;
			int dy = endY - startY;

			if (Math.abs(dx) > Math.abs(dy)) {// 左右划
				if (dx > 0) {// 向右滑动
					if (this.getCurrentItem() == 0) {
						// 第一个页面
						// 请求父控件及祖宗控件拦截事件
						getParent().requestDisallowInterceptTouchEvent(false);
					}
				} else {
					// 向左滑动
					if (getCurrentItem() == this.getAdapter().getCount() - 1) {
						// 最后一个item
						// 请求父控件及祖宗控件拦截事件
						getParent().requestDisallowInterceptTouchEvent(false);
					}
				}

			} else {
				// 上下滑动
				// 请求父控件及祖宗控件拦截事件
				getParent().requestDisallowInterceptTouchEvent(false);
			}

			break;

		default:
			break;
		}

		return super.dispatchTouchEvent(ev);
	}

}
