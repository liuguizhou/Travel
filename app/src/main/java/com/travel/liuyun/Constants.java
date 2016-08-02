package com.travel.liuyun;

/**
 * Created by liuguizhou on 2016/7/26.
 */
public class Constants {

    public static final int NONE = 0;
    public static final int PHOTOHRAPH = 1;// 拍照
    public static final int PHOTOZOOM = 2; // 缩放
    public static final int PHOTORESOULT = 3;// 结果
    public static final int SCALE = 5;// 缩小倍数
    public static final String IMAGE_UNSPECIFIED = "image/*";

    private static final String SERVER_URL = "http://api.7mlzg.com/Business/";
    public static final String MODIFY_USERINFO_MSG = SERVER_URL + "ModifyGuestInfo";
    public static final String MODIFY_USER_INFO = SERVER_URL + "ModifyGuestInfo";
    public static final String UPLOAD_PICTURE = "http://api.7mlzg.com/Common/UploadPicture";
}
