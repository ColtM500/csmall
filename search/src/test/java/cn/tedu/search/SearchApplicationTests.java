package cn.tedu.search;

import cn.tedu.search.entity.Item;
import cn.tedu.search.repository.ItemRespository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class SearchApplicationTests {

    @Autowired
    private ItemRespository itemRespository;

    @Test
    void contextLoads() {
    }

    @Test
    void add() {
        //实例化Item对象
        Item item = new Item()
                .setId(1L)
                .setTitle("罗技激光无线游戏鼠标")
                .setCategory("鼠标")
                .setBrand("罗技")
                .setPrice(168.0)
                .setImgPath("/1.png");
        //利用Spring Data ElasticSearch框架提供的新增方法，完成新增到ES
        itemRespository.save(item);
        System.out.println("ok");
    }

    //单查
    @Test
    void getOne(){
        //SpringData提供了按id查询ES中的数据方法
        //返回值是Optional类型，并且定义了泛型，我们将Optional理解为一个只能保存一个元素的List即可
        Optional<Item> optional = itemRespository.findById(1L);
        //从optional中获取其中的元素
        Item item = optional.get();
        System.out.println(item);
    }

    //批量增
    @Test
    void addList(){
        //实例化一个List,把要保存到ES中的对象都添加到这个集合中
        List<Item> list = new ArrayList<>();
        list.add(new Item(2L,"罗技激光有线办公鼠标","鼠标",
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


}
