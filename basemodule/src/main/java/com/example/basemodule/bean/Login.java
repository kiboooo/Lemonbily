package com.example.basemodule.bean;

import java.io.Serializable;
import java.util.Date;

public class Login implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login.Id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login.LPassWord
     *
     * @mbggenerated
     */
    private String lpassword;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login.LPhone
     *
     * @mbggenerated
     */
    private String lphone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login.LLiveTime
     *
     * @mbggenerated
     */
    private Date llivetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table login
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    public Login() {
    }

    public Login(Integer id, String lpassword, String lphone, Date llivetime) {
        this.id = id;
        this.lpassword = lpassword;
        this.lphone = lphone;
        this.llivetime = llivetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login.Id
     *
     * @return the value of login.Id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login.Id
     *
     * @param id the value for login.Id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login.LPassWord
     *
     * @return the value of login.LPassWord
     *
     * @mbggenerated
     */
    public String getLpassword() {
        return lpassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login.LPassWord
     *
     * @param lpassword the value for login.LPassWord
     *
     * @mbggenerated
     */
    public void setLpassword(String lpassword) {
        this.lpassword = lpassword == null ? null : lpassword.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login.LPhone
     *
     * @return the value of login.LPhone
     *
     * @mbggenerated
     */
    public String getLphone() {
        return lphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login.LPhone
     *
     * @param lphone the value for login.LPhone
     *
     * @mbggenerated
     */
    public void setLphone(String lphone) {
        this.lphone = lphone == null ? null : lphone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login.LLiveTime
     *
     * @return the value of login.LLiveTime
     *
     * @mbggenerated
     */
    public Date getLlivetime() {
        return llivetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login.LLiveTime
     *
     * @param llivetime the value for login.LLiveTime
     *
     * @mbggenerated
     */
    public void setLlivetime(Date llivetime) {
        this.llivetime = llivetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", lpassword=").append(lpassword);
        sb.append(", lphone=").append(lphone);
        sb.append(", llivetime=").append(llivetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}