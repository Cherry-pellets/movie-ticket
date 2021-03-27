package ncu.jinxiu.service;

import ncu.jinxiu.entity.HallType;
import ncu.jinxiu.mapper.HallTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallTypeService {
    @Autowired
    private HallTypeMapper hallTypeMapper;

    public List<HallType> getHallTypes() {
        return hallTypeMapper.getAll();
    }
}
