package com.jxa.blog.repository;

import com.jxa.blog.po.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    /*根据name查询一个tag*/
    Tag findByName(String name);

    /*根据id查询一个tag*/
    Tag getById(Long id);
}
