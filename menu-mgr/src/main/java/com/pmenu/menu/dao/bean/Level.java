package com.pmenu.menu.dao.bean;

import java.io.Serializable;

public class Level implements Serializable {
    private Short code;

    private String cndisplay;

    private String endisplay;

    private String description;

    private static final long serialVersionUID = 1L;

    public Short getCode() {
        return code;
    }

    public Level setCode(Short code) {
        this.code = code;
        return this;
    }

    public String getCndisplay() {
        return cndisplay;
    }

    public void setCndisplay(String cndisplay) {
        this.cndisplay = cndisplay == null ? null : cndisplay.trim();
    }

    public String getEndisplay() {
        return endisplay;
    }

    public void setEndisplay(String endisplay) {
        this.endisplay = endisplay == null ? null : endisplay.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Level other = (Level) that;
        return (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getCndisplay() == null ? other.getCndisplay() == null : this.getCndisplay().equals(other.getCndisplay()))
            && (this.getEndisplay() == null ? other.getEndisplay() == null : this.getEndisplay().equals(other.getEndisplay()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getCndisplay() == null) ? 0 : getCndisplay().hashCode());
        result = prime * result + ((getEndisplay() == null) ? 0 : getEndisplay().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", code=").append(code);
        sb.append(", cndisplay=").append(cndisplay);
        sb.append(", endisplay=").append(endisplay);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}