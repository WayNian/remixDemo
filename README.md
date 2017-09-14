# remixDemo
##### 关键字：ReactNative ,VideoView ,ijkPlayer ,AndroidVideoCache，边播放边解密
1. Android的双屏异显，能够把一个APP的不同内容，显示在两块屏幕（平板和外接显示器），用到了Android中的Presentation
1. 副屏主要是播放视频，播放器采用的ijkPlayer，能够播放本地视频，视频流和RTMP，HLS等
1. 将控制界面（播放，暂停）放在了主屏，副屏只播放视频
1. 控制界面是用ReactNative实现了，与原生UI组件进行交互
1. 利用AndroidVideoCache实现了本地缓存
1. 利用npm anywhere创建一个文件服务器，把加密的视频放在服务端，利用AndroidVideoCache，移动端能够实现边播放边解密，且本地缓存的视频不能被其他视频播放器播放
1. 播放时解密是放在AndroidVideoCache中HttpProxyCache.java文件中的responseWithCache方法实现的
