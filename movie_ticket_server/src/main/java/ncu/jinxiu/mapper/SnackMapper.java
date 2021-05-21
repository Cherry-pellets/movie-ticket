package ncu.jinxiu.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import ncu.jinxiu.entity.Snack;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SnackMapper extends BaseMapper<Snack> {

    @Select("select * from t_snack")
    List<Snack> selectAll();

    @Select("<script>" +
            "select * from t_snack where 1=1 " +
            "<if test='cinemaId!=null'>" +
            "and cinema_id=#{cinemaId} " +
            "</if>" +
            "and (first_title like '%${keyword}%' or second_title like '%${keyword}%') order by cur_number desc" +
            "</script>")
    List<Snack> getSnack(String keyword, Integer cinemaId);

    @Select("select * from t_snack where first_title=#{firstTitle}")
    Snack getSnackByName(String firstTitle);

    @Select("select * from t_snack where cinema_id=#{cinemaId}")
    List<Snack> selectByCinemaId(Integer cinemaId);

    @Select("<script>" +
            "select s.*,c.nm as cinemaNm from t_snack s left join t_cinema c on s.cinema_id=c.id" +
            " where 1=1 " +
            "and s.cinema_id in (" +
            "<foreach collection=\"cinemaId\" item=\"items\"  separator=\",\">" +
            "       #{items} " +
            " </foreach>)"+

            " order by s.cur_number desc" +
            "</script>")
    List<Snack> getSnackByCinemaId(@Param("cinemaId") List<Integer> cinemaId);
}
