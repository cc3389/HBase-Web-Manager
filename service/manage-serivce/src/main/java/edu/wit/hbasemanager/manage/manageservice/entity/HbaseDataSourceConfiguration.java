package edu.wit.hbasemanager.manage.manageservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("hbase_data_source_configuration")
public class HbaseDataSourceConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("properties")
    private String properties;

    @TableField("name")
    private String name;


}
