package com.jxa.blog.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString(exclude = {"blogs"})
@NoArgsConstructor//无参数构造方法
@Entity
@Table(name = "t_type")
public class Type {
    /*@GeneratedValue(strategy = GenerationType.IDENTITY)*/
    @Id
    @GeneratedValue
    private Long id;
    /*通过注解的方式，添加校验
    * name不能为空
    * 使用注解@NotBlank(message = "嘿嘿嘿！分类名称不能为空")
    * 如果为空了，后台会抛出异常
    * */
/*    @NotBlank(message = "嘿嘿嘿！分类名称不能为空")*/
    @NotEmpty(message = "嘿嘿嘿！分类名称不能为空")
    private String name;//分类名称
    /*type和blog的关系
    * 一个type下有多个blog
    * type和blog的关系是一对多的关系
    * @OneToMany
    * Many的一方作为关系维护端，在被维护的一端添加mappedBy属性
    * mappedBy="被维护的一端的属性名称"
    * */
    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<Blog>();

}
