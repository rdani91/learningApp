package hu.rozsa.daniel.learningapplication.fourth;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import hu.rozsa.daniel.learningapplication.R;

public class ExampleRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private final List<DummyEntity> fakeItems;

    public ExampleRecycleViewAdapter(List<DummyEntity> fakeItems) {
        this.fakeItems = fakeItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        switch (viewType) {
            case 0:
                itemView = inflater.inflate(R.layout.list_item_example_type1, parent, false);
                return new MyHolderType1(itemView);
            case 1:
                itemView = inflater.inflate(R.layout.list_item_example_type2, parent, false);
                return new MyHolderType2(itemView);
        }
        throw new IllegalStateException("This state can not be happen");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case 0:
                ((MyHolderType1) holder).tvDummy.setText("position:" + position);
                break;

            case 1:
                int offset = position % 6;
                int resId;
                switch (offset) {
                    case 0:
                        resId = R.drawable.fake_bean;
                        break;
                    case 1:
                        resId = R.drawable.fake_jon;
                        break;
                    case 2:
                        resId = R.drawable.fake_kim;
                        break;
                    case 3:
                        resId = R.drawable.fake_lagertha;
                        break;
                    case 4:
                        resId = R.drawable.fake_ragnar;
                        break;
                    case 5:
                        resId = R.drawable.fake_wut;
                        break;
                    case 6:
                        resId = R.drawable.fake_ygritte;
                        break;
                    default:
                        resId = R.drawable.fake_jon;
                }
                ((MyHolderType2) holder).imgDummy.setBackground(ContextCompat.getDrawable(context, resId));
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    @Override
    public int getItemCount() {
        return fakeItems.size();
    }

    public class MyHolderType1 extends RecyclerView.ViewHolder {
        public TextView tvDummy;

        public MyHolderType1(View itemView) {
            super(itemView);
            tvDummy = (TextView) itemView.findViewById(R.id.tvText);

        }
    }

    public class MyHolderType2 extends RecyclerView.ViewHolder {
        public ImageView imgDummy;

        public MyHolderType2(View itemView) {
            super(itemView);
            imgDummy = (ImageView) itemView.findViewById(R.id.imgSample);
        }
    }
}
