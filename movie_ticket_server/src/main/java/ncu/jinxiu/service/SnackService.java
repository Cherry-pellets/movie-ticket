package ncu.jinxiu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import ncu.jinxiu.config.util.PageBean;
import ncu.jinxiu.entity.Cinema;
import ncu.jinxiu.entity.Snack;
import ncu.jinxiu.mapper.CinemaMapper;
import ncu.jinxiu.mapper.SnackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SnackService {

    @Autowired
    private SnackMapper snackMapper;
    @Autowired
    private CinemaMapper cinemaMapper;

    public List<Snack> getSnacks(Integer cinemaId) {
        return snackMapper.selectByCinemaId(cinemaId);
    }

    public PageBean<Snack> getSnack(Integer pageNum, Integer limit, String keyword, Integer cinemaId) {
        PageHelper.startPage(pageNum,limit);
        PageInfo pageInfo=null;
        PageBean<Snack> page = new PageBean<>();

        List<Snack> snackList = snackMapper.getSnack(keyword,cinemaId);
        for(Snack snack : snackList){
            snack.setCinemaNm(cinemaMapper.selectById(snack.getCinemaId()).getNm());
        }
        pageInfo= new PageInfo(snackList);
        page.setBeanList(snackList);
        if(snackList.size()==0){
            List<Cinema> cinemas = cinemaMapper.selectByNM(keyword);
            if (cinemas.size()>0) {
                List<Integer> collect = cinemas.stream().map(c -> {
                    return c.getId();
                }).collect(Collectors.toList());
                List<Snack> snackByCinemaId = snackMapper.getSnackByCinemaId(collect);

                pageInfo= new PageInfo(snackByCinemaId);
                page.setBeanList(snackByCinemaId);

            }
        }


        page.setPc(pageInfo.getPageNum());
        page.setTr(pageInfo.getPages());
        page.setPs(pageInfo.getPageSize());

        return page;
    }

    public void update(Snack snack1) {
        snackMapper.updateById(snack1);
    }

    public Snack getSnackByName(String firstTitle) {
        return snackMapper.getSnackByName(firstTitle);
    }

    public void insertSnack(Snack snack1) {
        snackMapper.insert(snack1);
    }

    public void deleteSnack(Integer snackId) {
        snackMapper.deleteById(snackId);
    }
}
