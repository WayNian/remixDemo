package com.remixdemo;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Build;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.VideoView;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * Created by waynian on 2017/9/8.
 */

public class VideoViewManager extends SimpleViewManager<VideoView> {

    private DifferentDisplay presentation;
    Display[] presentationDisplays;

    @Override
    public String getName() {
        return "VideoView";
    }

    @Override
    protected VideoView createViewInstance(final ThemedReactContext reactContext) {
        final VideoView video = new VideoView(reactContext);
        DisplayManager displayManager = (DisplayManager) reactContext.getSystemService(Context.DISPLAY_SERVICE);
        presentationDisplays = displayManager.getDisplays();
        video.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    String language = presentationDisplays.length + "";
                    WritableMap map = Arguments.createMap();
                    map.putString("language", language);
                    // "topChange"事件在JS端映射到"onChange"，参考UIManagerModuleConstants.java
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

    @Override
    public void onDropViewInstance(VideoView view) {
        super.onDropViewInstance(view);
        view.stopPlayback();
    }

    @ReactProp(name = "source")
    public void setSource(VideoView videoView, @Nullable ReadableMap source) {

        if (source != null) {
            if (source.hasKey("url")) {
                String url = source.getString("url");
                FLog.e(VideoViewManager.class, "url = " + url);
                HashMap<String, String> headerMap = new HashMap<>();
                if (source.hasKey("headers")) {
                    ReadableMap headers = source.getMap("headers");
                    ReadableMapKeySetIterator iter = headers.keySetIterator();
                    while (iter.hasNextKey()) {
                        String key = iter.nextKey();
                        String value = headers.getString(key);
                        FLog.e(VideoViewManager.class, key + " = " + value);
                        headerMap.put(key, value);
                    }
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    videoView.setVideoURI(Uri.parse(url), headerMap);
                } else {
                    try {
                        Method setVideoURIMethod = videoView.getClass().getMethod("setVideoURI", Uri.class, Map.class);
                        setVideoURIMethod.invoke(videoView, Uri.parse(url), headerMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                videoView.start();
            }
        }
    }


}