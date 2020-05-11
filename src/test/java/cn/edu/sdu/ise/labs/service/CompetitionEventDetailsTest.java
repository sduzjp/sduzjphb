package cn.edu.sdu.ise.labs.service;

import cn.edu.sdu.ise.labs.dto.CompetitionEventListDTO;
import cn.edu.sdu.ise.labs.dto.ModifyCompetitionEventDTO;
import cn.edu.sdu.ise.labs.dto.NewCompetitionEventDTO;
import cn.edu.sdu.ise.labs.model.Page;
import cn.edu.sdu.ise.labs.model.Token;
import cn.edu.sdu.ise.labs.utils.TokenContextHolder;
import cn.edu.sdu.ise.labs.vo.CompetitionEventVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ author:周健平
 * @ date:2020/3/29 11:38
 * @ modifiedBy:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("cn.edu.sdu.ise.labs.dao")
@Slf4j
public class CompetitionEventDetailsTest {
    @Autowired
    private CompetitionEventService competitionEventService;

    /**
     * 测试获取比赛项目详情接口
     */
    @Test
    public void testGetDetails( ) {
        initCompetitionEventToken( );
        String eventCode = "0001";
        CompetitionEventVO competitionEventVO = new CompetitionEventVO( );
        competitionEventVO = competitionEventService.getDetails( eventCode );
        assert competitionEventService.getDetails( eventCode ) != null;
    }

    /**
     * 测试获取比赛项目列表（分页）接口
     */
    @Test
    public void testListByPage( ) {
        initCompetitionEventToken( );
        CompetitionEventListDTO competitionEventListDTO = new CompetitionEventListDTO( );
        competitionEventListDTO.setPage( 1 );
        competitionEventListDTO.setPageSize( 10 );
        Page<CompetitionEventVO> pageData = competitionEventService.listByPage( competitionEventListDTO );
        assert pageData.getList( ).size( ) > 0;
    }

    /**
     * 测试新建比赛项目接口
     */
    @Test
    public void testAddCompetitionEvent( ) {
        initCompetitionEventToken( );
        NewCompetitionEventDTO newCompetitionEventDTO = new NewCompetitionEventDTO( );
        newCompetitionEventDTO.setCompetitionEventName( "短跑" );
        newCompetitionEventDTO.setPlanStartAt( "2020-03-30" );
        newCompetitionEventDTO.setPlanEndAt( "2020-04-30" );
        newCompetitionEventDTO.setCreatedBy( "张三" );
        newCompetitionEventDTO.setUpdatedBy( "张三" );
        newCompetitionEventDTO.setSuiteType( 3 );
        newCompetitionEventDTO.setStatus( 1 );
        assert competitionEventService.addCompetitionEvent( newCompetitionEventDTO ) != null;
    }

    /**
     * 测试修改比赛项目接口
     */
    @Test
    public void testUpdateCompetitionEvent( ) {
        initCompetitionEventToken( );
        ModifyCompetitionEventDTO modifyCompetitionEventDTO = new ModifyCompetitionEventDTO( );
        modifyCompetitionEventDTO.setCompetitionEventCode( "0002" );
        modifyCompetitionEventDTO.setCompetitionEventName( "长跑" );
        modifyCompetitionEventDTO.setPlanStartAt( "2020-04-01" );
        modifyCompetitionEventDTO.setPlanEndAt( "2020-04-10" );
        modifyCompetitionEventDTO.setSuiteType( 1 );
        modifyCompetitionEventDTO.setStatus( 2 );
        modifyCompetitionEventDTO.setCreatedBy( "张三" );
        modifyCompetitionEventDTO.setUpdatedBy( "李四" );
        assert competitionEventService.updateCompetitionEvent( modifyCompetitionEventDTO ) != null;
    }

    /**
     * 测试根据比赛项目编码批量删除比赛项目列表接口
     */
    @Test
    public void testDeleteCompeitionEvent( ) {
        initCompetitionEventToken( );
        List<CompetitionEventVO> list = competitionEventService.listByName( "长跑" );
        assert list.size( ) != 0;
    }

    /**
     * 测试根据比赛项目名称获取比赛项目下拉列表接口
     */
    @Test
    public void testListByName( ) {
        initCompetitionEventToken( );
        List<CompetitionEventVO> competitionEventVOList = competitionEventService.listByName( "游泳" );
        assert competitionEventVOList.size( ) != 0;
    }

    private void initCompetitionEventToken( ) {
        Token token = new Token( );
        token.setTenantCode( "001" );
        token.setCreatedBy( "周健平" );
        token.setUpdatedBy( "zjp" );
        token.setTeacherCode( "TE00000001" );
        TokenContextHolder.setToken( token );
    }
}
