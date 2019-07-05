package com.cloud.admin.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: xujiping
 * @Date: 2019年7月5日 0005 上午 11:43:51
 * @Version 1.0
 */
@Data
public class PermissionAllTree implements Serializable {
    private static final long serialVersionUID = 1596456948776129918L;

    private List<PermissionTree> permissionTrees;

    /**
     * 选择的ID列表
     */
    private int[] checkedIds;
}
