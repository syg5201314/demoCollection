package sunning.democollection.design.scrollcard;
/*
 * Created by sunning on 2017/4/25.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sunning.democollection.R;

@CoordinatorLayout.DefaultBehavior(ScrollCardBehavior.class)
public class SlidingCardView extends FrameLayout {

    private int mHeadHeight;
    private TextView headView;
    private List<String> listItem;

    public SlidingCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.scroll_card_item, this);
        headView = (TextView) findViewById(R.id.scroll_card_item_head);
        initListData();
        initRecyclerView((RecyclerView) findViewById(R.id.scroll_card_item_list));
    }

    private void initListData() {
        if (listItem == null) {
            listItem = new ArrayList<>();
        }
        for (int i = 0; i < 20; i++) {
            listItem.add("叫我第" + i + "名");
        }
    }

    private void initRecyclerView(RecyclerView recyclerView) {
        if (recyclerView != null) {
            ScrollCardAdapter scrollCardAdapter = new ScrollCardAdapter(getContext(), listItem);
            recyclerView.setAdapter(scrollCardAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            scrollCardAdapter.notifyDataSetChanged();
            recyclerView.addItemDecoration(new ScrollCardItemDecoration());
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw && h != oldh) {
            mHeadHeight = headView.getMeasuredHeight();
        }
    }

    public int getHeadHeight() {
        return mHeadHeight;
    }
}
