package ncu.jinxiu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@MapperScan("ncu.jinxiu.mapper")
//@EnableScheduling

public class MovieTicketApplication {
    public static void main(String[] args) {
//        System.setProperty("es.set.netty.runtime.available.processors","false");
        SpringApplication.run(MovieTicketApplication.class, args);
    }
}
