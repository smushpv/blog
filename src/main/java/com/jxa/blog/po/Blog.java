package com.jxa.blog.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_blog")
@Setter
@Getter
@ToString(exclude = {"type","tags","user","comments"})
@NoArgsConstructor//无参数构造方法
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;//标题
    private String content;//博客内容
    private String firstPicture;//首图，地址url
    private String flag;//标记
    private Integer views;//浏览次数
    private boolean appreciation;//是否开启赞赏
    private boolean shareStatement;//是否开启分享
    private boolean commentabled;//是否开启评论
    private boolean published;//是否发表
    private boolean recommend;//是否推荐
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;//创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;//更新时间
    /*blog和type之间的关系
    * 一个blog对应一个type
    * blog和type是多对一的关系
    * @ManyToOne
    * Many的一方作为关系维护端
    * */
    @ManyToOne
    private Type type;
    /*blog和tag之间的关系
    * 一个blog可能对应一个或者多个tag
    * 一个tag下可能有多个blog
    * blog和tag之间是多对多的关系
    * 添加@ManyToMany
    * 添加级联关系：在新增一个blog时，同时添加一个tag,可以添加属性cascade = {CascadeType.PERSIST}
    *
    * */
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<Tag>();
    /*Blog和User之间的关系
    * 一个或者多个Blog对应一个user
    * blog和user之间的关系是多对一的关系
    * */
    @ManyToOne
    private User user;
    /*blog和comment的关系
    * 一个blog可能有一个或者多个comment对象
    * 所以blog和comment是一对多的关系
    * Many的一方作为关系维护端，在被维护的一端添加mappedBy属性
    * mappedBy="被维护的一端的属性名称"
    * */
    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<Comment>();
}
