package ncu.jinxiu.service;

import ncu.jinxiu.entity.Order;
import ncu.jinxiu.entity.Seat;
import ncu.jinxiu.entity.Times;
import ncu.jinxiu.mapper.OrderMapper;
import ncu.jinxiu.mapper.SeatMapper;
import ncu.jinxiu.mapper.TimesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class SeatService {
    @Autowired
    private SeatMapper seatMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private TimesMapper timesMapper;


    public List<Seat> getSeatByHallId(Integer hallId, Integer timesId) {
        List<Seat> seats = seatMapper.getSeatByHallId(hallId);
        Times times = timesMapper.selectById(timesId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String date = simpleDateFormat.format(times.getStartTime());
        List<Order> orders = orderMapper.getByTimesId(timesId,date);
        String tmp = "";
        for(Order order : orders){
            tmp += order.getDescribe();
        }
        for(Seat seat : seats){
            if(tmp.contains(seat.getyCoord() + "排" + seat.getxCoord() + "座"))
                seat.setStatus("booked");
        }
        return  seats;
    }

    public void update(Seat s) {
        seatMapper.updateById(s);
    }

    public List<Seat> getSeats(Integer hallId) {
        return seatMapper.getSeatByHallId(hallId);
    }

    public void commitSeat(Integer hallId, List<Seat> newSeatList, List<Seat> changeSeatList) throws Exception {
        //座位据库中的座位list
        List<Seat> seats = seatMapper.getSeatByHallId(hallId);
        //数据库中的座位 二维数组
        Seat[][] dateSeatsObject = new Seat[15][15];
        for(Seat s : seats) {
            dateSeatsObject[s.getyCoord()-1][s.getxCoord()-1] = s;
        }
        //改变后的座位情况 二维数组
        Seat[][] finalSeatsObject = new Seat[15][15];
        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 15; x++) {
                Seat seat = dateSeatsObject[y][x];
                if(seat!=null){
                    finalSeatsObject[y][x]=(Seat)seat.clone();
                }
            }
        }
        //改变后的座位情况--新增座位的改变
        for(Seat s : newSeatList){
            int x = s.getxCoord();
            int y = s.getyCoord();
            s.setType("danren");
            s.setHallId(hallId);
            s.setStatus("ok");
            s.setxCoord(x);
            s.setyCoord(y);
            finalSeatsObject[y-1][x-1] = s;
        }
        //改变后的座位情况--变更座位的改变
        for(Seat s : changeSeatList){
            Seat seat = finalSeatsObject[s.getyCoord() - 1][s.getxCoord() - 1];
            if(null==seat){
                throw new Exception("数据存在错误，变更的数据并不存在");
            }
            if("danren".equals(seat.getType())){
                seat.setType("road");
            }else{
                seat.setType("danren");
            }
            finalSeatsObject[s.getyCoord() - 1][s.getxCoord() - 1]=seat;
        }

        //获取座位的最小矩形数组<坐标>--开始
        int x_max=0;
        int x_min=14;
        int y_max=0;
        int y_min=14;

        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 15; x++) {
                Seat seat = finalSeatsObject[y][x];
                if(seat!=null){

                    if("danren".equals(seat.getType())){
                        //存在座位时
                        if(x>=x_max)x_max=x;
                        if(x<=x_min)x_min = x;
                        if(y>=y_max)y_max = y;
                        if(y<=y_min)y_min = y;

                    }
                }
            }
        }
        System.out.println("x_min"+x_min);
        System.out.println("x_max"+x_max);
        System.out.println("y_min"+y_min);
        System.out.println("y_max"+y_max);
        //获取座位的最小矩形数组<坐标>--结束


        //对finalSeatsObject进行多删，少补后得到最小矩形座位。
        System.out.println("补全后数据：");
        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 15; x++) {
                Seat seat = finalSeatsObject[y][x];
                if(x>x_max||x<x_min||y>y_max||y<y_min){//多删
                    finalSeatsObject[y][x]=null;
                }else{//少补
                    if(seat!=null){//已存在座位，不处理

                    }else{//不存在座位，改为road座位；
                        Seat newSet = new Seat();
                        newSet.setType("road");
                        newSet.setHallId(hallId);
                        newSet.setxCoord(x+1);
                        newSet.setyCoord(y+1);
                        newSet.setStatus("ok");
                        finalSeatsObject[y][x]=newSet;
                    }
                }
                System.out.print(null==finalSeatsObject[y][x]?"空  ":finalSeatsObject[y][x].getType()+"  ");
            }
            System.out.println("");
        }

        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 15; x++) {
                Seat finalSeat = finalSeatsObject[y][x];
                Seat dateSeat = dateSeatsObject[y][x];
                if(finalSeat==null){
                    if(dateSeat==null){
                        //座位为空，不处理
                    }else{
                        //删除数据库中已有座位
                        seatMapper.deleteById(dateSeat);
                    }
                }  else{
                    if(dateSeat==null){
                        //新增座位
                        seatMapper.insert(finalSeat);
                    }else{
                        //修改座位
                        seatMapper.updateById(finalSeat);
                    }
                }
            }
        }


//        for(Seat s : changeSeatList){
//            Seat tmp = dateSeatsObject[s.getyCoord()-1][s.getxCoord()-1];//待修改，看看前段传的是什么下标
//            if(tmp.getType().equals("danren")){
//                if(dateSeatsObject[s.getyCoord()-1][s.getxCoord()]!=null
//                        ||dateSeatsObject[s.getyCoord()][s.getxCoord()-1]!=null
//                        ||dateSeatsObject[s.getyCoord()+1][s.getxCoord()]!=null
//                        ||dateSeatsObject[s.getyCoord()][s.getxCoord()+1]!=null){
//                    tmp.setType("road");
//                    seatMapper.updateById(tmp);
//                }else{
//                    seatMapper.deleteById(tmp);
//                }
//            }else{
//                tmp.setType("danren");
//                seatMapper.updateById(tmp);
//            }
//        }
//        //新的座位
//        for(Seat s : newSeatList){
//            int x = s.getxCoord();//前段传的是座位号，直接使用
//            int y = s.getyCoord();//前段传的是座位号，直接使用
////            if(x-2>=0 && seatInfo[y][x-1]==null && seatInfo[y][x-2]!=null){
////                Seat seat = new Seat();
////                seat.setType("road");
////                seat.setHallId(hallId);
////                seat.setxCoord(x-1);
////                seat.setyCoord(y);
////                seat.setStatus("ok");
////                seatMapper.insert(seat);
////            }
////            if(x+2<15 && seatInfo[y][x+1]==null && seatInfo[y][x+2]!=null){
////                Seat seat = new Seat();
////                seat.setType("road");
////                seat.setHallId(hallId);
////                seat.setxCoord(x+1);
////                seat.setyCoord(y);
////                seat.setStatus("ok");
////                seatMapper.insert(seat);
////            }
////            if(y-2>=0 && seatInfo[y-1][x]==null&& seatInfo[y-2][x]!=null){
////                Seat seat = new Seat();
////                seat.setType("road");
////                seat.setHallId(hallId);
////                seat.setxCoord(x);
////                seat.setyCoord(y-1);
////                seat.setStatus("ok");
////                seatMapper.insert(seat);
////            }
////            if(y+2<15 && seatInfo[y+1][x]==null && seatInfo[y+2][x]!=null){
////                Seat seat = new Seat();
////                seat.setType("road");
////                seat.setHallId(hallId);
////                seat.setxCoord(x);
////                seat.setyCoord(y+1);
////                seat.setStatus("ok");
////                seatMapper.insert(seat);
////            }
//            s.setType("danren");
//            s.setHallId(hallId);
//            s.setStatus("ok");
//            s.setxCoord(x);
//            s.setyCoord(y);
//            seatMapper.insert(s);
//            dateSeatsObject[y-1][x-1] = s;
//        }
    }
}
