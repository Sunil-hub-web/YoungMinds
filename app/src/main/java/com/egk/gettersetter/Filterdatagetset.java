package com.egk.gettersetter;

public class Filterdatagetset {

    String testPaperId;
    String testPaperTitle;
    String testPaperDesc;

    public Filterdatagetset(String testPaperId, String testPaperTitle, String testPaperDesc) {
        this.testPaperId = testPaperId;
        this.testPaperTitle = testPaperTitle;
        this.testPaperDesc = testPaperDesc;
    }

    public String getTestPaperId() {
        return testPaperId;
    }

    public void setTestPaperId(String testPaperId) {
        this.testPaperId = testPaperId;
    }

    public String getTestPaperTitle() {
        return testPaperTitle;
    }

    public void setTestPaperTitle(String testPaperTitle) {
        this.testPaperTitle = testPaperTitle;
    }

    public String getTestPaperDesc() {
        return testPaperDesc;
    }

    public void setTestPaperDesc(String testPaperDesc) {
        this.testPaperDesc = testPaperDesc;
    }
}
