package hu.rozsa.daniel.learningapplication.sixth;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import hu.rozsa.daniel.learningapplication.R;

public class ContentProviderFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private List<String> items = new ArrayList<>();
    private ArrayAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content_provider, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        view.findViewById(R.id.btnAddSampleToCp)
            .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createAlertDialogForAddSampleItem();
                }
            });

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);

                getDataFromDB();
//                getDataFromCP();
            }
        });

        ListView lvSample = (ListView) view.findViewById(R.id.lvSampleCP);
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, items);
        lvSample.setAdapter(adapter);
    }

    private void getDataFromDB() {
        items.clear();

        MyTestDbWrapper myTestDbWrapper = MyTestDbWrapper.getInstance();
        myTestDbWrapper.initContext(getActivity());
        List<String> namesWithAge = myTestDbWrapper.getNamesWithAgeAbove25();
        items.addAll(namesWithAge);
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    private void getDataFromCP() {
        items.clear();
        Cursor c = getActivity().getContentResolver()
                                .query(
                                        ContentProviderExample.CONTENT_URI,
                                        new String[]{"name", "age"},
                                        null,
                                        null,
                                        null
                                );

        if (c != null) {
            while (c.moveToNext()) {
                String concatString = c.getString(c.getColumnIndex(MyDBHandler.NAME)) + " - "
                        + c.getString(c.getColumnIndex(MyDBHandler.AGE));
                items.add(concatString);
            }
        }

        adapter.notifyDataSetChanged();

        swipeRefreshLayout.setRefreshing(false);

    }

    private void createAlertDialogForAddSampleItem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View dialogView = View.inflate(getActivity(), R.layout.dialog_cp_add_item, null);
        final EditText etName = (EditText) dialogView.findViewById(R.id.etName);
        final EditText etAge = (EditText) dialogView.findViewById(R.id.etAge);

        builder.setView(dialogView)
               .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       String name = etName.getText()
                                           .toString();
                       String age = etAge.getText()
                                         .toString();

                       putIntoCP(name, age);
                   }
               })
               .setNegativeButton("nein", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                   }
               });
        builder.create()
               .show();

    }

    private void putIntoCP(String name, String age) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDBHandler.NAME, name);
        contentValues.put(MyDBHandler.AGE, age);

        getActivity().getContentResolver()
                     .insert(ContentProviderExample.CONTENT_URI, contentValues);
    }
}
