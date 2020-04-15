package cn.edu.sdu.ise.labs.service.impl;

import cn.edu.sdu.ise.labs.constant.PrefixConstant;
import cn.edu.sdu.ise.labs.dao.DepartmentMapper;
import cn.edu.sdu.ise.labs.dto.DepartmentDTO;
import cn.edu.sdu.ise.labs.dto.DepartmentQueryDTO;
import cn.edu.sdu.ise.labs.model.Department;
import cn.edu.sdu.ise.labs.model.Page;
import cn.edu.sdu.ise.labs.model.Token;
import cn.edu.sdu.ise.labs.service.DepartmentService;
import cn.edu.sdu.ise.labs.service.KeyMaxValueService;
import cn.edu.sdu.ise.labs.service.utils.DepartmentUtils;
import cn.edu.sdu.ise.labs.utils.FormatUtils;
import cn.edu.sdu.ise.labs.utils.PageUtils;
import cn.edu.sdu.ise.labs.utils.TokenContextHolder;
import cn.edu.sdu.ise.labs.vo.DepartmentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 先看懂department的业务逻辑然后自己写接口实现类的业务逻辑！！
 *
 * @author 李洪文
 * @description
 * @date 2019/12/3 9:33
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    //DepartmentMapper接口中定义的是关于增删改查数据库表的抽象方法，由mybatis自动生成。

    @Autowired
    private DepartmentMapper departmentMapper;

    //KeyMaxValueService接口定义的是生成各业务表的抽象方法

    @Autowired
    private KeyMaxValueService keyMaxValueService;

    //覆盖重写接口中的抽象方法

    /**
     * 根据查询条件返回部门信息列表分页
     * 查询条件有部门名称、租户代码、页码、页记录条数
     * 我的想法：可不可以就是直接根据你查询的这四个条件来判断，如果存在符合这四个条件的信息就返回分页列表，否则就返回null
     *
     * @param queryDTO
     * @return 部门信息列表分页Page<DepartmentVO>
     */
    @Override
    public Page<DepartmentVO> listByPage( DepartmentQueryDTO queryDTO ) {
        //如果入口参数(查询条件)为null，那么自己创建一个查询部门条件的实例，是不是说查询为null时最后返回全部部门的信息？所以先得创建一个
        //queryDTO，以便之后将每条信息都添加到返回的信息列表中？
        if ( queryDTO == null ) {
            queryDTO = new DepartmentQueryDTO( );
        }

        //取得部门名称，但是括号FormatUtils.makeFuzzySearchTerm这是在干什么？
        queryDTO.setDepartmentName( FormatUtils.makeFuzzySearchTerm( queryDTO.getDepartmentName( ) ) );
        //token这个实体类是在干什么？TokenContextHolder这个类中是从前端获得用户输入的查询条件信息吗？
        Token token = TokenContextHolder.getToken( );

        //获取命中个数
        Integer size = departmentMapper.count( queryDTO , token.getTenantCode( ) );
        //pageUtils是一个分页工具类
        PageUtils pageUtils = new PageUtils( queryDTO.getPage( ) , queryDTO.getPageSize( ) , size );
        Page<DepartmentVO> pageData = new Page<>( pageUtils.getPage( ) , pageUtils.getPageSize( ) , pageUtils.getTotal( ) , new ArrayList<>( ) );
        if ( size == 0 ) {
            // 没有命中，则返回空数据。
            return pageData;
        }

        //入口参数为查询条件、开始位置、记录数量、租户代码
        List<Department> list = departmentMapper.list( queryDTO , pageUtils.getOffset( ) , pageUtils.getLimit( ) , token.getTenantCode( ) );
        for ( Department department : list ) {
            //convertToVO将实体对象转换为VO对象，并且将符合查询条件的每一个部门信息加到分页列表中
            pageData.getList( ).add( DepartmentUtils.convertToVO( department ) );
        }

        return pageData;
    }


    /**
     * 新建部门
     *
     * @param departmentDTO 部门输入对象
     * @return 部门编码
     */
    @Override
    public String addDepartment( DepartmentDTO departmentDTO ) {
        // 校验输入数据正确性
        DepartmentUtils.validateDepartment( departmentDTO );
        // 创建实体对象，用以保存到数据库
        Department department = new Department( );
        // 将输入的字段全部复制到实体对象中
        BeanUtils.copyProperties( departmentDTO , department );
        // 生成业务代码
        department.setDepartmentCode( keyMaxValueService.generateBusinessCode( PrefixConstant.DEPARTMENT ) );
        // 将token相关信息填入department对象
        TokenContextHolder.formatInsert( department );

        // 调用DAO方法保存到数据库表
        departmentMapper.insert( department );
        return department.getDepartmentCode( );
    }

    /**
     * 更新部门数据
     *
     * @param departmentDTO 部门输入对象
     * @return 部门编码
     */
    @Override
    public String updateDepartment( DepartmentDTO departmentDTO ) {
        // 校验输入数据正确性
        Token token = TokenContextHolder.getToken( );
        DepartmentUtils.validateDepartment( departmentDTO );
        Assert.hasText( departmentDTO.getDepartmentCode( ) , "部门代码不能为空" );
        Department department = departmentMapper.getByCode( departmentDTO.getDepartmentCode( ) , token.getTenantCode( ) );
        Assert.notNull( department , "为找到部门，代码为：" + departmentDTO.getDepartmentCode( ) );

        BeanUtils.copyProperties( departmentDTO , department );
        department.setUpdatedBy( token.getTenantCode( ) );
        departmentMapper.updateByPrimaryKey( department );
        return department.getDepartmentCode( );
    }

    /**
     * 根据编码列表，批量删除部门
     *
     * @param codeList 编码列表
     */
    @Override
    public void deleteByCodes( List<String> codeList ) {
        Assert.notEmpty( codeList , "部门编码列表不能为空" );
        Token token = TokenContextHolder.getToken( );
        departmentMapper.deleteByCodes( codeList , token.getTeacherCode( ) , token.getTenantCode( ) );
    }

    /**
     * 根据编码列表，获取部门集合（内部调用）
     *
     * @param codeList 部门编码列表
     * @return 包含部门信息的映射，key是部门编码
     */
    @Override
    public Map<String, DepartmentVO> getDepartmentMap( List<String> codeList ) {
        if ( CollectionUtils.isEmpty( codeList ) ) {
            return new HashMap<>( 1 << 2 );
        }

        Token token = TokenContextHolder.getToken( );
        List<Department> departmentList = departmentMapper.listByCodes( codeList , token.getTenantCode( ) );
        return departmentList.stream( )
                .map( item -> DepartmentUtils.convertToVO( item ) )
                .collect( Collectors.toMap( DepartmentVO::getDepartmentCode , Function.identity( ) ) );
    }

    /**
     * 根据部门名称获取下拉列表
     *
     * @param departmentName 部门名称（模糊匹配）
     * @return 部门列表
     */
    @Override
    public List<DepartmentVO> listByName( String departmentName ) {
        Token token = TokenContextHolder.getToken( );
        departmentName = FormatUtils.makeFuzzySearchTerm( departmentName );
        List<Department> departmentList = departmentMapper.listByName( departmentName , token.getTenantCode( ) );
        return departmentList.stream( )
                .map( item -> DepartmentUtils.convertToVO( item ) )
                .collect( Collectors.toList( ) );
    }
}
