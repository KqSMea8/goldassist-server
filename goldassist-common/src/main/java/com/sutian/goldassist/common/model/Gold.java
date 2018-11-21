package com.sutian.goldassist.common.model;

/**
 * @author sutian
 * @email sijin.zsj@alibaba-inc.com
 * @create 2018/11/16 下午5:12
 */
public class Gold {

    private String code;

    private String name;

    private String pe;

    private Double marketValue;

    private Double pr;

    private String tags;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPe() {
        return pe;
    }

    public void setPe(String pe) {
        this.pe = pe;
    }

    public Double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(Double marketValue) {
        this.marketValue = marketValue;
    }

    public Double getPr() {
        return pr;
    }

    public void setPr(Double pr) {
        this.pr = pr;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
