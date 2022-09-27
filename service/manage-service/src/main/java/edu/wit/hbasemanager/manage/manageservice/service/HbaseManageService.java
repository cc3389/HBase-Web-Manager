package edu.wit.hbasemanager.manage.manageservice.service;

import edu.wit.hbasemanager.manage.manageservice.entity.HbaseManage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2022-09-25
 */
public interface HbaseManageService extends IService<HbaseManage> {
    List<HbaseManage> selectByUserId(Integer userId);
    void insertOne(Integer dataId, Integer userId);
    void delSourceData(Integer dataId);
}
