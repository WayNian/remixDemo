/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, {Component} from "react";
import {View, Dimensions, TouchableOpacity, Text} from "react-native";
import LinearLayout from "./LinearLayoutComponent";
import Toast from './ToastTest'

export default class app extends Component {
    _OnPressPause() {
        this.video.pause()
    }

    _OnPressStart() {
        this.video.start()
    }

    render() {
        return (
            <View style={{flex: 1, alignItems: 'center', justifyContent: 'center',}}>
                <LinearLayout
                    ref={(video) => {
                        this.video = video
                    }}
                    style={{
                        width: 0,
                        height: 0
                    }}
                    // onTouch={(event) => {
                    //     Toast.show(event.nativeEvent.language, Toast.SHORT)
                    // }}
                    // url={'http://v4.music.126.net/20170913033049/81855a3a7f79c3fca9d424d8830dc1a7/web/cloudmusic/IGAwYDAwMTchIjA1IjIgIg==/mv/302093/3489ca539bab3c019102e36f653be7df.mp4'}
                     url={'http://v4.music.126.net/20170913031548/175211cfc0a9ce11d219877278ba61b3/web/cloudmusic/mv/20170828101855/8df49678-f917-450f-b694-733308c8124d/417c6b0cdbbb1df80ba3f250c3fda820.mp4'}
                    // url={'http://v4.music.126.net/20170912221955/8e05906865ec4efc4104bee8f09784b2/web/cloudmusic/MTI5MDc0OTc=/3e516fdb7aef784da92843462a9ee374/460f52025ac1ff130d07ccb4d22b09b7.mp4'}
                    //url={'http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8'}
                />
                <View style={{flexDirection: 'row'}}>
                    <TouchableOpacity
                        onPress={this._OnPressStart.bind(this)}
                        style={{
                            width: 80,
                            height: 50,
                            backgroundColor: 'gray',
                            marginTop: 20,
                            alignItems: 'center',
                            justifyContent: 'center'
                        }}>
                        <Text style={{fontSize: 15, color: '#fff'}}>开始</Text>
                    </TouchableOpacity>

                    <TouchableOpacity
                        onPress={this._OnPressPause.bind(this)}
                        style={{
                            width: 80,
                            height: 50,
                            backgroundColor: 'gray',
                            marginTop: 20,
                            alignItems: 'center',
                            justifyContent: 'center',
                            marginLeft: 20
                        }}>
                        <Text style={{fontSize: 15, color: '#fff'}}>停止</Text>
                    </TouchableOpacity>
                </View>
            </View>
        );
    }
}
