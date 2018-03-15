package com.example.taquio.trasearch.Samok;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.taquio.trasearch.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Del Mar on 2/7/2018.
 */

public class VideosFragment extends Fragment {
    private static final String TAG = "VideosFragment";
    FirebaseAuth mAuth;
    private VideoView myPlayer;
    private MediaController myController;
    private Uri vidLocation;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_videos, container, false);
        mAuth = FirebaseAuth.getInstance();

//        myPlayer = view.findViewById(R.id.videoView);
//
//
//        myController = new MediaController(getContext());
//        myController.setAnchorView(myPlayer);
//        vidLocation = Uri.parse("https://www.youtube.com/watch?v=whFVhvM-J0U");
//        setUpMedia();
        Button buttonPlayVideo2 = (Button)view.findViewById(R.id.button1);
        getActivity().getWindow().setFormat(PixelFormat.UNKNOWN);
//displays a video file
        VideoView mVideoView2 = (VideoView)view.findViewById(R.id.videoView1);
        String uriPath2 = "https://www.youtube.com/watch?v=whFVhvM-J0U";
        Uri uri2 = Uri.parse(uriPath2);
        mVideoView2.setVideoURI(uri2);
        mVideoView2.requestFocus();
        mVideoView2.start();
        buttonPlayVideo2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoView mVideoView2 = (VideoView) view.findViewById(R.id.videoView1);
// VideoView mVideoView = new VideoView(this);
                String uriPath = "https://www.youtube.com/watch?v=whFVhvM-J0U";
                Uri uri2 = Uri.parse(uriPath);
                mVideoView2.setVideoURI(uri2);
                mVideoView2.requestFocus();
                mVideoView2.start();
            }
        });
        return view;
    }

    private void setUpMedia() {
        myPlayer.setMediaController(myController);
        myPlayer.setVideoURI(vidLocation);
        myPlayer.start();
    }
}
