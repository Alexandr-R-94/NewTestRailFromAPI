package models;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cases {
    @Expose
    private int id;
    @Expose
    private String title;
    @Expose
    private String case_ids;
    @Expose
    private int type_id;
    @Expose
    private int priority_id;
}
