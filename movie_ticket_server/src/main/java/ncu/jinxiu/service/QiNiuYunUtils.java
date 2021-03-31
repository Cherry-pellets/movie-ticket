package ncu.jinxiu.service;


import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QiNiuYunUtils {
    @Value("${qiniuyun.accessKey}")
    private String accessKey;

    @Value("${qiniuyun.secretKey}")
    private String secretKey;

    @Value("${qiniuyun.bucket}")
    private String bucket;

    @Value("${qiniuyun.prefix}")
    private String prefix;

    @Value("${qiniuyun.path}")
    private String path;

    /**
     *
     * @param uploadBytes 文件的字节流
     * @param fileName 上传后的文件名字
     * @return
     */
    public String uploadFile(byte[] uploadBytes,String fileName){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(uploadBytes, prefix+fileName, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                return path+"/"+prefix+fileName;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public String getHeadPortraitUrl(String fileName){
        if(StringUtils.isEmpty(fileName)){
            //返回默认头像地址：
            return path+"/"+prefix+"defaultPicture.jpg";
        }else{
            return path+"/"+prefix+fileName;
        }
    }
}
