package com.remixdemo;

import android.app.Presentation;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.widget.VideoView;

import com.remixdemo.media.IjkVideoView;

/**
 * Created by waynian on 2017/9/6.
 */

public class DifferentDisplay extends Presentation {
    public IjkVideoView videoView;
    public IjkVideoView videoView2;

    String url = "http://v4.music.126.net/20170912221955/8e05906865ec4efc4104bee8f09784b2/web/cloudmusic/MTI5MDc0OTc=/3e516fdb7aef784da92843462a9ee374/460f52025ac1ff130d07ccb4d22b09b7.mp4";

    public DifferentDisplay(Context outerContext, Display display) {
        super(outerContext, display);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_video);

        videoView = (IjkVideoView) findViewById(R.id.video_view);
        videoView2 = (IjkVideoView) findViewById(R.id.video_view2);

    }
}
