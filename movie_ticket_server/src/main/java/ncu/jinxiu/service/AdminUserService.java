package ncu.jinxiu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ncu.jinxiu.config.util.PageBean;
import ncu.jinxiu.entity.AdminUser;
import ncu.jinxiu.mapper.AdminRoleMapper;
import ncu.jinxiu.mapper.AdminUserMapper;
import ncu.jinxiu.mapper.CinemaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;
    @Autowired
    private CinemaMapper cinemaMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;

    public AdminUser getByUserName(String username) {
        return adminUserMapper.getByUserName(username);
    }

    public PageBean<AdminUser> getAdmins(Integer pageNum, Integer limit, String keyword) {
        PageHelper.startPage(pageNum,limit);
        List<AdminUser> adminUsers = adminUserMapper.getAdminsByKeword(keyword);
        PageInfo<AdminUser> pageInfo = new PageInfo<>(adminUsers);
        for(AdminUser adminUser : adminUsers){
            if(adminUser.getCineamId()!=null)
                adminUser.setCinemaNm(cinemaMapper.selectById(adminUser.getCineamId()).getNm());
            adminUser.setRoleId(adminRoleMapper.getByUserId(adminUser.getId()).getId());
        }
        PageBean<AdminUser> page = new PageBean<>();
        page.setPc(pageInfo.getPageNum());
        page.setPs(pageInfo.getPageSize());
        page.setTr(pageInfo.getPages());
        page.setBeanList(adminUsers);
        return page;
    }

    public void updateInfo(AdminUser user) {
        adminUserMapper.updateById(user);
    }

    public void insertInfo(AdminUser user) {
        adminUserMapper.insert(user);
    }
}
