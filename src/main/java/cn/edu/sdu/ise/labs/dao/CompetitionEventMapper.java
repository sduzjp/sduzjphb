package cn.edu.sdu.ise.labs.dao;

import cn.edu.sdu.ise.labs.dto.CompetitionEventListDTO;
import cn.edu.sdu.ise.labs.model.CompetitionEvent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 获取比赛项目详情
 * 增删改查即可，对方法加上备注！
 *
 * @ author:周健平
 * @ date:2020/3/25 17:26
 * @ modifiedBy:
 */
@Mapper
public interface CompetitionEventMapper {
    /**
     * 根据主键删除记录
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey( Integer id );

    /**
     * 新增记录
     *
     * @param record
     * @return
     */
    int insert( CompetitionEvent record );

    /**
     * 根据主键查询记录
     *
     * @param id
     * @return
     */
    CompetitionEvent selectByPrimaryKey( Integer id );

    /**
     * 根据主键修改更新记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey( CompetitionEvent record );

    /**
     * 根据比赛项目编码获取比赛项目详情，入口参数为比赛项目编码
     *
     * @param competitionEventCode 比赛项目编码
     * @return 比赛项目信息详情
     */

    CompetitionEvent getByCode(
            @Param("competitionEventCode") String competitionEventCode );

    /**
     * 根据查询条件获取命中个数，入口参数为比赛项目名称
     *
     * @param competitionEventName 查询条件:比赛项目名称
     * @param tenantCode           租户代码
     * @return 命中数量
     */
    Integer count(
            @Param("competitionEventName") String competitionEventName ,
            @Param("tenantCode") String tenantCode
    );

    /**
     * 根据查询条件获取命中个数，入口参数为比赛项目编码
     *
     * @param competitionEventCode 查询条件:比赛项目编码
     * @return 命中数量
     */
    Integer countCode(
            @Param("competitionEventCode") String competitionEventCode
    );

    /**
     * 根据查询条件获取比赛项目列表
     *
     * @param eventListDTO 查询条件
     * @param offset       开始位置
     * @param limit        记录数量
     * @param tenantCode   租户代码
     * @return 比赛项目列表
     */
    List<CompetitionEvent> eventList(
            @Param("eventListDTO") CompetitionEventListDTO eventListDTO ,
            @Param("tenantCode") String tenantCode ,
            @Param("offset") Integer offset ,
            @Param("limit") Integer limit );

    /**
     * 根据比赛项目编码列表批量删除比赛项目信息
     * sql语句写的是否正确？
     *
     * @param competitionEventCodeList 比赛项目编码列表
     */
    void deleteByCodes(
            @Param("competitionEventCodeList") List<String> competitionEventCodeList );

    /**
     * 根据比赛项目名称获取比赛项目详情下拉列表
     *
     * @param competitionEventName
     * @return 比赛项目详情下拉列表
     */
    List<CompetitionEvent> listByName(
            @Param("competitionEventName") String competitionEventName

    );

}