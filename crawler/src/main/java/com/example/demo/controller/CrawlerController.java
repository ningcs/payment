package com.example.demo.controller;

import com.example.demo.core.ExtractService;
import com.example.demo.entity.Article;
import com.example.demo.rule.Rule;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ningcs on 2018/8/17.
 */
@RestController
public class CrawlerController {

    @RequestMapping(value = "/getCrawlerfo", method ={RequestMethod.GET,RequestMethod.POST} )
    public List<Article> getCrawlerfo() {
        Rule rule = new Rule(
                "https://movie.douban.com/j/search_subjects",
                new String[] { "type","tag","page_limit","page_start" }, new String[] { "tv","热门","50","0" },
                null,Rule.BODY, Rule.GET);
        List<Article> extracts = ExtractService.extract(rule);
        System.out.println(extracts);
        return extracts;
    }
//
//    @RequestMapping(value = "/getCrawlerfoPage", method ={RequestMethod.GET,RequestMethod.POST} )
//    public List<LinkTypeData> getCrawlerfoPage() {
//        Rule rule = new Rule(
//                "https://movie.douban.com/",
//                null, null,
//                "slide-container", Rule.CLASS, Rule.GET);
//        List<LinkTypeData> extracts = ExtractService.extract(rule);
//        System.out.println(extracts);
//        return extracts;
//    }
}
