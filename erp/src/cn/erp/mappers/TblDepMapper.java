package cn.erp.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.erp.pojo.TblDep;
import cn.erp.pojo.TblDepExample;

public interface TblDepMapper {
    int countByExample(TblDepExample example);

    int deleteByExample(TblDepExample example);

    int deleteByPrimaryKey(Long uuid);

    int insert(TblDep record);

    int insertSelective(TblDep record);

    List<TblDep> selectByExample(TblDepExample example);

    TblDep selectByPrimaryKey(Long uuid);

    int updateByExampleSelective(@Param("record") TblDep record, @Param("example") TblDepExample example);

    int updateByExample(@Param("record") TblDep record, @Param("example") TblDepExample example);

    int updateByPrimaryKeySelective(TblDep record);

    int updateByPrimaryKey(TblDep record);

    /**
     * list数据
     * @param pageNo
     * @param pageSize
     * @return
     */
	List<TblDep> selListPage(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize, @Param("name") String name,@Param("tele")  String tele);
	
	int selListPageCount(@Param("name") String name,@Param("tele")  String tele);
}