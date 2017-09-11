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

    render() {
        return (
            <View style={{flex: 1, alignItems: 'center', justifyContent: 'center',}}>
                <VideoView
                    style={{
                        // width: Dimensions.get('window').width,
                        // height: Dimensions.get('window').height
                        width: 380,
                        height: 250
                    }}
                    onTouch={(event) => {
                        Toast.show(event.nativeEvent.language, Toast.SHORT)
                    }}
                    source={
                        {
                            // url: 'http://v4.music.126.net/20170911213314/5a5a9861c529453099c21c7a33148985/web/cloudmusic/ICAgMCYgMiIgIDYgMTBhNA==/mv/5621379/04bafd00fcb328b4f812516a11f92e75.mp4',
                            url: 'http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8',
                            headers: {
                                'refer': 'myRefer'
                            }
                        }
                    }
                />

                <TouchableOpacity
                    style={{width: 80, height: 50, backgroundColor: 'blue', marginTop: 20}}>
                    <Text>开始</Text>
                </TouchableOpacity>
            </View>
        );
    }
}
