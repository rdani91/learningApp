package hu.rozsa.daniel.learningapplication.fourth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import hu.rozsa.daniel.learningapplication.R;

public class ListViewFragment extends Fragment {


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView listView = (ListView) view.findViewById(R.id.lvExample);
        List<DummyEntity> fakeItems = new ArrayList<>();
        ExampleAdapter adapter = new ExampleAdapter(fakeItems);
        listView.setAdapter(adapter);
        for (int i = 0; i < 111; i++) {
            fakeItems.add(new DummyEntity());
        }

        adapter.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listview_example, container, false);
    }
}
