package endpoints;

public interface MilestonesEndpoints {

    String GET_ALL_MILESTONES = "index.php?/api/v2/get_milestones/%d"; //%d - This is projectID
    String GET_MILESTONE = "index.php?/api/v2/get_milestone/%d"; //%d - This is milestoneID
    String POST_ADD_MILESTONE = "index.php?/api/v2/add_milestone/%d"; //%d - This is projectID
    String POST_UPDATE_MILESTONE = "index.php?/api/v2/update_milestone/%d"; //%d - This is milestoneID
    String POST_DELETE_MILESTONE = "index.php?/api/v2/delete_milestone/%d"; //%d - This is milestoneID
}
