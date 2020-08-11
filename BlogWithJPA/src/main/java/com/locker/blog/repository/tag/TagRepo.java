package com.locker.blog.repository.tag;

import com.locker.blog.domain.post.Post;
import com.locker.blog.domain.tag.PostTag;
import com.locker.blog.domain.tag.Tag;

import java.util.List;

public interface TagRepo {
    public Tag isDuptag(String name);//중복된 태그 인가(중복인 경우 해당 tagid)
    public int insertNewTag(Tag tag);//(중복이 아닐떄) 새로운 태그 추가
    public int connectTag(PostTag posttag);//posttag테이블에서 pid와 tagid연결
    public List<Tag> getTags(Long pid);//해당 포스트의 모든 태그 출력
}
