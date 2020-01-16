package com.plr.vehicle.controller;

import com.plr.vehicle.model.PageModel;
import com.plr.vehicle.model.ResultListModel;
import com.plr.vehicle.model.UserModel;
import com.plr.vehicle.pojo.PagePojo;
import com.plr.vehicle.service.IAuthorityDetailService;
import com.plr.vehicle.service.IAuthorityService;
import com.plr.vehicle.service.IPageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:55
 */
@Controller
@RequestMapping("/PageController")
public class PageController {

    /**
     * 日志输出标记
     */
    private static final String LOG = "PageController";
    /**
     * 声明日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(PageController.class);

    /**
     * 声明服务
     */
    @Resource
    private IAuthorityService authorityService;

    @Resource
    private IAuthorityDetailService authorityDetailService;

    @Resource
    private IPageService pageService;

    @RequestMapping("/getAllPage")
    @ResponseBody
    public ResultListModel<PageModel> getAllPage() {
        List<PagePojo> pageList = pageService.getAllPage();
        ResultListModel<PageModel> result = ResultListModel.getInstance(pageList.size());
        for (PagePojo temp : pageList) {
            result.addData(convertPageModel(temp));
        }
        return result;
    }

    @RequestMapping(value = "/getAuthorityPage")
    @ResponseBody
    public ResultListModel<PageModel> getAuthorityPage(@RequestParam(required = false) String authorityId, HttpSession httpSession) {
        ResultListModel<PageModel> pageResultList = new ResultListModel<>();
        UserModel loginUser = (UserModel) httpSession.getAttribute(httpSession.getId());
        List<String> authorityIds;
        if (authorityId != null && !authorityId.isEmpty()) {
            authorityIds = new ArrayList<>();
            authorityIds.add(authorityId);
        } else {
            authorityIds = authorityService.getAuthorityIdsByUserId(Long.valueOf(loginUser.getUserId()));
        }
        List<Integer> pageIdList = authorityDetailService.getPageIdList(authorityIds);
        if (pageIdList != null && !pageIdList.isEmpty()) {
            List<PagePojo> pageList = pageService.getPage(pageIdList);
            if (pageList != null && !pageList.isEmpty()) {
                List<PageModel> pageModelList = new ArrayList<>();
                for (PagePojo page : pageList) {
                    PageModel pageModel = convertPageModel(page);
                    pageModelList.add(pageModel);
                }
                pageResultList.setData(pageModelList);
                pageResultList.setResult(true);
            }
        }
        return pageResultList;
    }


    /**
     * 将PagePojo转换为PageModel
     *
     * @param pagePojo
     * @return
     */
    private PageModel convertPageModel(PagePojo pagePojo) {
        PageModel pageModel = new PageModel();
        if (pagePojo != null) {
            pageModel.setPageId(String.valueOf(pagePojo.getPageId()));
            pageModel.setPageNameEn(pagePojo.getPageNameEn());
            pageModel.setTitle(pagePojo.getPageNameCn());
            pageModel.setPageRemark(pagePojo.getPageRemark());
            pageModel.setHref(pagePojo.getPageUrl());
            pageModel.setIcon(pagePojo.getPageIcon());
        }
        return pageModel;
    }

}
