package models;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Sections {
    @Expose
    private int id;
    @Expose
    private int section_id;
    @Expose
    private String name;
}
