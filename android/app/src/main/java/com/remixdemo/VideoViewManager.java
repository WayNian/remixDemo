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
import android.widget.LinearLayout;
import android.widget.VideoView;

import com.danikula.videocache.HttpProxyCacheServer;
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

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by waynian on 2017/9/8.
 */

public class VideoViewManager extends SimpleViewManager<LinearLayout> {

    private static final String TAG = "SimpleViewManager";
    private DifferentDisplay presentation;
    private Display[] presentationDisplays;
    private IjkVideoView videoView;

    private Context context;

    private static final int COMMAND_START_ID = 1;
    private static final String COMMAND_START_NAME = "start";

    private static final int COMMAND_PAUSE_ID = 2;
    private static final String COMMAND_PAUSE_NAME = "pause";

    @Override
    public String getName() {
        return "LinearLayout";
    }

    @Override
    protected LinearLayout createViewInstance(final ThemedReactContext reactContext) {
        context = reactContext;
        final LinearLayout linearLayout = new LinearLayout(reactContext);
        DisplayManager displayManager = (DisplayManager) reactContext.getSystemService(Context.DISPLAY_SERVICE);
        presentationDisplays = displayManager.getDisplays();

        if (presentationDisplays.length > 1) {
            presentation = new DifferentDisplay(reactContext, presentationDisplays[1]);
        } else {
            presentation = new DifferentDisplay(reactContext, presentationDisplays[0]);
        }
        presentation.show();
        videoView = presentation.videoView;
        return linearLayout;
    }

    @Nullable
    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of(
                COMMAND_PAUSE_NAME, COMMAND_PAUSE_ID,
                COMMAND_START_NAME, COMMAND_START_ID
        );
    }

    @Override
    public void receiveCommand(LinearLayout linearLayout, int commandId, @Nullable ReadableArray args) {
        switch (commandId) {
            case COMMAND_START_ID:
                if (!videoView.isPlaying()) {
                    videoView.start();
                }
                break;
            case COMMAND_PAUSE_ID:
                if (videoView.isPlaying()) {
                    videoView.pause();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onDropViewInstance(LinearLayout linearLayout) {
        super.onDropViewInstance(linearLayout);
        videoView.stopPlayback();
    }

    //接受ReactNative 组件传递过来的参数
    @ReactProp(name = "url")
    public void setSource(LinearLayout linearLayout, @Nullable String url) {
        if (url != null) {
            HttpProxyCacheServer proxy = new MainApplication().getProxy(context);
            String proxyUrl = proxy.getProxyUrl(url);

            videoView.setVideoURI(Uri.parse(proxyUrl));
            videoView.start();
        }
    }
}
