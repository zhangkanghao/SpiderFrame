package com.imudges.zkh.main;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Map;

public class ConsolePipeline implements Pipeline {

    public void process(ResultItems resultItems, Task task) {
        System.out.println("get page:"+resultItems.getRequest().getUrl());
        //遍历所有结果，输出到控制台，”author“”name“”readme“都是一个key，结果都是对应的
        for (Map.Entry<String,Object> entry:resultItems.getAll().entrySet()){
            System.out.println(entry.getKey()+":\t"+entry.getValue());
        }
    }
}
