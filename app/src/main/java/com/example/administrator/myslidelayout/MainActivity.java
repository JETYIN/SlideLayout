package com.example.administrator.myslidelayout;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.Window;

import com.nineoldandroids.view.ViewHelper;

public class MainActivity extends FragmentActivity {


    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        initEvents();

    }

    /**
     * 在主页上设置按钮 click触发的出现右边
     **/
    public void OpenRightMenu(View view) {
        mDrawerLayout.openDrawer(Gravity.RIGHT);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,
                Gravity.RIGHT);
    }


    private void initEvents() {
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
                                            @Override
                                            public void onDrawerStateChanged(int newState) {
                                            }

                                            @Override
                                            public void onDrawerSlide(View drawerView, float slideOffset) {
                                                View mContent = mDrawerLayout.getChildAt(0);
                                                View mMenu = drawerView;
                                                boolean flag = false;
                                                float scale = 1 - slideOffset;
                                                float leftScale, rightScale;
/**设置flag为false在设置平移的不在x，y方向上伸缩**/
                                                if (flag) {
                                                    rightScale = 0.8f + scale * 0.2f;
                                                    leftScale = 1 - 0.3f * scale;
                                                } else {
                                                    rightScale = 1.0f;
                                                    leftScale = 1.0f;
                                                }
                                                if (drawerView.getTag().equals("LEFT")) {
                                                    ViewHelper.setScaleX(mMenu, leftScale);
                                                    ViewHelper.setScaleY(mMenu, leftScale);
                                                    ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
                                                    ViewHelper.setTranslationX(mContent,
                                                            mMenu.getMeasuredWidth() * (1 - scale));
                                                    ViewHelper.setPivotX(mContent, 0);
                                                    ViewHelper.setPivotY(mContent,
                                                            mContent.getMeasuredHeight() / 2);
                                                    mContent.invalidate();
                                                    ViewHelper.setScaleX(mContent, rightScale);
                                                    ViewHelper.setScaleY(mContent, rightScale);
                                                }
                                                /**为右边设置的滑动**/
                                                else {
                                                    ViewHelper.setTranslationX(mContent,
                                                            -mMenu.getMeasuredWidth() * slideOffset);
                                                    ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
                                                    ViewHelper.setPivotY(mContent,
                                                            mContent.getMeasuredHeight() / 2);
                                                    mContent.invalidate();
                                                    ViewHelper.setScaleX(mContent, rightScale);
                                                    ViewHelper.setScaleY(mContent, rightScale);
                                                }

                                            }

                                            @Override
                                            public void onDrawerOpened(View drawerView) {

                                            }

                                            @Override
                                            public void onDrawerClosed(View drawerView) {
                                                /**为防止出现多个页面可以手动设置页面是否隐藏**/
                                               /* mDrawerLayout.setDrawerLockMode(
                                                        DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.LEFT);*/
                                            }
                                        }

        );
    }

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerLayout);
        /**本demo设置可以两边滑动，此处设置lock，设置后不可以滑到左边，只能通过代码打开左边**/
       /* mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
                Gravity.LEFT);*/
    }
}
