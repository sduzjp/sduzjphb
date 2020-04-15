package cn.edu.sdu.ise.labs.model;

import lombok.Data;

import java.util.Date;

/**
 * @ author:周健平
 * @ date:2020/3/25 17:26
 * @ modifiedBy:
 */
@Data
public class CompetitionEvent {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 比赛项目编码
     */
    private String competitionEventCode;
    /**
     * 比赛项目名称
     */
    private String competitionEventName;
    /**
     * 组别
     */
    private Integer suiteType;
    /**
     * 计划开始日期
     */
    private Date planStartAt;
    /**
     * 计划结束日期
     */
    private Date planEndAt;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新时间
     */
    private Date updatedAt;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 更新人
     */
    private String updatedBy;
}