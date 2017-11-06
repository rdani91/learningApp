package hu.rozsa.daniel.learningapplication.libs.firebase;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.greenrobot.eventbus.EventBus;

import hu.rozsa.daniel.learningapplication.libs.eventbus.SampleEvent;

public class MyFireBaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String messageId = remoteMessage.getMessageId();
        EventBus.getDefault().post(new SampleEvent(messageId));
    }
}
