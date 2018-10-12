package com.hwh.shiro.realm;

import com.hwh.mapper.PermissionMapper;
import com.hwh.mapper.RoleMapper;
import com.hwh.mapper.UserMapper;
import com.hwh.po.Permission;
import com.hwh.po.Role;
import com.hwh.po.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义的realm
 * @author hwh
 * @create 2018/9/25 10:54
 */
public class CustomRealm extends AuthorizingRealm{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;
    /**
     * 实现授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String usercode = (String) principalCollection.getPrimaryPrincipal();
        //从数据库或者缓存中获得角色数据
        Set<String> roles = getRoleByUserCode(usercode);
        Set<String> permissions = getPermissionsByUserCode(usercode);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * 通过账号获得权限资源
     * @param usercode
     * @return
     */
    private Set<String> getPermissionsByUserCode(String usercode) {
        Set<Permission> set = permissionMapper.getPermissionByUserCode(usercode);
        Set<String> permissions = new HashSet<>();
        if(set != null) {
            for(Permission permission : set) {
                permissions.add(permission.getPercode());
            }
            return permissions;
        }
        return null;
    }

    /**
     * 通过账号获得角色
     * @param usercode
     * @return
     */
    private Set<String> getRoleByUserCode(String usercode) {
        System.out.println("从数据库中读取角色Roles");
        Set<Role> set = roleMapper.getRoleByUserCode(usercode);
        Set<String> roles = new HashSet<>();
        if(set != null) {
            for(Role role : set) {
                roles.add(role.getName());
            }
            return roles;
        }
        return null;
    }

    /**
     * 实现认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //从主体传过来的认证信息中获得用户名
        String usercode = (String) authenticationToken.getPrincipal();
        //通过用户名到数据库中获得凭证
        User user = getUserByUserCode(usercode);
        String password = user.getPassword();
        if(password == null) {
            return null;
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(usercode,password,getName());
        //加盐
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(getUserByUserCode(usercode).getSalt()));

        return authenticationInfo;
    }

    /**
     * 通过账号获得用户信息
     * @param usercode
     * @return
     */
    private User getUserByUserCode(String usercode) {
        User user = userMapper.getUserByUserCode(usercode);
        return user;
    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123456","uiwueylm");
        System.out.println(md5Hash.toString());
    }
}
