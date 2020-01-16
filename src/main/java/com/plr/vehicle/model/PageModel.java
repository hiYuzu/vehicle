package com.plr.vehicle.model;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:26
 */
public class PageModel extends BaseModel {

    private String pageId;
    private String href;
    private String pageNameEn;
    private String title;
    private String pageRemark;
    private String icon;
    private boolean spread = false;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getPageNameEn() {
        return pageNameEn;
    }

    public void setPageNameEn(String pageNameEn) {
        this.pageNameEn = pageNameEn;
    }

//    public String getPageNameCn() {
//        return pageNameCn;
//    }

//    public void setPageNameCn(String pageNameCn) {
//        this.pageNameCn = pageNameCn;
//    }

    public String getPageRemark() {
        return pageRemark;
    }

    public void setPageRemark(String pageRemark) {
        this.pageRemark = pageRemark;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
