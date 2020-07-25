package com.imooc3.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc3.enums.CommentLevel;
import com.imooc3.mapper.*;
import com.imooc3.pojo.*;
import com.imooc3.pojo.vo.CommentLevelCountsVO;
import com.imooc3.pojo.vo.ItemCommentVO;
import com.imooc3.service.ItemService;
import com.imooc3.utils.DesensitizationUtil;
import com.imooc3.utils.PagedGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemsMapper itemsMapper;
    @Autowired
    private ItemsImgMapper itemsImgMapper;
    @Autowired
    private ItemsParamMapper itemsParamMapper;
    @Autowired
    private ItemsSpecMapper itemsSpecMapper;
    @Autowired
    private ItemsCommentsMapper itemsCommentsMapper;
    @Autowired
    private ItemsMapperCustom itemsMapperCustom;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Items queryItemById(String itemId) {
        return itemsMapper.selectByPrimaryKey(itemId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsImg> queryItemImgList(String itemId) {
        Example itemsImgExp = new Example(ItemsImg.class);
        Example.Criteria criteria = itemsImgExp.createCriteria();
        criteria.andEqualTo("itemId",itemId);          /*这里只需要外键放进去*/

        List<ItemsImg> result =  itemsImgMapper.selectByExample(itemsImgExp);

        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsSpec> queryItemSpecList(String itemId) {
        Example itemsSpecExp = new Example(ItemsSpec.class);
        Example.Criteria criteria = itemsSpecExp.createCriteria();
        criteria.andEqualTo("itemId",itemId);          /*这里只需要外键放进去*/

        List<ItemsSpec> result =  itemsSpecMapper.selectByExample(itemsSpecExp);

        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsParam queryItemParam(String itemId) {

        Example itemsParamExp = new Example(ItemsParam.class);
        Example.Criteria criteria = itemsParamExp.createCriteria();
        criteria.andEqualTo("itemId",itemId);          /*这里只需要外键放进去*/

        ItemsParam  result =  itemsParamMapper.selectOneByExample(itemsParamExp);/*selectone纸擦讯一条数据就可以，这个时候拿出来的是一个对象，而不是一个List*/

        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public CommentLevelCountsVO queryCommentCounts(String itemId) {

        Integer goodCounts = getCommentCounts(itemId, CommentLevel.GOOD.type);
        Integer nomalCounts = getCommentCounts(itemId, CommentLevel.NORMAL.type);
        Integer badCounts = getCommentCounts(itemId, CommentLevel.BAD.type);

        Integer totalCounts = goodCounts + nomalCounts + badCounts;

        CommentLevelCountsVO CountsVO = new CommentLevelCountsVO();
        CountsVO.setGoodCounts(goodCounts);
        CountsVO.setNormalCounts(nomalCounts);
        CountsVO.setBadCounts(badCounts);
        CountsVO.setTotalCounts(totalCounts);

        return CountsVO;
    }

    Integer getCommentCounts(String itemId,Integer level){
        ItemsComments condition = new ItemsComments();
        condition.setItemId(itemId);
        if (level != null) {
            condition.setCommentLevel(level);
        }
        return itemsCommentsMapper.selectCount(condition);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryPagedComments(String itemId,
                                                  Integer level,
                                                  Integer page,
                                                  Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("itemId",itemId);
        map.put("level",level);

        /**
         * page: 第几页
         * pageSize： 每页显示条数
         */

        PageHelper.startPage(page, pageSize);
        List<ItemCommentVO> list = itemsMapperCustom.queryItemComments(map);

        /*信息脱敏*/
        for (ItemCommentVO vo : list) {
            vo.setNickname(DesensitizationUtil.commonDisplay(vo.getNickname()));
        }

        return setterPagedGride(list, page);
    }

    private  PagedGridResult setterPagedGride(List<?> list, Integer page) {/*因为是共用化。所以不用指定哪个类，用？就可以*/
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());

        return grid;
    }
}
