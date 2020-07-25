package com.imooc3.controller;


import com.imooc3.pojo.Items;
import com.imooc3.pojo.ItemsImg;
import com.imooc3.pojo.ItemsParam;
import com.imooc3.pojo.ItemsSpec;
import com.imooc3.pojo.vo.CommentLevelCountsVO;
import com.imooc3.pojo.vo.ItemInfoVO;
import com.imooc3.service.ItemService;
import com.imooc3.utils.IMOOCJSONResult;
import com.imooc3.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "商品接口",tags = "商品信息展示的相关接口")
@RestController
@RequestMapping("items")   /*写上路由*/
public class ItemsController extends BaseConroller{

    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "查询商品详情",notes = "查询商品详情",httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public IMOOCJSONResult info(
            @ApiParam(name = "itemId", value = "商品id", required = true)
            @PathVariable String itemId) {

        if (StringUtils.isBlank(itemId)) {
            return IMOOCJSONResult.errorMsg(null);
        }

        Items item = itemService.queryItemById(itemId);
        List<ItemsImg> itemImgList = itemService.queryItemImgList(itemId);
        List<ItemsSpec> itemSpecsList = itemService.queryItemSpecList(itemId);
        ItemsParam itemParam= itemService.queryItemParam(itemId);
        /*因为这里是有两个对象两个List，IMOOCJSONResult只能返回一个对象，所以这里要在vo里定义新的累*/
        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItem(item);
        itemInfoVO.setItemImgList(itemImgList);
        itemInfoVO.setItemSpecList(itemSpecsList);
        itemInfoVO.setItemParams(itemParam);

        return IMOOCJSONResult.ok(itemInfoVO);
    }

    @ApiOperation(value = "查询商品评价等级",notes = "查询商品评价等级",httpMethod = "GET")
    @GetMapping("/commentLevel")
    public IMOOCJSONResult commentLevel(
            @ApiParam(name = "itemId", value = "商品id", required = true)
            @RequestParam String itemId) {/*这是一个请求路径，不是一个请求参数了，所以改为RequestParam*/

        if (StringUtils.isBlank(itemId)) {
            return IMOOCJSONResult.errorMsg(null);
        }

        CommentLevelCountsVO items=itemService.queryCommentCounts(itemId);

        return IMOOCJSONResult.ok(items);
    }

    @ApiOperation(value = "查询商品评论",notes = "查询商品评论",httpMethod = "GET")
    @GetMapping("/comments")
    public IMOOCJSONResult comments(
            @ApiParam(name = "itemId", value = "商品id", required = true)/*除了商品id是必填的*/
            @RequestParam String itemId,
            @ApiParam(name = "level", value = "评价等级", required = false)
            @RequestParam Integer level,
            @ApiParam(name = "page", value = "查询下一页的第几页", required = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "分页的每一页显示的条数", required = false)
            @RequestParam Integer pageSize) {/*这是一个请求路径，不是一个请求参数了，所以改为RequestParam*/

        if (StringUtils.isBlank(itemId)) {
            return IMOOCJSONResult.errorMsg(null);
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = COMMENT_PAGE_SIZE;
        }

        PagedGridResult grid=itemService.queryPagedComments(itemId,
                                                            level,
                                                            page,
                                                            pageSize);
        //mybatis-pagehelper
        /**
         *page:第几页
         *pageSize：每页显示条数
         */

        return IMOOCJSONResult.ok(grid);
    }
}
