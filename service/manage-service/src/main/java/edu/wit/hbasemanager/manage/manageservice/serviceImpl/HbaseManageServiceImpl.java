package edu.wit.hbasemanager.manage.manageservice.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.wit.hbasemanager.manage.manageservice.entity.HbaseManage;
import edu.wit.hbasemanager.manage.manageservice.mapper.HbaseManageMapper;
import edu.wit.hbasemanager.manage.manageservice.service.HbaseManageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Shawn Yue
 * @Description //TODO Shawn Yue
 * @Date 18:39 2022/9/26
 * @Param
 * @return
 **/
@Service
public class HbaseManageServiceImpl extends ServiceImpl<HbaseManageMapper, HbaseManage> implements HbaseManageService {
    @Autowired
    HbaseManageMapper hbaseManageMapper;

    /**
     * @Author Shawn Yue
     * @Description // 根据传入的userId查询管理表 得到数据源名称
     * @Date 18:33 2022/9/26
     * @Param [userId]
     * @return HbaseManage
     **/
    @Override
    public List<HbaseManage> selectByUserId(Integer userId) {
        QueryWrapper<HbaseManage> qwManage = new QueryWrapper<>();
        List<HbaseManage> hbaseManages = baseMapper.selectList(qwManage.eq("user_id", userId));
        return hbaseManages;
    }

    @Override
    public void insertOne(Integer dataId, Integer userId) {
        HbaseManage hbaseManage = new HbaseManage();
        hbaseManage.setDatasourceId(dataId);
        hbaseManage.setUserId(userId);
        baseMapper.insert(hbaseManage);
    }

    @Override
    public void delSourceData(Integer dataId) {
        QueryWrapper<HbaseManage> qwManage = new QueryWrapper<>();
        baseMapper.delete(qwManage.eq("id", dataId));
    }
}
