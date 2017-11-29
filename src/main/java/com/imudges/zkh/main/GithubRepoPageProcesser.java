package com.imudges.zkh.main;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class GithubRepoPageProcesser implements PageProcessor {
    /**
     * 重试3次。每次延时100ms
     */
    private Site site=Site.me().setRetryTimes(3).setSleepTime(100);


    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name",page.getHtml().xpath("/html/head//title/text()").toString());
        /*page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());*/
        if (page.getResultItems().get("name")==null){
            //skip this page
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcesser()).addPipeline(new ConsolePipeline()).addUrl("https://github.com/code4craft").thread(5).run();
    }
}
