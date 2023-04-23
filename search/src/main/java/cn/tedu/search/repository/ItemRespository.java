package cn.tedu.search.repository;

import cn.tedu.search.entity.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

// Repository是Spring家族对持久层的命名规范
@Repository
public interface ItemRespository extends ElasticsearchRepository<Item,Long> {
    // ItemRepository接口继承ElasticsearchRepository父接口,这个父接口由SpringData提供
    // ElasticsearchRepository泛型<[要操作的实体类],[这个实体类的主键类型]>
    // 继承之后,当前接口就可以直接使用父接口中声明的方法,包含了Item实体类基本的增删改查
}
