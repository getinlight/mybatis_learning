package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    SysUser selectById(Long id);

    List<SysUser> selectAll();

    List<SysRole> selectRolesByUserId(Long userid);

    int insert(SysUser sysUser);

    int insert2(SysUser sysUser);

    int insert3(SysUser sysUser);

    int updateById(SysUser sysUser);

    int deleteById(Long id);

    List<SysRole> selectRolesByUserIdAndRoleEnabled(
            @Param("userId") Long userId,
            @Param("enabled") Integer enabled
    );

    List<SysRole> selectRolesByUserAndRole(
            @Param("user") SysUser user,
            @Param("role") SysRole role
    );

    List<SysUser> selectByUser(SysUser user);

    int updateByIdSelective(SysUser sysUser);

    SysUser selectByIdOrUserName(SysUser sysUser);

    List<SysUser> selectByIdList(List<Long> idList);

    int insertList(List<SysUser> userList);

    int updateByMap(Map<String, Object> map);

    SysUser selectUserAndRoleById(Long id);

    SysUser selectUserAndRoleById2(Long id);

}

