package ncu.jinxiu.config.es;

import ncu.jinxiu.entity.Cinema;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CinemaRepository extends ElasticsearchRepository<Cinema,Integer> {


}
