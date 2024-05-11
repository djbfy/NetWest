package com.qiqi.netnest.Service.Impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiqi.netnest.Enum.ReturnCode;
import com.qiqi.netnest.Exception.BusinessException;
import com.qiqi.netnest.Mapper.TagMapper;
import com.qiqi.netnest.Pojo.Tag;
import com.qiqi.netnest.Service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> getTags() {
        return tagMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public int insertTag(Tag tag) {
        Tag type = tagMapper.selectOne(new QueryWrapper<Tag>().eq("type", tag.getType()));
        if (!ObjectUtil.isNull(type)){
            throw new BusinessException(ReturnCode.RC999.getCode(),"tag重复");
        }
        try {
            return tagMapper.insert(tag);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
