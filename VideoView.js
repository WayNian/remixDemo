/**
 * Created by waynian on 2017/9/8.
 */
import React, {PropTypes}from 'react';
import {requireNativeComponent, View} from 'react-native';

var VideoView = {
    name: 'VideoView',
    propTypes: {
        style: View.propTypes.style,
        url: PropTypes.string,
        ...View.propTypes,//包含默认的View的属性，如果没有这句会报‘has no propType for native prop’错误
    }
};
var RCTVideoView = requireNativeComponent('VideoView', VideoView);
module.exports = RCTVideoView;