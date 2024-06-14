package com.qiqi.netnest.Service.Impl;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.COSObjectInputStream;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.utils.IOUtils;
import com.qiqi.netnest.Utils.CosClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class WxCosServiceImpl {

    @Autowired
    private CosClientUtil cosClientUtil;

    public HashMap<String, String> getObject() {
//        COSClient cosClient = cosClientUtil.getCosClient();
        List<String> objects = cosClientUtil.getObjects();
        HashMap<String, String> map = new HashMap<>();
        String bucketName = "qiqi-1306841265";
        for (String key:objects
             ) {
            String url="https://"+bucketName+".cos.ap-beijing.myqcloud.com/" +key;
            map.put(key,url);
        }
//        // 对象键(Key)是对象在存储桶中的唯一标识。详情请参见 [对象键](https://cloud.tencent.com/document/product/436/13324)
//        String key = "NetWest/lizhuo.jpg";
//
//        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
//        COSObjectInputStream cosObjectInput = null;
//
//        // 判断COS是否有用户头像，若没有则返回默认头像
//        boolean objectExists = cosClient.doesObjectExist(bucketName, key);
//
//        try {
//            COSObject cosObject = cosClient.getObject(getObjectRequest);
//            cosObjectInput = cosObject.getObjectContent();
//        } catch (CosServiceException e) {
//            e.printStackTrace();
//        } catch (CosClientException e) {
//            e.printStackTrace();
//        }
//
//        // 获取字节流
//        byte[] bytes = null;
//        try {
//            bytes = IOUtils.toByteArray(cosObjectInput);
//            // 将byte[]写入响应体
//            response.getOutputStream().write(bytes);
//            response.getOutputStream().flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            // 用完流之后一定要调用 close()
//            try {
//                cosObjectInput.close();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            cosClient.shutdown();
//        }
        return map;
    }
}
