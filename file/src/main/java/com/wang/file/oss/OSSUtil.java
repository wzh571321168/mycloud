package com.wang.file.oss;


import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.net.URL;
import java.util.*;

import static com.wang.file.oss.OssConfig.*;

/**
 * oss相关操作的工具类
 */
@Component
public class OSSUtil {
    private static Logger logger = LoggerFactory.getLogger(OSSUtil.class);


    /**
     * 单个文件
     * 根据所给文件的OSS地址，获取相应的url地址
     *
     * @param key
     * @param time url超时时间
     *
     * @return url String
     */
    public static String getImageUrl(String key, long time) {
        if (StringUtils.isEmpty(key)) {
            return "";
        }
        OSSClient client = new OSSClient(endpoint, accessId, accessKey);
        URL url = client.generatePresignedUrl(bucket, key, new Date(new Date().getTime() + time));
        client.shutdown();
        return url.toString();
    }

    /**
     * 单个文件
     * 根据所给文件的OSS地址，获取相应的url地址
     *
     * @param key
     * @param date url超时时间
     *
     * @return url String
     */
    public static String getImageUrl(String key, Date date) {
        if (key == null || key.isEmpty()) {
            return "";
        }
        OSSClient client = new OSSClient(endpoint, accessId, accessKey);
        URL url = client.generatePresignedUrl(bucket, key, date);
        client.shutdown();
        return url.toString();
    }

    /**
     * 单个文件
     * 根据所给文件的OSS地址，获取相应的url地址,时间为一周
     *
     * @param key
     * @return url String
     */
    public static String getImageUrlStillWeek(String key) {
        return getImageUrl(key, 3600 * 1000 * 24 * 7);
    }

    /**
     * 多个文件
     * 根据所给文件的OSS地址，获取相应的url地址
     * 过期时间为一周
     *
     * @return urlList
     */
    public static List<String> getImageUrls(List<String> list) {
        OSSClient client = new OSSClient(endpoint, accessId, accessKey);
        List<String> urlList = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null || list.get(i).isEmpty()) {
                urlList.add("");
            } else {
                URL url = client.generatePresignedUrl(bucket, list.get(i), new Date(new Date().getTime() + 3600 * 1000 * 24 * 7));
                urlList.add(url.toString());
            }
        }
        client.shutdown();
        return urlList;
    }

    /**
     * 多个文件
     * 根据所给文件的OSS地址，获取相应的url地址
     *
     * @param list
     * @param date 过期时间
     * @return urlList
     */
    public static List<String> getImageUrls(List<String> list, Date date) {
        OSSClient client = new OSSClient(endpoint, accessId, accessKey);
        List<String> urlList = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null || list.get(i).isEmpty()) {
                urlList.add("");
            } else {
                URL url = client.generatePresignedUrl(bucket, list.get(i), date);
                urlList.add(url.toString());
            }
        }
        client.shutdown();
        return urlList;
    }

    /**
     * 多个文件
     * 根据所给文件的OSS地址，获取相应的url地址
     * @param map
     * @param date 超时时间
     *
     * @return urlMap
     */
    public static Map<String, String> getImageUrls(Map<String, String> map, Date date) {
        OSSClient client = new OSSClient(endpoint, accessId, accessKey);
        Map<String, String> urlMap = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue() == null || entry.getValue().isEmpty()) {
                urlMap.put(entry.getKey(), "");
            } else {
                URL url = client.generatePresignedUrl(bucket, entry.getValue(), date);
                urlMap.put(entry.getKey(), url.toString());
            }
        }
        client.shutdown();
        return urlMap;
    }

    /**
     * 上传文件
     * 上传文件到OSS指定位置
     *
     * @return
     */
    public static String uploadFile(Long length, String fileName, InputStream content, String prefix) {
        //上传文件名采用当前时间戳_随机数
        Random r = new Random();
        Integer lastIndex = fileName.lastIndexOf(".");
        String filepattern = fileName.substring(lastIndex);
        String datenow = prefix + System.currentTimeMillis() + "_" + r.nextInt(10) + filepattern;

        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(length);
        OSSClient client = new OSSClient(endpoint, accessId, accessKey);
        client.putObject(bucket, datenow, content, meta);
        //logger.info("===========OOSUtil.uploadFile========"+url);
        client.shutdown();
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        String url = client.generatePresignedUrl(bucket, datenow, expiration).toString();
        logger.info(url);
        return url;
    }

    /**
     * 上传文件
     * 上传文件到OSS指定位置
     *
     * @return
     */
    public static String uploadFile(Long length, String fileName, InputStream content) {
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(length);
        OSSClient client = new OSSClient(endpoint, accessId, accessKey);
        client.putObject(bucket, fileName, content, meta);
        client.shutdown();
        return fileName;
    }

    /**
     * 上传文件
     * 上传文件到OSS指定位置
     *
     */
    public static String uploadFile(String fileName, File file) {
        try{
            InputStream content = new FileInputStream(file);
            String url = uploadFile(file.length(), fileName, content, "default/");
            return url;
        }catch(Exception e) {
            logger.error("上传文件失败", e);
        }
        return null;
    }

    /**
     * 上传文件
     * 上传文件到OSS指定位置
     *
     */
    public static String uploadFile(String fileName, File file, String path) {
        try{
            if(StringUtils.isEmpty(path)) {
                path = "default/";
            }
            InputStream content = new FileInputStream(file);
            String url = uploadFile(file.length(), fileName, content, path);
            return url;
        }catch(Exception e) {
            logger.error("上传文件失败", e);
        }
        return null;
    }

    /**
     * 上传文件
     * 上传文件到OSS指定位置
     *
     */
    public static String uploadFile(MultipartFile file) {
        try {
            String url = uploadFile(file.getSize(), file.getOriginalFilename(), file.getInputStream(), "default/");
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            logger.debug("名为{}的文件上传出错", file.getOriginalFilename());
        }
        return null;
    }

    /**
     * 上传文件
     * 上传文件到OSS指定位置
     *
     */
    public static String uploadFile(MultipartFile file, String path) {
        try {
            if(StringUtils.isBlank(path)) {
                path = "default/";
            }
            String url = uploadFile(file.getSize(), file.getOriginalFilename(), file.getInputStream(), path);
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            logger.debug("名为{}的文件上传出错", file.getOriginalFilename());
        }
        return null;
    }

    /**
     * 上传文件
     * 上传文件到OSS指定位置
     *
     * @return 不带域名url
     */
    public static List<String> uploadFile(MultipartFile[] files, String path) {
        if(files == null || files.length == 0) {
            logger.error("不可上传空文件");
            return null;
        }
        if(StringUtils.isBlank(path)) {
            path = "default/";
        }
        List<String> urls = new ArrayList<String>();
        for(int i = 0; i< files.length; i++) {
            try {
                String url = uploadFile(files[i].getSize(), files[i].getOriginalFilename(), files[i].getInputStream(), path);
                if(StringUtils.isNotEmpty(url)) {
                    urls.add(new URL(url).getPath());
                }
            } catch (IOException e) {
                logger.debug("名为" + files[i].getOriginalFilename() + "的文件上传出错", e);
            }
        }
        return urls;
    }

    /**
     * url添加域名
     * @param urls
     * @return
     */
    public static List<String> addUrlRealName(List<String> urls){
        if(urls == null || urls.size() == 0) {
            return urls;
        }
        List<String> newUrl = new ArrayList<String>();
        for(String s : urls) {
            if(s.startsWith("http://")) {
                newUrl.add(s);
            } else {
                newUrl.add("http://" + bucket + "." + endpoint + s);
            }
        }
        return newUrl;
    }

    /**
     * url添加域名
     * @param urls
     * @return
     */
    public static String removeUrlRealName(String urls){
        if(org.apache.commons.lang3.StringUtils.isEmpty(urls)) {
            return urls;
        }
        if(urls.startsWith("http://")) {
            try{
                urls = new URL(urls).getPath();
                return urls;
            }catch(Exception e) {
                return null;
            }
        }
        return urls;

    }

    /**
     * url添加域名
     * @param urls
     * @return
     */
    public static String addUrlRealName(String urls){
        if(StringUtils.isEmpty(urls)) {
            return urls;
        }
        if(urls.startsWith("http://")) {
            return urls;
        }
        urls = "http://" + bucket + "." + endpoint + urls;
        return urls;
    }

    /**
     * 上传文件
     * 上传文件到OSS指定位置
     * 文件经过压缩后再上传
     *
     * @param file            上传的文件
     * @param path
     * @param width           宽
     * @param height          高
     * @param keepAspectRatio 是否保持宽高比例
     * @return
     */
  /*  public static String uploadFileWithCompress(MultipartFile file, String path, int width, int height, boolean keepAspectRatio) {
        BufferedImage bufImg;
        try {
            //把图片读入到内存中
            bufImg = ImageIO.read(file.getInputStream());
            //压缩代码
            //就按指定的大小、是否保持长宽高 进行缩放
            bufImg = Thumbnails.of(bufImg).size(width, height).keepAspectRatio(keepAspectRatio).asBufferedImage();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();//存储图片文件byte数组
            ImageOutputStream ios = ImageIO.createImageOutputStream(bos);
            String fileName = file.getOriginalFilename();
            Integer lastIndex = fileName.lastIndexOf(".");
            String filepattern = fileName.substring(lastIndex + 1);
            ImageIO.write(bufImg, filepattern, ios); //图片按原图格式写入到 ImageOutputStream

            InputStream input2 = new ByteArrayInputStream(bos.toByteArray());
            Integer a = input2.available();
            Long fileSize = a.longValue();
            return uploadFile(fileSize, fileName, input2, path);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.debug("名为{}的文件上传出错", file.getOriginalFilename());
        }
        return null;
    }*/
    
    
    /**
     * 上传图片并返回结果
     * @param file
     * @return
     */
    public static Map<String, Object> uploadImageResult(MultipartFile file){
    	Map<String, Object> map = new HashMap<>();
		String imageUrl = "";
		if (file != null) {
			imageUrl = uploadFile(file);
			imageUrl = imageUrl.substring(0,imageUrl.indexOf("?"));
			System.out.println();
			if (imageUrl != null && !imageUrl.equals("")) {
				map.put("imageUrl", imageUrl);
				map.put("message", "上传图片成功");
				map.put("code", 200);
			}else{
				map.put("message", "上传图片失败");
				map.put("code", 500);
			}
			
		}else {
			map.put("message", "上传图片失败");
			map.put("code", 500);
		}
		
		return map;
    }

    /**
     * base64转inputStream
     * @param base64string
     * @return
     */
    private static InputStream BaseToInputStream(String base64string){
        ByteArrayInputStream stream = null;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes1 = decoder.decodeBuffer(base64string);
            stream = new ByteArrayInputStream(bytes1);
        } catch (Exception e) {
            // TODO: handle exception
        } 
        return stream;
    }

    /**
     * 上传到OSS服务器 如果同名文件会覆盖服务器上的
     *
     * @param fileName 文件名称 包括后缀名
     * @param base64 base64字符串
     * @return 出错返回"" ,唯一MD5数字签名
     */
    public static String upload(String fileName, String base64) {
        String resultStr = "";
        ByteArrayInputStream stream = null;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes1 = decoder.decodeBuffer(base64);
            stream = new ByteArrayInputStream(bytes1);
            // 创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
//            objectMetadata.setContentLength(instream.available());
//            objectMetadata.setCacheControl("no-cache");
//            objectMetadata.setHeader("Pragma", "no-cache");
//            objectMetadata.setContentType(getContentType(fileName.substring(fileName.lastIndexOf("."))));
//            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            // 上传文件 (上传文件流的形式)
            OSSClient client = new OSSClient(endpoint, accessId, accessKey);
            PutObjectResult putObjectResult = client.putObject(bucket, fileName, stream, objectMetadata);
            client.shutdown();
            // 解析结果
            resultStr = putObjectResult.getETag();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultStr;

    }

}
