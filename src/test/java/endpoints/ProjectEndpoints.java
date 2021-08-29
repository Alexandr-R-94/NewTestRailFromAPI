package endpoints;

public interface ProjectEndpoints {

    String GET_ALL_PROJECTS = "index.php?/api/v2/get_projects";
    String GET_PROJECT = "index.php?/api/v2/get_project/%d";
    String POST_ADD_PROJECT = "index.php?/api/v2/add_project";
    String POST_UPDATE_PROJECT = "index.php?/api/v2/update_project/%d";
    String POST_DELETE_PROJECT = "index.php?/api/v2/delete_project/%d";
}
