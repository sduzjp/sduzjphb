package cn.edu.sdu.ise.labs.model;

import lombok.Data;

import java.util.Date;

/**
 * 令牌实体类
 *
 * @author 李洪文
 * @date 2019/12/3 10:38
 */
@Data
public class Token {
    /**
     * 令牌token
     */
    private String accessToken;

    /**
     * 租户代码
     */
    private String tenantCode;
    /**
     * 比赛项目编码
     */
    private String competitionEventCode;
    /**
     * 教师名称
     */
    private String teacherName;

    /**
     * 教师编码
     */
    private String teacherCode;
    /**
     * 上次动作
     */
    private Date lastAction;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新人
     */
    private String updatedBy;
    /**
     * 更新时间
     */
    private Date updatedAt;

}
