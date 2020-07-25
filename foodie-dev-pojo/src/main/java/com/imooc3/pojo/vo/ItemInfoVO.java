package com.imooc3.pojo.vo;

import com.imooc3.pojo.Items;
import com.imooc3.pojo.ItemsImg;
import com.imooc3.pojo.ItemsParam;
import com.imooc3.pojo.ItemsSpec;

import java.util.List;

/**
 * 商品详情VO
 */
public class ItemInfoVO {
    private Items item;
    private List<ItemsImg> itemImgList ;
    private List<ItemsSpec> itemSpecList ;
    private ItemsParam itemParams;

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public List<ItemsImg> getItemImgList() {
        return itemImgList;
    }

    public void setItemImgList(List<ItemsImg> itemImgList) {
        this.itemImgList = itemImgList;
    }

    public List<ItemsSpec> getItemSpecList() {
        return itemSpecList;
    }

    public void setItemSpecList(List<ItemsSpec> itemSpecList) {
        this.itemSpecList = itemSpecList;
    }

    public ItemsParam getItemParams() {
        return itemParams;
    }

    public void setItemParams(ItemsParam itemParams) {
        this.itemParams = itemParams;
    }
}
