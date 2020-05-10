package com.bluesky.tech.spring.handle;

import com.bluesky.tech.spring.handle.filter.FilterChain;
import com.bluesky.tech.spring.handle.filter.IFilter;
import com.bluesky.tech.spring.handle.vo.FilterResultVo;
import com.bluesky.tech.spring.handle.vo.RequestVo;
import com.bluesky.tech.spring.handle.vo.Rsres;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 基于简单工厂方法模式 + 模板方法模式
 */
@Service
public class CommonHandler extends AbstractHandler {

	private Logger logger = LoggerFactory.getLogger(CommonHandler.class);

	private FilterChain<RequestVo> filterChain;

	@Resource(name = "caseOneFilter")
	private IFilter caseOneFilter;

	@Resource(name = "caseTwoFilter")
	private IFilter caseTwoFilter;

	@PostConstruct
	private void init() {
		filterChain = new FilterChain<>();
		//过滤器1
		filterChain.addFilter(caseOneFilter);
		//过滤器2
		filterChain.addFilter(caseTwoFilter);
	}

	@Override
	FilterChain<RequestVo> getFilterChain() {
		return filterChain;
	}

	@Override
	Rsres doHandle(RequestVo requestVo) {
		FilterResultVo resultVo = getFilterChain().filter(requestVo);
		return Rsres.Success(resultVo);
	}

	@Override
	String handlerName() {
		return "处理器1";
	}
}
