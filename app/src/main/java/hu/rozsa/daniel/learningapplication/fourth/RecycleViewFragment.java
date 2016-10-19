package hu.rozsa.daniel.learningapplication.fourth;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import hu.rozsa.daniel.learningapplication.R;

public class RecycleViewFragment extends Fragment {
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.context = container.getContext();
        return inflater.inflate(R.layout.fragment_recycle_view_example, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        List<DummyEntity> fakeItems = new ArrayList<>();

        ExampleRecycleViewAdapter adapter = new ExampleRecycleViewAdapter(fakeItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        for (int i = 0; i < 111; i++) {
            fakeItems.add(new DummyEntity());
        }
        adapter.notifyDataSetChanged();
    }


}
