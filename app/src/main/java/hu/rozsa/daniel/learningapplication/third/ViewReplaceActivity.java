package hu.rozsa.daniel.learningapplication.third;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import hu.rozsa.daniel.learningapplication.R;

public class ViewReplaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_replace);

        LinearLayout rootView = (LinearLayout) findViewById(R.id.customViewContainer);

        View dummyView = new DummyView(this); //saját tetszőleges kinézettel rendelkező View
        rootView.addView(dummyView);

        rootView.removeView(dummyView); //ezzel tudjuk leszedni a megadott view-t

        rootView.addView(dummyView);
       // rootView.addView(dummyView); //erre már elcrashelne, mert minden view-nak csak egy szülője lehet, és őt már hozzáadtuk egyszer
    }

    private class DummyView extends LinearLayout {
        Context context;

        public DummyView(Context context) {
            super(context);
            this.context = context;
            init();
        }

        public DummyView(Context context, AttributeSet attrs) {
            super(context, attrs);
            this.context = context;
            init();
        }

        public DummyView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            this.context = context;
            init();
        }

        private void init() {
//ezzel úgy mond saját magához hozzuk létre a vierw, olyan, mintha ő lenne az XML-ben a gyökér elem
//            inflate(context, R.layout.custom_view_dummy, this);  //kettő közül bármelyeiket használhatjuk
            LayoutInflater inflater = LayoutInflater.from(context);
            inflater.inflate(R.layout.custom_view_dummy, this, true); //a true fontos, ezzel mondjuk meg, hogy hozzáadja a parent-hez, aki jelenleg most ez a view, false esetén nem jelenik meg a view
        }

        @Override
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            Toast.makeText(context, "Attached!", Toast.LENGTH_SHORT)
                 .show();
        }

        @Override
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            Toast.makeText(context, "Detached!", Toast.LENGTH_SHORT)
                 .show();
        }

    }
}
