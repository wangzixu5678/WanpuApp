package com.example.administrator.wanpuapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */

public class NewsModel {
    @SerializedName("banner")
    private List<String> banner;
    @SerializedName("news1")
    private List<NewsBean> news1;
    @SerializedName("news2")
    private List<NewsBean> news2;
    @SerializedName("news3")
    private List<NewsBean> news3;
    @SerializedName("news4")
    private List<NewsBean> news4;

    public List<String> getBanner() {
        return banner;
    }

    public void setBanner(List<String> banner) {
        this.banner = banner;
    }

    public List<NewsBean> getNews1() {
        return news1;
    }

    public void setNews1(List<NewsBean> news1) {
        this.news1 = news1;
    }

    public List<NewsBean> getNews2() {
        return news2;
    }

    public void setNews2(List<NewsBean> news2) {
        this.news2 = news2;
    }

    public List<NewsBean> getNews3() {
        return news3;
    }

    public void setNews3(List<NewsBean> news3) {
        this.news3 = news3;
    }

    public List<NewsBean> getNews4() {
        return news4;
    }

    public void setNews4(List<NewsBean> news4) {
        this.news4 = news4;
    }

    public static class NewsBean {
        /**
         * id : 1
         * title : ewrwerw
         * news_category_id : 1
         * img : http://wanpu.oss-cn-hangzhou.aliyuncs.com/img/2016/12/21/LTAIVpYkrCgM1iRN_20161207153707_1230.jpg?OSSAccessKeyId=LTAIVpYkrCgM1iRN&Expires=1482887179&Signature=YbvHnpzfqxaq6MJYUTlp%2BPYSguY%3D
         * create_time : 1482306781
         */

        private int id;
        private String title;
        private int news_category_id;
        private String img;
        private int create_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getNews_category_id() {
            return news_category_id;
        }

        public void setNews_category_id(int news_category_id) {
            this.news_category_id = news_category_id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }
    }


}
