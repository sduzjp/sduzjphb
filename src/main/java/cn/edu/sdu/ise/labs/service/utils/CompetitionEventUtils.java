package cn.edu.sdu.ise.labs.service.utils;

import cn.edu.sdu.ise.labs.dto.ModifyCompetitionEventDTO;
import cn.edu.sdu.ise.labs.dto.NewCompetitionEventDTO;
import cn.edu.sdu.ise.labs.model.CompetitionEvent;
import cn.edu.sdu.ise.labs.utils.FormatUtils;
import cn.edu.sdu.ise.labs.vo.CompetitionEventVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

/**
 * @ author:周健平
 * @ date:2020/3/29 11:29
 * @ modifiedBy:
 */
public class CompetitionEventUtils {
    /**
     * 规范并校验NewCompetitionEventDTO
     *
     * @param newCompetitionEventDTO
     */
    public static void validateNewCompetitionEventDTO( NewCompetitionEventDTO newCompetitionEventDTO ) {
        FormatUtils.trimFieldToNull( newCompetitionEventDTO );
        Assert.notNull( newCompetitionEventDTO , "比赛项目输入数据不能为空" );
        Assert.hasText( newCompetitionEventDTO.getCompetitionEventName( ) , "比赛项目名称不能为空" );
    }

    /**
     * 规范并校验modifyCompetitionEventDTO
     *
     * @param modifyCompetitionEventDTO
     */
    public static void validateModifyCompetitionEventDTO( ModifyCompetitionEventDTO modifyCompetitionEventDTO ) {
        FormatUtils.trimFieldToNull( modifyCompetitionEventDTO );
        Assert.notNull( modifyCompetitionEventDTO , "比赛项目输入数据不能为空" );
        Assert.hasText( modifyCompetitionEventDTO.getCompetitionEventName( ) , "比赛项目名称不能为空" );
    }

    /**
     * 将实体对象转换为VO对象
     *
     * @param competitionevent 实体对象
     * @return VO对象
     */
    public static CompetitionEventVO convertToVO( CompetitionEvent competitionevent ) {
        CompetitionEventVO competitionEventVO = new CompetitionEventVO( );
        BeanUtils.copyProperties( competitionevent , competitionEventVO );
        competitionEventVO.setPlanStartAt( FormatUtils.formatDate( competitionevent.getPlanStartAt( ) ) );
        competitionEventVO.setPlanEndAt( FormatUtils.formatDate( competitionevent.getPlanEndAt( ) ) );
        competitionEventVO.setCreatedAt( FormatUtils.formatFullDate( competitionevent.getCreatedAt( ) ) );
        competitionEventVO.setUpdatedAt( FormatUtils.formatFullDate( competitionevent.getUpdatedAt( ) ) );
        return competitionEventVO;
    }
}
