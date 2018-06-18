package com.freshorganickart.arpitkanda.trainproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class Login extends Fragment implements GoogleApiClient.OnConnectionFailedListener{
    ImageView GoogleImage;
    ImageView ImageClick;
    String google_name="";
    String googel_email="";
    TextView Name;
    TextView EMail;
    Fragment fragment;
    Button login_button;
    GoogleApiClient googleApiClient;
    private static final int REQ_CODE=9001;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.login_info, container, false);
        Name=(TextView)view.findViewById(R.id.gg_n);
        GoogleImage=(ImageView)view.findViewById(R.id.gg_img);
        ImageClick=(ImageView)view.findViewById(R.id.gmail_id);
        EMail=(TextView)view.findViewById(R.id.gg_em);
        login_button=(Button)view.findViewById(R.id.login_button_id);
        google_login();
        GoogleSignInOptions signInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient=new GoogleApiClient.Builder(getContext()).enableAutoManage(getActivity(),this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();
        return view;

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void google_login(){
        ImageClick.setClickable(true);
        ImageClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,REQ_CODE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ_CODE){
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handle_result(result);
        }
    }

    public void handle_result(GoogleSignInResult result){

        if(result.isSuccess()){
            GoogleSignInAccount account= result.getSignInAccount();
            String name=account.getDisplayName();
            String email=account.getEmail();
            String img=account.getPhotoUrl().toString();
            Name.setText(name);
            EMail.setText(email);
            Glide.with(getContext()).load(img).into(GoogleImage);
            google_name=Name.getText().toString();
            googel_email=EMail.getText().toString();
            loadActivity();
        }

    }

    public void loadActivity(){
        Intent intent = new Intent(getContext(), My_Account.class);
        intent.putExtra("name",google_name);
        intent.putExtra("email",googel_email);
        startActivity(intent);
    }

}
