package com.example.taquio.trasearch.Samok;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taquio.trasearch.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportViewFragment extends Fragment {
    private static final String TAG = "ReportViewFragment";
    private View mMainView;
    private RecyclerView reportView_list;
    private DatabaseReference mReportDatabase,mUserDatabase,mPostDatabase;
    public ReportViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mMainView = inflater.inflate(R.layout.fragment_report_view, container, false);
        reportView_list = mMainView.findViewById(R.id.reportView_list);

        mReportDatabase = FirebaseDatabase.getInstance().getReference().child("Reports");
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mPostDatabase = FirebaseDatabase.getInstance().getReference().child("Users_Photos");


        reportView_list.setLayoutManager(new LinearLayoutManager(getContext()));
        return mMainView;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<ReportView,ReportViewViewHolder> reportViewViewHolderFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ReportView, ReportViewViewHolder>(
                ReportView.class,
                R.layout.reported_sigle,
                ReportViewViewHolder.class,
                mReportDatabase
        ) {
            @Override
            protected void populateViewHolder(final ReportViewViewHolder viewHolder, ReportView model, int position) {
                String list_id = getRef(position).getKey();

                mUserDatabase.child(list_id).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        String Name = dataSnapshot.child("Name").getValue().toString();
                        viewHolder.setPoster(Name);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                mPostDatabase.child(list_id).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        String URL = dataSnapshot.child("image_path").getValue().toString(),
                                photoDesc = dataSnapshot.child("photo_description").getValue().toString();
                        Long date = (Long) dataSnapshot.child("date_created").getValue();
                        viewHolder.setImage(URL,getContext());
                        viewHolder.setDate(date);
                        viewHolder.setPostDesc(photoDesc);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                mReportDatabase.child(list_id).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        String reportMsg = dataSnapshot.getValue().toString();
                        viewHolder.setReportMsg(reportMsg);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        };
        reportView_list.setAdapter(reportViewViewHolderFirebaseRecyclerAdapter);
    }

    public static class ReportViewViewHolder extends RecyclerView.ViewHolder {
        View mView;

            public ReportViewViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setImage(String URL, Context context)
        {
            ImageView reportView_image = itemView.findViewById(R.id.reportView_image);
            Picasso.with(context).load(URL)
                    .into(reportView_image);
        }
        public void setPoster(String poster)
        {
            TextView reportView_poster = itemView.findViewById(R.id.reportView_poster);
            reportView_poster.setText("Posted By: "+poster);
        }
        public void setPostDesc (String postDesc)
        {
            TextView reportView_photoDesc = itemView.findViewById(R.id.reportView_photoDesc);
            reportView_photoDesc.setText(postDesc);
        }
        public void setDate (Long date)
        {
            TextView reportView_photoDate = itemView.findViewById(R.id.reportView_photoDate);
            reportView_photoDate.setText(getDate(date,"MMM dd, yyyy E hh:mm aa"));
        }
        public void setReportMsg (String reportMsg)
        {
            TextView reportView_reportMsg = itemView.findViewById(R.id.reportView_reportMsg);
            reportView_reportMsg.setText(reportMsg);
        }
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
}
