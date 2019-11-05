package com.egk.gettersetter;

public class MatchPointGetSet {
    String match_point_id,match_date,description;

    public MatchPointGetSet(String match_point_id, String match_date, String description) {
        this.match_point_id = match_point_id;
        this.match_date = match_date;
        this.description = description;
    }

    public String getMatch_point_id() {
        return match_point_id;
    }

    public void setMatch_point_id(String match_point_id) {
        this.match_point_id = match_point_id;
    }

    public String getMatch_date() {
        return match_date;
    }

    public void setMatch_date(String match_date) {
        this.match_date = match_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
