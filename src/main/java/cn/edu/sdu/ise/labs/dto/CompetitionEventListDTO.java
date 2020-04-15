package cn.edu.sdu.ise.labs.dto;

import lombok.Data;

/**
 * 获取比赛项目列表入口参数DTO类
 * 入口参数：参数名  参数描述  数据类型
 * competitionEventName     比赛项目名称      String
 * teamCode     代表队编码       String
 * regCode      注册号       String
 * idNumber     证件号       String
 * <p>
 * 用于分页的参数
 * 页码   page    Integer
 * 每页记录数    pageSize    Integer
 *
 * @ author:周健平
 * @ date:2020/3/25 20:44
 * @ modifiedBy:
 */
@Data
public class CompetitionEventListDTO {
    /**
     * 比赛项目名称
     */
    private String competitionEventName;
    /**
     * 页码
     */
    private Integer page;
    /**
     * 每页记录数
     */
    private Integer pageSize;
}
