package com.bluesky.tech.spring.handle.filter;

import com.bluesky.tech.spring.handle.vo.FilterResultVo;
import com.bluesky.tech.spring.handle.vo.RequestVo;

public interface IFilter<R extends RequestVo> {
    /**
     * 过滤器执行逻辑
     */
    FilterResultVo filter(R r);

    /**
     * 过滤器名称
     */
    String filterName();

}
