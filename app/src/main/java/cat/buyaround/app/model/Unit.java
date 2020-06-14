package cat.buyaround.app.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Unit implements Serializable {
    @SerializedName("_id")
    private String id;
    private String name;

    public Unit() {
        name = null;
    }

    public Unit(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
