package sunning.democollection.custom.recyclercard;
/*
 * Created by sunning on 2017/4/27.
 */

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

import sunning.democollection.BaseActivity;
import sunning.democollection.R;

import static sunning.democollection.custom.recyclercard.SwipeCardBean.initDatas;

public class RecyclerCardActivity extends BaseActivity {

    RecyclerView mRv;
    RecyclerCardAdapter mAdapter;
    List<SwipeCardBean> mDatas;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.recycler_card_layout);
        mRv = (RecyclerView) findViewById(R.id.rv);
        mDatas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mDatas.addAll(initDatas());
        }

        mRv.setLayoutManager(new OverLayCardLayoutManager());
//        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mAdapter = new RecyclerCardAdapter(this, mDatas));
        CardConfig.initConfig(this);
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallBack(mRv, mAdapter, mDatas);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRv);
    }
}
