package cn.tedu.search.repository;

import cn.tedu.search.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repository是Spring家族对持久层的命名规范
@Repository
public interface ItemRespository extends ElasticsearchRepository<Item, Long> {
    // ItemRepository接口继承ElasticsearchRepository父接口,这个父接口由SpringData提供
    // ElasticsearchRepository泛型<[要操作的实体类],[这个实体类的主键类型]>
    // 继承之后,当前接口就可以直接使用父接口中声明的方法,包含了Item实体类基本的增删改查

    Iterable<Item> queryItemsByTitleMatches(String title);

    // 多条件自定义查询
    // 多个条件之间要使用And或Or进行分隔,表示多个条件间的逻辑关系
    // 我们要使用title和brand字段进行多条件查询
    // 参数的数量和需要的数量要匹配
    // 参数赋值到需要参数的位置是根据顺序依次赋值的,和参数名称无关
    List<Item> queryItemsByTitleMatchesAndBrandMatches(String title, String brand);

    // 排序查询
    // 在方法最后添加OrderBy关键字,然后指定排序依据的字段,最后设置升序(Asc\默认)降序(Desc)
    List<Item> queryItemsByTitleMatchesOrBrandMatchesOrderByPriceDesc(String title, String brand);

    //分页查询
    //要执行分页查询，首先要将返回值修改为Page类型
    //Page类型的功能是既能保存查询出的当前页数据，又能保存分页信息
    //分页信息就是本次查询结果中分页的相关数据，例如：当前页码，每页条数，总页数，总条数，有没有上一页……等
    //参数方面，在当前所有已经存在的参数最后，再添加一个Pageable类型的参数
    //在调用时，为这个Pageable参数赋值要查询的页码和每页的条数
    Page<Item> queryItemsByTitleMatchesOrBrandMatchesOrderByPriceDesc(
            String title, String brand, Pageable pageable
    );

    //    编写title包含"有线"分词或类型(category)是"键盘"的数据,按价格升序排序
    //            要求分页查询一页3条,查询第一页,输出分页信息
    Page<Item> queryItemsByTitleMatchesOrCategoryMatchesOrderByPriceAsc(
            String title, String category, Pageable pageable
    );
}
