package com.jxa.blog.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_tag")
@Setter
@Getter
@ToString(exclude = {"blogs"})
@NoArgsConstructor//无参数构造方法
public class Tag {

    /*
    * @GeneratedValue(strategy = GenerationType.IDENTITY)此注解让每一张表的主见单独自增
    * strategy = GenerationType.AUTO
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*通过注解的方式，添加校验
     * name不能为空
     * 使用注解@NotBlank(message = "嘿嘿嘿！分类名称不能为空")
     * 如果为空了，后台会抛出异常
     * */
/*    @NotBlank(message = "嘿嘿嘿！分类名称不能为空")*/
    @NotEmpty(message = "嘿嘿嘿！分类名称不能为空")
    private String name;
    /*tag和blog之间的关系
    * 一个tag可能包含多个blog
    * 同时，一个blog下可能有多个tag
    * 一个blog和tag之间是多对多的关系
    * 指定一个关系维护端，
    * blog一端维护关系
    * tags一端为被维护关系
    * Many的一方作为关系维护端，在被维护的一端添加mappedBy属性
    * mappedBy="被维护的一端的属性名称"
    * */
    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs = new ArrayList<Blog>();

}
