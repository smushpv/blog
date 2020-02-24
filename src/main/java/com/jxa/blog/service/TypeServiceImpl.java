package com.jxa.blog.service;

import com.jxa.blog.NotFoundException;
import com.jxa.blog.po.Type;
import com.jxa.blog.repository.TypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    /*增加一个type*/
    @Override
    @Transactional
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    /*根据id查询出一个Type*/
    @Override
    public Type getTypeById(Long id) {
        return typeRepository.getById(id);
    }

    /*分页查询Type
     * 返回page
     * */
    @Transactional
    @Override
    public Page<Type> listTypes(Pageable pageable) {
            return typeRepository.findAll(pageable);
    }

    /*根据name查询一个Type*/
    @Override
    public Type findOneByName(String name) {
        return typeRepository.findByName(name);
    }

    /*更新type
     * 先根据用户id查询出type对象
     * 然后在更新
     * */
    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.getOne(id);
        if (t == null){
            throw new NotFoundException("天呐，这个type居然不存在");
        }
        /*把type里边的值复制到t*/
        BeanUtils.copyProperties(type,t);
        return typeRepository.save(t);
    }

    /*根据id删除type*/
    @Transactional
    @Override
    public void deleteTypeById(Long id) {
        typeRepository.deleteById(id);
    }
}
