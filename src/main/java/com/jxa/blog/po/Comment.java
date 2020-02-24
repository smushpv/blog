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
@Table(name = "t_comment")
@Setter
@Getter
@ToString(exclude = {"blog","replyComments","parentComment"})//(exclude={"",})
@NoArgsConstructor//无参数构造方法
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    /*comment和blog的关系
     * 一个或者多个comment属于一个blog
     * 所以comment和blog是多对一的关系
     * */
    @ManyToOne
    private Blog blog;

    /*comment和comment之间的关系
    * 一个评论下可能有多个评论
    * so,一个comment作为父类时，可能会有多个子对象
    * */
    //comment作为父对象时，是一对多的关系,包含多个回复
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments = new ArrayList<Comment>();
    //comment作为子对象时，是多对一的关系
    @ManyToOne
    private Comment parentComment;

}
