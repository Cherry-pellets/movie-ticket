package ncu.jinxiu.entity.Vo;

import ncu.jinxiu.entity.CinemaBrand;
import ncu.jinxiu.entity.HallType;

import java.util.List;

public class CinemaFilter {
    private List<String> district;
    private List<CinemaBrand> cinemaBrandList;
    private List<HallType> hallTypeList;

    public List<String> getDistrict() {
        return district;
    }

    public void setDistrict(List<String> district) {
        this.district = district;
    }

    public List<CinemaBrand> getCinemaBrandList() {
        return cinemaBrandList;
    }

    public void setCinemaBrandList(List<CinemaBrand> cinemaBrandList) {
        this.cinemaBrandList = cinemaBrandList;
    }

    public List<HallType> getHallTypeList() {
        return hallTypeList;
    }

    public void setHallTypeList(List<HallType> hallTypeList) {
        this.hallTypeList = hallTypeList;
    }
}
