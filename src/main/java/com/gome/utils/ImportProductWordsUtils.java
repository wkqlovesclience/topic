package com.gome.utils;

import com.coo8.common.utils.Chinese2PinyinUtil;
import com.gome.productwords.manager.infer.ProductWordsManager;
import com.gome.productwords.model.ProductWordsCard;
import com.gome.productwords.model.ProductWordsCardImportLog;
import com.gome.productwords.model.ProductWordsErrorCard;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangkeqiang-ds on 2018/10/9.
 */
public class ImportProductWordsUtils {
    private static Logger logger = LoggerFactory.getLogger(ImportProductWordsUtils.class);
    private static final Integer DELETE = 1;
    private static final Integer NO_DELETE = 0;
    private static final Integer EDITOR = 1;
    private static final Integer NO_EDITOR = 0;
    private static final Integer INVALID = 1;
    private static final Integer NO_INVALID = 0;
    private static final Integer IS_ENABLED = 1;
    private static final Integer DIS_ENABLED = 1;

    public static Runnable getMethod(final List<String[]> readExcel, final String creator, final ProductWordsManager productWordsManager){
        logger.info("�������ݿ���߳̿�ʼ����");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    logger.info("�������ݿ���߳���������ʼִ��");
                    String createTime = String.format("%tF %<tT", System.currentTimeMillis());
                    List<ProductWordsCard> productWordsCards = new ArrayList<ProductWordsCard>();
                    List<ProductWordsErrorCard> productWordsErrorCards = new ArrayList<ProductWordsErrorCard>();
                    ProductWordsCardImportLog importLog = new ProductWordsCardImportLog();
                    //���26����дӢ����ĸ
                    List<String> letterList = new ArrayList<String>();
                    for (int j = 0; j < 26; j++) {
                        char c = (char)(65+j);
                        String str = String.valueOf(c);
                        letterList.add(str.toUpperCase());
                    }
                    for (int i = 0; i < readExcel.size(); i++) {
                        Date date = new java.util.Date();
                        String[] strings = readExcel.get(i);
                        List<String> strs = new ArrayList<String>();
                        for (int j = 0; j < 2; j++) {
                            String contents = strings[j];
                            strs.add(contents);
                        }
                        String productWordsBase = strs.get(0).replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", "");//����ƥ�人��������ĸ
                        String productWordsName = strs.get(1).replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", "");//����ƥ�人��������ĸ
                        if(StringUtils.isBlank(productWordsBase)||StringUtils.isBlank(productWordsName)){
                            continue;
                        }
                        logger.info("productWordsCard import productWordsBase" + productWordsBase + ",productWordsName" + productWordsName);
                        //У����Ʒ�ʴ����Ƿ��ظ�
                        int type = 0; //�ϴ������Ƿ���ȷ�ж�λ��0  ��ȷ��1  ��Ʒ���ظ�
                        ProductWordsCard productWord1 = productWordsManager.getByProductWordsName(productWordsName);
                        if (productWord1 == null) {
                            ProductWordsCard productWordsCardTemp = new ProductWordsCard();
                            productWordsCardTemp.setProductWordsBase(productWordsBase);
                            productWordsCardTemp.setProductWordsName(productWordsName);
                            productWordsCardTemp.setCreator(creator);
                            productWordsCardTemp.setCreateDate(date);
                            productWordsCardTemp.setIsEditor(NO_EDITOR);
                            productWordsCardTemp.setIsInvalid(NO_INVALID);//������Ч
                            productWordsCardTemp.setIsDelete(NO_DELETE);
                            String substring = Chinese2PinyinUtil.parseChinese(productWordsName).toUpperCase().substring(0, 1);
                            if(letterList.contains(substring)){
                                productWordsCardTemp.setBeginLetter(substring);
                            }else {
                                productWordsCardTemp.setBeginLetter("0-9");
                            }
                            productWordsManager.save(productWordsCardTemp);
                            productWordsCards.add(productWordsCardTemp);
                        } else {
                            ProductWordsErrorCard errorCard = new ProductWordsErrorCard();
                            errorCard.setProductWordsBase(productWordsBase);
                            errorCard.setProductWordsName(productWordsName);
                            type = 1;
                            errorCard.setType(type);
                            errorCard.setCreateTime(date);
                            errorCard.setCreator(creator);
                            productWordsErrorCards.add(errorCard);
                        }
                    }
                    importLog.setCreator(creator);
                    importLog.setTotalCount(productWordsCards.size() + productWordsErrorCards.size());
                    importLog.setFailCount(productWordsErrorCards.size());
                    importLog.setCreateTime(createTime);
                    Integer  importLogId = productWordsManager.addLog(importLog); // ������־����
                    if (!productWordsErrorCards.isEmpty()) {
                        productWordsManager.addError(productWordsErrorCards,importLogId); // ���ʧ������
                    }
                    logger.info("�������ݿ���߳�ִ�н���");
                } catch (Exception e) {
                    logger.info("�������ݿ���̳߳����쳣��"+e.getMessage());
                }


            }
        };
        return runnable;

    }
}
