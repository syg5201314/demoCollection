package sunning.democollection.animation.sandbox;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.InjectView;
import sunning.democollection.R;


public class ThingRecyclerAdapter extends BaseRecyclerAdapter<Thing> {

    @Override
    public ThingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ThingHolder(view);
    }

    public class ThingHolder extends BaseRecyclerAdapter<Thing>.ViewHolder {
        @InjectView(R.id.title)
        TextView titleTextView;

        public ThingHolder(View itemView) {
            super(itemView);
        }

        public void populate(Thing item) {
            titleTextView.setText(item.text);
        }
    }
}
