package ncu.jinxiu.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName("t_cinema_brand")
public class CinemaBrand extends Model<Movie> {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cinema_brand.id
     *
     * @mbg.generated Wed Feb 05 15:45:26 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cinema_brand.brand
     *
     * @mbg.generated Wed Feb 05 15:45:26 CST 2020
     */
    private String brand;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cinema_brand.id
     *
     * @return the value of t_cinema_brand.id
     *
     * @mbg.generated Wed Feb 05 15:45:26 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cinema_brand.id
     *
     * @param id the value for t_cinema_brand.id
     *
     * @mbg.generated Wed Feb 05 15:45:26 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cinema_brand.brand
     *
     * @return the value of t_cinema_brand.brand
     *
     * @mbg.generated Wed Feb 05 15:45:26 CST 2020
     */
    public String getBrand() {
        return brand;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cinema_brand.brand
     *
     * @param brand the value for t_cinema_brand.brand
     *
     * @mbg.generated Wed Feb 05 15:45:26 CST 2020
     */
    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}