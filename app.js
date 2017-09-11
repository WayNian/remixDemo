/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, {Component} from "react";
import {View, Dimensions} from "react-native";
import VideoView from "./VideoViewComponent";

export default class app extends Component {

    render() {
        return (
            <View style={{flex: 1, alignItems: 'center', justifyContent: 'center',}}>
                <VideoView
                    style={{
                        width: Dimensions.get('window').width,
                        height: Dimensions.get('window').height
                    }}
                    onChange={(event)=>{
                        alert(event.nativeEvent.language)
                    }}
                    source={
                        {
                            url: 'http://v4.music.126.net/20170911213314/5a5a9861c529453099c21c7a33148985/web/cloudmusic/ICAgMCYgMiIgIDYgMTBhNA==/mv/5621379/04bafd00fcb328b4f812516a11f92e75.mp4',
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
