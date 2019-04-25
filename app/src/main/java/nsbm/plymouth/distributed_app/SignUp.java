package nsbm.plymouth.distributed_app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class SignUp extends AppCompatActivity {


    ImageView userPhoto;

    static int PreqCode=1;
    static int REQUESTCODE=1;
    Uri pickedimg;
    EditText UserName,UserEmail,UserPassword;


    FirebaseAuth auth;
    ProgressBar LoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        auth=FirebaseAuth.getInstance();



        userPhoto=findViewById(R.id.imguser);
        userPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=22){
                    checkAndRequestForPermission();
                }
                else {
                    openGallery();
                }
            }
        });


        UserName=(EditText)findViewById(R.id.txtusername);
        UserEmail=(EditText)findViewById(R.id.txtemailaddress);
        UserPassword=(EditText)findViewById(R.id.txtpassword);

        final Button buttonreg = findViewById(R.id.btnregister);
        final Button buttoncancel=findViewById(R.id.btncancel);
        LoadingBar=findViewById(R.id.progressBar);
        buttonreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoadingBar.setVisibility(View.VISIBLE);

                final String uname=UserName.getText().toString();
                final String uemail=UserEmail.getText().toString();
                final String pword=UserPassword.getText().toString();

                if (uname.isEmpty() || uemail.isEmpty() || pword.isEmpty()){
                    showMessage("Please Verify All Fields");

                    LoadingBar.setVisibility(View.INVISIBLE);
                    return;

                }else {
                    CreateUserAccount(uname,uemail,pword);

                }


            }
        });

        buttoncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelkintent=new Intent(SignUp.this,Login.class);
                startActivity(cancelkintent);
            }
        });
    }


    private void CreateUserAccount(final String uname, String uemail, String pword) {

        auth.createUserWithEmailAndPassword(uemail,pword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            showMessage("Account Created");

                            updateUserINFO(uname,pickedimg,auth.getCurrentUser());

                        }else {
                            LoadingBar.setVisibility(View.INVISIBLE);
                            showMessage("Failed to Register"+task.getException().getMessage());
                        }
                    }
                });

    }

    private void updateUserINFO(final String uname, Uri pickedimg, final FirebaseUser currentUser) {

        StorageReference storageReference= FirebaseStorage.getInstance().getReference().child("users_photos");
        final StorageReference imgfilepath=storageReference.child(pickedimg.getLastPathSegment());
        imgfilepath.putFile(pickedimg).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                imgfilepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        UserProfileChangeRequest profileupadte=new UserProfileChangeRequest.Builder()
                                .setDisplayName(uname)
                                .setPhotoUri(uri)
                                .build();

                        currentUser.updateProfile(profileupadte)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()){
                                            showMessage("Resgistration Completed");
                                            updateUI();
                                        }
                                    }
                                });
                    }
                });


            }
        });


    }

    private void updateUI() {
        Intent intent= new Intent(SignUp.this,MainActivity.class);
        startActivity(intent);

    }

    private void showMessage(String message) {

        Toast.makeText(SignUp.this,message,Toast.LENGTH_LONG).show();

    }

    private void openGallery() {

        Intent GalleryIntent=new Intent(Intent.ACTION_GET_CONTENT);
        GalleryIntent.setType("image/*");
        startActivityForResult(GalleryIntent,REQUESTCODE);
    }

    private void checkAndRequestForPermission() {
        if (ContextCompat.checkSelfPermission(SignUp.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(SignUp.this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                Toast.makeText(SignUp.this,"Please Allow Permission",Toast.LENGTH_SHORT).show();
            }else {
                ActivityCompat.requestPermissions(SignUp.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PreqCode);
            }
        }else {

            openGallery();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUESTCODE && resultCode==RESULT_OK && data != null){
            pickedimg=data.getData();
            userPhoto.setImageURI(pickedimg);
        }

    }
}
