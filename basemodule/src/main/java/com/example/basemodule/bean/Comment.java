package com.example.basemodule.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Comment implements Serializable {


    /**
     * 0：交友圈
     * 1：视频
     */
    public static final Map<Integer, String> COMMENT_TYPE = new HashMap<Integer, String>() {{
        put(0, "交友圈");
        put(1, "视频");
    }};

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.ComID
     *
     * @mbggenerated
     */
    private Integer comid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.UID
     *
     * @mbggenerated
     */
    private Integer uid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.ToID
     *
     * @mbggenerated
     */
    private Integer toid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.ComType
     *
     * @mbggenerated
     */
    private Integer comtype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.ComContent
     *
     * @mbggenerated
     */
    private String comcontent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.ComTime
     *
     * @mbggenerated
     */
    private Date comtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table comment
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    public Comment(Integer comid, Integer uid, Integer toid, Integer comtype, String comcontent, Date comtime) {
        this.comid = comid;
        this.uid = uid;
        this.toid = toid;
        this.comtype = comtype;
        this.comcontent = comcontent;
        this.comtime = comtime;
    }

    public Comment() {
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.ComID
     *
     * @return the value of comment.ComID
     *
     * @mbggenerated
     */
    public Integer getComid() {
        return comid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.ComID
     *
     * @param comid the value for comment.ComID
     *
     * @mbggenerated
     */
    public void setComid(Integer comid) {
        this.comid = comid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.UID
     *
     * @return the value of comment.UID
     *
     * @mbggenerated
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.UID
     *
     * @param uid the value for comment.UID
     *
     * @mbggenerated
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.ToID
     *
     * @return the value of comment.ToID
     *
     * @mbggenerated
     */
    public Integer getToid() {
        return toid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.ToID
     *
     * @param toid the value for comment.ToID
     *
     * @mbggenerated
     */
    public void setToid(Integer toid) {
        this.toid = toid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.ComType
     *
     * @return the value of comment.ComType
     *
     * @mbggenerated
     */
    public Integer getComtype() {
        return comtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.ComType
     *
     * @param comtype the value for comment.ComType
     *
     * @mbggenerated
     */
    public void setComtype(Integer comtype) {
        this.comtype = comtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.ComContent
     *
     * @return the value of comment.ComContent
     *
     * @mbggenerated
     */
    public String getComcontent() {
        return comcontent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.ComContent
     *
     * @param comcontent the value for comment.ComContent
     *
     * @mbggenerated
     */
    public void setComcontent(String comcontent) {
        this.comcontent = comcontent == null ? null : comcontent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.ComTime
     *
     * @return the value of comment.ComTime
     *
     * @mbggenerated
     */
    public Date getComtime() {
        return comtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.ComTime
     *
     * @param comtime the value for comment.ComTime
     *
     * @mbggenerated
     */
    public void setComtime(Date comtime) {
        this.comtime = comtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", comid=").append(comid);
        sb.append(", uid=").append(uid);
        sb.append(", toid=").append(toid);
        sb.append(", comtype=").append(comtype);
        sb.append(", comcontent=").append(comcontent);
        sb.append(", comtime=").append(comtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}