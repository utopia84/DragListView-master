package com.utopia.activity.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * 重写LinearLayout方法，实现ListView Item的滑动菜单
 * @author utopia84
 * @version v1.0
 * @Time 2016/10/14
 */
public class DragItemView extends LinearLayout {

	public static final int STATE_CLOSE = 0;
	public static final int STATE_OPEN = 1;
	private View mContentView;
	public View mMenuView;
	public int mDownX;
	public int state = STATE_CLOSE;
	public boolean isFling;
	private int mBaseX;
	private Scroller scroll;

	/**
	 * 常用构造方法
	 * @param contentView  ListView的Item布局
	 * @param menuView		需要填充的右侧滑动菜单布局
     */
	public DragItemView(View contentView, View menuView) {
		super(contentView.getContext());
		scroll=new Scroller(getContext());
		mContentView = contentView;
		mMenuView = menuView;
		init();
	}

	private DragItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private DragItemView(Context context) {
		super(context);
	}


	/**
	 *合成内容与菜单
	 */
	private void init() {
		setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		LayoutParams contentParams = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		mContentView.setLayoutParams(contentParams);
	
		mMenuView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));

		addView(mContentView);
		addView(mMenuView);

	}


	public void swipe(int dis) {
		if (dis > mMenuView.getWidth()) {
			dis = mMenuView.getWidth();
		}
		if (dis < 0) {
			dis = 0;
		}
		mContentView.layout(-dis, mContentView.getTop(),
				mContentView.getWidth() - dis, getMeasuredHeight());
		mMenuView.layout(mContentView.getWidth() - dis, mMenuView.getTop(),
				mContentView.getWidth() + mMenuView.getWidth() - dis,
				mMenuView.getBottom());
	}

	@Override
	public void computeScroll() {
		if (state == STATE_OPEN) {
			if (scroll.computeScrollOffset()) {
				swipe(scroll.getCurrX());
				postInvalidate();
			}
		} else {
			if (scroll.computeScrollOffset()) {
				swipe(mBaseX - scroll.getCurrX());
				postInvalidate();
			}
		}
	}

	/**
	 * 关闭侧滑方法
	 */
	public void smoothCloseMenu() {
		state = STATE_CLOSE;
		mBaseX = -mContentView.getLeft();
		scroll.startScroll(0, 0, mBaseX, 0, 350);
		postInvalidate();
	}

	/**
	 * 打开侧滑方法
	 */
	public void smoothOpenMenu() {

		state = STATE_OPEN;
		scroll.startScroll(-mContentView.getLeft(), 0,
				mMenuView.getWidth()/2, 0, 350);
		postInvalidate();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		mMenuView.measure(MeasureSpec.makeMeasureSpec(0,
				MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(
				getMeasuredHeight(), MeasureSpec.EXACTLY));
		mContentView.measure(MeasureSpec.makeMeasureSpec(0,
				MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(
				getMeasuredHeight(), MeasureSpec.EXACTLY));
		
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		mContentView.layout(0, 0, getMeasuredWidth(),
				mContentView.getMeasuredHeight());
		mMenuView.layout(getMeasuredWidth(), 0,
				getMeasuredWidth() + mMenuView.getMeasuredWidth(),
				mContentView.getMeasuredHeight());

	}

}
