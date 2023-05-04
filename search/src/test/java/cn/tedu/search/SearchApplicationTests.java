package cn.tedu.search;

import cn.tedu.search.entity.Item;
import cn.tedu.search.repository.ItemRespository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class SearchApplicationTests {

    @Autowired
    private ItemRespository itemRespository;

    //执行单增
    @Test
    void add() {
        //实例化Item对象
        Item item = new Item()
                .setId(2L)
                .setTitle("罗技激光无线游戏鼠标2")
                .setCategory("鼠标2")
                .setBrand("罗技1")
                .setPrice(163.0)
                .setImgPath("/2.png");
        //利用Spring Data ElasticSearch框架提供的新增方法，完成新增到ES
        itemRespository.save(item);
        System.out.println("ok");
    }

    //单查
    @Test
    void getOne(){
        //SpringData提供了按id查询ES中的数据的方法
        //返回值是Optional泛型 并且定义了泛型 我们将Optional理解为一个只能保存一个元素的List即可
        Optional<Item> optional = itemRespository.findById(2L);
        //从optional中获取其中的元素
        Item item = optional.get();
        System.out.println(item);
    }

    //批量增
    @Test
    void addList(){
        //实例化一个List,把要保存到ES中的对象都添加到这个集合中
        List<Item> list = new ArrayList<>();
        list.add(new Item(1L,"罗技激光有线办公鼠标","鼠标",
                "罗技",88.0,"/2.jpg"));
        list.add(new Item(3L,"雷蛇机械无线游戏键盘","键盘",
                "雷蛇",268.0,"/3.jpg"));
        list.add(new Item(4L,"微软有线静音办公鼠标","鼠标",
                "微软",166.0,"/4.jpg"));
        list.add(new Item(5L,"罗技机械有线背光键盘","键盘",
                "罗技",208.0,"/5.jpg"));
        itemRespository.saveAll(list);
        System.out.println("ok");
    }

    // 全查
    @Test
    void getAll(){
        //findAll就是SpringData提供的从指定索引中全查所有数据的方法
        Iterable<Item> items = itemRespository.findAll();
        for (Item item: items){
            System.out.println(item);
        }
        System.out.println("-------------");
        items.forEach(item -> System.out.println(item));
    }

    //单条件自定义查询
    @Test
    void queryOne(){
        //查询items中，title字段包含“游戏”分词的数据
        Iterable<Item> items = itemRespository.queryItemsByTitleMatches("游戏");
        items.forEach(item -> System.out.println(items));
    }

    //多条件自定义查询
    @Test
    void queryTwo(){
        List<Item> items = itemRespository.queryItemsByTitleMatchesAndBrandMatches("游戏","罗技");
        items.forEach(item -> System.out.println(item));
    }

    //排序查询
    @Test
    void queryOrder(){
        //查询List分词包含“游戏”或brand是“罗技”的数据，价格降序排序
        List<Item> items = itemRespository.queryItemsByTitleMatchesOrBrandMatchesOrderByPriceDesc(
                "游戏","罗技"
        );
        items.forEach(item -> System.out.println(item));
    }

    //分页查询
    @Test
    void queryPage(){
        int page = 1;//要查询的页码
        int pageSize = 2;//设置每页条数
        //PageRequest.of是设置查询页码和每页条数的代码，但是
        //SpringData规定数字0开始为第一页，所以当前page为1，想第一页时，要page-1才行
        Page<Item> myPage = itemRespository.queryItemsByTitleMatchesOrBrandMatchesOrderByPriceDesc(
            "游戏","罗技", PageRequest.of(page-1, pageSize)
        );
        myPage.forEach(item -> System.out.println(item));

        //myPage对象包含了分页信息，输出分页信息演示
        System.out.println("总页数："+myPage.getTotalPages());
        System.out.println("总条数："+myPage.getTotalElements());
        System.out.println("当前页："+(myPage.getNumber()+1));
        System.out.println("每页条数："+myPage.getSize());
        System.out.println("是否为首页："+myPage.isFirst());
        System.out.println("是否为末页："+myPage.isLast());
    }

    //键盘分页查询
    @Test
    void queryKeyboard(){
        int page = 1;//要查询的页码
        int pageSize = 3; //设置每页条数
        Page<Item> myPage = itemRespository.queryItemsByTitleMatchesOrCategoryMatchesOrderByPriceAsc(
            "有线", "键盘", PageRequest.of(page-1, pageSize)
        );
        myPage.forEach(item -> System.out.println(item));

        //myPage对象包含了分页信息，输出分页信息演示
        System.out.println("总页数:"+myPage.getTotalPages());
        System.out.println("总条数："+myPage.getTotalElements());
        System.out.println("当前页："+(myPage.getNumber()+1));
        System.out.println("每页条数："+myPage.getSize());
        System.out.println("是否为首页"+myPage.isFirst());
        System.out.println("是否为末页："+myPage.isLast());
    }
}
