package hu.rozsa.daniel.learningapplication.architect.clean;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hu.rozsa.daniel.learningapplication.R;
import hu.rozsa.daniel.learningapplication.architect.mvp.auth.AuthenticationContract;
import hu.rozsa.daniel.learningapplication.architect.mvp.auth.DefaultAuthPresenter;
import hu.rozsa.daniel.learningapplication.architect.mvp.auth.RxAuthPresenter;
import hu.rozsa.daniel.learningapplication.architect.mvp.model.Injector;
import hu.rozsa.daniel.learningapplication.architect.mvp.model.entity.UserDetail;
import hu.rozsa.daniel.learningapplication.sixth.network.JsonSampleEntity;
import rdani.hu.logic.OnCompleteResult;
import rdani.hu.logic.entity.User;

public class UserListFragment extends Fragment {

    @BindView(R.id.progress) ProgressBar progressBar;
    @BindView(R.id.listUser) ListView    listUsers;

    private Unbinder    bind;
    private UserAdapter adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bind = ButterKnife.bind(this, view);

        adapter = new UserAdapter();
        listUsers.setAdapter(adapter);

        listUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Logic.getInstance().getUserInteractor().getUserDetail(adapter.getItem(pos));
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_users, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();

        Logic.getInstance().getUserInteractor().getUsers(new OnCompleteResult<List<User>>() {
            @Override
            public void onSuccess(List<User> result) {
                adapter.users.clear();
                adapter.users.addAll(result);
                adapter.notifyDataSetChanged();            }

            @Override
            public void onError(String error) {

            }
        });
    }


    private class UserAdapter extends BaseAdapter {
        private List<User> users = new ArrayList<>();

        @Override
        public int getCount() {
            return users.size();
        }

        @Override
        public User getItem(int pos) {
            return users.get(pos);
        }

        @Override
        public long getItemId(int pos) {
            return pos;
        }

        @Override
        public View getView(int pos, View convertView, ViewGroup viewGroup) {

            ViewHolder holder;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                convertView = inflater.inflate(R.layout.list_item_example_type1, viewGroup, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            User currentItem = getItem(pos);

            holder.tvName.setText(currentItem.name + " - " + currentItem.age);
            return convertView;
        }

        private class ViewHolder {
            TextView tvName;

            public ViewHolder(View convertView) {
                this.tvName = convertView.findViewById(R.id.tvText);
            }
        }
    }
}
