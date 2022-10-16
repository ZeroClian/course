package com.course.service;

import com.course.domain.Chapter;
import com.course.domain.ChapterExample;
import com.course.dto.ChapterDto;
import com.course.dto.PageDto;
import com.course.mapper.ChapterMapper;
import com.course.util.CopyUtil;
import com.course.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author ZeroClian
 * @date 2022-08-27 4:12 下午
 */
@Service
public class ChapterService {
    @Resource
    private ChapterMapper chapterMapper;

    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ChapterExample example = new ChapterExample();
        // example.createCriteria().andIdEqualTo("00000003");
        example.setOrderByClause("id desc");
        List<Chapter> chapterList = chapterMapper.selectByExample(example);
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapterList);
        pageDto.setTotal(pageInfo.getTotal());
        List<ChapterDto> chapterDtoList = CopyUtil.copyList(chapterList, ChapterDto.class);
        pageDto.setList(chapterDtoList);
    }

    public void save(ChapterDto chapterDto) {
        if (Objects.isNull(chapterDto.getId())) {
            chapterDto.setId(UuidUtil.getShortUuid());
            Chapter chapter = CopyUtil.copy(chapterDto, Chapter.class);
            chapterMapper.insert(chapter);
        } else {
            Chapter chapter = CopyUtil.copy(chapterDto, Chapter.class);
            chapterMapper.updateByPrimaryKey(chapter);
        }
    }

    public void delete(String id) {
        chapterMapper.deleteByPrimaryKey(id);
    }
}
