package hu.rozsa.daniel.learningapplication.seventh.notification;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hu.rozsa.daniel.learningapplication.R;

public class NotificationExampleFragment extends Fragment {


    private static final int FIRST_NOTI_ID = 12;
    private static final int BIG_NOTI_ID = 13;
    private static final int LONG_TEXT_NOTI_ID = 14;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        NotificationCompat.Builder builder = createFirstNotification();

        showNotification(builder, FIRST_NOTI_ID);
        showNotification(createLongTextNotification(), LONG_TEXT_NOTI_ID);
        showNotification(createBigPictureNotification(), BIG_NOTI_ID);
    }

    private NotificationCompat.Builder createLongTextNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext());
        builder.setSmallIcon(R.drawable.ic_menu_share)
               .setContentTitle("Long text title")
               .setStyle(new NotificationCompat.BigTextStyle().bigText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vel mauris ante. Quisque eros nibh, pellentesque a neque id, vestibulum facilisis lorem. Cras placerat mauris eu lacus finibus efficitur non at nunc. Fusce porttitor congue tincidunt. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam vitae bibendum tortor. Etiam vehicula libero leo, et facilisis nunc eleifend vel.\n" +
                                                                               "\n"))
               .setContentText("Long text text");
        return builder;
    }

    private NotificationCompat.Builder createBigPictureNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext());
        builder.setSmallIcon(R.drawable.ic_menu_share)
               .setContentTitle("Big picture title")
               .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.fake_ygritte)))
               .setContentText("Big picture text");
        return builder;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void showNotification(NotificationCompat.Builder builder, int ID) {
        NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(ID, builder.build());
    }

    @NonNull
    private NotificationCompat.Builder createFirstNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext());
        builder.setSmallIcon(R.drawable.ic_menu_gallery)
               .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.fake_ragnar))
               .setContentTitle("sample title")
               .setOngoing(true)
               .setContentText("sample text");
        return builder;
    }
}
