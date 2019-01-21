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
	 * ��ѯ��������
	 * @param id
	 */
	ExpenditureDto findExpenditure(int id);
	/**
	 * ��ҳ��ѯ�����
	 * @return
	 */
	List<ExpenditureDto> findExpenditureList(Expenditure entity);
	/**
	 * ͨ��ʱ��β�ѯ�����
	 * @param startDate append 00:00:00
	 * @param endDate append 23:59:59
	 * @return
	 */
	List<ExportExpenditureDto> exportExpenditureListByDate(String startDate,String endDate);
	/**
	 * �˿ڽ����
	 * @return
	 */
	List<PortType> findPortTypeList();
	int findPortByName(String name);
	/**
	 *	һ�����������
	 * @return
	 */
	List<ChannelDetailStair> findChannelStairList(int portId);
	ChannelDetailStair findChannelStair(String name,int portId);
	/**
	 * ͨ��stairId����������������
	 * @param stairId  
	 * @return
	 */
	List<ChannelDetailSecond> findChannelSecondList(long stairId);
	ChannelDetailSecond findChannelSecond(String name,long stairId);
	/**
	 * ͨ��stairId,secondId����������������
	 * @return
	 */
	List<ChannelDetailThird> findChannelThirdList(long stairId,long secondId);
	ChannelDetailThird findChannelThird(String name,long secondId);
	
	int addExpenditure(Expenditure entity);
	/**
	 * �������
	 * @param entitys
	 * @return
	 */
	int addExpenditureList(ExpenditurelListDto arrays);
	/**
	 * �����������
	 * @param details
	 * @return
	 * @throws Exception 
	 */
	void addChannelDetailList(ChannelDetailDto channels) throws Exception;

	/**
	 *��Ӷ˿�
	 * @param types
	 * @return
	 */
	int addPortType(PortType type);
	/**
	 *����Expenditure
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
