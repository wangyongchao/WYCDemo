/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.weishop.test.design;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewTreeObserver;

import com.weishop.test.R;
import com.weishop.test.util.AppUtils;

import java.util.Random;

public class AppBarLayoutUsageBase extends AppCompatActivity {
    private final Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.design_appbar_toolbar_scroll_tabs_pinned_with_swiperefres);

        // Retrieve the Toolbar from our content view, and set it as the action bar
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout)
//                findViewById(R.id.collapsing_app_bar);
//        if (appBarLayout != null && displayTitle()) {
//            appBarLayout.setTitle(getTitle());
//        }
//
//        TextView dialog = (TextView) findViewById(R.id.textview_dialogue);
//        if (dialog != null) {
//            dialog.setText(TextUtils.concat(Shakespeare.DIALOGUE));
//        }
        View content = findViewById(R.id.app_bar);
        View text1 = findViewById(R.id.text1);
        content.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.d(AppUtils.COMMON_TAG, "height=" + text1.getHeight());
                content.setMinimumHeight(text1.getHeight());
                content.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        AppBarLayout appBarLayout=findViewById(R.id.app_bar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.appbar_recyclerview);
        if (recyclerView != null) {
            setupRecyclerView(recyclerView);
        }

//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
//        if (tabLayout != null) {
//            setupTabs(tabLayout);
//        }

        final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        if (refreshLayout != null) {
            refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {


                @Override
                public void onRefresh() {
                    // Post a delayed runnable to reset the refreshing state in 2 seconds
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            refreshLayout.setRefreshing(false);
                        }
                    }, 2000);
                }
            });
        }

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.scrollToPosition(15);
                appBarLayout.setExpanded(false);

            }
        }, 1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    private void addRandomTab(TabLayout tabLayout) {
        Random r = new Random();
        String cheese = Cheeses.sCheeseStrings[r.nextInt(Cheeses.sCheeseStrings.length)];
        tabLayout.addTab(tabLayout.newTab().setText(cheese));
    }

    private void setupTabs(TabLayout tabLayout) {
        for (int i = 0; i < 10; i++) {
            addRandomTab(tabLayout);
        }
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new SimpleStringRecyclerViewAdapter(this, Cheeses.sCheeseStrings));
    }

}
