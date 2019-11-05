package com.egk.gettersetter;

public class TestPeperGetterSetter {

    String testPaperId;
    String testPaperTitle;
    String testPaperDesc;
    String source;

    public TestPeperGetterSetter(String testPaperId, String testPaperTitle, String testPaperDesc, String source) {
        this.testPaperId = testPaperId;
        this.testPaperTitle = testPaperTitle;
        this.testPaperDesc = testPaperDesc;
        this.source = source;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
