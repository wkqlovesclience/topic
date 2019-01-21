package com.gome.productwords.manager.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coo8.btoc.util.pages.PaginatedList;
import com.gome.productwords.dao.infer.ProductWordsCardDAO;
import com.gome.productwords.model.ProductWordsCard;
import com.gome.seo.productword.interfaces.IProductWordDubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangkeqiang-ds on 2018/9/6.
 */
public class IProductWordsDubboServiceImpl implements IProductWordDubboService {
    private Integer page = 1;
    private Integer page_size = 120;
    private Logger logger = LoggerFactory.getLogger(IProductWordsDubboServiceImpl.class);
    private ProductWordsCardDAO productWordsCardDAO;
    @Override
    public Integer getCharacterDataNum(String s) {
        Map<String,Object> search = new HashMap<String,Object>();
        int totalSum = 0;
        search.put("beginLetter",s);
        search.put("isDelete", 0);
        search.put("isInvalid",1);
        try {
            totalSum = productWordsCardDAO.getSumByBeginLetter(search);
        }catch (Exception e){
            return 0;
        }
        return totalSum;
    }

    @Override
    public JSONArray getproductWordsAllList(Integer num) {
        JSONArray jsonArray = new JSONArray();
       try {
           List<ProductWordsCard> freshProductWords = productWordsCardDAO.getFreshProductWords(num);
           if(freshProductWords!=null) {
               for (ProductWordsCard productWordsCard : freshProductWords) {
                   JSONObject tempJson = new JSONObject();
                   tempJson.put("id",productWordsCard.getId());
                   tempJson.put("name",productWordsCard.getProductWordsName());
                   jsonArray.add(tempJson);
               }
           }
       }catch (Exception e){
           return null;
       }
        return jsonArray;
    }

    @Override
    public JSONObject getproductWordsList(JSONObject jsonObject) {
        int total_Page = 0;
        int data_num = 0;
        JSONObject resultJson = new JSONObject();
        try {
            page = jsonObject.getInteger("page");
            page_size = jsonObject.getInteger("page_size");
            String beginLetter = jsonObject.getString("keyword");
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("pageSize", page_size);
            map.put("isDelete", 0);
            map.put("pageNumber", page);
            map.put("beginLetter",beginLetter);
            map.put("isInvalid",1);
            PaginatedList<ProductWordsCard> likeByMap = productWordsCardDAO.findLikeByMap(map);
            JSONArray productData = new JSONArray();
            if(likeByMap!=null){
                total_Page = likeByMap.getTotalPage();
                if(page>total_Page){
                    return null;
                }
                data_num = likeByMap.getTotalRec();
                JSONArray tempArray = new JSONArray();
                for (int i = 0;i<likeByMap.size();i++) {
                    JSONObject cardJson = new JSONObject();
                    cardJson.put("id",likeByMap.get(i).getId());
                    cardJson.put("name",likeByMap.get(i).getProductWordsName());
                    tempArray.add(cardJson);
                    if(tempArray.size()==12){
                        productData.add(tempArray);
                        tempArray = new JSONArray();
                    }
                }
                if (tempArray.size()<12&&tempArray.size()!=0){
                    productData.add(tempArray);
                }
            }
            resultJson.put("total_page",total_Page);
            resultJson.put("data_num",data_num);
            resultJson.put("page",page);
            resultJson.put("page_size",page_size);
            resultJson.put("keyWord",beginLetter);
            resultJson.put("productdata",productData);
        }catch (Exception e){
            return resultJson;
        }
        return resultJson;
    }

    /**
     * 暂时写死
     * @return
     */
    @Override
    public JSONArray getFriendlyLinkList() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name","国美+");
        jsonObject1.put("url","www.gomeplus.com/");
        jsonArray.add(jsonObject1);
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("name","国美在线手机版");
        jsonObject2.put("url","m.gome.com.cn");
        jsonArray.add(jsonObject2);
        return jsonArray;
    }

    @Override
    public String getProductWordNameByID(String s) {
        String productWordsTempID =s.replaceAll("[^(0-9)]", "");
        String productWordName = null;
        try {
            ProductWordsCard productWordsCard = productWordsCardDAO.getByIdForDubbo(Integer.valueOf(productWordsTempID));
            if(productWordsCard!=null){
                productWordName = productWordsCard.getProductWordsName();
            }
        }catch (Exception e){
            return null;
        }
        return productWordName;
    }

    @Override
    public JSONArray getLikeProductWordByID(String s) {
        JSONArray jsonArray = new JSONArray();
        try {
            ProductWordsCard productWordsCard = productWordsCardDAO.getById(Integer.valueOf(s.replaceAll("[^(0-9)]", "")));
            List<ProductWordsCard> productWordsBaseList = productWordsCardDAO.getByProductWordsBase(productWordsCard.getProductWordsBase());
            if(productWordsBaseList!=null){
                if(productWordsBaseList.size()<15){
                    int randomSize = 15-productWordsBaseList.size();
                    List<ProductWordsCard> randomList = productWordsCardDAO.getByRandomSize(randomSize);
                    productWordsBaseList.addAll(randomList);
                }
                for (ProductWordsCard wordsCard : productWordsBaseList) {
                    JSONObject tempJson = new JSONObject();
                    tempJson.put("id",wordsCard.getId());
                    tempJson.put("name",wordsCard.getProductWordsName());
                    jsonArray.add(tempJson);
                }
            }
        }catch (Exception e){
            return null;
        }
        return jsonArray;
    }

    @Override
    public String getFriendProductWordByID(String s) {
        List<ProductWordsCard> friendProductWordByID = new ArrayList<ProductWordsCard>();
        try {
            Integer productId = Integer.valueOf(s.replaceAll("[^(0-9)]", ""));
            friendProductWordByID = productWordsCardDAO.getFriendProductWordByID(productId);
            if (friendProductWordByID!=null&&friendProductWordByID.size()>1){
                for (ProductWordsCard productWordsCard : friendProductWordByID) {
                    if(!productWordsCard.getId().equals(productId)){
                        return productWordsCard.getProductWordsName();
                    }
                }
            }
        }catch (Exception e){
            return null;
        }
        return  null;
    }

    public ProductWordsCardDAO getProductWordsCardDAO() {
        return productWordsCardDAO;
    }

    public void setProductWordsCardDAO(ProductWordsCardDAO productWordsCardDAO) {
        this.productWordsCardDAO = productWordsCardDAO;
    }
}
