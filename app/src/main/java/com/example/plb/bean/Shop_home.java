package com.example.plb.bean;

import java.util.List;

public class Shop_home {


    /**
     * Advertisement : [{"advertisementId":1,"advertisementURL":"http://39.98.68.40:8080/RetailManager/static/images/activity/20181228-005.jpg"},{"advertisementId":2,"advertisementURL":"http://39.98.68.40:8080/RetailManager/static/images/activity/20181228-006.jpg"},{"advertisementId":3,"advertisementURL":"http://39.98.68.40:8080/RetailManager/static/images/activity/20181228-007.jpg"},{"advertisementId":4,"advertisementURL":"http://39.98.68.40:8080/RetailManager/static/images/activity/20190116-001.jpg"}]
     * Activity : {"data3":[{"activityId":3,"ImgURL":"http://39.98.68.40:8080/RetailManager/static/images/activity/20190109-ch01.jpg","introduce":"满200减80","activity_item_Name":"小吃零食"},{"activityId":3,"introduce":"满200减80","activity_item_Name":"小吃零食","ImgURL":"http://39.98.68.40:8080/RetailManager/static/images/activity/20190109-ch02.jpg"},{"activityId":3,"introduce":"满199减100","activity_item_Name":"水果罐头","ImgURL":"http://39.98.68.40:8080/RetailManager/static/images/activity/20190109-ch03.jpg"},{"activityId":3,"introduce":"满99减50","activity_item_Name":"坚果零食","ImgURL":"http://39.98.68.40:8080/RetailManager/static/images/activity/20190109-ch04.jpg"}],"data2":[{"activityId":2,"introduce":"年度销量领先","activity_item_Name":"爆款精选","ImgURL":"http://39.98.68.40:8080/RetailManager/static/images/activity/20190109-ndbk.jpg"},{"activityId":2,"introduce":"零食热销推荐","activity_item_Name":"吃货都爱","ImgURL":"http://39.98.68.40:8080/RetailManager/static/images/activity/20190109-lstj.jpg"},{"activityId":2,"introduce":"大家都在用","activity_item_Name":"居家必备","ImgURL":"http://39.98.68.40:8080/RetailManager/static/images/activity/20190109-jjbb.jpg"},{"activityId":2,"introduce":"年度爆款精选","activity_item_Name":"精选服装","ImgURL":"http://39.98.68.40:8080/RetailManager/static/images/activity/20190109-yftj.jpg"},{"activityId":2,"introduce":"大内存充电宝榜","activity_item_Name":"充电宝榜","ImgURL":"http://39.98.68.40:8080/RetailManager/static/images/activity/20190109-cdb.jpg"},{"activityId":2,"introduce":"畅销多用不粘锅","activity_item_Name":"厨卫用品","ImgURL":"http://39.98.68.40:8080/RetailManager/static/images/activity/20190109-cg.jpg"}],"data1":[{"activityId":1,"introduce":"满800返16","activity_item_Name":"水和乳饮","ImgURL":"http://39.98.68.40:8080/RetailManager/static/images/activity/20190109-redbull.jpg"},{"activityId":1,"introduce":"满800返16","activity_item_Name":"粮油副食","ImgURL":"http://39.98.68.40:8080/RetailManager/static/images/activity/20190109-jly.jpg"},{"activityId":1,"introduce":"秒杀5折","activity_item_Name":"休闲食品","ImgURL":"http://39.98.68.40:8080/RetailManager/static/images/activity/20190109-hly.jpg"},{"activityId":1,"introduce":"0.99元秒杀","activity_item_Name":"个护家清","ImgURL":"http://39.98.68.40:8080/RetailManager/static/images/activity/20190109-tz.jpg"},{"activityId":1,"introduce":"5折起疯狂抢","activity_item_Name":"家纺百货","ImgURL":"http://39.98.68.40:8080/RetailManager/static/images/activity/20190109-jf.jpg"},{"activityId":1,"introduce":"正品直降","activity_item_Name":"母婴玩具","ImgURL":"http://39.98.68.40:8080/RetailManager/static/images/activity/20190109-mywj.jpg"}],"type":[{"id":1,"activity_theme":"特价商品"},{"id":2,"activity_theme":"热销商品"},{"id":3,"activity_theme":"吃货复苏季"}]}
     * Market : [{"id":1,"marketName":"五一市场","longitude":112.6188755035,"latitude":26.9196592391},{"id":2,"marketName":"衡州大市场","longitude":112.6238,"latitude":26.88855},{"id":3,"marketName":"三泰市场","longitude":112.57018,"latitude":26.89071}]
     */

    private ActivityBean Activity;
    private List<AdvertisementBean> Advertisement;
    private List<MarketBean> Market;

    public ActivityBean getActivity() {
        return Activity;
    }

    public void setActivity(ActivityBean Activity) {
        this.Activity = Activity;
    }

    public List<AdvertisementBean> getAdvertisement() {
        return Advertisement;
    }

    public void setAdvertisement(List<AdvertisementBean> Advertisement) {
        this.Advertisement = Advertisement;
    }

    public List<MarketBean> getMarket() {
        return Market;
    }

    public void setMarket(List<MarketBean> Market) {
        this.Market = Market;
    }

    public static class ActivityBean {
        private List<Data3Bean> data3;
        private List<Data2Bean> data2;
        private List<Data1Bean> data1;
        private List<TypeBean> type;

        public List<Data3Bean> getData3() {
            return data3;
        }

        public void setData3(List<Data3Bean> data3) {
            this.data3 = data3;
        }

        public List<Data2Bean> getData2() {
            return data2;
        }

        public void setData2(List<Data2Bean> data2) {
            this.data2 = data2;
        }

        public List<Data1Bean> getData1() {
            return data1;
        }

        public void setData1(List<Data1Bean> data1) {
            this.data1 = data1;
        }

        public List<TypeBean> getType() {
            return type;
        }

        public void setType(List<TypeBean> type) {
            this.type = type;
        }

        public static class Data3Bean {
            /**
             * activityId : 3
             * ImgURL : http://39.98.68.40:8080/RetailManager/static/images/activity/20190109-ch01.jpg
             * introduce : 满200减80
             * activity_item_Name : 小吃零食
             */

            private int activityId;
            private String ImgURL;
            private String introduce;
            private String activity_item_Name;

            public int getActivityId() {
                return activityId;
            }

            public void setActivityId(int activityId) {
                this.activityId = activityId;
            }

            public String getImgURL() {
                return ImgURL;
            }

            public void setImgURL(String ImgURL) {
                this.ImgURL = ImgURL;
            }

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
            }

            public String getActivity_item_Name() {
                return activity_item_Name;
            }

            public void setActivity_item_Name(String activity_item_Name) {
                this.activity_item_Name = activity_item_Name;
            }
        }

        public static class Data2Bean {
            /**
             * activityId : 2
             * introduce : 年度销量领先
             * activity_item_Name : 爆款精选
             * ImgURL : http://39.98.68.40:8080/RetailManager/static/images/activity/20190109-ndbk.jpg
             */

            private int activityId;
            private String introduce;
            private String activity_item_Name;
            private String ImgURL;

            public int getActivityId() {
                return activityId;
            }

            public void setActivityId(int activityId) {
                this.activityId = activityId;
            }

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
            }

            public String getActivity_item_Name() {
                return activity_item_Name;
            }

            public void setActivity_item_Name(String activity_item_Name) {
                this.activity_item_Name = activity_item_Name;
            }

            public String getImgURL() {
                return ImgURL;
            }

            public void setImgURL(String ImgURL) {
                this.ImgURL = ImgURL;
            }
        }

        public static class Data1Bean {
            /**
             * activityId : 1
             * introduce : 满800返16
             * activity_item_Name : 水和乳饮
             * ImgURL : http://39.98.68.40:8080/RetailManager/static/images/activity/20190109-redbull.jpg
             */

            private int activityId;
            private String introduce;
            private String activity_item_Name;
            private String ImgURL;

            public int getActivityId() {
                return activityId;
            }

            public void setActivityId(int activityId) {
                this.activityId = activityId;
            }

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
            }

            public String getActivity_item_Name() {
                return activity_item_Name;
            }

            public void setActivity_item_Name(String activity_item_Name) {
                this.activity_item_Name = activity_item_Name;
            }

            public String getImgURL() {
                return ImgURL;
            }

            public void setImgURL(String ImgURL) {
                this.ImgURL = ImgURL;
            }
        }

        public static class TypeBean {
            /**
             * id : 1
             * activity_theme : 特价商品
             */

            private int id;
            private String activity_theme;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getActivity_theme() {
                return activity_theme;
            }

            public void setActivity_theme(String activity_theme) {
                this.activity_theme = activity_theme;
            }
        }
    }

    public static class AdvertisementBean {
        /**
         * advertisementId : 1
         * advertisementURL : http://39.98.68.40:8080/RetailManager/static/images/activity/20181228-005.jpg
         */

        private int advertisementId;
        private String advertisementURL;

        public int getAdvertisementId() {
            return advertisementId;
        }

        public void setAdvertisementId(int advertisementId) {
            this.advertisementId = advertisementId;
        }

        public String getAdvertisementURL() {
            return advertisementURL;
        }

        public void setAdvertisementURL(String advertisementURL) {
            this.advertisementURL = advertisementURL;
        }
    }

    public static class MarketBean {
        /**
         * id : 1
         * marketName : 五一市场
         * longitude : 112.6188755035
         * latitude : 26.9196592391
         */

        private int id;
        private String marketName;
        private double longitude;
        private double latitude;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMarketName() {
            return marketName;
        }

        public void setMarketName(String marketName) {
            this.marketName = marketName;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }
    }
}
