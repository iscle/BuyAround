package cat.buyaround.app.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Category implements Serializable {
    @SerializedName("_id")
    private String id;
    private String name;
    private String icon;

    public Category() {
        name = null;
        icon = null;
    }

    public Category(String name) {
        this.name = name;
        icon = null;
    }

    public Category(String name, String icon) {
        this.name = name;
        this.icon = icon;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
