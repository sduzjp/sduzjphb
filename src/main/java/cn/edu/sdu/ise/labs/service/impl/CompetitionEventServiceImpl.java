package cn.edu.sdu.ise.labs.service.impl;

import cn.edu.sdu.ise.labs.constant.PrefixConstant;
import cn.edu.sdu.ise.labs.dao.CompetitionEventMapper;
import cn.edu.sdu.ise.labs.dto.CompetitionEventListDTO;
import cn.edu.sdu.ise.labs.dto.ModifyCompetitionEventDTO;
import cn.edu.sdu.ise.labs.dto.NewCompetitionEventDTO;
import cn.edu.sdu.ise.labs.model.CompetitionEvent;
import cn.edu.sdu.ise.labs.model.Page;
import cn.edu.sdu.ise.labs.model.Token;
import cn.edu.sdu.ise.labs.service.CompetitionEventService;
import cn.edu.sdu.ise.labs.service.KeyMaxValueService;
import cn.edu.sdu.ise.labs.service.utils.CompetitionEventUtils;
import cn.edu.sdu.ise.labs.utils.FormatUtils;
import cn.edu.sdu.ise.labs.utils.PageUtils;
import cn.edu.sdu.ise.labs.utils.TokenContextHolder;
import cn.edu.sdu.ise.labs.utils.TypeDescUtils;
import cn.edu.sdu.ise.labs.vo.CompetitionEventVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description:比赛项目管理服务
 *
 * @ author:周健平
 * @ date:2020/3/29 15:06
 * @ modifiedBy:
 */
@Service
public class CompetitionEventServiceImpl implements CompetitionEventService {

    @Autowired
    private CompetitionEventMapper competitionEventMapper;

    @Autowired
    private KeyMaxValueService keyMaxValueService;

    /**
     * 获取比赛项目详情
     *
     * @param eventCode 入口参数为比赛项目编码
     * @return competitionEventVO对象，比赛项目详情
     */
    @Override
    public CompetitionEventVO getDetails( String eventCode ) {
        //用户登录后保存用户个人信息
        Token token = TokenContextHolder.getToken( );

        //创建一个competitionEventVO对象用来返回符合查询比赛项目编码的项目详情
        CompetitionEventVO competitionEventVO = new CompetitionEventVO( );

        //利用模糊查询
        competitionEventVO.setCompetitionEventCode( FormatUtils.makeFuzzySearchTerm( eventCode ) );

        //获取命中个数
        Integer size = competitionEventMapper.count( competitionEventVO.getCompetitionEventCode( ) );
        if ( size == 0 ) {
            // 没有命中，则返回空数据。
            return competitionEventVO;
        }

        //若命中则将比赛项目实体类对象转换为VO对象
        competitionEventVO = CompetitionEventUtils.convertToVO( competitionEventMapper.getByCode( eventCode ) );

        //加上组别名称和状态描述
        TypeDescUtils.SuiteTypeDesc( competitionEventVO );
        TypeDescUtils.StatusTypeDesc( competitionEventVO );

        //返回VO对象
        return competitionEventVO;
    }

    /**
     * 获取比赛项目列表（分页）
     *
     * @param competitionEventListDTO 入口参数为比赛项目名称，页码，每页记录条数
     * @return 比赛项目列表
     */
    @Override
    public Page<CompetitionEventVO> listByPage( CompetitionEventListDTO competitionEventListDTO ) {
        //防止发生空指针异常
        if ( competitionEventListDTO == null ) {
            competitionEventListDTO = new CompetitionEventListDTO( );
        }
        String name = competitionEventListDTO.getCompetitionEventName( );
        //取得比赛项目名称，进行模糊查询
        competitionEventListDTO.setCompetitionEventName( FormatUtils.makeFuzzySearchTerm( competitionEventListDTO.getCompetitionEventName( ) ) );

        // 根据比赛项目名称获取命中个数
        Integer size = competitionEventMapper.count( competitionEventListDTO.getCompetitionEventName( ) );

        //pageUtils是一个分页工具类，入口参数为页码、页记录数、总记录数
        PageUtils pageUtils = new PageUtils( competitionEventListDTO.getPage( ) , competitionEventListDTO.getPageSize( ) , size );
        Page<CompetitionEventVO> pageData = new Page<>( pageUtils.getPage( ) , pageUtils.getPageSize( ) , pageUtils.getTotal( ) , new ArrayList<>( ) );
        if ( size == 0 ) {
            // 没有命中，则返回空数据。
            return pageData;
        }

        //入口参数为查询条件，比赛项目名称，页码，每页记录条数
        CompetitionEventVO competitionEventVO = new CompetitionEventVO( );
        competitionEventListDTO.setCompetitionEventName( name );
        List<CompetitionEvent> list = competitionEventMapper.eventList( competitionEventListDTO );
        for ( CompetitionEvent competitionEvent : list ) {
            //convertToVO将实体对象转换为VO对象，并且将符合查询条件的每一个部门信息加到分页列表中
            competitionEventVO = CompetitionEventUtils.convertToVO( competitionEvent );
            TypeDescUtils.StatusTypeDesc( competitionEventVO );
            TypeDescUtils.SuiteTypeDesc( competitionEventVO );
            if ( competitionEventVO.getSuiteType( ) != null && competitionEventVO.getStatusDesc( ) != null ) {
                pageData.getList( ).add( competitionEventVO );
            }
        }
        return pageData;

    }

    /**
     * 新建比赛项目
     *
     * @param newCompetitionEventDTO 入口参数为比赛项目名称、计划开始时间、计划结束时间、组别、状态、创建人、更新人
     * @return 比赛项目编码
     */
    @Override
    public String addCompetitionEvent( NewCompetitionEventDTO newCompetitionEventDTO ) {
        // 校验输入数据正确性
        CompetitionEventUtils.validateNewCompetitionEventDTO( newCompetitionEventDTO );

        // 创建实体对象，用以保存到数据库
        CompetitionEvent competitionEvent = new CompetitionEvent( );

        // 将输入的字段全部复制到实体对象中
        BeanUtils.copyProperties( newCompetitionEventDTO , competitionEvent );
        competitionEvent.setPlanStartAt( FormatUtils.parseDate( newCompetitionEventDTO.getPlanStartAt( ) ) );
        competitionEvent.setPlanEndAt( FormatUtils.parseDate( newCompetitionEventDTO.getPlanEndAt( ) ) );

        //校验组别和状态是否输入正确
        if ( TypeDescUtils.SuiteTypeDescValid( competitionEvent ) == null || TypeDescUtils.StatusDescValid( competitionEvent ) == null ) {
            return null;
        }

        // 生成业务编码，比赛项目编码
        competitionEvent.setCompetitionEventCode( keyMaxValueService.generateBusinessCode( PrefixConstant.COMPETITIONEVENT ) );

        // 将token相关信息填入competitionEvent对象
        TokenContextHolder.formatInsert( competitionEvent );

        // 将项目创建人、更新人填入
        competitionEvent.setCreatedBy( newCompetitionEventDTO.getCreatedBy( ) );
        competitionEvent.setUpdatedBy( newCompetitionEventDTO.getUpdatedBy( ) );

        // 调用DAO方法保存到数据库表
        competitionEventMapper.insert( competitionEvent );

        //返回新建比赛项目编码
        return competitionEvent.getCompetitionEventCode( );
    }

    /**
     * 修改更新比赛项目
     *
     * @param modifyCompetitionEventDTO 入口参数为比赛项目编码、比赛项目名称、开始时间、结束时间、组别、状态
     * @return 比赛项目编码
     */
    @Override
    public String updateCompetitionEvent( ModifyCompetitionEventDTO modifyCompetitionEventDTO ) {
        // 校验输入数据正确性
        CompetitionEventUtils.validateModifyCompetitionEventDTO( modifyCompetitionEventDTO );
        Assert.hasText( modifyCompetitionEventDTO.getCompetitionEventCode( ) , "比赛项目编码不能为空" );

        //校验比赛项目编码存在性，若存在则获取命中个数，若不存在则返回null和报错“比赛项目编码不存在”
        Integer size = competitionEventMapper.countCode( modifyCompetitionEventDTO.getCompetitionEventCode( ) );
        if ( size == 0 ) {
            System.out.println( "比赛项目编码不存在" );
            return null;
        }

        //根据比赛项目编码修改比赛项目信息
        CompetitionEvent competitionEvent = competitionEventMapper.getByCode( modifyCompetitionEventDTO.getCompetitionEventCode( ) );
        competitionEvent.setPlanStartAt( FormatUtils.parseDate( modifyCompetitionEventDTO.getPlanStartAt( ) ) );
        competitionEvent.setPlanEndAt( FormatUtils.parseDate( modifyCompetitionEventDTO.getPlanEndAt( ) ) );
        BeanUtils.copyProperties( modifyCompetitionEventDTO , competitionEvent );

        // 将token相关信息填入competitionEvent对象
        TokenContextHolder.formatInsert( competitionEvent );

        //将创建人和更新人信息送入competitionEvent
        competitionEvent.setCreatedBy( modifyCompetitionEventDTO.getCreatedBy( ) );
        competitionEvent.setUpdatedBy( modifyCompetitionEventDTO.getUpdatedBy( ) );

        //调用DAO方法保存到数据库表
        competitionEventMapper.updateByPrimaryKey( competitionEvent );

        //返回修改更新的比赛项目编码
        return competitionEvent.getCompetitionEventCode( );

    }

    /**
     * 删除比赛项目
     *
     * @param competitionEventCodeList 入口参数为比赛项目编码的列表
     * @return 删除记录的条数
     */
    @Override
    public Integer deleteCompetitionEvent( List<String> competitionEventCodeList ) {
        //用户登录后保存用户个人信息
        Token token = TokenContextHolder.getToken( );

        //校验比赛项目列表的输入正确性
        Assert.notEmpty( competitionEventCodeList , "比赛项目编码列表不能为空" );

        //获取比赛项目编码命中个数即将要删除的记录条数
        Integer size = 0;
        for ( int i = 0 ; i < competitionEventCodeList.size( ) ; i++ ) {
            size += competitionEventMapper.count( competitionEventCodeList.get( i ) );
        }

        //根据比赛项目编码列表批量删除比赛项目信息
        competitionEventMapper.deleteByCodes( competitionEventCodeList );

        //返回删除的记录条数
        return size;
    }

    @Override
    public List<CompetitionEventVO> listByName( String competitionEventName ) {
        Token token = TokenContextHolder.getToken( );
        //根据比赛项目名称进行模糊查询
        competitionEventName = FormatUtils.makeFuzzySearchTerm( competitionEventName );
        List<CompetitionEvent> competitionEventList = competitionEventMapper.listByName( competitionEventName );
        return competitionEventList.stream( )
                .map( item -> CompetitionEventUtils.convertToVO( item ) )
                .collect( Collectors.toList( ) );
    }
}
