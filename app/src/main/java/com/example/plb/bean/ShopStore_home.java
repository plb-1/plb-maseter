package com.example.plb.bean;

import java.util.List;

public class ShopStore_home {


    /**
     * Recommendedshop : {"commodity":[{"id":1,"Image":"http://39.98.68.40:8080/RetailManager/static/images/commodity/20181228-008.jpg","storeId":1,"wholesalePrice":56.2},{"id":2,"Image":"http://39.98.68.40:8080/RetailManager/static/images/commodity/004.jpg","storeId":1,"wholesalePrice":2},{"id":3,"Image":"http://39.98.68.40:8080/RetailManager/static/images/commodity/20190109-010.jpg","storeId":1,"wholesalePrice":4},{"id":4,"Image":"http://39.98.68.40:8080/RetailManager/static/images/commodity/002.jpg","storeId":1,"wholesalePrice":38.5},{"id":5,"Image":"http://39.98.68.40:8080/RetailManager/static/images/commodity/20190109-011.jpg","storeId":2,"wholesalePrice":4},{"id":6,"Image":"http://39.98.68.40:8080/RetailManager/static/images/commodity/20190109-009.jpg","storeId":2,"wholesalePrice":35},{"id":7,"Image":"http://39.98.68.40:8080/RetailManager/static/images/commodity/2-001.jpg","storeId":2,"wholesalePrice":88.5},{"id":8,"Image":"http://39.98.68.40:8080/RetailManager/static/images/commodity/20190112-003.jpg","storeId":2,"wholesalePrice":20},{"id":9,"Image":"http://39.98.68.40:8080/RetailManager/static/images/commodity/20190112-001.jpg","storeId":3,"wholesalePrice":38},{"id":10,"Image":"http://39.98.68.40:8080/RetailManager/static/images/commodity/20190112-005.jpg","storeId":3,"wholesalePrice":60},{"id":11,"Image":"http://39.98.68.40:8080/RetailManager/static/images/commodity/20190112-007.jpg","storeId":3,"wholesalePrice":47.5},{"id":12,"Image":"http://39.98.68.40:8080/RetailManager/static/images/commodity/20190112-100.jpg","storeId":3,"wholesalePrice":60}],"store":[{"closeTime":"20:00","storeName":"测试001批发店铺","storeId":1,"openTime":"9:00","slogan":"掌上生活，疯狂特惠","logoUrl":"http://39.98.68.40:8080/RetailManager/static/images/store/20190111-001.jpg"},{"closeTime":"20:00","storeName":"测试002批发店铺","storeId":2,"openTime":"9:00","slogan":"此处应有尽有","logoUrl":"http://39.98.68.40:8080/RetailManager/static/images/store/20190111-002.jpg"},{"closeTime":"20:00","storeName":"测试003批发店铺","storeId":3,"openTime":"9:00","slogan":"机不可失，失不再来","logoUrl":"http://39.98.68.40:8080/RetailManager/static/images/store/20190111-003.jpg"}]}
     */

    private RecommendedshopBean Recommendedshop;

    public RecommendedshopBean getRecommendedshop() {
        return Recommendedshop;
    }

    public void setRecommendedshop(RecommendedshopBean Recommendedshop) {
        this.Recommendedshop = Recommendedshop;
    }

    public static class RecommendedshopBean {
        private List<CommodityBean> commodity;
        private List<StoreBean> store;

        public List<CommodityBean> getCommodity() {
            return commodity;
        }

        public void setCommodity(List<CommodityBean> commodity) {
            this.commodity = commodity;
        }

        public List<StoreBean> getStore() {
            return store;
        }

        public void setStore(List<StoreBean> store) {
            this.store = store;
        }

        public static class CommodityBean {
            /**
             * id : 1
             * Image : http://39.98.68.40:8080/RetailManager/static/images/commodity/20181228-008.jpg
             * storeId : 1
             * wholesalePrice : 56.2
             */

            private int id;
            private String Image;
            private int storeId;
            private double wholesalePrice;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage() {
                return Image;
            }

            public void setImage(String Image) {
                this.Image = Image;
            }

            public int getStoreId() {
                return storeId;
            }

            public void setStoreId(int storeId) {
                this.storeId = storeId;
            }

            public double getWholesalePrice() {
                return wholesalePrice;
            }

            public void setWholesalePrice(double wholesalePrice) {
                this.wholesalePrice = wholesalePrice;
            }
        }

        public static class StoreBean {
            /**
             * closeTime : 20:00
             * storeName : 测试001批发店铺
             * storeId : 1
             * openTime : 9:00
             * slogan : 掌上生活，疯狂特惠
             * logoUrl : http://39.98.68.40:8080/RetailManager/static/images/store/20190111-001.jpg
             */

            private String closeTime;
            private String storeName;
            private int storeId;
            private String openTime;
            private String slogan;
            private String logoUrl;

            public String getCloseTime() {
                return closeTime;
            }

            public void setCloseTime(String closeTime) {
                this.closeTime = closeTime;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public int getStoreId() {
                return storeId;
            }

            public void setStoreId(int storeId) {
                this.storeId = storeId;
            }

            public String getOpenTime() {
                return openTime;
            }

            public void setOpenTime(String openTime) {
                this.openTime = openTime;
            }

            public String getSlogan() {
                return slogan;
            }

            public void setSlogan(String slogan) {
                this.slogan = slogan;
            }

            public String getLogoUrl() {
                return logoUrl;
            }

            public void setLogoUrl(String logoUrl) {
                this.logoUrl = logoUrl;
            }
        }
    }
}
