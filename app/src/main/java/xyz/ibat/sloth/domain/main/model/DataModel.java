package xyz.ibat.sloth.domain.main.model;

import java.util.List;

/**
 * Created by DongJr on 2017/1/13.
 */

public class DataModel {


    /**
     * error : false
     * results : [{"_id":"5875e66c421aa9315ea79931","createdAt":"2017-01-11T16:01:48.998Z","desc":"在Chrome DevTools内查看Android设备上所有HTTP(S)流量","images":["http://img.gank.io/b33cc839-18de-48e0-8644-089e6986609a","http://img.gank.io/a4ce3373-2f20-4fe5-bbcf-9f6dc3847591"],"publishedAt":"2017-01-12T11:30:59.369Z","source":"web","type":"Android","url":"https://github.com/misakuo/Dream-Catcher","used":true,"who":"moxun"},{"_id":"5876e940421aa93161103df1","createdAt":"2017-01-12T10:26:08.237Z","desc":"类似 Facebook 加载时，条目的闪烁效果，酷酷的。","images":["http://img.gank.io/985a1011-632f-40c1-9ca5-1bd9cb17a69f","http://img.gank.io/c5c916fc-b391-40ec-9962-df8588f23cba"],"publishedAt":"2017-01-12T11:30:59.369Z","source":"chrome","type":"Android","url":"https://github.com/sharish/ShimmerRecyclerView","used":true,"who":"代码家"},{"_id":"5872fb61421aa9315bfbe843","createdAt":"2017-01-09T10:54:25.659Z","desc":"android多图选择 图片/视频 单选or多选，以及视频录制。","images":["http://img.gank.io/eafb5329-c252-42f3-a8cc-48f6b247c9f8","http://img.gank.io/f58b7772-ce21-4875-b709-2f21ba2d2c3f"],"publishedAt":"2017-01-11T12:05:20.787Z","source":"web","type":"Android","url":"https://github.com/LuckSiege/PictureSelector","used":true,"who":null},{"_id":"587469c5421aa9316407fb95","createdAt":"2017-01-10T12:57:41.909Z","desc":"top-5-android-libraries-january-2017","publishedAt":"2017-01-11T12:05:20.787Z","source":"web","type":"Android","url":"https://medium.cobeisfresh.com/top-5-android-libraries-january-2017-53e217783fc9","used":true,"who":"kg"},{"_id":"58759363421aa93161103ddd","createdAt":"2017-01-11T10:07:31.126Z","desc":"Android 多渠道打包的 Android Studio / IDEA 插件","images":["http://img.gank.io/e9e0de53-aaba-4e2b-a123-cdaac87eeb56"],"publishedAt":"2017-01-11T12:05:20.787Z","source":"web","type":"Android","url":"https://github.com/nukc/ApkMultiChannelPlugin","used":true,"who":"C君"},{"_id":"58759c0d421aa93161103ddf","createdAt":"2017-01-11T10:44:29.208Z","desc":"Android 状态切换按钮效果","images":["http://img.gank.io/10c58071-a555-4e1f-84ff-8220644a35e9"],"publishedAt":"2017-01-11T12:05:20.787Z","source":"chrome","type":"Android","url":"https://github.com/zagum/Android-SwitchIcon","used":true,"who":"代码家"},{"_id":"58759c56421aa9315bfbe856","createdAt":"2017-01-11T10:45:42.636Z","desc":"Android 跑马灯效果，适合做个小广告，通知之类的。","images":["http://img.gank.io/ba7b29c2-55d9-4cc5-9861-ffc19d332950"],"publishedAt":"2017-01-11T12:05:20.787Z","source":"chrome","type":"Android","url":"https://github.com/gongwen/MarqueeViewLibrary","used":true,"who":"代码家"},{"_id":"58732679421aa93161103dd0","createdAt":"2017-01-09T13:58:17.708Z","desc":"Tinker接入及源码分析，从简单介绍到如何接入再到加载补丁、合成补丁源码分析","images":["http://img.gank.io/1408b5b4-23b3-410e-80cb-9cb096d1ad8a"],"publishedAt":"2017-01-10T11:33:19.525Z","source":"web","type":"Android","url":"http://qlm.pw/2017/01/07/tinker%E6%8E%A5%E5%85%A5%E5%8F%8A%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90%EF%BC%88%E4%B8%80%EF%BC%89/","used":true,"who":"Linmin Qiu"},{"_id":"58732e0a421aa9316407fb8a","createdAt":"2017-01-09T14:30:34.824Z","desc":"两行代码搞定Android视图扩散切换效果~","images":["http://img.gank.io/082da85a-9c5a-4330-a6c9-8ab624792b9f"],"publishedAt":"2017-01-10T11:33:19.525Z","source":"web","type":"Android","url":"https://github.com/zhangke3016/ViewSpreadTranslationController","used":true,"who":"张珂"},{"_id":"58745452421aa9316407fb93","createdAt":"2017-01-10T11:26:10.880Z","desc":"支持计数效果的 FloatingActionButton","images":["http://img.gank.io/2047fbd1-57cc-4efe-be4e-a31487904825"],"publishedAt":"2017-01-10T11:33:19.525Z","source":"chrome","type":"Android","url":"https://github.com/andremion/CounterFab","used":true,"who":"代码家"}]
     */

    private boolean error;
    /**
     * _id : 5875e66c421aa9315ea79931
     * createdAt : 2017-01-11T16:01:48.998Z
     * desc : 在Chrome DevTools内查看Android设备上所有HTTP(S)流量
     * images : ["http://img.gank.io/b33cc839-18de-48e0-8644-089e6986609a","http://img.gank.io/a4ce3373-2f20-4fe5-bbcf-9f6dc3847591"]
     * publishedAt : 2017-01-12T11:30:59.369Z
     * source : web
     * type : Android
     * url : https://github.com/misakuo/Dream-Catcher
     * used : true
     * who : moxun
     */

    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
