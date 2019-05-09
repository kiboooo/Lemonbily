package com.example.basemodule.utils;

import android.icu.text.SimpleDateFormat;

import com.bumptech.glide.request.RequestOptions;
import com.example.basemodule.R;

import org.apaches.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 判断是否是手机号
     * 目前已支持的匹配号段

     中国电信号段


     133、149、153、173、177、180、181、189、199

     中国联通号段

     130、131、132、145、155、156、166、175、176、185、186
     中国移动号段
     134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188、198


     其他号段
     14号段以前为上网卡专属号段，如中国联通的是145，中国移动的是147等等。
     虚拟运营商
     电信：1700、1701、1702
     移动：1703、1705、1706
     联通：1704、1707、1708、1709、171

     */

    public static boolean isMobile(String mobile) {
        String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }


    public static boolean isTextEmpty(String s) {
        return s == null || s.equals("");
    }

    public static String passwordEncode(String s) {
        return DigestUtils.md5Hex(s);
    }

    public static RequestOptions avatarRequestOption(){
        return new RequestOptions()
                .circleCrop()
                .placeholder(R.drawable.base_avatar_default)
                .error(R.drawable.base_avatar_default)
                .fallback(R.drawable.base_avatar_default_mind);
    }

    public static RequestOptions imageRequestOption(){
        return new RequestOptions()
                .placeholder(R.drawable.base_image_loading_error_state)
                .error(R.drawable.base_image_loading_error_state)
                .fallback(R.drawable.base_image_loading_error_state);
    }

    public static List<String> initBannerList(){
        List<String> list = new ArrayList<>();
        list.add("/lemonbily/picture/music_05.jpg");
        list.add("/lemonbily/picture/movie_01.jpg");
        list.add("/lemonbily/picture/music_03.jpg");
        list.add("/lemonbily/picture/music_04.jpg");
        list.add("/lemonbily/picture/music_02.jpg");
        return list;
    }


}
