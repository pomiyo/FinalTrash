package com.example.taquio.trasearch.Samok;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.taquio.trasearch.R;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by taquio on 2/16/18.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder>{

    private static final String TAG = "ChatAdapter";
    private List<Chats> mMessageList;
    private DatabaseReference mUserDatabase;
    private FirebaseAuth mAuth;
    private LinearLayout.LayoutParams layoutParams;

    public ChatAdapter(List<Chats> mMessageList) {

        this.mMessageList = mMessageList;

    }

    public static String getDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.ENGLISH);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_single_layout ,parent, false);

        return new ChatViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ChatViewHolder holder, int position) {
        Chats c = mMessageList.get(position);

        String from_user = c.getFrom();
        String message_type = c.getType();
        mAuth = FirebaseAuth.getInstance();


        String current_user_id = mAuth.getCurrentUser().getUid();
        Log.d(TAG, "onBindViewHolder: Current ID: "+current_user_id);

        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(from_user);
        layoutParams = (LinearLayout.LayoutParams) holder.messageText.getLayoutParams();

        if(from_user.equals(current_user_id))
        {
            holder.messageText.setBackgroundColor(Color.WHITE);
            holder.messageText.setTextColor(Color.BLACK);
            holder.messageImage.setVisibility(View.GONE);
            holder.messageImage.setEnabled(false);
        }else {
            holder.messageText.setBackgroundColor(Color.BLUE);
        }
        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onBindViewHolder: "+dataSnapshot.child("Image_thumb").getValue().toString());

                String image = dataSnapshot.child("Image_thumb").getValue().toString();
                Picasso.with(holder.profileImage.getContext()).load(image)
                        .placeholder(R.drawable.man).into(holder.profileImage);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        if(message_type.equals("text")) {
            holder.messageImage.setVisibility(View.INVISIBLE);
            holder.messageImage.setEnabled(false);
            holder.messageImage.setImageResource(0);
            holder.messageText.setText(c.getMessage());
            holder.chatSingleTime.setText(getDate(c.getTime(),"MMM dd, yyyy E hh:mm aa"));
            holder.messageText.setVisibility(View.VISIBLE);
        } else if (message_type.equals("image")) {
            holder.messageImage.setEnabled(true);
            holder.messageText.setVisibility(View.INVISIBLE);
            holder.messageImage.setVisibility(View.VISIBLE);
            Picasso.with(holder.profileImage.getContext()).load(c.getMessage())
                    .placeholder(R.drawable.man).into(holder.messageImage);
            holder.chatSingleTime.setText(getDate(c.getTime(),"MMM dd, yyyy E hh:mm aa"));

        }


    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder
    {
        public TextView messageText,chatSingleTime;
        public CircleImageView profileImage;
        public ImageView messageImage;

        public ChatViewHolder(View view) {
            super(view);

            messageText = view.findViewById(R.id.chatSingleText);
            profileImage = view.findViewById(R.id.chatSingleImage);
            messageImage = view.findViewById(R.id.chatSingleConvImage);
            chatSingleTime = view.findViewById(R.id.chatSingleTime);

        }
    }
}
