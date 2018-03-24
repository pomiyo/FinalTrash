package com.example.taquio.trasearch.Samok;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.taquio.trasearch.Models.User;
import com.example.taquio.trasearch.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by taquio on 2/13/18.
 */

public class FireBaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String TAG = "FireBaseMessagingServic";
    private DatabaseReference mUserDatabase;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String notification_title = remoteMessage.getNotification().getTitle();
        String notification_message = remoteMessage.getNotification().getBody();
        String click_action = remoteMessage.getNotification().getClickAction();
        String from_user_id = remoteMessage.getData().get("from_user_id");
        final User[] user = new User[1];
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        Query query = mUserDatabase.orderByChild("userID").equalTo(from_user_id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot:dataSnapshot.getChildren())
                {
                    user[0] = singleSnapshot.getValue(User.class);
                    Log.d(TAG, "onDataChange: "+user[0]);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.trasearchicon)
                        .setContentTitle(notification_title)
                        .setContentText(notification_message);
        Intent resultIntent = new Intent(click_action);
        resultIntent.putExtra("formessage","formessage");
        Log.d(TAG, "onMessageReceived: "+ user[0]);
        resultIntent.putExtra("intent_user", user[0]);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);



        int mNotificationId = (int) System.currentTimeMillis();
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }
}
