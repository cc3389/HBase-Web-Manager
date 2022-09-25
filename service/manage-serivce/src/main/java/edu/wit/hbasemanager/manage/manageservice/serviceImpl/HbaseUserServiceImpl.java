package edu.wit.hbasemanager.manage.manageservice.serviceImpl;

import edu.wit.hbasemanager.manage.manageservice.entity.HbaseUser;
import edu.wit.hbasemanager.manage.manageservice.mapper.HbaseUserMapper;
import edu.wit.hbasemanager.manage.manageservice.service.HbaseUserService;
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
public class HbaseUserServiceImpl extends ServiceImpl<HbaseUserMapper, HbaseUser> implements HbaseUserService {

}
