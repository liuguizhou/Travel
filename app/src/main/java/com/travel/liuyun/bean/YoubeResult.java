package com.travel.liuyun.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by liuguizhou on 2016/8/3.
 */
public class YoubeResult {


    /**
     * status : 1
     * message : 查询成功！
     * data : {"ID":"6488","Pictures":"http://www.7mlzg.com/uploads/IDv_1470165557.png","HeadImg":"http://www.7mlzg.com/uploads/kha_1470165616.png","TrueName":"","Sex":"0","SexTitle":"å¥³","Mobile":"15256298062","Age":"2016","Degree":"","Hometown":"54","AreaID":"0","Nationals":"","WorkingTime":"","DriverLicense":"","Professional":"","IdCard":"","State":"0","StateTime":"2016/7/8","IdCardPicture":"","MyLable":[],"BusinessLabel":[{"id":"33","codename":"讲解","description":"可提供景点的讲解服务，但由此产生的门票、游览等费用需双方协商","picture":""}],"MyCustomLable":"","Introduce":"","MyRecommendedCode":"7bMFn","MyRecommendedCodeURL":"http://t.cn/Rt2X8Ls","MyRecommendedQRCode":"http://www.7mlzg.com/set_QRCode.aspx?txt=http://t.cn/Rt2X8Ls","MyRecommendedMsg":"我已成为妙游向导，为来当地的游客提供向导服务。一起加入吧，时间自由轻松，一天500轻松到手！","PermanentEWM":"https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQG/8ToAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xLy0wUTlVUzdtRVFSb1QxTzlXR3pZAAIEp0CIVwMEAAAAAA==","morepicture":[],"TouristsPhoto":[],"Services":[],"CarInfo":[]}
     */

    private int status;
    private String message;
    /**
     * ID : 6488
     * Pictures : http://www.7mlzg.com/uploads/IDv_1470165557.png
     * HeadImg : http://www.7mlzg.com/uploads/kha_1470165616.png
     * TrueName :
     * Sex : 0
     * SexTitle : å¥³
     * Mobile : 15256298062
     * Age : 2016
     * Degree :
     * Hometown : 54
     * AreaID : 0
     * Nationals :
     * WorkingTime :
     * DriverLicense :
     * Professional :
     * IdCard :
     * State : 0
     * StateTime : 2016/7/8
     * IdCardPicture :
     * MyLable : []
     * BusinessLabel : [{"id":"33","codename":"讲解","description":"可提供景点的讲解服务，但由此产生的门票、游览等费用需双方协商","picture":""}]
     * MyCustomLable :
     * Introduce :
     * MyRecommendedCode : 7bMFn
     * MyRecommendedCodeURL : http://t.cn/Rt2X8Ls
     * MyRecommendedQRCode : http://www.7mlzg.com/set_QRCode.aspx?txt=http://t.cn/Rt2X8Ls
     * MyRecommendedMsg : 我已成为妙游向导，为来当地的游客提供向导服务。一起加入吧，时间自由轻松，一天500轻松到手！
     * PermanentEWM : https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQG/8ToAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xLy0wUTlVUzdtRVFSb1QxTzlXR3pZAAIEp0CIVwMEAAAAAA==
     * morepicture : []
     * TouristsPhoto : []
     * Services : []
     * CarInfo : []
     */

    private DataBean data;

    public static YoubeResult objectFromData(String str) {

        return new Gson().fromJson(str, YoubeResult.class);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String ID;
        private String Pictures;
        private String HeadImg;
        private String TrueName;
        private String Sex;
        private String SexTitle;
        private String Mobile;
        private String Age;
        private String Degree;
        private String Hometown;
        private String AreaID;
        private String Nationals;
        private String WorkingTime;
        private String DriverLicense;
        private String Professional;
        private String IdCard;
        private String State;
        private String StateTime;
        private String IdCardPicture;
        private String MyCustomLable;
        private String Introduce;
        private String MyRecommendedCode;
        private String MyRecommendedCodeURL;
        private String MyRecommendedQRCode;
        private String MyRecommendedMsg;
        private String PermanentEWM;
        private List<?> MyLable;
        /**
         * id : 33
         * codename : 讲解
         * description : 可提供景点的讲解服务，但由此产生的门票、游览等费用需双方协商
         * picture :
         */

        private List<BusinessLabelBean> BusinessLabel;
        private List<?> morepicture;
        private List<?> TouristsPhoto;
        private List<?> Services;
        private List<?> CarInfo;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getPictures() {
            return Pictures;
        }

        public void setPictures(String Pictures) {
            this.Pictures = Pictures;
        }

        public String getHeadImg() {
            return HeadImg;
        }

        public void setHeadImg(String HeadImg) {
            this.HeadImg = HeadImg;
        }

        public String getTrueName() {
            return TrueName;
        }

        public void setTrueName(String TrueName) {
            this.TrueName = TrueName;
        }

        public String getSex() {
            return Sex;
        }

        public void setSex(String Sex) {
            this.Sex = Sex;
        }

        public String getSexTitle() {
            return SexTitle;
        }

        public void setSexTitle(String SexTitle) {
            this.SexTitle = SexTitle;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public String getAge() {
            return Age;
        }

        public void setAge(String Age) {
            this.Age = Age;
        }

        public String getDegree() {
            return Degree;
        }

        public void setDegree(String Degree) {
            this.Degree = Degree;
        }

        public String getHometown() {
            return Hometown;
        }

        public void setHometown(String Hometown) {
            this.Hometown = Hometown;
        }

        public String getAreaID() {
            return AreaID;
        }

        public void setAreaID(String AreaID) {
            this.AreaID = AreaID;
        }

        public String getNationals() {
            return Nationals;
        }

        public void setNationals(String Nationals) {
            this.Nationals = Nationals;
        }

        public String getWorkingTime() {
            return WorkingTime;
        }

        public void setWorkingTime(String WorkingTime) {
            this.WorkingTime = WorkingTime;
        }

        public String getDriverLicense() {
            return DriverLicense;
        }

        public void setDriverLicense(String DriverLicense) {
            this.DriverLicense = DriverLicense;
        }

        public String getProfessional() {
            return Professional;
        }

        public void setProfessional(String Professional) {
            this.Professional = Professional;
        }

        public String getIdCard() {
            return IdCard;
        }

        public void setIdCard(String IdCard) {
            this.IdCard = IdCard;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public String getStateTime() {
            return StateTime;
        }

        public void setStateTime(String StateTime) {
            this.StateTime = StateTime;
        }

        public String getIdCardPicture() {
            return IdCardPicture;
        }

        public void setIdCardPicture(String IdCardPicture) {
            this.IdCardPicture = IdCardPicture;
        }

        public String getMyCustomLable() {
            return MyCustomLable;
        }

        public void setMyCustomLable(String MyCustomLable) {
            this.MyCustomLable = MyCustomLable;
        }

        public String getIntroduce() {
            return Introduce;
        }

        public void setIntroduce(String Introduce) {
            this.Introduce = Introduce;
        }

        public String getMyRecommendedCode() {
            return MyRecommendedCode;
        }

        public void setMyRecommendedCode(String MyRecommendedCode) {
            this.MyRecommendedCode = MyRecommendedCode;
        }

        public String getMyRecommendedCodeURL() {
            return MyRecommendedCodeURL;
        }

        public void setMyRecommendedCodeURL(String MyRecommendedCodeURL) {
            this.MyRecommendedCodeURL = MyRecommendedCodeURL;
        }

        public String getMyRecommendedQRCode() {
            return MyRecommendedQRCode;
        }

        public void setMyRecommendedQRCode(String MyRecommendedQRCode) {
            this.MyRecommendedQRCode = MyRecommendedQRCode;
        }

        public String getMyRecommendedMsg() {
            return MyRecommendedMsg;
        }

        public void setMyRecommendedMsg(String MyRecommendedMsg) {
            this.MyRecommendedMsg = MyRecommendedMsg;
        }

        public String getPermanentEWM() {
            return PermanentEWM;
        }

        public void setPermanentEWM(String PermanentEWM) {
            this.PermanentEWM = PermanentEWM;
        }

        public List<?> getMyLable() {
            return MyLable;
        }

        public void setMyLable(List<?> MyLable) {
            this.MyLable = MyLable;
        }

        public List<BusinessLabelBean> getBusinessLabel() {
            return BusinessLabel;
        }

        public void setBusinessLabel(List<BusinessLabelBean> BusinessLabel) {
            this.BusinessLabel = BusinessLabel;
        }

        public List<?> getMorepicture() {
            return morepicture;
        }

        public void setMorepicture(List<?> morepicture) {
            this.morepicture = morepicture;
        }

        public List<?> getTouristsPhoto() {
            return TouristsPhoto;
        }

        public void setTouristsPhoto(List<?> TouristsPhoto) {
            this.TouristsPhoto = TouristsPhoto;
        }

        public List<?> getServices() {
            return Services;
        }

        public void setServices(List<?> Services) {
            this.Services = Services;
        }

        public List<?> getCarInfo() {
            return CarInfo;
        }

        public void setCarInfo(List<?> CarInfo) {
            this.CarInfo = CarInfo;
        }

        public static class BusinessLabelBean {
            private String id;
            private String codename;
            private String description;
            private String picture;

            public static BusinessLabelBean objectFromData(String str) {

                return new Gson().fromJson(str, BusinessLabelBean.class);
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCodename() {
                return codename;
            }

            public void setCodename(String codename) {
                this.codename = codename;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }
        }
    }
}
