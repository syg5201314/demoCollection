package sunning.democollection.custom.recyclercard;
/*
 * Created by sunning on 2017/4/27.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import sunning.democollection.R;

class RecyclerCardAdapter extends RecyclerView.Adapter<RecyclerCardAdapter.CardViewHolder> {

    private Context mContext;

    private List<SwipeCardBean> mData;

    public RecyclerCardAdapter(Context mContext, List<SwipeCardBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CardViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recycler_card_item, parent, false));
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        SwipeCardBean swipeCardBean = mData.get(position);
        Glide.with(mContext).load(swipeCardBean.getUrl()).crossFade().into(holder.iv);
        holder.tv.setText(swipeCardBean.getName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {

        ImageView iv;
        TextView tv;


        CardViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tvName);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}
