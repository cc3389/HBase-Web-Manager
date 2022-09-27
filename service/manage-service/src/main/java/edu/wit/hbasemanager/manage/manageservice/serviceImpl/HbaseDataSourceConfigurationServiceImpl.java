package edu.wit.hbasemanager.manage.manageservice.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.wit.hbasemanager.manage.manageservice.entity.HbaseDataSourceConfiguration;
import edu.wit.hbasemanager.manage.manageservice.mapper.HbaseDataSourceConfigurationMapper;
import edu.wit.hbasemanager.manage.manageservice.service.HbaseDataSourceConfigurationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-09-25
 */
@Service
public class HbaseDataSourceConfigurationServiceImpl extends ServiceImpl<HbaseDataSourceConfigurationMapper, HbaseDataSourceConfiguration> implements HbaseDataSourceConfigurationService {

    @Override
    public String selectDataNameByDataId(Integer dataId) {
        QueryWrapper<HbaseDataSourceConfiguration> qw = new QueryWrapper<>();
        HbaseDataSourceConfiguration id = baseMapper.selectOne(qw.eq("id", dataId));
        return id.getName();
    }

    @Override
    public void insertOne(String sourceName, String ip) {
        HbaseDataSourceConfiguration hdsConfig = new HbaseDataSourceConfiguration();
        hdsConfig.setName(sourceName);
        hdsConfig.setProperties(ip);
        baseMapper.insert(hdsConfig);
    }

    @Override
    public Integer selectDataIdByName(String sourceName) {
        QueryWrapper<HbaseDataSourceConfiguration> qw = new QueryWrapper<>();
        HbaseDataSourceConfiguration one = baseMapper.selectOne(qw.eq("name", sourceName));
        return one.getId();
    }

    @Override
    public void delSourceData(Integer dataId) {
        QueryWrapper<HbaseDataSourceConfiguration> qw = new QueryWrapper<>();
        baseMapper.delete(qw.eq("id", dataId));
    }
}
