package model;

/**
 * Created by maximize on 12/17/2016 AD.
 */

public class Type {
    private String type;
    private Boolean typeCheck;

    public Type() {
        type = "";
        typeCheck = false;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getTypeCheck() {
        return typeCheck;
    }

    public void setTypeCheck(Boolean typeCheck) {
        this.typeCheck = typeCheck;
    }
}
