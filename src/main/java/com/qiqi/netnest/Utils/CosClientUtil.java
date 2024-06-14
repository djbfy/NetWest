package com.qiqi.netnest.Utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.Bucket;
import com.qcloud.cos.model.COSObjectSummary;
import com.qcloud.cos.model.ListObjectsRequest;
import com.qcloud.cos.model.ObjectListing;
import com.qcloud.cos.region.Region;
import com.qiqi.netnest.Pojo.CosConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CosClientUtil {

    @Resource
    CosConfig config;

    public COSClient getCosClient() {
        String secretId = config.getSecretId();
        String secretKey = config.getSecretKey();
        BasicCOSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region("ap-beijing");
        ClientConfig clientConfig = new ClientConfig(region);
        clientConfig.setHttpProtocol(HttpProtocol.https);
        return new COSClient(cred, clientConfig);
    }

    public void close() {
        this.getCosClient().shutdown();
    }

    public List<Bucket> getBucketList() {
        COSClient cosClient = this.getCosClient();
        List<Bucket> buckets = cosClient.listBuckets();
        cosClient.shutdown();
        return buckets;
    }

    public List<String> getObjects() {
        COSClient cosClient = this.getCosClient();
        // Bucket 的命名格式为 BucketName-APPID ，此处填写的存储桶名称必须为此格式
        String bucketName = "qiqi-1306841265";
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
        // 设置 bucket 名称
        listObjectsRequest.setBucketName(bucketName);
        // prefix 表示列出的 object 的 key 以 prefix 开始
        listObjectsRequest.setPrefix("NetWest/");
        // deliter 表示分隔符, 设置为/表示列出当前目录下的 object, 设置为空表示列出所有的 object
        listObjectsRequest.setDelimiter("/");
        // 设置最大遍历出多少个对象, 一次 listobject 最大支持1000
        listObjectsRequest.setMaxKeys(3);
        ObjectListing objectListing = null;
        ArrayList<String> list = new ArrayList<>();
        do {
            try {
                objectListing = cosClient.listObjects(listObjectsRequest);
            } catch (CosClientException e) {
                e.printStackTrace();
                return list;
            }
            // object summary 表示所有列出的 object 列表
            List<COSObjectSummary> cosObjectSummaries = objectListing.getObjectSummaries();
            for (COSObjectSummary cosObjectSummary : cosObjectSummaries) {
                // 文件的路径 key
                String key = cosObjectSummary.getKey();
                // 文件的 etag
                String etag = cosObjectSummary.getETag();
                // 文件的长度
                long fileSize = cosObjectSummary.getSize();
                // 文件的存储类型
                String storageClasses = cosObjectSummary.getStorageClass();
                if (!key.endsWith("/")) {
                    list.add(key);
                    log.info("key:{},etag:{},fileSize:{},class:{}", key, etag, fileSize, storageClasses);
                }
            }
            String nextMarker = objectListing.getNextMarker();
            listObjectsRequest.setMarker(nextMarker);
        } while (objectListing.isTruncated());
        cosClient.shutdown();
        return list;
    }
    //TODO 上传对象
}
