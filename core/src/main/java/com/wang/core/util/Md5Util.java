package com.wang.core.util;

import java.security.MessageDigest;
import java.util.Random;

public class Md5Util {
    //公盐
    private static final String PUBLIC_SALT = "zlglz&1" ;
    //公盐
    private static final String CUSTOMER_PUBLIC_SALT = "zlglz&1" ;
    //十六进制下数字到字符的映射数组
    private final static String[] hexDigits = {"0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 用户端app密码加密，盐值为 ：私盐+公盐
     * @param  password 密码
     * @param  salt 私盐
     * @return  MD5加密字符串
     */
    public static String encryptCustomPassword(String password,String salt){
        return encodeByMD5(password+salt+CUSTOMER_PUBLIC_SALT).toLowerCase();
    }

    /**
     * 密码加密，盐值为 ：私盐+公盐
     * @param  password 密码
     * @param  salt 私盐
     * @return  MD5加密字符串
     */
    public static String encryptPassword(String password,String salt){
        return encodeByMD5(password+salt+PUBLIC_SALT).toLowerCase();
    }

    /**
     * md5加密算法
     * @param  originString
     * @return
     */
    private static String encodeByMD5(String originString){
        if (originString != null){
            try{
                //创建具有指定算法名称的信息摘要
                MessageDigest md = MessageDigest.getInstance("MD5");
                //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
                byte[] results = md.digest(originString.getBytes());
                //将得到的字节数组变成字符串返回
                String resultString = byteArrayToHexString(results);
                return resultString.toUpperCase();
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 转换字节数组为十六进制字符串
     * @param     字节数组
     * @return    十六进制字符串
     */
    private static String byteArrayToHexString(byte[] b){
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++){
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /** 将一个字节转化成十六进制形式的字符串     */
    private static String byteToHexString(byte b){
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * 生成6位随机字母数字
     * @return
     */
    public static String getStringRandom6() {

        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for(int i = 0; i < 6; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
    
    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultString;
    }

    public static void main(String[] args) {
        for(int i=0;i<100;i++)
        {
            System.out.println(getStringRandom6());
        }


        System.out.println(encryptPassword("123456","dhhfWU"));
    }
}
