/**
 * Created by waynian on 2017/9/8.
 */
import React, {Component, PropTypes} from 'react';
import {requireNativeComponent, View, UIManager, findNodeHandle} from 'react-native';

// 第一个参数是原生模块的名称， 第二个是当前组件的名称
//nativeOnly。有时候有一些特殊的属性，想从原生组件中导出，但是又不希望它们成为对应React封装组件的属性。
var LinearLayout = requireNativeComponent('LinearLayout', LinearLayoutComponent, {
    nativeOnly: {onChange: true}
});

var RCT_VIDEO_REF = "LinearLayout"

class LinearLayoutComponent extends Component {
    pause() {
        UIManager.dispatchViewManagerCommand(
            findNodeHandle(this.refs[RCT_VIDEO_REF]),
            UIManager.LinearLayout.Commands.pause,
            null
        );
    }

    start() {
        UIManager.dispatchViewManagerCommand(
            findNodeHandle(this.refs[RCT_VIDEO_REF]),
            UIManager.LinearLayout.Commands.start,
            null
        );
    }

    render() {
        // onChange事件是JS已经定义好的，对应原生的topChange事件
        return <LinearLayout
            {...this.props}
            ref={RCT_VIDEO_REF}
            onChange={(event) => {
                this.props.onTouch(event);
            }}/>;
    }
}

LinearLayoutComponent.propTypes = {
    array: PropTypes.arrayOf(PropTypes.string),
    onTouch: PropTypes.func,
    ...View.propTypes, // 包含默认的View的属性
};

module.exports = LinearLayoutComponent;