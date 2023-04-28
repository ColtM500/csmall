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

    //执行单增
    @Test
    void add() {
        //实例化Item对象
        Item item = new Item()
                .setId(2L)
                .setTitle("罗技激光无线游戏鼠标2")
                .setCategory("鼠标2")
                .setBrand("罗技")
                .setPrice(163.0)
                .setImgPath("/2.png");
        //利用Spring Data ElasticSearch框架提供的新增方法，完成新增到ES
        itemRespository.save(item);
        System.out.println("ok");
    }

    //单查
    @Test
    void getOne(){

    }

    //批量增
    @Test
    void addList(){

    }

    // 全查
    @Test
    void getAll(){

    }

    //单条件自定义查询
    @Test
    void queryOne(){

    }

    //多条件自定义查询
    @Test
    void queryTwo(){

    }

}
