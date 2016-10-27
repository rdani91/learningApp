package hu.rozsa.daniel.learningapplication.seventh.customtag;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import hu.rozsa.daniel.learningapplication.R;

public class MyCustomView extends LinearLayout {
    public MyCustomView(Context context) {
        super(context);
        init(null);
    }

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MyCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }


    private void init(AttributeSet attrs) {
        Context context = getContext();
        inflate(context, R.layout.my_view, this);

        String customString;
        if (attrs != null) {
            TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.MyCustomView);
            customString = t.getString(R.styleable.MyCustomView_customText);

            t.recycle();
        } else {
            customString = "not set";
        }

        TextView tvCustomAttr = (TextView) findViewById(R.id.tvCustomAttr);
        tvCustomAttr.setText(customString);
    }
}
