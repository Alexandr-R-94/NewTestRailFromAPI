package endpoints;

public interface ReqresEndpoints {

    String GET_USERS_LIST = "/api/users?page=2";
    String GET_SINGLE_USER = "/api/users/2";
    String GET_SINGLE_USER_NOT_FOUND = "/api/users/23";
    String GET_LIST = "/api/unknown";
    String GET_SINGLE = "/api/unknown/2";
    String GET_SINGLE_NOT_FOUND = "/api/unknown/23";
    String POST_CREATE = "/api/users";
    String PUT_UPDATE = "/api/users/2";
    String PATCH_UPDATE = "/api/users/2";
    String DELETE_DELETE = "/api/users/2";
    String POST_REGISTER_SUCCESSFUL = "/api/register";
    String POST_REGISTER_UNSUCCESSFUL = "/api/register";
    String POST_LOGIN_SUCCESSFUL = "/api/login";
    String POST_LOGIN_UNSUCCESSFUL = "/api/login";
    String GET_DELAYED_RESPONSE = "/api/users?delay=3";


}
