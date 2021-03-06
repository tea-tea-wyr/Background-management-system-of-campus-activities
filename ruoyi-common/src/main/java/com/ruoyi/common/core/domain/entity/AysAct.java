package com.ruoyi.common.core.domain.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动对象 ays_act
 *
 * @author ruoyi
 * @date 2021-01-06
 */
public class AysAct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 活动id */
    private Long actId;

    /** 所属组织方id */
    private Long orgId;

    /** 名称 */
    @Excel(name = "名称")
    private String aname;

    /** 主题 */
    @Excel(name = "主题")
    private String asubject;

    /** 标签类型 */
    @Excel(name = "标签类型")
    private String atype;


    /** 开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date astart;

    /** 结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date aend;

    /** 开始时间 */
    @JsonFormat(pattern = "HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "HH:mm:ss")
    private Date astahour;

    /** 结束时间 */
    @JsonFormat(pattern = "HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "HH:mm:ss")
    private Date aendhour;

    /** 地点 */
    @Excel(name = "地点")
    private String alocation;

    /** 对象 */
    @Excel(name = "对象")
    private String aobject;

    /** 人数 */
    @Excel(name = "人数")
    private Integer anum;

    /** 分数类型 */
    @Excel(name = "分数类型")
    private String agradetype;

    /** 总时间 */
    @Excel(name = "总时间")
    private Integer ahour;

    /** 分数 */
    @Excel(name = "分数")
    private Integer agrade;

    public AysAct getAysAct() {
        return aysAct;
    }

    public void setAysAct(AysAct aysAct) {
        this.aysAct = aysAct;
    }

    private AysAct aysAct;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public AysOrg getOrg() {
        return org;
    }

    public void setOrg(AysOrg org) {
        this.org = org;
    }

    private AysOrg org;

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }


    public void setActId(Long actId)
    {
        this.actId = actId;
    }

    public Long getActId()
    {
        return actId;
    }
    public void setOrgId(Long orgId)
    {
        this.orgId = orgId;
    }

    public Long getOrgId()
    {
        return orgId;
    }
    public void setAname(String aname)
    {
        this.aname = aname;
    }

    public String getAname()
    {
        return aname;
    }
    public void setAsubject(String asubject)
    {
        this.asubject = asubject;
    }

    public String getAsubject()
    {
        return asubject;
    }
    public void setAtype(String atype)
    {
        this.atype = atype;
    }

    public String getAtype()
    {
        return atype;
    }
    public void setAstart(Date astart)
    {
        this.astart = astart;
    }

    public Date getAstart()
    {
        return astart;
    }
    public void setAend(Date aend)
    {
        this.aend = aend;
    }

    public Date getAend()
    {
        return aend;
    }
    public void setAlocation(String alocation)
    {
        this.alocation = alocation;
    }


    public Date getAstahour()
    {
        return astahour;
    }
    public void setAendhour(Date aendhour)
    {
        this.aendhour = aendhour;
    }

    public Date getAendhour()
    {
        return aendhour;
    }

    public String getAlocation()
    {
        return alocation;
    }
    public void setAobject(String aobject)
    {
        this.aobject = aobject;
    }

    public String getAobject()
    {
        return aobject;
    }
    public void setAnum(Integer anum)
    {
        this.anum = anum;
    }

    public Integer getAnum()
    {
        return anum;
    }
    public void setAgradetype(String agradetype)
    {
        this.agradetype = agradetype;
    }

    public String getAgradetype()
    {
        return agradetype;
    }
    public void setAgrade(Integer agrade)
    {
        this.agrade = agrade;
    }

    public void setAhour(Integer ahour)
    {
        this.ahour = ahour;
    }

    public Integer getAhour()
    {
        return ahour;
    }

    public Integer getAgrade()
    {
        return agrade;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("actId", getActId())
                .append("orgId", getOrgId())
                .append("aname", getAname())
                .append("asubject", getAsubject())
                .append("atype", getAtype())
                .append("astart", getAstart())
                .append("aend", getAend())
                .append("astahour", getAstahour())
                .append("aendhour", getAendhour())
                .append("alocation", getAlocation())
                .append("aobject", getAobject())
                .append("anum", getAnum())
                .append("agradetype", getAgradetype())
                .append("ahour", getAhour())
                .append("agrade", getAgrade())
                .append("delFlag", getDelFlag())
                .append("org", getOrg())
                .toString();
    }
}
