package edu.wit.hbasemanager.manage.manageservice.service;

import edu.wit.hbasemanager.manage.manageservice.entity.HbaseDataSourceConfiguration;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2022-09-25
 */
public interface HbaseDataSourceConfigurationService extends IService<HbaseDataSourceConfiguration> {
    String selectDataNameByDataId(Integer dataId);
    void insertOne(String sourceName, String ip);
    Integer selectDataIdByName(String sourceName);
    void delSourceData(Integer dataId);
}
