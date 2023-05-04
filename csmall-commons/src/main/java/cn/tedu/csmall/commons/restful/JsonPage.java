package cn.tedu.csmall.commons.restful;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class JsonPage<T> implements Serializable {

    //JsonPage是用于统一代替PageInfo或Page这样分页查询结果类型的
    //其中要包含 分页信息 和 查询到的数据 两方面
    //我们这里只声明少量分页信息即可 实际开发中 有额外需要再添加额外属性
    @ApiModelProperty(value = "总页数", name = "totalPages")
    private Integer totalPages;

    @ApiModelProperty(value = "总条数", name = "totalCount")
    private Long totalCount;

    @ApiModelProperty(value = "页码", name = "page")
    private Integer page;

    @ApiModelProperty(value = "每页条数", name = "pageSize")
    private Integer pageSize;

    //还要声明一个属性，保存查询到的数据
    @ApiModelProperty(value = "分页数据", name = "list")
    private List<T> list;

    //下面编写一个方法，能够将PageInfo类型对象转换为JsonPage类型对象返回
    public static <T> JsonPage<T> restPage(PageInfo<T> pageInfo){
        //下面编写的是转换代码，要将pageInfo中相同意义的属性赋值到JsonPage对象中
        JsonPage<T> jsonPage = new JsonPage<>();
        jsonPage.setTotalPages(pageInfo.getPages());
        jsonPage.setTotalCount(pageInfo.getTotal());
        jsonPage.setPage(pageInfo.getPageNum());
        jsonPage.setPageSize(pageInfo.getPageSize());
        //别忘了分页数据的复制过程
        jsonPage.setList(pageInfo.getList());
        //最后返回转换完成的对象!
        return jsonPage;
    }
}
