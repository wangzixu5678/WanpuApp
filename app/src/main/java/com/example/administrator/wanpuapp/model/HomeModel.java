package com.example.administrator.wanpuapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/12/27.
 */

public class HomeModel {
    private List<String> bannner;
    private List<CompanyBean> company;
    private List<ProduceBean> produce;
    @SerializedName("new")
    private List<NewBean> newX;

    public List<String> getBannner() {
        return bannner;
    }

    public void setBannner(List<String> bannner) {
        this.bannner = bannner;
    }

    public List<CompanyBean> getCompany() {
        return company;
    }

    public void setCompany(List<CompanyBean> company) {
        this.company = company;
    }

    public List<ProduceBean> getProduce() {
        return produce;
    }

    public void setProduce(List<ProduceBean> produce) {
        this.produce = produce;
    }

    public List<NewBean> getNewX() {
        return newX;
    }

    public void setNewX(List<NewBean> newX) {
        this.newX = newX;
    }

    public static class CompanyBean {
        /**
         * id : 1
         * name : 紫园区rr
         * logo : http://wanpu.oss-cn-hangzhou.aliyuncs.com/img/2016/12/23/LTAIVpYkrCgM1iRN_20161213144613_1046.jpg?OSSAccessKeyId=LTAIVpYkrCgM1iRN&Expires=1482801200&Signature=utp08K7wfPKFlq5OMz2IIvtjqeM%3D
         * image : http://wanpu.oss-cn-hangzhou.aliyuncs.com/img/2016/12/21/LTAIVpYkrCgM1iRN_20161204191104_1497.jpg?OSSAccessKeyId=LTAIVpYkrCgM1iRN&Expires=1482801200&Signature=aFhCgP1L%2FVRthBfJUydpcotZ3jQ%3D
         * des : dddddddddddddddddddddd
         */

        private int id;
        private String name;
        private String logo;
        private String image;
        private String des;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }
    }

    public static class ProduceBean {
        /**
         * id : 1
         * goods : 阿斯达四方
         * small_img : http://wanpu.oss-cn-hangzhou.aliyuncs.com/img/2016/12/20/LTAIVpYkrCgM1iRN_20161212222012_1057.jpg?OSSAccessKeyId=LTAIVpYkrCgM1iRN&Expires=1482801200&Signature=eRqpJXqvMlCxTcw3%2FlcfpLCvuV8%3D
         */

        private int id;
        private String goods;
        private String small_img;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGoods() {
            return goods;
        }

        public void setGoods(String goods) {
            this.goods = goods;
        }

        public String getSmall_img() {
            return small_img;
        }

        public void setSmall_img(String small_img) {
            this.small_img = small_img;
        }
    }

    public static class NewBean {
        /**
         * id : 1
         * title : ewrwerw
         * create_time : 1482306781
         * img : http://wanpu.oss-cn-hangzhou.aliyuncs.com/img/2016/12/21/LTAIVpYkrCgM1iRN_20161207153707_1230.jpg?OSSAccessKeyId=LTAIVpYkrCgM1iRN&Expires=1482801200&Signature=MjY%2FEI3rsGgcBMHFF0vTkJcV4Ds%3D
         */

        private int id;
        private String title;
        private int create_time;
        private String img;

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

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
