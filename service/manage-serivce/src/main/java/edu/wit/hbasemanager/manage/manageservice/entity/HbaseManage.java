package edu.wit.hbasemanager.manage.manageservice.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 作者
 * @since 2022-09-25
 */
@Getter
@Setter
@TableName("hbase_manage")
public class HbaseManage implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId("datasource_id")
    private Integer datasourceId;

      @TableId("user_id")
    private Integer userId;


}
