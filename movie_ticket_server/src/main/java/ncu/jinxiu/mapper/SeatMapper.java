package ncu.jinxiu.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import ncu.jinxiu.entity.Seat;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatMapper extends BaseMapper<Seat> {
    @Select("select * from t_seat where hall_id=#{hallId}")
    List<Seat> getSeatByHallId(Integer hallId);

}
