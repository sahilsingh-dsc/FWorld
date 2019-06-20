package com.example.fwprld.ui.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fwprld.R;
import com.example.fwprld.adapters.FClubAdapter;
import com.example.fwprld.adapters.PopularAdapterNew;
import com.example.fwprld.adapters.profileadapters.CareerAdapter;
import com.example.fwprld.adapters.profileadapters.EducationAdapter;
import com.example.fwprld.common.LoginSession;
import com.example.fwprld.models.SongBean;
import com.example.fwprld.models.profilemodels.BasicInfoData;
import com.example.fwprld.models.profilemodels.CareerData;
import com.example.fwprld.models.profilemodels.EducationData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class EditProfileActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    ImageView imgProfileAvatar;
    TextView txtChangeProfileAvatar, txtEditProfileBirthday, txtEditProfileRelationStatus, txtEditProfileAddEdu, txtEditProfileAddCarrer, txtStartDate, txtEndDate;
    private Uri avatarPath;
    FirebaseUser firebaseUser;
    FirebaseStorage storage;
    StorageReference avatarStoreRef;
    private final int PICK_IMAGE_REQUEST = 71;
    private AlertDialog loadingDialog;
    String[] genderList = {"Male", "Female", "Other"};
    String[] relationList = {"Single", "Seeking contacts", "In a relationship", "Engaged", "Married", "Divorced", "Widowed", "Secret"};
    private String gender, relation;
    int mYear, mMonth ,mDay;
    String school_name, start_date, end_date;
    String datetag, avatar_url="";
    private DatabaseReference profileRef,popularRef;
    private TextView txtEditProfileGender;
    String fid;
    EditText txtProfileName, txtEditProfileHomeTown, etxtProfileStatus;
    TextView txtEditProfileFID ,txtEditProfileUpdate,tvBack;

    List<EducationData> educationDataList;
    RecyclerView recyclerEdu;

    List<CareerData> careerDataList;
    RecyclerView recyclerCareer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        fid = firebaseUser.getUid();

        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        loadingDialog = new SpotsDialog.Builder().setContext(EditProfileActivity.this)
                .setTheme(R.style.loading)
                .setMessage("Uploading Avatar")
                .setCancelable(false)
                .build();


        txtProfileName = findViewById(R.id.txtProfileName);
        txtEditProfileHomeTown = findViewById(R.id.txtEditProfileHomeTown);
        etxtProfileStatus = findViewById(R.id.etxtProfileStatus);
        txtEditProfileFID = findViewById(R.id.txtEditProfileFID);

        imgProfileAvatar = findViewById(R.id.imgProfileAvatar);
        txtChangeProfileAvatar = findViewById(R.id.txtChangeProfileAvatar);
        tvBack = findViewById(R.id.tv_back);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

        txtChangeProfileAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        txtEditProfileGender = findViewById(R.id.txtEditProfileGender);
        txtEditProfileGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseGender();
            }
        });

        txtEditProfileBirthday = findViewById(R.id.txtEditProfileBirthday);
        txtEditProfileBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datetag = "BIRTHDAY";
                getDatePicker();
            }
        });

        txtEditProfileRelationStatus = findViewById(R.id.txtEditProfileRelationStatus);
        txtEditProfileRelationStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseRelationShip();
            }
        });

        txtEditProfileAddEdu = findViewById(R.id.txtEditProfileAddEdu);
        txtEditProfileAddEdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEducation();
            }
        });
        txtEditProfileAddCarrer = findViewById(R.id.txtEditProfileAddCareer);
        txtEditProfileAddCarrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCaree();
            }
        });


        getAllUserEdu();
        getUserInfo();
//        getPopUserInfo();
        txtEditProfileUpdate = findViewById(R.id.txtEditProfileUpdate);
        txtEditProfileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInfo();
            }
        });

        avatarStoreRef = FirebaseStorage.getInstance().getReference("FWORLD_USER_AVATAR").child(fid+".jpg");
        avatarStoreRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                try {
                    avatar_url = uri.toString();
                    Glide.with(EditProfileActivity.this).load(avatar_url).into(imgProfileAvatar);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            avatarPath = data.getData();

            try {
                //getting image from gallery
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), avatarPath);

                //Setting image to ImageView
                imgProfileAvatar.setImageBitmap(bitmap);
                uploadImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage() {

        if(avatarPath != null) {
            loadingDialog.show();

            avatarStoreRef = FirebaseStorage.getInstance().getReference("FWORLD_USER_AVATAR").child(fid+".jpg");
            UploadTask uploadTask = avatarStoreRef.putFile(avatarPath);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    loadingDialog.dismiss();
                    Toast.makeText(EditProfileActivity.this, "Upload successful", Toast.LENGTH_SHORT).show();


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    loadingDialog.dismiss();
                    Toast.makeText(EditProfileActivity.this, "Upload Failed" + e, Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            Toast.makeText(EditProfileActivity.this, "Select Avatar", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),SettingActivity.class));
        finish();
        super.onBackPressed();
    }

    private void chooseGender(){

            final LayoutInflater li = LayoutInflater.from(EditProfileActivity.this);

            View confirmDialog = li.inflate(R.layout.activity_create_dialog, null);
            final ListView listView = (ListView) confirmDialog.findViewById(R.id.list);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(EditProfileActivity.this, android.R.layout.simple_list_item_1, genderList);
            listView.setAdapter(arrayAdapter);
            AlertDialog.Builder alert = new AlertDialog.Builder(EditProfileActivity.this);
            alert.setView(confirmDialog);
            final AlertDialog alertDialog = alert.create();
            alertDialog.show();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    alertDialog.dismiss();
                    gender = ((TextView) view).getText().toString();
                    txtEditProfileGender.setText(gender);

                }
            });
        }

    private void chooseRelationShip(){

        final LayoutInflater li = LayoutInflater.from(EditProfileActivity.this);

        View confirmDialog = li.inflate(R.layout.activity_create_dialog, null);
        final ListView listView = (ListView) confirmDialog.findViewById(R.id.list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(EditProfileActivity.this, android.R.layout.simple_list_item_1, relationList);
        listView.setAdapter(arrayAdapter);
        AlertDialog.Builder alert = new AlertDialog.Builder(EditProfileActivity.this);
        alert.setView(confirmDialog);
        final AlertDialog alertDialog = alert.create();
        alertDialog.show();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alertDialog.dismiss();
                relation = ((TextView) view).getText().toString();
                txtEditProfileRelationStatus.setText(relation);

            }
        });
    }

    private void addEducation(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(EditProfileActivity.this);
        LayoutInflater inflater = EditProfileActivity.this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.education_alert, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog eduDialog = dialogBuilder.create();

        final EditText etxtSchoolName = dialogView.findViewById(R.id.etxtSchoolName);

        txtStartDate = dialogView.findViewById(R.id.txtStartDate);

        txtStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datetag = "EDUSTART";
                getDatePicker();

            }
        });
        txtEndDate = dialogView.findViewById(R.id.txtEndDate);
        txtEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datetag = "EDUEND";
                getDatePicker();
            }
        });



        TextView txtSaveEdu = dialogView.findViewById(R.id.txtSaveEdu);
        txtSaveEdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                school_name = etxtSchoolName.getText().toString();

                if (TextUtils.isEmpty(school_name)){
                    Toast.makeText(EditProfileActivity.this, "Please enter school name", Toast.LENGTH_SHORT).show();
                    return;
                }

                start_date = txtStartDate.getText().toString();

                if (TextUtils.isEmpty(start_date)){
                    Toast.makeText(EditProfileActivity.this, "Please tell when you started here", Toast.LENGTH_SHORT).show();
                    return;
                }

                end_date = txtEndDate.getText().toString();

                if (TextUtils.isEmpty(end_date)){
                    end_date = "Present";
                }

                EducationData educationData = new EducationData(school_name, start_date ,end_date);

                profileRef = FirebaseDatabase.getInstance().getReference("FWORLD_USER_DATA").child("USER_PROFILE");
                profileRef.child("USER_EDUCATION").child(fid).push().setValue(educationData);



                eduDialog.dismiss();
                Toast.makeText(EditProfileActivity.this, "Saved", Toast.LENGTH_SHORT).show();

            }
        });

        eduDialog.show();
    }

    TextView tvJoinDate, tvLeavDate;
    private void addCaree(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(EditProfileActivity.this);
        LayoutInflater inflater = EditProfileActivity.this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.career_alert, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog CareerDlg = dialogBuilder.create();

        final EditText etPosition = dialogView.findViewById(R.id.etposition);
        final EditText etCompNm = dialogView.findViewById(R.id.etcompanyname);

        tvJoinDate = dialogView.findViewById(R.id.txt_join_date);

        tvJoinDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datetag = "JoinDate";
                getDatePicker();

            }
        });

        tvLeavDate = dialogView.findViewById(R.id.txt_leave_date);
        tvLeavDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datetag = "LeavDate";
                getDatePicker();
            }
        });



        TextView txtSaveCareer = dialogView.findViewById(R.id.txtSaveCareer);
        txtSaveCareer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String position = etPosition.getText().toString();
                String companyNm = etCompNm.getText().toString();

                if (TextUtils.isEmpty(position)){
                    Toast.makeText(EditProfileActivity.this, "Please enter position", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(companyNm)){
                    Toast.makeText(EditProfileActivity.this, "Please enter company name", Toast.LENGTH_SHORT).show();
                    return;
                }

               String join_date = tvJoinDate.getText().toString();

                if (TextUtils.isEmpty(join_date)){
                    Toast.makeText(EditProfileActivity.this, "Please tell us when you join", Toast.LENGTH_SHORT).show();
                    return;
                }

               String leave_date = tvLeavDate.getText().toString();

                if (TextUtils.isEmpty(leave_date)){
                    Toast.makeText(EditProfileActivity.this, "Please tell us when you leave", Toast.LENGTH_SHORT).show();
                    return;
                }


                CareerData careerData = new CareerData(position,companyNm, join_date ,leave_date);

     DatabaseReference careerRef = FirebaseDatabase.getInstance().getReference("FWORLD_USER_DATA").child("USER_PROFILE");
                careerRef.child("USER_CAREER").child(fid).push().setValue(careerData);

                CareerDlg.dismiss();
                Toast.makeText(EditProfileActivity.this, "Saved", Toast.LENGTH_SHORT).show();

            }
        });

        CareerDlg.show();
    }


    public void getDatePicker(){
        DatePickerDialog dialogDatePicker = new DatePickerDialog(EditProfileActivity.this, this, mYear, mMonth, mDay);
        dialogDatePicker.getDatePicker().setCalendarViewShown(false);
        dialogDatePicker.show();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if (datetag.equals("BIRTHDAY")){
            txtEditProfileBirthday.setText(new StringBuilder().append(year).append("-").append(month+1).append("-").append(dayOfMonth).toString());
        } else if (datetag.equals("EDUSTART")){
            txtStartDate.setText(new StringBuilder().append(year).append("-").append(month+1).toString());
        } else if (datetag.equals("EDUEND")){
            txtEndDate.setText(new StringBuilder().append(year).append("-").append(month+1).toString());
        }
        else if (datetag.equals("JoinDate")){
            tvJoinDate.setText(new StringBuilder().append(year).append("-").append(month+1).toString());
        } else if (datetag.equals("LeavDate")){
            tvLeavDate.setText(new StringBuilder().append(year).append("-").append(month+1).toString());
        }

    }


    private void getAllUserEdu(){

        recyclerEdu = (RecyclerView) findViewById(R.id.recyclerEdu);
        recyclerEdu.setHasFixedSize(true);
        recyclerEdu.setLayoutManager(new LinearLayoutManager(EditProfileActivity.this));

        recyclerCareer = (RecyclerView) findViewById(R.id.recyclerCareer);
        recyclerCareer.setHasFixedSize(true);
        recyclerCareer.setLayoutManager(new LinearLayoutManager(EditProfileActivity.this));

        educationDataList = new ArrayList<>();
        educationDataList.clear();

        careerDataList = new ArrayList<>();
        careerDataList.clear();

        profileRef = FirebaseDatabase.getInstance().getReference("FWORLD_USER_DATA").child("USER_PROFILE");
        profileRef.child("USER_EDUCATION").child(fid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                educationDataList.clear();
                for (DataSnapshot eduSnap : dataSnapshot.getChildren()){

                    String school_name = (String) eduSnap.child("school_name").getValue();
                    String start_date = (String) eduSnap.child("start_date").getValue();
                    String end_date = (String) eduSnap.child("end_date").getValue();

                    EducationData educationData = new EducationData(school_name, start_date, end_date);
                    educationDataList.add(educationData);

                }

                EducationAdapter educationAdapter = new EducationAdapter(educationDataList, EditProfileActivity.this);
                recyclerEdu.setAdapter(educationAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        profileRef.child("USER_CAREER").child(fid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                careerDataList.clear();
                for (DataSnapshot eduSnap : dataSnapshot.getChildren()){

                    String position = (String) eduSnap.child("position").getValue();
                    String company_name = (String) eduSnap.child("company_name").getValue();
                    String join_date = (String) eduSnap.child("join_date").getValue();
                    String leave_date = (String) eduSnap.child("leave_date").getValue();
//
                    CareerData educationData = new CareerData(position,company_name, join_date, leave_date);
                    careerDataList.add(educationData);

                }

                CareerAdapter educationAdapter = new CareerAdapter(careerDataList, EditProfileActivity.this);
                recyclerCareer.setAdapter(educationAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    boolean checkrecord=false;
    String user_profile="";
    private void saveUserInfo(){

        String user_name, user_fid, user_gender1, user_birthday, user_relationship_status, user_about, user_status;


        user_name = txtProfileName.getText().toString();
        user_fid = firebaseUser.getUid();
        user_gender1 = txtEditProfileGender.getText().toString();
        user_birthday = txtEditProfileBirthday.getText().toString();
        user_relationship_status = txtEditProfileRelationStatus.getText().toString();
        user_about = txtEditProfileHomeTown.getText().toString();
        user_status = etxtProfileStatus.getText().toString();

         if(!TextUtils.isEmpty(avatar_url))
         {
             user_profile=avatar_url;
         }

        String followers = "0";
        String followings = "0";
        String rank_record = "0";
        String bio = "foody,music listner fun friends";

        BasicInfoData basicInfoData = new BasicInfoData(user_name, user_fid, user_gender1, user_birthday ,user_relationship_status, user_about, user_status
                ,followers,followings,rank_record,bio);

        profileRef = FirebaseDatabase.getInstance().getReference("FWORLD_USER_DATA").child("USER_PROFILE");

        profileRef.child("USER_BASIC_INFO").child(user_fid).setValue(basicInfoData);
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();



        DatabaseReference checkpopup = FirebaseDatabase.getInstance().getReference("POPULAR_USERS");
        checkpopup.child("POPULAR_IN_MP").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ftalSnap : dataSnapshot.getChildren()){

//                    String user_name = (String) ftalSnap.child("user_name").getValue();
                    String  user_fidloac = (String) ftalSnap.child("user_id").getValue();
//                    String  user_pic = (String) ftalSnap.child("user_pic").getValue();

                    if(user_fid.equals(user_fidloac))
                    {
                        checkrecord=true;
                    }

                }



                SongBean songbean=new SongBean("1","https://firebasestorage.googleapis.com/v0/b/fworld-3c022.appspot.com/o/FCLUB_ROOM%2Fdrum-roll.jpg?alt=media&token=6a4aeabd-7b0f-4b9c-a71b-2ecb72e6223b",
                        "Test Song","5","1.4k","2:30","Song signer","https://firebasestorage.googleapis.com/v0/b/fworld-3c022.appspot.com/o/FCLUB_ROOM%2FWikipedia_User-ICON_byNightsight.png?alt=media&token=c91393da-1bd8-4788-81fe-2951a04adb3f"
                        ,"username",user_fid,user_name,user_profile
                );
                Log.e("","checkrecord= "+checkrecord);
                if(!checkrecord)
                {
                    popularRef.child("POPULAR_IN_MP").push().setValue(songbean);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//////////////////////////for popular users//////////////////
        popularRef = FirebaseDatabase.getInstance().getReference("POPULAR_USERS");
//        PopularAddBean bean=new PopularAddBean(fid,"1");
//        popularRef.child("POPULAR_IN_MP").push().setValue(bean);//.setValue(basicInfoData);
//        popularRef.child("BEST_F_TALENT_SOLO").push().setValue(bean);//.setValue(basicInfoData);
//        popularRef.child("BEST_COLLAB_DUET").push().setValue(bean);//.setValue(basicInfoData);
//        popularRef.child("MOST_SHARED").push().setValue(bean);//.setValue(basicInfoData);
//        popularRef.child("TOP_STARTS").push().setValue(bean);//.setValue(basicInfoData);
//        popularRef.child("TOP_FRIENDS").push().setValue(bean);//.setValue(basicInfoData);
//        popularRef.child("TOP_GIFTER").push().setValue(bean);//.setValue(basicInfoData);
//        popularRef.child("HOT_TOPICS").push().setValue(bean);//.setValue(basicInfoData);

//        popularRef.child("POPULAR_IN_MP").push().setValue(basicInfoData);//.setValue(basicInfoData);
//        popularRef.child("BEST_F_TALENT_SOLO").push().setValue(basicInfoData);//.setValue(basicInfoData);
//        popularRef.child("BEST_COLLAB_DUET").push().setValue(basicInfoData);//.setValue(basicInfoData);
//        popularRef.child("MOST_SHARED").push().setValue(basicInfoData);//.setValue(basicInfoData);
//        popularRef.child("TOP_STARTS").push().setValue(basicInfoData);//.setValue(basicInfoData);
//        popularRef.child("TOP_FRIENDS").push().setValue(basicInfoData);//.setValue(basicInfoData);
//        popularRef.child("TOP_GIFTER").push().setValue(basicInfoData);//.setValue(basicInfoData);
//        popularRef.child("HOT_TOPICS").push().setValue(basicInfoData);//.setValue(basicInfoData);
//        song_id,song_image,song_name,song_like,song_plays,song_playtime,song_singer,songby_image,songby_name,user_id


//        popularRef.child("BEST_F_TALENT_SOLO").push().setValue(songbean);
//        popularRef.child("BEST_COLLAB_DUET").push().setValue(songbean);
//        popularRef.child("MOST_SHARED").push().setValue(songbean);
//        popularRef.child("TOP_STARTS").push().setValue(songbean);
//        popularRef.child("TOP_STARTS").push().setValue(songbean);
//        popularRef.child("TOP_FRIENDS").push().setValue(songbean);
//        popularRef.child("TOP_GIFTER").push().setValue(songbean);
//        popularRef.child("HOT_TOPICS").push().setValue(songbean);


        //////////////////////////for you may like users//////////////////

//        DatabaseReference  youmaylike = FirebaseDatabase.getInstance().getReference("YOU_MAY_LIKE");
//
//        youmaylike.child("RECENT_LIKE").push().setValue(songbean);

        //////////////////////////for you may like users//////////////////

    }

    private void getUserInfo(){
        profileRef = FirebaseDatabase.getInstance().getReference("FWORLD_USER_DATA").child("USER_PROFILE");
        profileRef.child("USER_BASIC_INFO").child(fid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String user_name, user_fid, user_gender, user_birthday, user_relationship_status, user_about, user_status;

                user_name = (String) dataSnapshot.child("user_name").getValue();
                user_fid = (String) dataSnapshot.child("user_fid").getValue();
                user_gender = (String) dataSnapshot.child("user_gender").getValue();
                user_birthday = (String) dataSnapshot.child("user_birthday").getValue();
                user_relationship_status = (String) dataSnapshot.child("user_relationship_status").getValue();
                user_about = (String) dataSnapshot.child("user_about").getValue();
                user_status = (String) dataSnapshot.child("user_status").getValue();


                txtProfileName.setText(user_name);
                txtEditProfileFID.setText(user_fid);
                txtEditProfileGender.setText(user_gender);
                txtEditProfileBirthday.setText(user_birthday);
                txtEditProfileRelationStatus.setText(user_relationship_status);
                txtEditProfileHomeTown.setText(user_about);
                etxtProfileStatus.setText(user_status);

//            if()


                JSONObject jsLogin=null;
                try {
                     jsLogin=new JSONObject();
                    jsLogin.put("id",fid);
                    jsLogin.put("name",user_name);
                    jsLogin.put("pic",avatar_url);
                    jsLogin.put("mobile",new LoginSession(EditProfileActivity.this).GetMobileNo(EditProfileActivity.this));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                            LoginSession sessionParam = new LoginSession(jsLogin);
                            sessionParam.persist(EditProfileActivity.this);//for session manage at splash,login screen


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void getPopUserInfo(){
        DatabaseReference profileRefCheck = FirebaseDatabase.getInstance().getReference("POPULAR_USERS");
        profileRefCheck.child("POPULAR_IN_MP").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String user_name, user_fid, user_gender, user_birthday, user_relationship_status, user_about, user_status;

                user_name = (String) dataSnapshot.child("user_name").getValue();
//                user_fid = (String) dataSnapshot.child("user_fid").getValue();
//                user_gender = (String) dataSnapshot.child("user_gender").getValue();
//                user_birthday = (String) dataSnapshot.child("user_birthday").getValue();
//                user_relationship_status = (String) dataSnapshot.child("user_relationship_status").getValue();
//                user_about = (String) dataSnapshot.child("user_about").getValue();
//                user_status = (String) dataSnapshot.child("user_status").getValue();

                Log.e("","popular user_name= "+user_name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}
