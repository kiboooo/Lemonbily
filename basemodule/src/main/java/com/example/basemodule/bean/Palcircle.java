package com.example.basemodule.bean;

import java.io.Serializable;
import java.util.Date;

public class Palcircle implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column palcircle.PalID
     *
     * @mbggenerated
     */
    private Integer palid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column palcircle.PalUserID
     *
     * @mbggenerated
     */
    private Integer paluserid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column palcircle.PalContent
     *
     * @mbggenerated
     */
    private String palcontent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column palcircle.PalLickNum
     *
     * @mbggenerated
     */
    private Integer pallicknum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column palcircle.PalPicture
     *
     * @mbggenerated
     */
    private String palpicture;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column palcircle.PalTime
     *
     * @mbggenerated
     */
    private Date paltime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table palcircle
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    public Palcircle() {
    }

    public Palcircle(Integer palid, Integer paluserid, String palcontent, Integer pallicknum, String palpicture, Date paltime) {
        this.palid = palid;
        this.paluserid = paluserid;
        this.palcontent = palcontent;
        this.pallicknum = pallicknum;
        this.palpicture = palpicture;
        this.paltime = paltime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column palcircle.PalID
     *
     * @return the value of palcircle.PalID
     *
     * @mbggenerated
     */
    public Integer getPalid() {
        return palid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column palcircle.PalID
     *
     * @param palid the value for palcircle.PalID
     *
     * @mbggenerated
     */
    public void setPalid(Integer palid) {
        this.palid = palid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column palcircle.PalUserID
     *
     * @return the value of palcircle.PalUserID
     *
     * @mbggenerated
     */
    public Integer getPaluserid() {
        return paluserid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column palcircle.PalUserID
     *
     * @param paluserid the value for palcircle.PalUserID
     *
     * @mbggenerated
     */
    public void setPaluserid(Integer paluserid) {
        this.paluserid = paluserid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column palcircle.PalContent
     *
     * @return the value of palcircle.PalContent
     *
     * @mbggenerated
     */
    public String getPalcontent() {
        return palcontent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column palcircle.PalContent
     *
     * @param palcontent the value for palcircle.PalContent
     *
     * @mbggenerated
     */
    public void setPalcontent(String palcontent) {
        this.palcontent = palcontent == null ? null : palcontent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column palcircle.PalLickNum
     *
     * @return the value of palcircle.PalLickNum
     *
     * @mbggenerated
     */
    public Integer getPallicknum() {
        return pallicknum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column palcircle.PalLickNum
     *
     * @param pallicknum the value for palcircle.PalLickNum
     *
     * @mbggenerated
     */
    public void setPallicknum(Integer pallicknum) {
        this.pallicknum = pallicknum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column palcircle.PalPicture
     *
     * @return the value of palcircle.PalPicture
     *
     * @mbggenerated
     */
    public String getPalpicture() {
        return palpicture;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column palcircle.PalPicture
     *
     * @param palpicture the value for palcircle.PalPicture
     *
     * @mbggenerated
     */
    public void setPalpicture(String palpicture) {
        this.palpicture = palpicture == null ? null : palpicture.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column palcircle.PalTime
     *
     * @return the value of palcircle.PalTime
     *
     * @mbggenerated
     */
    public Date getPaltime() {
        return paltime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column palcircle.PalTime
     *
     * @param paltime the value for palcircle.PalTime
     *
     * @mbggenerated
     */
    public void setPaltime(Date paltime) {
        this.paltime = paltime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table palcircle
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", palid=").append(palid);
        sb.append(", paluserid=").append(paluserid);
        sb.append(", palcontent=").append(palcontent);
        sb.append(", pallicknum=").append(pallicknum);
        sb.append(", palpicture=").append(palpicture);
        sb.append(", paltime=").append(paltime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}