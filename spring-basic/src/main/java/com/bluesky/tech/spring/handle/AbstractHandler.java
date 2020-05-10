package com.bluesky.tech.spring.handle;

import com.bluesky.tech.spring.handle.filter.FilterChain;
import com.bluesky.tech.spring.handle.vo.RequestVo;
import com.bluesky.tech.spring.handle.vo.Rsres;
import com.bluesky.tech.spring.util.JsonUtil;
import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public abstract class AbstractHandler<R extends RequestVo> {
    private Logger logger = LoggerFactory.getLogger(AbstractHandler.class);

//    @Resource
//    protected RedisClient redisClient;

    /**
     * 过滤器执行逻辑
     * 模板方法
     */
    public Rsres handle(R requestVo) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            logger.info("处理器[{}]开始执行，请求参数:{}", handlerName(), JsonUtil.toJsonString(requestVo));
            return doHandle(requestVo);
        } catch (Exception e) {
            logger.error("处理器[{}]执行异常", handlerName(), e);
            return Rsres.Fail("请求失败");
        } finally {
            logger.info("处理器[{}]执行结束! 耗时:{}", handlerName(), stopwatch.elapsed(TimeUnit.MILLISECONDS));
        }
    }

    /**
     * 获取过滤器责任链
     */
    abstract FilterChain<R> getFilterChain();

    /**
     * 处理领取逻辑
     */
    abstract Rsres doHandle(R requestVo);

    /**
     * 处理器名称
     */
    abstract String handlerName();


}
