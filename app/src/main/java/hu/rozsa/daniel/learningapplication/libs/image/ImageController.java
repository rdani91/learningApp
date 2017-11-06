package hu.rozsa.daniel.learningapplication.libs.image;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

public class ImageController {

    public void loadImageGlide(ImageView view, String url) {
        RequestOptions requestOptions = new RequestOptions().centerCrop().override(200, 200);
        Glide.with(view).load(url).apply(requestOptions).into(view);
    }

    public void loadImagePicasso(ImageView view, String url){
        Picasso.with(view.getContext()).load(url).resize(40,40).centerCrop().into(view);
    }
}
