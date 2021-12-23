package com.nebula.linjiangxian.model;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jerrick
 * @Date: 2021/12/18/23:39
 * @Description:
 */

public class Author {
    public String id;
    public String name;
    public String dynasty;
    public String courtesyName;
    public String pseudonym;

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

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public String getCourtesyName() {
        return courtesyName;
    }

    public void setCourtesyName(String courtesyName) {
        this.courtesyName = courtesyName;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }
}
