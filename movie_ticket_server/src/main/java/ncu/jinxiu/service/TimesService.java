package ncu.jinxiu.service;

import ncu.jinxiu.mapper.DaysMapper;
import ncu.jinxiu.mapper.TimesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimesService {
    @Autowired
    private TimesMapper timesMapper;
    @Autowired
    private DaysMapper daysMapper;

    public void deleteMovieSchedule(Integer timesId,Integer daysId) {
        timesMapper.deleteById(timesId);
        if(daysMapper.isHashTimes(daysId)==0){
            daysMapper.deleteById(daysId);
        }
    }
}
