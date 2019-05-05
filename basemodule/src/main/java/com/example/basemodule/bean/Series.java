package com.example.basemodule.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Series implements Serializable {

    /**
     * -1：无类别
     * 0：音乐
     * 1：电视剧
     * 2：电影
     */
    public static final Map<Integer, String> SERIES_TYPE = new HashMap<Integer, String>() {{
        put(-1,"无类别");
        put(0, "音乐");
        put(1, "电视剧");
        put(2, "电影");
    }};

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column series.SeriesID
     *
     * @mbggenerated
     */
    private Integer seriesid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column series.SType
     *
     * @mbggenerated
     */
    private Integer stype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column series.SName
     *
     * @mbggenerated
     */
    private String sname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column series.SDescribe
     *
     * @mbggenerated
     */
    private String sdescribe;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column series.SNum
     *
     * @mbggenerated
     */
    private Integer snum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table series
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    public Series() {
    }

    public Series(Integer seriesid, Integer stype, String sname, String sdescribe, Integer snum) {
        this.seriesid = seriesid;
        this.stype = stype;
        this.sname = sname;
        this.sdescribe = sdescribe;
        this.snum = snum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column series.SeriesID
     *
     * @return the value of series.SeriesID
     * @mbggenerated
     */
    public Integer getSeriesid() {
        return seriesid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column series.SeriesID
     *
     * @param seriesid the value for series.SeriesID
     * @mbggenerated
     */
    public void setSeriesid(Integer seriesid) {
        this.seriesid = seriesid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column series.SType
     *
     * @return the value of series.SType
     * @mbggenerated
     */
    public Integer getStype() {
        return stype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column series.SType
     *
     * @param stype the value for series.SType
     * @mbggenerated
     */
    public void setStype(Integer stype) {
        this.stype = stype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column series.SName
     *
     * @return the value of series.SName
     * @mbggenerated
     */
    public String getSname() {
        return sname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column series.SName
     *
     * @param sname the value for series.SName
     * @mbggenerated
     */
    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column series.SDescribe
     *
     * @return the value of series.SDescribe
     * @mbggenerated
     */
    public String getSdescribe() {
        return sdescribe;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column series.SDescribe
     *
     * @param sdescribe the value for series.SDescribe
     * @mbggenerated
     */
    public void setSdescribe(String sdescribe) {
        this.sdescribe = sdescribe == null ? null : sdescribe.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column series.SNum
     *
     * @return the value of series.SNum
     * @mbggenerated
     */
    public Integer getSnum() {
        return snum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column series.SNum
     *
     * @param snum the value for series.SNum
     * @mbggenerated
     */
    public void setSnum(Integer snum) {
        this.snum = snum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table series
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", seriesid=").append(seriesid);
        sb.append(", stype=").append(stype);
        sb.append(", sname=").append(sname);
        sb.append(", sdescribe=").append(sdescribe);
        sb.append(", snum=").append(snum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}