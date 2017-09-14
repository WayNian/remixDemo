/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, {Component} from "react";
import {Text, TouchableOpacity, View} from "react-native";
import LinearLayout from "./LinearLayoutComponent";

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
                    //测试地址,已加密的视频
                    url={'http://192.168.1.199:8000/fadeEncrypt.mp4'}
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
