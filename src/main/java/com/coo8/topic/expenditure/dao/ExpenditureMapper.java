package com.coo8.topic.expenditure.dao;

import java.math.BigDecimal;
import java.util.List;

import com.coo8.topic.expenditure.pojo.*;
import org.apache.ibatis.annotations.Param;

/**
 * REM��̨���ѹ���Dao
 * @author yangjunjie-ds
 *
 */
public interface ExpenditureMapper {
	/**
	 * ��ӵ���������Ϣ
	 * @param exp
	 */
	int insertExpenditureDetail(Expenditure entity);
	/**
	 * ��Ӷ���������Ϣ
	 * @param exp
	 */
	void insertExpenditureDetailList(List<Expenditure> entitys);
	/**
	 * ���ݻ���id��û��Ѷ���
	 * @param id
	 * @return
	 */
	Expenditure getExpenditureById(@Param("id") int  id);
	
	/**
	 * ��ҳ��ȡ����
	 */
	List<Expenditure> getExpenditureList(Expenditure entity);
	/**
	 * ����ʱ��λ�ȡ����
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Expenditure> getExpenditureListByDate(@Param("startDate") String startDate,@Param("endDate") String endDate);

	/**
	 * ����ʱ��λ�ȡͳ�ƶ���
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<ExpendAnalyseBase> getExpenditureAnalyseListByDate(@Param("startDate") String startDate, @Param("endDate") String endDate);

	/**
	 * �޸�|ɾ��Expenditure����
	 * @param entity
	 */
	void updateExpenditure(Expenditure entity);
	
	/**
	 * ���һ��������Ϣ
	 * @param detail
	 */
	int insertChannelDetail_Stair(ChannelDetailStair stair);
	/**
	 * ��Ӷ���������Ϣ
	 * @param detail
	 */
	int insertChannelDetail_Second(ChannelDetailSecond second);
	/**
	 * �������������Ϣ
	 * @param detail
	 */
	int insertChannelDetail_Third(ChannelDetailThird third);
	/**
	 * ������Ӷ˿�����
	 * @param type
	 * @return
	 */
	int insertPortType(PortType type);
	/**
	 * ����id��ѯ�˿�����
	 * @param id
	 * @return
	 */
	List<PortType> getPortTypeList(@Param("id")int id);
	int getPortByName(@Param("name") String name);
	/**
	 *����һ������id,�˿�id��ȡ����
	 * @param id
	 * @param portId
	 * @param delFlag  0:δɾ�� 1:ɾ�� -1:������ݴ�״̬ɸѡ����
	 * @return
	 */
	List<ChannelDetailStair> getChannelStairList(@Param("id")long id,@Param("portId")int portId,@Param("delFlag")int delFlag);
	ChannelDetailStair getChannelStairByName(@Param("name")String name,@Param("portId")int portId);
	/**
	 * ���ݶ�������id��ȡ����
	 * @param id
	 * @param stairId
	 * @param delFlag  0:δɾ�� 1:ɾ�� -1:������ݴ�״̬ɸѡ����
	 * @return
	 */
	List<ChannelDetailSecond> getChannelSecondList(@Param("id")long id,@Param("stairId") long stairId,@Param("delFlag")int delFlag);
	ChannelDetailSecond getChannelSecondByName(@Param("name")String name,@Param("stairId")long stairId);
	/**
	 * ������������id��ȡ����
	 * @param id
	 * @param stairId
	 * @param secondId
	 * @param delFlag  0:δɾ�� 1:ɾ�� -1:������ݴ�״̬ɸѡ����
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
