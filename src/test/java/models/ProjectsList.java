package models;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.util.List;

@Data
public class ProjectsList {

    @Expose
    List<Project> data;
}
