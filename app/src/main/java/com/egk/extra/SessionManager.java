package com.egk.extra;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;




import com.egk.egk.Login;

/**
 * Created by Narendra on 6/8/2017.
 */

public class SessionManager {

    SharedPreferences sharedprefernce;
    SharedPreferences.Editor editor;

    Context context;
    int PRIVATE_MODE=0;

    private static final String PREF_NAME="sharedcheckLogin";

    private static final String User_Id="userid";
    private static final String IS_LOGIN="islogin";
    private static final String userPassword="password";
    private static final String USERNAME="username";
    private static final String USERPHONENUMBER="userPhoneNumber";
    private static final String USEREMAIL="userEmail";
    private static final String UserCity="usercity";
    private static final String SessionId="sessionid";
    private static final String Photo="Photo";
    private static final String Subcription="Subcription";
    private static final String PackageID="PackageID";
    private static final String RemainigDay="RemainigDay";
    private static final String ExpireDay="ExpireDay";
    private static final String Today="Today";
    private static final String MonthlyGk="MonthlyGk";
    private static final String CategogryGK="CategogryGK";
    private static final String MatchPoint="MatchPoint";
    private static final String Glossary="Glossary";
    private static final String Reports="Reports";
    private static final String SamplePaper="SamplePaper";
    private static final String UpcomingExam="UpcomingExam";
    private static final String BasicGK="BasicGK";
    private static final String Quiz="Quiz";


    public SessionManager(Context context){

        this.context =  context;
        sharedprefernce = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor=sharedprefernce.edit();

    }


    public  String getSubcription() {
        return sharedprefernce.getString(Subcription,"");
    }
    public void setSubcription(String subcription ){
        editor.putString(Subcription,subcription);
        editor.commit();

    }

    public  String getPackageID() {
        return sharedprefernce.getString(PackageID,"");
    }
    public void setPackageID(String packageid ){
        editor.putString(PackageID,packageid);
        editor.commit();

    }

    public  String getRemainigDay() {
        return sharedprefernce.getString(RemainigDay,"");
    }
    public void setRemainigDay(String remainigday ){
        editor.putString(RemainigDay,remainigday);
        editor.commit();

    }

    public  String getExpireDay() {
        return sharedprefernce.getString(ExpireDay,"");
    }
    public void setExpireDay(String expireday ){
        editor.putString(ExpireDay,expireday);
        editor.commit();

    }

    public  String getToday() {
        return sharedprefernce.getString(Today,"");
    }
    public void setToday(String today ){
        editor.putString(Today,today);
        editor.commit();

    }

    public  String getMonthlyGk() {
        return sharedprefernce.getString(MonthlyGk,"");
    }
    public void setMonthlyGk(String monthlygk ){
        editor.putString(MonthlyGk,monthlygk);
        editor.commit();

    }

    public  String getCategogryGK() {
        return sharedprefernce.getString(CategogryGK,"");
    }
    public void setCategogryGK(String categogrygk ){
        editor.putString(CategogryGK,categogrygk);
        editor.commit();

    }

    public  String getMatchPoint() {
        return sharedprefernce.getString(MatchPoint,"");
    }
    public void setMatchPoint(String matchpoint ){
        editor.putString(MatchPoint,matchpoint);
        editor.commit();

    }

    public  String getGlossary() {
        return sharedprefernce.getString(Glossary,"");
    }
    public void setGlossary(String glossary ){
        editor.putString(Glossary,glossary);
        editor.commit();

    }

    public  String getReports() {
        return sharedprefernce.getString(Reports,"");
    }
    public void setReports(String reports ){
        editor.putString(Reports,reports);
        editor.commit();

    }

    public  String getSamplePaper() {
        return sharedprefernce.getString(SamplePaper,"");
    }
    public void setSamplePaper(String samplepaper ){
        editor.putString(SamplePaper,samplepaper);
        editor.commit();

    }

    public  String getUpcomingExam() {
        return sharedprefernce.getString(UpcomingExam,"");
    }
    public void setUpcomingExam(String upcomingexam ){
        editor.putString(UpcomingExam,upcomingexam);
        editor.commit();

    }

    public  String getBasicGK() {
        return sharedprefernce.getString(BasicGK,"");
    }
    public void setBasicGK(String basicgk ){
        editor.putString(BasicGK,basicgk);
        editor.commit();

    }

    public  String getQuiz() {
        return sharedprefernce.getString(Quiz,"");
    }
    public void setQuiz(String quiz ){
        editor.putString(Quiz,quiz);
        editor.commit();

    }


    public Boolean isLogin(){
        return sharedprefernce.getBoolean(IS_LOGIN, false);

    }
    public void setLogin(){

        editor.putBoolean(IS_LOGIN, true);
        editor.commit();

    }


//    Bill values


    public void setUserID(String id ){

     editor.putString(User_Id,id);
     editor.commit();


    }
    public String getUserID(){

        return  sharedprefernce.getString(User_Id,"DEFAULT");
    }

    public void setUserName(String name){
        editor.putString(USERNAME,name);
        editor.commit();

    }
    public String getUserName(){
        return sharedprefernce.getString(USERNAME,"DEFAULT");
    }

    public void setUserPhonenumber(String uphone){
        editor.putString(USERPHONENUMBER,uphone);
        editor.commit();
    }
    public String getUserPhonenumber(){
        return sharedprefernce.getString(USERPHONENUMBER,"DEFAULT");
    }

    public void setUserEmail(String name) {
        editor.putString(USEREMAIL,name);
        editor.commit();
    }
    public String getUserEmail(){
        return sharedprefernce.getString(USEREMAIL,"DEFAULT");
    }

    public void setUSERcity(String ucity) {
        editor.putString(UserCity,ucity);
        editor.commit();
    }
    public String getUSERcity(){
        return sharedprefernce.getString(UserCity,"DEFAULT");
    }

    public void setUserPassword(String userPass ){

        editor.putString(userPassword,userPass);
        editor.commit();

    }
    public String getUserPassword() {
        return sharedprefernce.getString(userPassword,"DEFAULT");
    }

    public void setSessionId(String sessionid ){

        editor.putString(SessionId,sessionid);
        editor.commit();

    }
    public  String getSessionId() {
        return sharedprefernce.getString(SessionId,"DEFAULT");
    }

    public  String getPhoto() {
        return sharedprefernce.getString(Photo,"DEFAULT");
    }
    public void setPhoto(String photo ){
        editor.putString(Photo,photo);
        editor.commit();

    }

    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, Login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);





    }

}


