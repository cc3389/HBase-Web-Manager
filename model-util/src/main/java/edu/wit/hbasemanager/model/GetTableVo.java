package edu.wit.hbasemanager.model;

import lombok.Data;

/**
 * @Classname GetTableVo
 * @Description // hbase表所有信息
 * @Author Shawn Yue
 * @Date 11:19
 * @Version 1.0
 **/
@Data
public class GetTableVo {
    private String row;
    private String family;
    private String qualifier;
    private String value;
}
