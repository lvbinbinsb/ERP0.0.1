package cn.erp.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.erp.dto.MenuVo;
import cn.erp.pojo.TblMenu;
import cn.erp.pojo.TblMenuExample;
import cn.erp.util.PageResult;

public interface TblMenuMapper {
    int countByExample(TblMenuExample example);

    int deleteByExample(TblMenuExample example);

    int deleteByPrimaryKey(Long uuid);

    int insert(TblMenu record);

    int insertSelective(TblMenu record);

    List<TblMenu> selectByExample(TblMenuExample example);

    TblMenu selectByPrimaryKey(Long uuid);

    int updateByExampleSelective(@Param("record") TblMenu record, @Param("example") TblMenuExample example);

    int updateByExample(@Param("record") TblMenu record, @Param("example") TblMenuExample example);

    int updateByPrimaryKeySelective(TblMenu record);

    int updateByPrimaryKey(TblMenu record);
    
    List<MenuVo> selectSonAndFather(PageResult pageResult);
}