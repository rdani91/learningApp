package hu.rozsa.daniel.learningapplication.fourth;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hu.rozsa.daniel.learningapplication.R;

public class ExampleAdapter extends BaseAdapter {
    private final List<DummyEntity> items;

    public ExampleAdapter(List<DummyEntity> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public DummyEntity getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int viewType = getItemViewType(position);
        Context context = parent.getContext();
        switch (viewType) {
            case 0:

                ViewHolderType1 holderType1;

                if (convertView == null) {
                    holderType1 = new ViewHolderType1();
                    convertView = LayoutInflater.from(context)
                                                .inflate(R.layout.list_item_example_type1, parent, false);
                    holderType1.tvDummy = (TextView) convertView.findViewById(R.id.tvText);

                    convertView.setTag(holderType1);

                } else {
                    holderType1 = (ViewHolderType1) convertView.getTag();
                }

                holderType1.tvDummy.setText("sample text : " + String.valueOf(position));
                return convertView;
            case 1:

                ViewHolderType2 holderType2;
                if (convertView == null) {
                    holderType2 = new ViewHolderType2();
                    convertView = LayoutInflater.from(context)
                                                .inflate(R.layout.list_item_example_type2, parent, false);
                    holderType2.imgDummy = (ImageView) convertView.findViewById(R.id.imgSample);
                    convertView.setTag(holderType2);
                } else{
                    holderType2 = (ViewHolderType2) convertView.getTag();
                }

                int offset = position % 6;
                int resId;
                switch (offset){
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

                holderType2.imgDummy.setImageDrawable(ContextCompat.getDrawable(context, resId));

                return convertView;


        }
        throw new IllegalStateException("Ilyen state nem fordulhat elő, mert csak páros és páratlan számok léteznek :D");
    }

    private class ViewHolderType1 {
        TextView tvDummy;
    }

    private class ViewHolderType2 {
        ImageView imgDummy;
    }
}
