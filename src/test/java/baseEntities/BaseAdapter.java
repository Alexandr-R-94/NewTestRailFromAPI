package baseEntities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BaseAdapter {

    protected Gson gson;
    public BaseAdapter() {
        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }
}
