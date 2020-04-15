package cn.edu.sdu.ise.labs.vo;

import lombok.Data;

/**
 * 获取比赛项目详情出参VO类，返回一个类
 * 参数名      参数描述        数据类型
 * competitionEventCode     比赛项目编码      String
 * competitionEventName     比赛项目名称      String
 * planStartAt      计划开始时间      String
 * planEndAt        计划结束时间      String
 * suiteType        组别          number
 * suiteTypeDesc        组别名称        String
 * status       状态      number
 * statusDesc       状态描述        String
 * createdAt        创建时间        String
 * createdBy        创建人          String
 * updatedAt        更新时间        String
 * updatedBy        更新人          String
 *
 * @ author:周健平
 * @ date:2020/3/25 20:42
 * @ modifiedBy:
 */
@Data
public class CompetitionEventVO {
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
     * 组别名称
     */
    private String suiteTypeDesc;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 状态描述
     */
    private String statusDesc;
    /**
     * 创建时间
     */
    private String createdAt;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 更新时间
     */
    private String updatedAt;
    /**
     * 更新人
     */
    private String updatedBy;

}
