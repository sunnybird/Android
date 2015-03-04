package com.example.jinlong.dailyartical.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.jinlong.dailyartical.R;
import com.example.jinlong.dailyartical.adapter.MyFragmentAdapter;
import com.example.jinlong.dailyartical.bean.Comment;
import com.example.jinlong.dailyartical.config.Config;
import com.example.jinlong.dailyartical.fragments.ArticalFragment;
import com.example.jinlong.dailyartical.fragments.CommentFragment;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 文章显示界面
 */
public class ArticalActivity extends FragmentActivity {

    private FragmentPagerAdapter fragmentPagerAdapter;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artical);

        Bmob.initialize(this, Config.bmobAppKey);

        fragments = new ArrayList<Fragment>();

        fragments.add(new ArticalFragment());
        fragments.add(new CommentFragment());

        fragmentPagerAdapter = new MyFragmentAdapter(getSupportFragmentManager(),fragments);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(mFragmentPagerAdapter);

    }

    /**
     * 返回键事件监听,双击退出应用
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {

                this.finish();

            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 点赞事件监听
     *
     * @param view
     */
    public void startAdd(View view) {

        Bundle bundle = (Bundle) view.getTag();
        final String objectid = bundle.getString("obidetid");
        int numberStart = bundle.getInt("number");
		
        Comment comment = new Comment();
        comment.setFavour(numberStart + 1);
		
        comment.update(this, objectid, new UpdateListener() {
            @Override
            public void onSuccess() {
                ((CommentFragment) fragments.get(1)).updateComment(objectid);
            }
            @Override
            public void onFailure(int code, String msg) {
                Log.i("err", "更新失败：" + msg);
            }
        });

    }

}
