package com.cloud.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cloud.admin.entity.SysPermission;
import com.cloud.admin.entity.SysRolePermission;
import com.cloud.admin.entity.vo.PermissionAllTree;
import com.cloud.admin.entity.vo.PermissionTree;
import com.cloud.admin.entity.vo.PermissionVo;
import com.cloud.admin.mapper.SysPermissionMapper;
import com.cloud.admin.service.SysPermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.admin.service.SysRolePermissionService;
import com.cloud.admin.service.SysRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-06-06
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Autowired
    private SysRolePermissionService rolePermissionService;

    @Autowired
    private SysRoleService roleService;

    @Override
    public String getUri(int id) {
        SysPermission sysPermission = selectById(id);
        if (sysPermission != null) {
            return sysPermission.getUri();
        }
        return null;
    }

    @Override
    public List<PermissionVo> list(int platform, String userId, Integer level) {
        List<PermissionVo> permissionVoList = new ArrayList<>();
        Integer roleId = roleService.getRoleId(platform, userId);
        if (roleId == null) {
            return null;
        }
        List<SysRolePermission> rolePermissions = listByRole(roleId, level);
        if (rolePermissions != null && rolePermissions.size() > 0) {
            permissionVoList = rolePermissions.stream().map(this::wrapper).collect(Collectors.toList());
        }
        return permissionVoList;
    }

    @Override
    public List<PermissionTree> listChild(Integer upId) {
        List<PermissionTree> permissionVoList = new ArrayList<>();
        Wrapper<SysPermission> wrapper = new EntityWrapper<>();
        if (upId != null) {
            SysPermission up = selectById(upId);
            if (up == null) {
                upId = null;
            } else {
                Integer upLevel = up.getLevel();
                wrapper.eq("up_id", upId);
                wrapper.eq("level", upLevel + 1);
            }
        }
        if (upId == null) {
            wrapper.eq("level", 1);
        }
        List<SysPermission> list = selectList(wrapper);
        if (list != null && list.size() > 0) {
            permissionVoList = list.stream().map(this::wrapper).collect(Collectors.toList());
        }
        return permissionVoList;
    }

    private List<PermissionTree> setTree(List<PermissionTree> trees, int count) {
        if (count > 1) {
            for (PermissionTree tree : trees) {
                List<PermissionTree> childTrees = listChild(tree.getId());
                setTree(childTrees, count - 1);
                tree.setChildren(childTrees);
            }
        }
        return trees;
    }

    @Override
    public List<PermissionTree> listAll() {
        int maxLevel = getMaxLevel();
        List<PermissionTree> trees = listChild(null);
        List<PermissionTree> permissionTrees = setTree(trees, maxLevel);
        return permissionTrees;
    }

    @Override
    public int[] listRole(Integer roleId) {
        List<SysRolePermission> permissions = rolePermissionService.listByRole(roleId);
        if (permissions != null && permissions.size() > 0){
            int size = permissions.size();
            int[] ids = new int[size];
            for (int i = 0; i < size; i++) {
                ids[i] = permissions.get(i).getPermissionId();
            }
            return ids;
        }
        return new int[]{};
    }

    @Override
    public List<SysRolePermission> listByRole(int roleId, Integer level) {
        Wrapper<SysRolePermission> wrapper = new EntityWrapper<>();
        wrapper.eq("role_id", roleId);
        if (level != null) {
            wrapper.eq("level", level);
        }
        return rolePermissionService.selectList(wrapper);
    }

    @Override
    public PermissionVo wrapper(SysRolePermission rolePermission) {
        PermissionVo permissionVo = new PermissionVo();
        if (rolePermission == null) {
            return permissionVo;
        }
        Integer permissionId = rolePermission.getPermissionId();
        SysPermission permission = selectById(permissionId);
        if (permission == null) {
            return permissionVo;
        }
        BeanUtils.copyProperties(permission, permissionVo);
        return permissionVo;
    }

    @Override
    public PermissionTree wrapper(SysPermission permission) {
        PermissionTree permissionTree = new PermissionTree();
        if (permission == null) {
            return permissionTree;
        }
        BeanUtils.copyProperties(permission, permissionTree);
        permissionTree.setName(permission.getMenuName());
        permissionTree.setValue(permission.getMenuValue());
        permissionTree.setLeaf(false);
        return permissionTree;
    }

    @Override
    public int getMaxLevel() {
        Wrapper<SysPermission> wrapper = new EntityWrapper<>();
        wrapper.orderBy("level", false);
        SysPermission permission = selectOne(wrapper);
        if (permission == null) {
            return 0;
        }
        return permission.getLevel();
    }
}
