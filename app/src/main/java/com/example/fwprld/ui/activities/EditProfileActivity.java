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
import com.example.fwprld.adapters.profileadapters.EducationAdapter;
import com.example.fwprld.models.profilemodels.BasicInfoData;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class EditProfileActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    ImageView imgProfileAvatar;
    TextView txtChangeProfileAvatar, txtEditProfileBirthday, txtEditProfileRelationStatus, txtEditProfileAddEdu, txtStartDate, txtEndDate;
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
    String datetag, avatar_url;
    private DatabaseReference profileRef;
    private TextView txtEditProfileGender;
    String fid;
    EditText txtProfileName, txtEditProfileHomeTown, etxtProfileStatus;
    TextView txtEditProfileFID ,txtEditProfileUpdate;

    List<EducationData> educationDataList;
    RecyclerView recyclerEdu;

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


        getAllUserEdu();
        getUserInfo();

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
                avatar_url = uri.toString();
                Glide.with(EditProfileActivity.this).load(avatar_url).into(imgProfileAvatar);
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


    public void getDatePicker(){
        DatePickerDialog dialogDatePicker = new DatePickerDialog(EditProfileActivity.this, this, mYear, mMonth, mDay);
        dialogDatePicker.getDatePicker().setCalendarViewShown(false);
        dialogDatePicker.show();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if (datetag.equals("BIRTHDAY")){
            txtEditProfileBirthday.setText(new StringBuilder().append(year).append("-").append(month).append("-").append(dayOfMonth).toString());
        } else if (datetag.equals("EDUSTART")){
            txtStartDate.setText(new StringBuilder().append(year).append("-").append(month).toString());
        } else if (datetag.equals("EDUEND")){
            txtEndDate.setText(new StringBuilder().append(year).append("-").append(month).toString());
        }

    }


    private void getAllUserEdu(){

        recyclerEdu = (RecyclerView) findViewById(R.id.recyclerEdu);
        recyclerEdu.setHasFixedSize(true);
        recyclerEdu.setLayoutManager(new LinearLayoutManager(EditProfileActivity.this));
        educationDataList = new ArrayList<>();
        educationDataList.clear();

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
    }


    private void saveUserInfo(){

        String user_name, user_fid, user_gender, user_birthday, user_relationship_status, user_about, user_status;


        user_name = txtProfileName.getText().toString();
        user_fid = firebaseUser.getUid();
        user_gender = txtEditProfileGender.getText().toString();
        user_birthday = txtEditProfileBirthday.getText().toString();
        user_relationship_status = txtEditProfileRelationStatus.getText().toString();
        user_about = txtEditProfileHomeTown.getText().toString();
        user_status = etxtProfileStatus.getText().toString();

        BasicInfoData basicInfoData = new BasicInfoData(user_name, user_fid, user_gender, user_birthday ,user_relationship_status, user_about, user_status);

        profileRef = FirebaseDatabase.getInstance().getReference("FWORLD_USER_DATA").child("USER_PROFILE");

        profileRef.child("USER_BASIC_INFO").child(user_fid).setValue(basicInfoData);
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();

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


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
