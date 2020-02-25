package com.jxa.blog.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_user")
@Setter
@Getter
@ToString(exclude = {"blogs"})
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer type;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    /*blog和user的关系
    * 一个blog属于一个user
    * 一个user可能会写一个或者多个blog
    * user和blog之间是一对多的关系
    * */
    @OneToMany(mappedBy = "user")
    private List<Blog> blogs = new ArrayList<Blog>();
}
