package com.qiqi.netnest.Service;

import com.qiqi.netnest.Pojo.Tag;

import java.util.List;

public interface TagService {

    List<Tag> getTags();
    int insertTag(Tag tag);
}
