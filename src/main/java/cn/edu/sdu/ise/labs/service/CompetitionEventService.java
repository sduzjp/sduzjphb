package cn.edu.sdu.ise.labs.service;

import cn.edu.sdu.ise.labs.dto.CompetitionEventListDTO;
import cn.edu.sdu.ise.labs.dto.ModifyCompetitionEventDTO;
import cn.edu.sdu.ise.labs.dto.NewCompetitionEventDTO;
import cn.edu.sdu.ise.labs.model.Page;
import cn.edu.sdu.ise.labs.vo.CompetitionEventVO;

import java.util.List;

/**
 * @ author:周健平
 * @ date:2020/3/29 14:51
 * @ modifiedBy:
 */
public interface CompetitionEventService {
    /**
     * 实现获取比赛项目详情
     *
     * @param eventCode 入口参数为比赛项目编码
     * @return CompetitionEventDetailsVO类
     */
    CompetitionEventVO getDetails( String eventCode );

    /**
     * 获取比赛项目列表（分页）
     *
     * @param competitionEventListDTO 入口参数为比赛项目名称、代表队编码、注册号、证件号
     * @return 比赛项目列表
     */
    Page<CompetitionEventVO> listByPage( CompetitionEventListDTO competitionEventListDTO );

    /**
     * 新建比赛项目
     *
     * @param newCompetitionEventDTO 入口参数为比赛项目名称、开始时间、结束时间、组别、状态
     * @return 比赛项目编码
     */
    String addCompetitionEvent( NewCompetitionEventDTO newCompetitionEventDTO );

    /**
     * 修改比赛项目
     *
     * @param modifyCompetitionEventDTO 入口参数为比赛项目编码、比赛项目名称、开始时间、结束时间、组别、状态
     * @return 比赛项目编码
     */
    String updateCompetitionEvent( ModifyCompetitionEventDTO modifyCompetitionEventDTO );

    /**
     * 删除比赛项目
     *
     * @param competitionEventCodeList 入口参数为比赛项目编码的列表
     * @return 删除记录的条数
     */
    Integer deleteCompetitionEvent( List<String> competitionEventCodeList );

    /**
     * 根据比赛项目名称获取下拉列表
     *
     * @param competitionEventName
     * @return 比赛项目详情的下拉列表
     */
    List<CompetitionEventVO> listByName( String competitionEventName );
}
