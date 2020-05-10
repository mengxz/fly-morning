package com.bluesky.tech.spring.handle.filter;

import com.bluesky.tech.spring.handle.vo.FilterResultVo;
import com.bluesky.tech.spring.handle.vo.RequestVo;
import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public abstract class AbstractFilter implements IFilter{

    private Logger logger = LoggerFactory.getLogger(AbstractFilter.class);

//    @Resource
//    protected RedisClient redisClient;


    /**
     * 过滤器执行逻辑
     * 模板方法
     * @param requestVo
     */
    @Override
    public FilterResultVo filter(RequestVo requestVo) {
        logger.info("请求过滤[{}]开始!", filterName());
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            return doFilter(requestVo);
        } catch (Exception e) {
            logger.error("过滤[{}]异常", filterName(), e);
            //throw new Exception("过滤器验证异常报错:"+filterName());
        } finally {
            logger.info("过滤[{}]结束! 耗时:{}", filterName(), stopwatch.elapsed());
        }
        return null;
    }

    /**
     * 各个过滤器的真实过滤逻辑
     */
    abstract FilterResultVo doFilter(RequestVo requestVo);

    /**
     * 封装返回
     */
    protected String wrapResult(int result){
        return "wrapResult_"+result;
    }
}
