package hu.rozsa.daniel.learningapplication.third;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import hu.rozsa.daniel.learningapplication.R;

public class EventHandling extends Fragment{

    private Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = container.getContext();
        return inflater.inflate(R.layout.fragment_event_handling, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        View tvEvent =view.findViewById(R.id.tvEvent);
        tvEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Event handling from code", Toast.LENGTH_SHORT)
                     .show();
            }
        });

        tvEvent.setOnTouchListener(new View.OnTouchListener() { //ezt már XML-ből nem tudjuk megcsinálni
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        Toast.makeText(context, "Action Down happened", Toast.LENGTH_SHORT)
                             .show();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        System.out.println("Ebből túl sok hívódna, ezért nem rakok ide toastot");
                        break;

                    case MotionEvent.ACTION_UP:
                        Toast.makeText(context, "Action Up happened", Toast.LENGTH_SHORT)
                             .show();
                        break;

                }

                return false; //ha true, akkor "elnyeli" az eseményt, és nem adja tovább az alatta lévő viewknak.
            }
        });
    }
}
