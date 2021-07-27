package com.randommeme.controller.api;

import com.randommeme.common.constant.CommonStatusEnum;
import com.randommeme.common.util.TokenUtils;
import com.randommeme.convert.RecommendConvert;
import com.randommeme.dto.RecommendCountDto;
import com.randommeme.service.IRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * 推荐表  控制器
 *
 * @author lsivt
 * @date 2021-06-23 10:41:53
 */
@Slf4j
@RestController
@RequestMapping("/api/recommend")
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class RecommendApiController {

    @Autowired
    private IRecommendService recommendService;
    @Autowired
    private RecommendConvert recommendConvert;

    @GetMapping("/recommend")
    public void recommend(HttpServletRequest httpServletRequest,
                          @RequestParam("contentId") Long contentId) {
        recommendService.recommend(TokenUtils.getUserIP(httpServletRequest), contentId, CommonStatusEnum.USABLE.getCode());
    }

    @GetMapping("/notRecommend")
    public void notRecommend(HttpServletRequest httpServletRequest,
                             @RequestParam("contentId") Long contentId) {
        recommendService.recommend(TokenUtils.getUserIP(httpServletRequest), contentId, CommonStatusEnum.DISABLED.getCode());
    }

    @GetMapping("/recommendCount")
    public RecommendCountDto recommendCount(@RequestParam("contentId") Long contentId) {
        return recommendConvert.countPoToDto(recommendService.recommendCount(contentId));
    }
}
