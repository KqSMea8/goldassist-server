package com.sutian.goldassist.core.schedule;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sutian
 * @email sijin.zsj@alibaba-inc.com
 * @create 2018/11/16 下午3:55
 * 接受策略问句，形成报告
 */
@Service
public class WenCaiSpiderJob {

    private static List<String> questions = new ArrayList();

    private static String TOKEN_URL ="https://www.iwencai.com/data-robot/get-fusion-data";

    private static String GOLD_URLs = "https://www.iwencai.com/stockpick/cache?token={0}&p=1&perpage=70&changeperpage=1";

    static {
        questions.add("创投概念股");
        questions.add("macd金叉");
    }

    public void doTask(){

    }

}
