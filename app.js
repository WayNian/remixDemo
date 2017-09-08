/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, {Component} from "react";
import {View, Dimensions} from "react-native";
import VideoView from "./VideoView";

export default class app extends Component {

    constructor() {
        super();
        this._onChange = this._onChange.bind(this);
    }

    _onChange(event: Event) {
        alert(event.map.language)
    }

    render() {
        return (
            <View style={{flex: 1, alignItems: 'center', justifyContent: 'center',}}>
                <VideoView
                    style={{
                        width: Dimensions.get('window').width,
                        height: Dimensions.get('window').height
                    }}
                    onChange={this._onChange()}
                    source={
                        {
                            url: 'http://v4.music.126.net/20170909051955/bd50b10b0c6e3f47ff16c5d281d9de7e/web/cloudmusic/MyE3MTUhMiU1MSAxJDAgMA==/mv/290244/3659fac8c8faa7b20cf483bd50f1bc57.mp4',
                            headers: {
                                'refer': 'myRefer'
                            }
                        }
                    }
                />
            </View>
        );
    }
}
