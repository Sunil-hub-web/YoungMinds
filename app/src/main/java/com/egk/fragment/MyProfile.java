package com.egk.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bumptech.glide.Glide;
import com.egk.egk.Egk_nav;
import com.egk.egk.R;
import com.egk.extra.AppSingleton;
import com.egk.extra.SessionManager;
import com.egk.extra.ViewDialog;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import static android.app.Activity.RESULT_OK;


public class MyProfile extends Fragment {

    ImageView backicon,user_image,edtprofile_icon_img;
    EditText email,phone,name,city,occupation,address;
    TextView birthdate;
    Button save_profile,edit;
    ViewDialog progressDialog;
    SessionManager session;
    Calendar mcurrentDate;
    int day, month, year;
    Spinner gender;
    String statename,stateId;
    HashMap<String,String> hashMapSatte = new HashMap<String, String>();
    ArrayList<String> categories = new ArrayList<String>();
    ArrayList<String> categoriesGender = new ArrayList<String>();
    Spinner state;
    ArrayAdapter<String> dataAdapter;
    ArrayAdapter<String> dataAdapterGender;
    private static String encodedrediept = "";
    Uri targetUri;
    String baseimage = "",value,statess;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        View v= inflater.inflate(R.layout.fragment_my_profile, container, false);

        categoriesGender.add("Select Gender");
        categoriesGender.add("Male");
        categoriesGender.add("Female");

        backicon=(ImageView)v.findViewById(R.id.backicon);
        edtprofile_icon_img=(ImageView)v.findViewById(R.id.edtprofile_icon_img);
        user_image=(ImageView)v.findViewById(R.id.user_image);
        progressDialog = new ViewDialog(getActivity());
        edit=(Button) v.findViewById(R.id.edit);
        gender = (Spinner) v.findViewById(R.id.gender);
        save_profile=(Button) v.findViewById(R.id.save_profile);
        email=(EditText) v.findViewById(R.id.email);
        phone=(EditText) v.findViewById(R.id.phone);
        name=(EditText) v.findViewById(R.id.name);
        city=(EditText) v.findViewById(R.id.city);
        state=(Spinner)v.findViewById(R.id.state);
        occupation=(EditText)v.findViewById(R.id.occupation);
        address=(EditText)v.findViewById(R.id.address);
        birthdate=(TextView) v.findViewById(R.id.birthdate);

        session = new SessionManager(getActivity());
        getuserProfile();

//        user_image.setEnabled(false);


        dataAdapterGender = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categoriesGender);
        dataAdapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(dataAdapterGender);

        mcurrentDate = Calendar.getInstance();
        day = mcurrentDate.get(Calendar.DAY_OF_MONTH);
        month = mcurrentDate.get(Calendar.MONTH);
        year = mcurrentDate.get(Calendar.YEAR);

        month = month + 1;
        birthdate.setText(day + "/" + month + "/" + year);

        birthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        birthdate.setText(dayOfMonth+ "/" + month + "/" +year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });




        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).toString().equalsIgnoreCase("Select State")){
                    stateId = "";
                    statename = "";
                }else{

                    statename = parent.getItemAtPosition(position).toString();
                    stateId = hashMapSatte.get(statename);
                    Log.d("CheckSpinner", statename);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        state.setEnabled(false);
        state.setClickable(false);






        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setEnabled(true);
                email.setEnabled(true);
//                phone.setEnabled(true);
                state.setEnabled(true);
                city.setEnabled(true);
                address.setEnabled(true);
                occupation.setEnabled(true);
                birthdate.setEnabled(true);
                edit.setVisibility(View.GONE);
                save_profile.setVisibility(View.VISIBLE);
                state.setEnabled(true);
//                user_image.setEnabled(true);
//                Glide.with(getActivity()).load(R.drawable.profile_icon).into(user_image);
//                edtprofile_icon_img.setVisibility(View.VISIBLE);
                state.setClickable(true);
            }
        });
        save_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                edtprofile_icon_img.setVisibility(View.GONE);

                if (name.getText().length() == 0) {
                    Toast.makeText(getActivity(), "Enter Name", Toast.LENGTH_SHORT).show();

                }
                  else if (email.getText().length() == 0) {
                    Toast.makeText(getActivity(), "Enter Email Id", Toast.LENGTH_SHORT).show();


                } else if (!isEmailValid(email.getText().toString())) {

                    Toast.makeText(getActivity(), "Please enter Valid mail", Toast.LENGTH_SHORT).show();

                }
                else if (address.getText().length() == 0) {
                    Toast.makeText(getActivity(), "Enter Address", Toast.LENGTH_SHORT).show();

                }else if (city.getText().length() == 0) {
                    Toast.makeText(getActivity(), "Enter City", Toast.LENGTH_SHORT).show();

                }else if (occupation.getText().length() == 0) {
                    Toast.makeText(getActivity(), "Enter Occupation", Toast.LENGTH_SHORT).show();

                }
                  else {
                    Updateuserprofile updateuserprofile =new Updateuserprofile();
                    updateuserprofile.execute();

                }

            }
        });
        backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), Egk_nav.class);
                startActivity(i);
            }
        });

        user_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opengallery();
            }
        });




        return v;

    }

    public void opengallery() {

        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 0);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("plih", String.valueOf(data));
        Log.d("plih", String.valueOf(requestCode));
        if (data != null
                && requestCode == 0
        ) {
            Log.d("plih", "2");

            if (resultCode == RESULT_OK) {
                Log.d("plih", "3");
                targetUri = data.getData();
                Log.d("plih", "4");
                Log.d("jdhfnf", String.valueOf(targetUri));
                session.setPhoto(String.valueOf(targetUri));
               Picasso.with(getActivity()).load(targetUri).transform(new CircleTransform()).into(user_image);

                InputStream imageStream = null;
                Log.d("plih", "5");
                try {
                    Log.d("plih", "6");
                    imageStream = getActivity().getContentResolver().openInputStream(targetUri);
                } catch (FileNotFoundException e) {
                    Log.d("plih", "7");
                    e.printStackTrace();
                    Log.d("plih", String.valueOf(e));
                }
                Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                yourSelectedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                Log.d("plih", "8");
                    baseimage = encodeTobase64(yourSelectedImage);
                    session.setPhoto(baseimage);
                    Log.d("dfevrds", session.getPhoto());

                Updateuserprofile updateuserprofile =new Updateuserprofile();
                updateuserprofile.execute();
//                Log.d("jkzvfs",baseimage);

//                UploadToServer uploadToServer=new UploadToServer();
//                uploadToServer.execute();




            }
        }else{
            Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    public static String encodeTobase64(Bitmap image) {
        Bitmap immagex=image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 25, baos);
        byte[] b = baos.toByteArray();
        encodedrediept = Base64.encodeToString(b,Base64.DEFAULT);

        String str = encodedrediept;
        int maxLogSize = 1000;
        for(int i = 0; i <= str.length() / maxLogSize; i++) {
            int start = i * maxLogSize;
            int end = (i+1) * maxLogSize;
            end = end > str.length() ? str.length() : end;
            Log.v("hjkfvbn", str.substring(start, end));
        }
        return encodedrediept;
    }

    public void getuserProfile(){
                 String a = "{\"u_id\":\"";
                 String c = "\"}";
        String url = "https://egknow.com/service-web/webservice.php?method=getUserProfile&data="+a+session.getUserID()+c;
        Log.d("profile", url);
        progressDialog.showDialog();
        Log.d("Receive", url);
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("profileresponse", response.toString());
                        String REsult = response.toString();
                        //    pDialog.dismiss();
                        progressDialog.hideDialog();

                        try {
                            JSONObject jsonObject = new JSONObject(REsult);
                            String success = jsonObject.getString("success");
                            if(success.equals("true")){

                                JSONObject jsonSub = jsonObject.getJSONObject("userProf");

                                String names = jsonSub.getString("name");
                                String emails = jsonSub.getString("email");
                                String mobile =jsonSub.getString("mobile");
                                statess = jsonSub.getString("state");
                                String citys = jsonSub.getString("city");
                                String addresss = jsonSub.getString("address");
                                String occupations = jsonSub.getString("occupation");
                                String dobs = jsonSub.getString("dob");
                                String img = jsonSub.getString("img");


                                name.setText(names);
                                email.setText(emails);
                                phone.setText(mobile);
                                session.setPhoto(img);
                                city.setText(citys);
                                address.setText(addresss);
                                occupation.setText(occupations);
                                birthdate.setText(dobs);
                                if (img.equalsIgnoreCase("")){
                                    Picasso.with(getActivity()).load(R.drawable.profile_icon).transform(new CircleTransform()).into(user_image);
                                }else {
                                    Picasso.with(getActivity()).load(img).transform(new CircleTransform()).into(user_image);
                                }

                                getState();

//                                if(citys.equals(" ")){
//                                    city.setHint("City");
//                                }else {
//                                    city.setText(citys);
//                                }

////                                if(addresss.equals(" ")){
//                                    address.setHint("Addresss");
//                                }else {
//                                    address.setText(addresss);
                               // }
//                                if(occupations.equals(" ")){
//                                    occupation.setHint("Occupations");
//                                }else{
//                                    occupation.setText(occupations);
//                                }

//                                birthdate.setText(dobs);
//                                Log.d("RanjeetProfiel",names);

                            }


                        } catch (Exception r) {
                            progressDialog.hideDialog();
                            Log.d("Ranjeetkumar", "ranjeet Error" + r.toString());
//                            Toast.makeText(getActivity(), "Successfully Logined", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hideDialog();
                Log.d("Ranjeet", "Error: " + error.getMessage());
                // hide the progress dialog

//               getValues();
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                    Toast.makeText(getActivity(), "Please check Internet Connection", Toast.LENGTH_SHORT).show();
                    // ...
                }
            }
        });
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppSingleton.getInstance(getActivity()).addToRequestQueue(jsonObjReq, url);
    }

    public class Updateuserprofile extends AsyncTask<String, String, String> {


        protected void onPreExecute() {

            progressDialog.showDialog();
        }

        @Override
        protected String doInBackground(String... params) {

            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

            nameValuePairs.add(new BasicNameValuePair("u_id",session.getUserID()));
            nameValuePairs.add(new BasicNameValuePair("name",name.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("mobile",phone.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("email",email.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("state_id",stateId));
            nameValuePairs.add(new BasicNameValuePair("city",city.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("address",address.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("occupation",occupation.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("dob",birthdate.getText().toString() ));
            nameValuePairs.add(new BasicNameValuePair("img",baseimage));
            Log.d("sjjn",baseimage);


            String url="https://egknow.com/service-web/webservice.php?method=editUserProfile";


            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(url);
//                httppost.setHeader("X-API-KEY", "admin@123");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
                value = EntityUtils.toString(response.getEntity());
                Log.v("updateprofile", "In the try Loop" + value);
            } catch (Exception e) {
//                loadinglayout.setVisibility(View.GONE);
                progressDialog.hideDialog();
                Log.v("updateprofileree", "Error in http connection " + e.toString());
            }
            return value;
        }


        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d("hfhdf",result);


            try {
//

//                progress.dismiss();
//                progressDialog.hideDialog();
                progressDialog.hideDialog();

                JSONObject jsonObject = new JSONObject(result);
                String success = jsonObject.getString("success");

                if(success.equalsIgnoreCase("true")) {

                               JSONObject jsonSub = jsonObject.getJSONObject("userProf");
                                String names = jsonSub.getString("name").trim();
                                String emails = jsonSub.getString("email").trim();
                                String mobile =jsonSub.getString("mobile").trim();
                                String states = jsonSub.getString("state").trim();
                                String citys = jsonSub.getString("city").trim();
                                String addresss = jsonSub.getString("address").trim();
                                String occupations = jsonSub.getString("occupation").trim();
                                String dobs = jsonSub.getString("dob").trim();
                                String img = jsonSub.getString("img").trim();


                                name.setText(names);
                                email.setText(emails);
                                phone.setText(mobile);

                                city.setText(citys);
                                address.setText(addresss);
                                occupation.setText(occupations);
                                birthdate.setText(dobs);
                    if (img.equalsIgnoreCase("")){
                        Picasso.with(getActivity()).load(R.drawable.profile_icon).transform(new CircleTransform()).into(user_image);
                    }else {
                        Picasso.with(getActivity()).load(img).transform(new CircleTransform()).into(user_image);
                    }
//                                Picasso.with(getActivity()).load(img).transform(new CircleTransform()).into(user_image);
                                session.setPhoto(img);
                                Log.d("djkj",img);
                                Log.d("djhf",session.getPhoto());

                    int spinnerPosition = dataAdapter.getPosition(states);
                    state.setSelection(spinnerPosition);

                               name.setEnabled(false);
                                email.setEnabled(false);
                                phone.setEnabled(false);
                                state.setEnabled(false);
                                city.setEnabled(false);
                                address.setEnabled(false);
                                occupation.setEnabled(false);
                                birthdate.setEnabled(false);
                                edit.setVisibility(View.VISIBLE);
                                save_profile.setVisibility(View.GONE);
//                                user_image.setEnabled(false);

                                state.setEnabled(false);
                                state.setClickable(false);


                    Toast.makeText(getActivity(), "Profile Update Successfully", Toast.LENGTH_SHORT).show();

                }else if (success.equalsIgnoreCase("false")){

                    String message=jsonObject.getString("msg");
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(getActivity(), "Login Failed Plz try again", Toast.LENGTH_SHORT).show();
                }

//
            } catch (Exception e) {
//                progressDialog.hideDialog();
                progressDialog.hideDialog();
                Log.d("MateriralDesign",e.toString());

            }

        }
    }

    public void getState(){

        String url = "https://egknow.com/service-web/webservice.php?method=getStateList";
        Log.d("RanjeetUrlCheck", url);

        url = url.replaceAll("\\s", "%20");


        Log.d("Receive", url);

        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Ranjeetregister", response.toString());
                        String REsult = response.toString();
                        //    pDialog.dismiss();

                        try {

                            progressDialog.hideDialog();
                            JSONObject jsonObjMain = new JSONObject(REsult);
                            String statuse = jsonObjMain.getString("success");

                            if (statuse.equalsIgnoreCase("true")) {


                                JSONArray jsonArray = jsonObjMain.getJSONArray("stateList");

                                for(int i =0 ; i<jsonArray.length();i++){

                                    JSONObject jsonObjectSub = jsonArray.getJSONObject(i);

                                    String state_id= jsonObjectSub.getString("state_id");
                                    String state_name =jsonObjectSub.getString("state_name");
                                    categories.add(state_name);

                                    hashMapSatte.put(state_name,state_id);
                                }
                                categories.add(0, "Select State");
                                dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);
                                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                state.setAdapter(dataAdapter);
                                int spinnerPosition = dataAdapter.getPosition(statess);
                                state.setSelection(spinnerPosition);


                            }
                            else{

                                String errro_mesg = jsonObjMain.getString("err_msg");
                                Toast.makeText(getActivity(), errro_mesg, Toast.LENGTH_SHORT).show();
                            }
//
                        } catch (Exception r) {
                            progressDialog.hideDialog();
                            Log.d("Ranjeetkumar", "ranjeet Error" + r.toString());
                            Toast.makeText(getActivity(), "Not Registered Succesfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Ranjeet", "Error: " + error.getMessage());
                // hide the progress dialog

//               getValues();
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                    Toast.makeText(getActivity(), "Please check Internet Connection", Toast.LENGTH_SHORT).show();
                    // ...
                }
            }
        });
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppSingleton.getInstance(getActivity()).addToRequestQueue(jsonObjReq, url);

    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    class CircleTransform implements Transformation {

        boolean mCircleSeparator = false;

        public CircleTransform() {
        }

        public CircleTransform(boolean circleSeparator) {
            mCircleSeparator = circleSeparator;
        }

        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }
            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());
            Canvas canvas = new Canvas(bitmap);
            BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.FILTER_BITMAP_FLAG);
            paint.setShader(shader);
            float r = size / 2f;
            canvas.drawCircle(r, r, r - 1, paint);
            // Make the thin border:
            Paint paintBorder = new Paint();
            paintBorder.setStyle(Paint.Style.STROKE);
            paintBorder.setColor(Color.argb(84,0,0,0));
            paintBorder.setAntiAlias(true);
            paintBorder.setStrokeWidth(1);
            canvas.drawCircle(r, r, r-1, paintBorder);

            // Optional separator for stacking:
            if (mCircleSeparator) {
                Paint paintBorderSeparator = new Paint();
                paintBorderSeparator.setStyle(Paint.Style.STROKE);
                paintBorderSeparator.setColor(Color.parseColor("#ffffff"));
                paintBorderSeparator.setAntiAlias(true);
                paintBorderSeparator.setStrokeWidth(4);
                canvas.drawCircle(r, r, r+1, paintBorderSeparator);
            }
            squaredBitmap.recycle();
            return bitmap;
        }


        @Override
        public String key() {
            return "circle";
        }
    }

    }




