package com.imooc3.service;

import com.imooc3.pojo.Items;
import com.imooc3.pojo.ItemsImg;
import com.imooc3.pojo.ItemsParam;
import com.imooc3.pojo.ItemsSpec;
import com.imooc3.pojo.vo.CommentLevelCountsVO;
import com.imooc3.pojo.vo.ShopcartVO;
import com.imooc3.utils.PagedGridResult;

import java.util.List;

public interface ItemService {
    /**
     * 根据商品ID查询详情
     * @param itemId
     * @return
     */
    public Items queryItemById(String itemId);/*因为后期要分库分表，就要使用全局唯一id，就使用到String类型*/

    /**
     * 根据商品ID查询商品图片列表
     * @param itemId
     * @return
     */
    public List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 根据商品ID查询规格
     * @param itemId
     * @return
     */
    public List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     *根据商品ID查询商品参数
     * @param itemId
     * @return
     */
    public ItemsParam queryItemParam(String itemId);/*参数只需要获取单条数据就可以了，所以ItemsParam*/

    /**
     * 根据商品ID查询商品参的评价等级数量
     * @param itemId
     */
    public CommentLevelCountsVO queryCommentCounts(String itemId);

    /**
     * 根据商品id查询商品的评价（分页）
     * @param itemId
     * @param level
     * @return
     */
    public PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer pageSize);

    /**
     * 搜索商品管理列表
     * @param keywords
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult searchItems(String keywords, String sort,
                                       Integer page, Integer pageSize);

    /**
     * 根据分类id搜索商品列表
     * @param catId
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult searhItems(Integer catId, String sort,
                                      Integer page, Integer pageSize);

    /**
     * 根据规格ids查询最新的购物车中的商品数据（用于刷新渲染购物车中的商品数据）
     * @param specIds
     * @return
     */
    public  List<ShopcartVO> queryItemsBySpecIds(String specIds);

    /**
     * 根据商品规格id获取规格对象的具体信息
     * @param specId
     * @return
     */
    public ItemsSpec queryItemSpecById(String specId);/*这里用于OrderServiceImpl*/

    /**
     *根据商品id获得商品图片主图url
     * @param itemId
     * @return
     */
    public String queryItemMainImgById(String itemId);

    /**
     * 减少库存
     * @param speceId
     * @param buyCounts
     */
    public void decreaseItemSpecStock(String speceId, int buyCounts);

}
