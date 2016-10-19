package hu.rozsa.daniel.learningapplication.third;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hu.rozsa.daniel.learningapplication.R;

public class ExampleFragment extends Fragment {

    public static ExampleFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ExampleFragment fragment = new ExampleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_example, container, false);
    }

    private void sendMessageToActivity(String msg){
        //csak onAttach után hívható

        if(isAdded()){ //ezzel lecsekkolom, hogy valóban látható, hozzá van adva a fragment
            ((FragmentExampleActivity)getActivity()).sendMessage(msg);

        }
    }
}
