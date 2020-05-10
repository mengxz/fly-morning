package com.bluesky.tech.spring.handle.filter;

import com.bluesky.tech.spring.handle.vo.FilterResultVo;
import com.bluesky.tech.spring.handle.vo.RequestVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class FilterChain<R extends RequestVo> {

    private Logger logger = LoggerFactory.getLogger(FilterChain.class);

    //过滤器集合链
    private List<IFilter<R>> filters = new ArrayList<>();

    /**
     * 追加过滤器
     */
    public void addFilter(IFilter filter){
        filters.add(filter);
    }

    /**
     * 校验过滤器链执行开始
     */
    public FilterResultVo filter(R r){
        for (IFilter<R> filter : filters) {
            FilterResultVo filterResult = filter.filter(r);
            logger.info("过滤器:{}-过滤结果:{}",filter.filterName(),filterResult.isSuccess() ? "true" : "false");
            if(!filterResult.isSuccess()){
                return filterResult;
            }
        }
        return FilterResultVo.Success("OK");
    }
}
