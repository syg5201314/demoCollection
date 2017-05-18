package sunning.democollection.demo.elemedetail;/*
 * Created by sunning on 2017/1/4.
 */

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import sunning.democollection.BaseActivity;
import sunning.democollection.R;
import sunning.democollection.demo.MarginConfig;

public class ElemeDetailContentActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private ViewPager mViewPager;
    private ElemeHeaderView mHeaderView;
    private boolean isFirst;
    private RelativeLayout mBottomView;
    public static int bottomY;

    private ArrayList<View> views;
    private int[] imgs = {R.mipmap.pizza, R.mipmap.pic22222, R.mipmap.pic33333};


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.eleme_content_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mHeaderView = (ElemeHeaderView) findViewById(R.id.zoomHeader);
        mViewPager.setAdapter(new Adapter());
        mViewPager.setOffscreenPageLimit(4);
        CtrlLinearLayoutManager layoutManager = new CtrlLinearLayoutManager(this);

        //未展开禁止滑动
        layoutManager.setScrollEnabled(false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new ListAdapter());
        mRecyclerView.setAlpha(0);
        mBottomView = (RelativeLayout) findViewById(R.id.rv_bottom);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int size = mViewPager.getChildCount();
        if (isFirst) {
            for (int i = 0; i < size; i++) {
                View v = mViewPager.getChildAt(i).findViewById(R.id.ll_bottom);
                v.setY(mViewPager.getChildAt(i).findViewById(R.id.imageView).getHeight());
                v.setX(MarginConfig.MARGIN_LEFT_RIGHT);
                mHeaderView.setY(mHeaderView.getY() - 1);
                isFirst = false;
            }
        }
        bottomY = (int) mBottomView.getY();
        mBottomView.setTranslationY(mBottomView.getY() + mBottomView.getHeight());
        mHeaderView.setBottomView(mBottomView, bottomY);
    }

    class Adapter extends PagerAdapter {

        @Override public Object instantiateItem(ViewGroup container, int position) {
            views.get(position).findViewById(R.id.imageView).setBackgroundResource(imgs[position]);
            container.addView(views.get(position));
            return views.get(position);
        }

        public Adapter() {
            views = new ArrayList<>();
            views.add(View.inflate(ElemeDetailContentActivity.this, R.layout.item_img, null));
            views.get(0).findViewById(R.id.btn_buy).setOnClickListener(v -> Toast.makeText(ElemeDetailContentActivity.this, "buy", Toast.LENGTH_SHORT).show());
            views.add(View.inflate(ElemeDetailContentActivity.this, R.layout.item_img, null));
            views.add(View.inflate(ElemeDetailContentActivity.this, R.layout.item_img, null));
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    @Override
    public void onBackPressed() {
        if (mHeaderView.isExpand()) {
            mHeaderView.restore(mHeaderView.getY());
        } else {
            finish();
        }
    }
}
