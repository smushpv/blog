package com.jxa.blog.service;

import com.jxa.blog.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TagService {
    /*增加一个tag*/
    Tag saveTag(Tag tag);

    /*根据id查询出一个Tag*/
    Tag getTagById(Long id);

    /*分页查询Tag
     * 返回page
     * */
    Page<Tag> listTags(Pageable pageable);

    /*根据name查询一个Tag*/
    Tag findOneByName(String name);

    /*修改Tag
     * 先根据用户id查询出Tag对象
     * 然后在更新
     * */
    Tag updateTag(Long id,Tag tag);

    /*根据id删除tag*/
    void deleteTagById(Long id);
}

