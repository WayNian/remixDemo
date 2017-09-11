/**
 * Created by waynian on 2017/9/8.
 */
import React, {Component, PropTypes} from 'react';
import {requireNativeComponent, View} from 'react-native';

// 第一个参数是原生模块的名称， 第二个是当前组件的名称
var VideoView = requireNativeComponent('VideoView', VideoViewComponent, {
    nativeOnly: {onChange: true}
});

class VideoViewComponent extends Component {
    render() {
        // onChange事件是JS已经定义好的，对应原生的topChange事件
        return <VideoView
            {...this.props}
            onChange={(event) => {
                this.props.onTouch(event);
            }}/>;
    }
}

VideoViewComponent.propTypes = {
    array: PropTypes.arrayOf(PropTypes.string),
    onTouch: PropTypes.func,
    ...View.propTypes, // 包含默认的View的属性
};

module.exports = VideoViewComponent;