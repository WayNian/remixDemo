/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, {Component} from "react";
import {View, Dimensions, TouchableOpacity, Text} from "react-native";
import VideoView from "./VideoViewComponent";
import Toast from './ToastTest'

export default class app extends Component {
    _OnPressPause() {
        this.video.pause()
    }

    render() {
        return (
            <View style={{flex: 1, alignItems: 'center', justifyContent: 'center',}}>
                <VideoView
                    ref={(video)=>{this.video = video}}
                    style={{
                        // width: Dimensions.get('window').width,
                        // height: Dimensions.get('window').height
                        width: 380,
                        height: 250
                    }}
                    onTouch={(event) => {
                        Toast.show(event.nativeEvent.language, Toast.SHORT)
                    }}
                    url={'http://v4.music.126.net/20170912221955/8e05906865ec4efc4104bee8f09784b2/web/cloudmusic/MTI5MDc0OTc=/3e516fdb7aef784da92843462a9ee374/460f52025ac1ff130d07ccb4d22b09b7.mp4'}
                    //url={'http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8'}
                />

                <TouchableOpacity
                    onPress={this._OnPressPause.bind(this)}
                    style={{width: 80, height: 50, backgroundColor: 'blue', marginTop: 20}}>
                    <Text>停止</Text>
                </TouchableOpacity>
            </View>
        );
    }
}
