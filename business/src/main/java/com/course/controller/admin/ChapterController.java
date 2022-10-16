package com.course.controller.admin;

import com.course.dto.ChapterDto;
import com.course.dto.PageDto;
import com.course.dto.ResponseDto;
import com.course.service.ChapterService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Justin
 */
@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        chapterService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto list(@RequestBody ChapterDto chapterDto) {
        ResponseDto responseDto = new ResponseDto();
        chapterService.save(chapterDto);
        responseDto.setContent(chapterDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        chapterService.delete(id);
        return new ResponseDto();
    }
}
