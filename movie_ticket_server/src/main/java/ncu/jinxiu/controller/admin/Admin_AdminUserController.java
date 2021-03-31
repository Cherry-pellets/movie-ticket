package ncu.jinxiu.controller.admin;

import ncu.jinxiu.config.util.PageBean;
import ncu.jinxiu.config.util.Result;
import ncu.jinxiu.entity.AdminRole;
import ncu.jinxiu.entity.AdminUser;
import ncu.jinxiu.entity.Cinema;
import ncu.jinxiu.entity.Vo.AdminOptions;
import ncu.jinxiu.service.AdminRoleService;
import ncu.jinxiu.service.AdminUserService;
import ncu.jinxiu.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/admin/auser")
public class Admin_AdminUserController {

    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private CinemaService cinemaService;
    @Autowired
    private AdminRoleService adminRoleService;

    @GetMapping("/getAdmins")
    public Result getAdmins(@RequestParam("pageNum") Integer pageNum,
                            @RequestParam("limit") Integer limit,
                            @RequestParam(value = "keyword",required = false) String keyword){
        PageBean<AdminUser> adminUserPageBean = adminUserService.getAdmins(pageNum,limit,keyword);
        return new Result(adminUserPageBean);
    }

    @GetMapping("/getOptions")
    public Result getOptions(){
        List<AdminRole> res1 = adminRoleService.getRoles();
        List<Cinema> res2 = cinemaService.getAllCinema();
        AdminOptions adminOptions = new AdminOptions();
        adminOptions.setRoles(res1);
        adminOptions.setCinemas(res2);
        return new Result(adminOptions);
    }

    @PostMapping("/updateInfo")
    public Result updateInfo(@RequestBody HashMap<String,String> map){
        Integer userId = null;
        if(map.get("userId")!=null)
            userId = Integer.parseInt(map.get("userId"));
        String name = map.get("name");
        String username = map.get("username");
        String password = map.get("password");
        Integer roleId = Integer.parseInt(map.get("roleId"));
        Integer cinemaId = Integer.parseInt(map.get("cineamId"));
        AdminUser user = new AdminUser();
        user.setId(userId);
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setCineamId(cinemaId);
        if(userId!=null)
            adminUserService.updateInfo(user);
        else {
            user.setAvatar("https://mokespace.cn/kodexplorer/index.php?share/fileProxy&user=1&sid=MEyKNFbx");
            adminUserService.insertInfo(user);
            adminRoleService.insertInfo(user.getId(),roleId);
        }
        return new Result(user);
    }
}
