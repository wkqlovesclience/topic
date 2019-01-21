package com.coo8.topic.expenditure.dao;

import java.math.BigDecimal;
import java.util.List;

import com.coo8.topic.expenditure.pojo.*;
import org.apache.ibatis.annotations.Param;

/**
 * REM后台花费功能Dao
 * @author yangjunjie-ds
 *
 */
public interface ExpenditureMapper {
	/**
	 * 添加单条花费信息
	 * @param exp
	 */
	int insertExpenditureDetail(Expenditure entity);
	/**
	 * 添加多条花费信息
	 * @param exp
	 */
	void insertExpenditureDetailList(List<Expenditure> entitys);
	/**
	 * 根据花费id获得花费对象
	 * @param id
	 * @return
	 */
	Expenditure getExpenditureById(@Param("id") int  id);
	
	/**
	 * 分页获取对象集
	 */
	List<Expenditure> getExpenditureList(Expenditure entity);
	/**
	 * 根据时间段获取对象集
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Expenditure> getExpenditureListByDate(@Param("startDate") String startDate,@Param("endDate") String endDate);

	/**
	 * 根据时间段获取统计对象集
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<ExpendAnalyseBase> getExpenditureAnalyseListByDate(@Param("startDate") String startDate, @Param("endDate") String endDate);

	/**
	 * 修改|删除Expenditure对象
	 * @param entity
	 */
	void updateExpenditure(Expenditure entity);
	
	/**
	 * 添加一级渠道信息
	 * @param detail
	 */
	int insertChannelDetail_Stair(ChannelDetailStair stair);
	/**
	 * 添加二级渠道信息
	 * @param detail
	 */
	int insertChannelDetail_Second(ChannelDetailSecond second);
	/**
	 * 添加三级渠道信息
	 * @param detail
	 */
	int insertChannelDetail_Third(ChannelDetailThird third);
	/**
	 * 批量添加端口类型
	 * @param type
	 * @return
	 */
	int insertPortType(PortType type);
	/**
	 * 根据id查询端口类型
	 * @param id
	 * @return
	 */
	List<PortType> getPortTypeList(@Param("id")int id);
	int getPortByName(@Param("name") String name);
	/**
	 *根据一级渠道id,端口id获取对象
	 * @param id
	 * @param portId
	 * @param delFlag  0:未删除 1:删除 -1:无需根据此状态筛选数据
	 * @return
	 */
	List<ChannelDetailStair> getChannelStairList(@Param("id")long id,@Param("portId")int portId,@Param("delFlag")int delFlag);
	ChannelDetailStair getChannelStairByName(@Param("name")String name,@Param("portId")int portId);
	/**
	 * 根据二级渠道id获取对象
	 * @param id
	 * @param stairId
	 * @param delFlag  0:未删除 1:删除 -1:无需根据此状态筛选数据
	 * @return
	 */
	List<ChannelDetailSecond> getChannelSecondList(@Param("id")long id,@Param("stairId") long stairId,@Param("delFlag")int delFlag);
	ChannelDetailSecond getChannelSecondByName(@Param("name")String name,@Param("stairId")long stairId);
	/**
	 * 根据三级渠道id获取对象
	 * @param id
	 * @param stairId
	 * @param secondId
	 * @param delFlag  0:未删除 1:删除 -1:无需根据此状态筛选数据
	 * @return
	 */
	List<ChannelDetailThird> getChannelThirdList(@Param("id")int id,@Param("stairId") long stairId,@Param("secondId") long secondId,@Param("delFlag")int delFlag);
	ChannelDetailThird getChannelThirdByName(@Param("name")String name,@Param("secondId")long secondId);

	List<ExpendAnalyseCharge> selectExpendDataInMonth(@Param("yearNum")String yearNum,@Param("stairId")Integer stairId);

	List<ExpendPortAnalyseCharge> selectPortExpendDataInMonth(@Param("yearNum")String yearNum,@Param("portId")Integer portId);

	List<WholeExpendAnalyseCharge> selectWholeExpendDataInMonth(@Param("yearNum")String yearNum);

	BigDecimal selectPortExpendAmount(@Param("startDate") String startDate, @Param("endDate") String endDate,@Param("portId")Integer portId);

	BigDecimal selectWholeExpendAmount(@Param("startDate") String startDate, @Param("endDate") String endDate);
	
}
