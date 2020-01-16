package com.plr.vehicle.pojo;

/**
 * @Author: WangLei
 * @Description: 系统页面POJO
 * @Date: Create in 2018/3/8 11:36
 * @Modify by WangLei
 */
public class PagePojo extends BasePojo {

    private int pageId;
    private String pageNameEn;
    private String pageNameCn;
    private String pageRemark;
    private String pageUrl;
    private String pageIcon = "&#xe613;";

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public String getPageNameEn() {
        return pageNameEn;
    }

    public void setPageNameEn(String pageNameEn) {
        this.pageNameEn = pageNameEn;
    }

    public String getPageNameCn() {
        return pageNameCn;
    }

    public void setPageNameCn(String pageNameCn) {
        this.pageNameCn = pageNameCn;
    }

    public String getPageRemark() {
        return pageRemark;
    }

    public void setPageRemark(String pageRemark) {
        this.pageRemark = pageRemark;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getPageIcon() {
        return pageIcon;
    }

    public void setPageIcon(String pageIcon) {
        this.pageIcon = pageIcon;
    }
}
