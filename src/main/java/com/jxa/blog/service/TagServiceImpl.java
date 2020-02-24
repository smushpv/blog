package com.jxa.blog.service;

import com.jxa.blog.NotFoundException;
import com.jxa.blog.po.Tag;
import com.jxa.blog.po.Type;
import com.jxa.blog.repository.TagRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    /*增加一个tag*/
    @Override
    @Transactional
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    /*根据id查询出一个Tag*/
    @Override
    public Tag getTagById(Long id) {
        return tagRepository.getById(id);
    }

    /*分页查询Tag
     * 返回page
     * */
    @Transactional
    @Override
    public Page<Tag> listTags(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }


    /*根据name查询一个Tag*/
    @Override
    public Tag findOneByName(String name) {
        return tagRepository.findByName(name);
    }

    /*更新Tag
     * 先根据用户id查询出Tag对象
     * 然后在更新
     * */
    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagRepository.getOne(id);
        if (t == null){
            throw new NotFoundException("天呐，这个tag居然不存在");
        }
        /*把type里边的值复制到t*/
        BeanUtils.copyProperties(tag,t);
        return tagRepository.save(t);
    }

    /*根据id删除tag*/
    @Transactional
    @Override
    public void deleteTagById(Long id) {
        tagRepository.deleteById(id);
    }

}
