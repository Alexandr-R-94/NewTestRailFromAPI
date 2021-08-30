package endpoints;

public interface CasesEndpoint {

    String ADD_CASE = "index.php?/api/v2/add_case/%d"; //%d - sectionID
    String GET_CASE = "index.php?/api/v2/get_case/%d"; //%d - caseID
    String GET_HISTORY_OF_CASE = "index.php?/api/v2/get_history_for_case/%d"; //%d - caseID
    String POST_UPDATE_CASE = "index.php?/api/v2/update_case/%d"; //%d - caseID
    String POST_DELETE_CASE = "index.php?/api/v2/delete_case/%d"; //%d - caseID


}
