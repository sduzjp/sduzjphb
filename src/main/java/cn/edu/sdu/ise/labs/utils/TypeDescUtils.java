package cn.edu.sdu.ise.labs.utils;

import cn.edu.sdu.ise.labs.model.CompetitionEvent;
import cn.edu.sdu.ise.labs.vo.CompetitionEventVO;

/**
 * @ author:周健平
 * @ date:2020/4/1 15:29
 * @ modifiedBy:
 */
public class TypeDescUtils {
    public static void SuiteTypeDesc( CompetitionEventVO competitionEventVO ) {
        if ( competitionEventVO.getSuiteType( ) == 1 ) {
            competitionEventVO.setSuiteTypeDesc( "成年组" );
        } else if ( competitionEventVO.getSuiteType( ) == 2 ) {
            competitionEventVO.setSuiteTypeDesc( "青少年组" );
        } else if ( competitionEventVO.getSuiteType( ) == 3 ) {
            competitionEventVO.setSuiteTypeDesc( "老年组" );
        } else {
            competitionEventVO.setSuiteType( null );
        }
    }

    public static void StatusTypeDesc( CompetitionEventVO competitionEventVO ) {
        if ( competitionEventVO.getStatus( ) == 1 ) {
            competitionEventVO.setStatusDesc( "未开始" );
        } else if ( competitionEventVO.getStatus( ) == 2 ) {
            competitionEventVO.setStatusDesc( "进行中" );
        } else if ( competitionEventVO.getStatus( ) == 3 ) {
            competitionEventVO.setStatusDesc( "已结束" );
        } else {
            competitionEventVO.setStatusDesc( null );
        }
    }

    public static String SuiteTypeDescValid( CompetitionEvent competitionEvent ) {
        if ( competitionEvent.getSuiteType( ) == 1 ) {
            return "1";
        } else if ( competitionEvent.getSuiteType( ) == 2 ) {
            return "2";
        } else if ( competitionEvent.getSuiteType( ) == 3 ) {
            return "3";
        } else {
            return null;
        }
    }

    public static String StatusDescValid( CompetitionEvent competitionEvent ) {
        if ( competitionEvent.getStatus( ) == 1 ) {
            return "1";
        } else if ( competitionEvent.getStatus( ) == 2 ) {
            return "2";
        } else if ( competitionEvent.getStatus( ) == 3 ) {
            return "3";
        } else {
            return null;
        }
    }

//    public static
}
