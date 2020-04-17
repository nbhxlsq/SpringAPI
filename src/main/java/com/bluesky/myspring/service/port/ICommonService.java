package com.bluesky.myspring.service.port;

import com.bluesky.myspring.entity.AuthorMenu;
import com.bluesky.myspring.entity.ResultInfo;

import java.util.List;

/**
 * 系统通用服务
 */
public interface ICommonService {
    /**
     * 根据系统类型获取要菜单
     * @param type
     * @return
     */
    public ResultInfo<List<AuthorMenu>> GetSysRootMenuByType(Integer type);

    /**
     *
     * @param prdid
     * @return
     */
    public ResultInfo<List<AuthorMenu>>GetChildrenMenu(String prdid);

    public  ResultInfo<Boolean>SaveMenu(AuthorMenu menu);

    public  ResultInfo<Boolean>RemoveMenu(String mid);

    public ResultInfo<Boolean>LockOrUnLockMenu(String nid,Boolean isfreeze);

    /**
     * 验证当前菜单是否有效
     * @param menu
     * @return
     */
    public ResultInfo<String> VialdMenu(AuthorMenu menu);
}
