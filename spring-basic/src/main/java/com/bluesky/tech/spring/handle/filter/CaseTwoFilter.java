package com.bluesky.tech.spring.handle.filter;

import com.bluesky.tech.spring.Service.IShopService;
import com.bluesky.tech.spring.handle.vo.FilterResultVo;
import com.bluesky.tech.spring.handle.vo.RequestVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CaseTwoFilter extends AbstractFilter {
    private Logger logger = LoggerFactory.getLogger(CaseTwoFilter.class);

    @Resource
    private IShopService shopService;

    /**
     * @param requestVo 参数
     */
    @Override
    public FilterResultVo doFilter(RequestVo requestVo) {
        FilterResultVo<String> result = new FilterResultVo<>();
        logger.info(filterName()+" doFilter:"+requestVo.getUserId());
        return FilterResultVo.Success(null);
    }


    @Override
    public String filterName() {
        return "过滤器2";
    }
}
