package com.docwei.navbtndemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigtionButtonBar.TabSelectLinster {

    private NavigtionButtonBar mNavBtn;
    private NavigtionButtonBar mNav_onlytext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }

    private void initData() {
        mNav_onlytext = (NavigtionButtonBar) findViewById(R.id.nav_onlytext);
        mNav_onlytext.addItem(new NavigtionButtonItem(0,"100元").setActiveColor(R.color.colorAccent))
                .addItem(new NavigtionButtonItem(0,"200元").setActiveColor(R.color.colorAccent))
                .addItem(new NavigtionButtonItem(0,"500元").setActiveColor(R.color.colorAccent))
                .setTab(0);
        mNav_onlytext.setTabSelectLinster(this);

        mNavBtn = (NavigtionButtonBar) findViewById(R.id.nav);
         mNavBtn.addItem(new NavigtionButtonItem(R.mipmap.ic_home_white_24dp,"福利").setActiveColor(R.color.colorAcc))
                .addItem(new NavigtionButtonItem(R.mipmap.ic_favorite_white_24dp,"IOS").setActiveColor(R.color.orange))
                .addItem(new NavigtionButtonItem(R.mipmap.ic_book_white_24dp, "Android").setActiveColor(R.color.teal))
                .addItem(new NavigtionButtonItem(R.mipmap.ic_music_note_white_24dp, " 前端").setActiveColor(R.color.grey))
                .addItem(new NavigtionButtonItem(R.mipmap.ic_tv_white_24dp, "瞎推荐").setActiveColor(R.color.brown))
                .setTab(-1);
        mNavBtn.setTabSelectLinster(this);
    }

    @Override
    public void tabSelected(int position) {
        Toast.makeText(this,"postion--->"+position,Toast.LENGTH_SHORT).show();
    }
}
