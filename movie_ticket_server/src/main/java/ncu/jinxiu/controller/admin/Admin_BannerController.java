package ncu.jinxiu.controller.admin;

import ncu.jinxiu.config.util.PageBean;
import ncu.jinxiu.config.util.Result;
import ncu.jinxiu.entity.Banner;
import ncu.jinxiu.service.BannerService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/admin/banner")
@RequiresPermissions("广告管理")
public class Admin_BannerController {

    @Autowired
    private BannerService bannerService;

    @GetMapping("/getBanner")
    public Result getBanner(@RequestParam("pageNum") Integer pageNum,
                            @RequestParam("limit") Integer limit,
                            @RequestParam(value = "keyword",required = false) String keyword){
        PageBean<Banner> bannerPageBean = bannerService.getBanner(pageNum,limit,keyword);
        return new Result(bannerPageBean);
    }

    @PostMapping("/addBanner")
    public Result addBanner(@RequestBody String banner) {
        String s = StringEscapeUtils.unescapeJava(banner);
        JSONObject object = JSONObject.fromObject(s);
        Banner banner1 = (Banner) JSONObject.toBean(object.getJSONObject("banner"), Banner.class);
        if(banner1.getId()!=null){
            bannerService.update(banner1);
        }else{
            //添加
            bannerService.insert(banner1);
        }
        return new Result(banner1);
    }

    @PostMapping("/deleteBanner")
    public Result deleteBanner(@RequestBody HashMap<String,Integer> map){
        Integer bannerId = map.get("bannerId");
        bannerService.deleteBanner(bannerId);
        return new Result(bannerId);
    }
}
