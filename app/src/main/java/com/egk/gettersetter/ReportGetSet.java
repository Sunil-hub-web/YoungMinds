package com.egk.gettersetter;

public class ReportGetSet {
    String reports_id,reports_name,description;

    public ReportGetSet(String reports_id, String reports_name, String description) {
        this.reports_id = reports_id;
        this.reports_name = reports_name;
        this.description = description;
    }

    public String getReports_id() {
        return reports_id;
    }

    public void setReports_id(String reports_id) {
        this.reports_id = reports_id;
    }

    public String getReports_name() {
        return reports_name;
    }

    public void setReports_name(String reports_name) {
        this.reports_name = reports_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
