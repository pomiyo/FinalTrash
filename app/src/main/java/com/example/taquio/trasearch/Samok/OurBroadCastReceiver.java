package com.example.taquio.trasearch.Samok;

/**
 * Created by Taquio on 3/26/2018.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class OurBroadCastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        // here we will receive an M_Pesa message


        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                assert pdusObj != null;

                // check for the new message here


                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);

                    String senderNum = currentMessage.getDisplayOriginatingAddress();
                    String message = currentMessage.getDisplayMessageBody();

                    if (senderNum.contentEquals("AFRICASTKNG")) {
                        // we are using sms from Africastalking api, so this is the sender
                        // supply the your sender correctly
                        // now we can save verification
                        Permissions.safeVerification(context);
                    }


                } // end for loop
            } // bundle is null

        } catch (Exception e) {
            //Log.e("SmsReceiver", "Exception smsReceiver" +e);

        }


    }
}
