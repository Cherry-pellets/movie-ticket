package ncu.jinxiu.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import ncu.jinxiu.entity.CinemaBrand;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaBrandMapper extends BaseMapper<CinemaBrand> {

    @Select("select * from t_cinema_brand where id=#{brandId}")
    CinemaBrand getById(Integer brandId);

    @Select("select * from t_cinema_brand")
    List<CinemaBrand> getAll();
}
