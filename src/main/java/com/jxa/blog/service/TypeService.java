package com.jxa.blog.service;

import com.jxa.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeService {

    /*增加一个type*/
    Type saveType(Type type);

    /*根据id查询出一个Type*/
    Type getTypeById(Long id);

    /*分页查询Type
    * 返回page
    * */
    Page<Type> listTypes(Pageable pageable);

    /*根据name查询一个Type*/
    Type findOneByName(String name);

    /*修改type
    * 先根据用户id查询出type对象
    * 然后在更新
    * */
    Type updateType(Long id,Type type);

    /*根据id删除type*/
    void deleteTypeById(Long id);
}
