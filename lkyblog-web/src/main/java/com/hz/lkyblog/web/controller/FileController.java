package com.hz.lkyblog.web.controller;

import com.alibaba.fastjson.JSON;
import com.hz.lkyblog.biz.files.FileDownloadService;
import com.hz.lkyblog.biz.files.FileUploadService;
import com.hz.lkyblog.dao.es.eo.ArticleEO;
import com.hz.lkyblog.dao.es.service.ArticleEsService;
import com.hz.lkyblog.utils.rest.WebResult;
import com.hz.lkyblog.web.aspect.IgnoreLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/filedl")
public class FileController {

    @Resource
    private FileDownloadService fileDownloadService;
    @Resource
    private FileUploadService fileUploadService;

    @IgnoreLogin
    @RequestMapping(value = "/{type}/{filePath}", method = RequestMethod.GET)
    @ResponseBody
    public void getFilePath(@PathVariable("type") String type,
                            @PathVariable("filePath") String filePath,
                            HttpServletResponse response) {
        byte[] fileBytes = fileDownloadService.getFileBytes(type, filePath);
        try {
            response.getOutputStream().write(fileBytes);
        } catch (IOException e) {
            log.error("getFilePath error type:{},filePath:{}", type, fileBytes);
        }
    }


    @IgnoreLogin
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public WebResult<String> uploadFile(@RequestParam(value = "upfile", required = false) MultipartFile file) {
        String downloadUrl = null;
        try {
            downloadUrl = fileUploadService.uploadPicFile(file.getBytes(), file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return WebResult.buildSuccessResponse(downloadUrl);
    }


    public static void main(String[] args) {
        ArticleEO articleEO = new ArticleEO();
        articleEO.setCreateTime(new Date());
        articleEO.setDesc("我就是测试下");
        articleEO.setId(1L);
        articleEO.setTagId(2L);
        articleEO.setName("测试文章1");
        articleEO.setType(1);
        articleEO.setPublishTime(new Date());

        System.out.println(JSON.toJSON(articleEO));
    }

}
