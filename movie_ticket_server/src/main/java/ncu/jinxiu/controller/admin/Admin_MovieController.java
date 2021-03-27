package ncu.jinxiu.controller.admin;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import ncu.jinxiu.config.util.PageBean;
import ncu.jinxiu.config.util.Result;
import ncu.jinxiu.entity.Movie;
import ncu.jinxiu.service.FileService;
import ncu.jinxiu.service.MoviceService;
import ncu.jinxiu.service.QiNiuYunUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/movie")
public class Admin_MovieController {
    private final static Logger logger = LoggerFactory.getLogger(Admin_MovieController.class);

    @Autowired
    private MoviceService moviceService;
    @Autowired
    private FileService fileService;
    @Autowired
    private QiNiuYunUtils qiNiuYunUtils;

    @PostMapping("/addMoive")
    @RequiresPermissions("电影管理")
    public Result addMovie(@RequestBody String movie){
        String s = StringEscapeUtils.unescapeJava(movie);
//        JSONObject object = JSONObject.fromObject(s);
//        Movie movie1 = (Movie) JSONObject.toBean(object.getJSONObject("movie"),Movie.class);
        Movie movie1 = JSONObject.parseObject(s).getJSONObject("movie").toJavaObject(Movie.class);
        StringBuffer stringBuffer = new StringBuffer();
        for(String ss : movie1.getPhotosList()){
            stringBuffer.append(ss+",");
        }
        movie1.setPhotos(stringBuffer.substring(0,stringBuffer.length()-1));
        //修改
        if(movie1.getId()!=null){
            moviceService.update(movie1);
        }else{
            //添加
            Movie mv = moviceService.getMovieByName(movie1.getNm());
            if(mv!=null)
                return new Result(Result.ERROR,"该电影已存在");
            moviceService.insertMovie(movie1);
        }
        return new Result(movie);
    }

    @PostMapping("/deleteMovie")
    @RequiresPermissions("电影管理")
    public Result deleteMovie(@RequestBody HashMap<String, String> map){
        Integer movieId = Integer.parseInt(map.get("movieId"));
        moviceService.deleteById(movieId);
        return new Result(movieId);
    }

    @GetMapping("/getMovies")
    @RequiresPermissions("电影管理")
    public Result getMovies(@RequestParam("pageNum") Integer pageNum,
                           @RequestParam("limit") Integer limit,
                           @RequestParam(value = "keyword",required = false) String keyword){
        PageBean<Movie> moviePageBean = moviceService.getMovies(pageNum,limit,keyword);
        return new Result(moviePageBean);
    }

    @PostMapping("/upLoadFile")
    @RequiresPermissions("上传文件")
    public Result upLoadFile(@RequestParam(value = "img",required = false) MultipartFile img,
                             @RequestParam(value = "videoImg",required = false) MultipartFile videoImg,
                             @RequestParam(value = "video",required = false) MultipartFile video){
        Map<String,String> res = new HashMap<>();

        try {
            if(img!=null){
    //            String fileNameImg = fileService.storeFile(img);
                String fileNameImg = qiNiuYunUtils.uploadFile(img.getBytes(), img.getOriginalFilename());
                if(StringUtils.isEmpty(fileNameImg)){
                    return new Result(new Exception("七牛云上传失败"));
                }
                res.put("img",fileNameImg);
            }
            if(videoImg!=null){
    //            String fileNameVideoImg = fileService.storeFile(videoImg);
                String fileNameVideoImg = qiNiuYunUtils.uploadFile(videoImg.getBytes(),videoImg.getOriginalFilename());
                if(StringUtils.isEmpty(fileNameVideoImg)){
                    return new Result(new Exception("七牛云上传失败"));
                }
                res.put("videoImg",fileNameVideoImg);
            }
            if(video!=null){
    //            String fileNameVideo = fileService.storeFile(video);
                String fileNameVideo = qiNiuYunUtils.uploadFile(video.getBytes(),video.getOriginalFilename());
                if(StringUtils.isEmpty(fileNameVideo)){
                    return new Result(new Exception("七牛云上传失败"));
                }
                res.put("video",fileNameVideo);
            }
        } catch (IOException e) {
            return new Result(e);
        }
        return new Result(res);
    }
}
