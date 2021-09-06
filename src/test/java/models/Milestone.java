package models;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Milestone {
    @Expose
    private int id;
    @Expose
    private String name;
    @Expose
    private String description;
    @Expose
    private boolean is_completed;
}
