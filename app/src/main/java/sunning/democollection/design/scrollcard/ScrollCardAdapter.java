package sunning.democollection.design.scrollcard;
/*
 * Created by sunning on 2017/4/25.
 */

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sunning.democollection.R;

class ScrollCardAdapter extends RecyclerView.Adapter<ScrollCardAdapter.ScrollCardViewHolder> {

    private Context context;
    private List<String> mItem;

    ScrollCardAdapter(Context context, List<String> item) {
        this.context = context;
        this.mItem = item;
    }

    @Override
    public ScrollCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.widget_card, parent, false);
        return new ScrollCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScrollCardViewHolder holder, int position) {
        holder.itemText.setText(mItem.get(position));
        holder.itemText.setTextColor(ContextCompat.getColor(context, R.color.black));
    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }

    static class ScrollCardViewHolder extends RecyclerView.ViewHolder {

        TextView itemText;

        ScrollCardViewHolder(View itemView) {
            super(itemView);
            itemText = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
