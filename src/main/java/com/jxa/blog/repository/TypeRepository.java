package com.jxa.blog.repository;

import com.jxa.blog.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type,Long> {
    /*根据name查询一个Type*/
    Type findByName(String name);

    /*根据id查询一个type*/
    Type getById(Long id);

}
