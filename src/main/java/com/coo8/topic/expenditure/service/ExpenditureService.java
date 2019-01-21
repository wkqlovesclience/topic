package com.coo8.topic.expenditure.service;

import java.math.BigDecimal;
import java.util.List;

import com.coo8.topic.expenditure.dto.ChannelDetailDto;
import com.coo8.topic.expenditure.dto.ExpenditureDto;
import com.coo8.topic.expenditure.dto.ExpenditurelListDto;
import com.coo8.topic.expenditure.dto.ExportExpenditureDto;
import com.coo8.topic.expenditure.pojo.*;

public interface ExpenditureService {

	/**
	 * 查询单个对象
	 * @param id
	 */
	ExpenditureDto findExpenditure(int id);
	/**
	 * 分页查询结果集
	 * @return
	 */
	List<ExpenditureDto> findExpenditureList(Expenditure entity);
	/**
	 * 通过时间段查询结果集
	 * @param startDate append 00:00:00
	 * @param endDate append 23:59:59
	 * @return
	 */
	List<ExportExpenditureDto> exportExpenditureListByDate(String startDate,String endDate);
	/**
	 * 端口结果集
	 * @return
	 */
	List<PortType> findPortTypeList();
	int findPortByName(String name);
	/**
	 *	一级渠道结果集
	 * @return
	 */
	List<ChannelDetailStair> findChannelStairList(int portId);
	ChannelDetailStair findChannelStair(String name,int portId);
	/**
	 * 通过stairId查出二级渠道结果集
	 * @param stairId  
	 * @return
	 */
	List<ChannelDetailSecond> findChannelSecondList(long stairId);
	ChannelDetailSecond findChannelSecond(String name,long stairId);
	/**
	 * 通过stairId,secondId查出三级渠道结果集
	 * @return
	 */
	List<ChannelDetailThird> findChannelThirdList(long stairId,long secondId);
	ChannelDetailThird findChannelThird(String name,long secondId);
	
	int addExpenditure(Expenditure entity);
	/**
	 * 批量添加
	 * @param entitys
	 * @return
	 */
	int addExpenditureList(ExpenditurelListDto arrays);
	/**
	 * 批量添加渠道
	 * @param details
	 * @return
	 * @throws Exception 
	 */
	void addChannelDetailList(ChannelDetailDto channels) throws Exception;

	/**
	 *添加端口
	 * @param types
	 * @return
	 */
	int addPortType(PortType type);
	/**
	 *更新Expenditure
	 * @param dto
	 * @return
	 */
	int modifyExpenditure(ExpenditureDto dto);

	List<ExpendAnalyseBase> getExpenditureAnalyseListByDate(String startDate, String endDate);

	List<ExpendAnalyseCharge> selectExpendDataInMonth(String yearNum,Integer stairId);

	List<ExpendPortAnalyseCharge> selectPortExpendDataInMonth(String yearNum,Integer portId);

	List<WholeExpendAnalyseCharge> selectWholeExpendDataInMonth(String yearNum);

	BigDecimal selectPortExpendAmount(String startDate, String endDate, Integer portId);

	BigDecimal selectWholeExpendAmount(String startDate,String endDate);

}
