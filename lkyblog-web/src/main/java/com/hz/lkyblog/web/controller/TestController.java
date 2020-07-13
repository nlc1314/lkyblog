package com.hz.lkyblog.web.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.hz.lkyblog.dao.es.eo.ArticleEO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Controller
@RequestMapping("test")
public class TestController {

    public static void main(String[] args) {
//        ArticleEO articleEO = new ArticleEO();
//        articleEO.setCreateTime(new Date());
//        articleEO.setDesc("我就是测试下");
//        articleEO.setId(1L);
//        articleEO.setTagId(2L);
//        articleEO.setName("测试文章1");
//        articleEO.setType(1);
//        articleEO.setPublishTime(new Date());
//
//        System.out.println(JSON.toJSON(articleEO));


        List<Map<String,String>> list = Lists.newArrayList();



        List<A> list1 = Lists.newArrayList();



        Map<String,String> map1 = Maps.newHashMap();
        map1.put("id","12");
        map1.put("name","sasask");


        Map<String,String> map2 = Maps.newHashMap();
        map2.put("id","4");
        map2.put("name","sasask");

        list.add(map1);
        list.add(map2);


        TestController t=new TestController();

        A a1 = t.new A(12L,"sas");
        A a2 = t.new A(12L,"sas");
        A a3 = t.new A(13L,"sas");
        A a4 = t.new A(15L,"sas");
        A a5 = t.new A(4L,"sas");

        list1.add(a1);
        list1.add(a2);
        list1.add(a3);
        list1.add(a4);
        list1.add(a5);



        Set<Long> ids = list.stream().map(m -> m.get("id")).map(Long::new).collect(Collectors.toCollection(HashSet::new));

        List<A> collect = list1.stream().filter(a -> ids.contains(a.getId())).collect(Collectors.toList());

        System.out.println(collect.size());
    }

    @AllArgsConstructor
    @Data
    class A{
        private Long id;
        private String name;
    }

}
