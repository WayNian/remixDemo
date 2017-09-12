package com.remixdemo;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.remixdemo.media.IjkVideoView;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * Created by waynian on 2017/9/8.
 */

public class VideoViewManager extends SimpleViewManager<IjkVideoView> {

    private static final String TAG = "SimpleViewManager";
    private DifferentDisplay presentation;
    Display[] presentationDisplays;
    IjkVideoView video;
    IjkVideoView videoView2;
    String urlRN;

    private static final int COMMAND_PAUSE_ID = 1;
    private static final String COMMAND_PAUSE_NAME = "pause";

    @Override
    public String getName() {
        return "VideoView";
    }

    @Override
    protected IjkVideoView createViewInstance(final ThemedReactContext reactContext) {
//        final VideoView video = new VideoView(reactContext);
        DisplayManager displayManager = (DisplayManager) reactContext.getSystemService(Context.DISPLAY_SERVICE);
        presentationDisplays = displayManager.getDisplays();

        if (presentationDisplays.length > 1) {
            presentation = new DifferentDisplay(reactContext, presentationDisplays[1]);
        } else {
            presentation = new DifferentDisplay(reactContext, presentationDisplays[0]);
        }
        presentation.show();
        video = presentation.videoView;
        videoView2 = presentation.videoView2;
        ((ViewGroup) video.getParent()).removeView(video);
        video.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    String language = presentationDisplays.length + "";
                    WritableMap map = Arguments.createMap();
                    map.putString("language", language);
                    // "topChange"事件在JS端映射到"onChange"
                    reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(video.getId()
                            , "topChange", map);
                    return true;
                } else {
                    return false;
                }
            }
        });

        return video;
    }

    @Nullable
    @Override
    public Map<String, Integer> getCommandsMap() {
        Log.d(TAG, "getCommandsMap: 执行了");
        return MapBuilder.of(
                COMMAND_PAUSE_NAME, COMMAND_PAUSE_ID
        );
    }

    @Override
    public void receiveCommand(IjkVideoView root, int commandId, @Nullable ReadableArray args) {
        switch (commandId) {
            case COMMAND_PAUSE_ID:
                Log.d(TAG, "receiveCommand: 执行了"  + urlRN);
                videoView2.setVideoURI(Uri.parse(urlRN));
                videoView2.start();
                video.pause();
                break;
            default:
                break;
        }
    }

    @Override
    public void onDropViewInstance(IjkVideoView view) {
        super.onDropViewInstance(view);
        view.stopPlayback();
    }

    @ReactProp(name = "url")
    public void setSource(IjkVideoView videoView, @Nullable String url) {
        if (url != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                videoView.setVideoURI(Uri.parse(url));
                urlRN = url;
            } else {
                try {
                    Method setVideoURIMethod = videoView.getClass().getMethod("setVideoURI", Uri.class, Map.class);
                    setVideoURIMethod.invoke(videoView, Uri.parse(url));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            videoView.start();
        }
    }


}
