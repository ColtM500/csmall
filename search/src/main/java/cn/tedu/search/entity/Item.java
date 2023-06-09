package cn.tedu.search.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.lang.annotation.Documented;

@Data
//相当于生成了set方法 返回值都是Item return的都是this
@Accessors(chain = true)    //支持链式set赋值
@AllArgsConstructor         //生成包含全部参数的构造方法
@NoArgsConstructor          //生成无参的构造方法
//@Document是SpringDataElasticsearch框架标记一个对应的ES实体类的注解
//indexName属性指定当前实体类对应索引的名称，运行时如果索引不存在，会自动创建这个索引
@Document(indexName = "items")
public class Item implements Serializable {

    //Spring Data通过@Id注解标记实体类的主键
    @Id
    private Long id;

    //title是要进行分词的字段
    //@Field标记Es中的一个字段
    @Field(type = FieldType.Text,
            analyzer = "ik_max_word",
            searchAnalyzer = "ik_max_word")
    private String title;

    // FieldType.Keyword表示不需要分词的字符串类型
    @Field(type = FieldType.Keyword )
    private String category;

    @Field(type = FieldType.Keyword )
    private String brand;

    @Field(type = FieldType.Double )
    private Double price;

    // imgPath是图片路径,不会成为搜索条件,所以这个字段不需要创建索引
    // index = false就是设置当前字段不创建索引的配置,以节省一定空间
    // 但是imgPath这个字段的值ES还是保存的
    @Field(type = FieldType.Keyword,index = false)
    private String imgPath;
}
