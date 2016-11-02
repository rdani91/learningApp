package hu.rozsa.daniel.learningapplication.eighth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hu.rozsa.daniel.learningapplication.R;

public class TouchExampleFragment extends Fragment implements View.OnTouchListener, GestureDetector.OnGestureListener {
    private TextView tvLog;
    private GestureDetectorCompat gestureDetectorCompat;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_touch_example, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View rootView = view.findViewById(R.id.root);
        View upperView1 = view.findViewById(R.id.upperView1);
        View upperView2 = view.findViewById(R.id.upperView2);
        tvLog = (TextView) view.findViewById(R.id.tvLog);

        view.findViewById(R.id.btnClearText)
            .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvLog.setText("");
                }
            });


        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        concatLog("rootView Action Down");
                        break;

                    case MotionEvent.ACTION_UP:
                        concatLog("rootView Action Up");
                        break;
                }
                return false;
            }
        });

        view.setOnTouchListener(this);

        upperView1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        concatLog("upperView1 Action Down");
                        break;

                    case MotionEvent.ACTION_UP:
                        concatLog("upperView1 Action Up");
                        break;
                }
                return true;
            }
        });

        upperView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        concatLog("upperView2 Action Down");
                        break;

                    case MotionEvent.ACTION_UP:
                        concatLog("upperView2 Action Up");
                        break;
                }
                return false;
            }
        });


        gestureDetectorCompat = new GestureDetectorCompat(getContext(), this);

    }


    private void concatLog(String textToConcat) {
        tvLog.setText(tvLog.getText() + "\n" + textToConcat);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetectorCompat.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        concatLog("Gesture onDown");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        concatLog("Gesture onSingleTap");

        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        concatLog("Gesture onLongPress");

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        concatLog("Gesture onFling");

        return true;
    }
}
