package cn.allms.leave.rpc;
/**
 * 用户接口定义
 */
public interface UserRpc {

    /**
     * 用户登录
     *
     * @param password
     * @param username
     * @return
     */
    String login0(String password, String username);

}
