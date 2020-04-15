package cn.edu.sdu.ise.labs.dto;

import lombok.Data;

/**
 * 修改比赛项目入口参数DTO类
 * 参数名          参数描述         数据类型
 * competitionEventCode     比赛项目编码      String
 * competitionEventName     比赛项目名称      String
 * planStartAt      计划开始时间      String
 * planEndAt        计划结束时间      String
 * suiteType        组别          number
 * status           状态          number
 *
 * @ author:周健平
 * @ date:2020/3/25 20:56
 * @ modifiedBy:
 */
@Data
public class ModifyCompetitionEventDTO {
    /**
     * 比赛项目编码
     */
    private String competitionEventCode;
    /**
     * 比赛项目名称
     */
    private String competitionEventName;
    /**
     * 计划开始时间
     */
    private String planStartAt;
    /**
     * 计划结束时间
     */
    private String planEndAt;
    /**
     * 组别
     */
    private Integer suiteType;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 更新人
     */
    private String updatedBy;
    /**
     * 创建人
     */
    private String createdBy;
}
