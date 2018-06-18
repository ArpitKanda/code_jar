package com.freshorganickart.arpitkanda.trainproject;

//import android.support.v8.renderscript.RenderScript;

//import com.dd.morphingbutton.MorphingButton;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.auth.PhoneAuthCredential;
//import com.google.firebase.auth.PhoneAuthProvider;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;


//import politician.ansyst.com.politician.ImageHelper;
//import politician.ansyst.com.politician.R;


//public class MainActivity extends AppCompatActivity {
//    final static String MYPREF="MYPREFERENCES";
//    final  static String COUNT="count";
//    SharedPreferences sharedPreferences;
//    EditText name,location,phoneNumber,verification;
//    Button sendOTP,verifyButton;
//    FirebaseAuth mAuth;
//    ImageView scrolling_background;
//    String mVerificationId;
//    PhoneAuthProvider.ForceResendingToken mResendToken;
//    ProgressDialog progressDialog;
//    DatabaseReference databaseReference,databaseReference2;
//
//    SharedPreferences.Editor editor;
//    ImageHelper blurBuilder;
//    Bitmap mBlurBitmap;
//    private RenderScript rs = null;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        setContentView(R.layout.activity_main);
//        name=findViewById(R.id.personName);
//        location=findViewById(R.id.location);
//        phoneNumber=findViewById(R.id.phoneNumber);
//        rs = RenderScript.create(this);
//        scrolling_background=findViewById(R.id.scrolling_background);
//
//        blurBuilder=new ImageHelper();
//        if (mBlurBitmap == null) {
//            mBlurBitmap = createBlurBitmap();
//            scrolling_background.setImageBitmap(mBlurBitmap);
//        }
//            //Blurry.with(getApplicationContext()).capture(scrolling_background).into(scrolling_background);
//        databaseReference= FirebaseDatabase.getInstance().getReference("Users");
//
//      sharedPreferences=getSharedPreferences(MYPREF,MODE_PRIVATE);
//       editor=sharedPreferences.edit();
//        sendOTP=findViewById(R.id.sendButton);
//        progressDialog=new ProgressDialog(this);
//        progressDialog.setTitle("Sending Code");
//        verification=findViewById(R.id.verificationCode);
//        verifyButton=findViewById(R.id.verifyButton);
//        mAuth=FirebaseAuth.getInstance();
//        sendOTP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (TextUtils.isEmpty(name.getText().toString().trim())){
//                    name.setError("Empty");
//                    return;
//                }
//
//                if (TextUtils.isEmpty(phoneNumber.getText().toString().trim())){
//                    phoneNumber.setError("Empty");
//                    return;
//                }
//                if (TextUtils.isEmpty(location.getText().toString().trim())){
//                    location.setError("Empty");
//                    return;
//                }
//                if (phoneNumber.getText().toString().trim().length()!=10){
//                    phoneNumber.setError("Wrong Number");
//                    return;
//                }
//                sendOTP.setEnabled(false);
//                progressDialog.setCancelable(false);
//                progressDialog.show();
//                PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                        "+91"+phoneNumber.getText().toString().trim(), 60, TimeUnit.SECONDS, MainActivity.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                            @Override
//                            public void onVerificationCompleted(final PhoneAuthCredential phoneAuthCredential) {
//
//
//                                signInWithPhoneAuthCredential(phoneAuthCredential);
//
//                            }
//
//                            @Override
//                            public void onVerificationFailed(FirebaseException e) {
//                                progressDialog.dismiss();
//                                Toast.makeText(MainActivity.this, "There was an error in verified", Toast.LENGTH_SHORT).show();
//
//                            }
//                            @Override
//                            public void onCodeSent(String verificationId,
//                                                   PhoneAuthProvider.ForceResendingToken token) {
//                                progressDialog.dismiss();
//
//                                name.setVisibility(View.GONE);
//                                location.setVisibility(View.GONE);
//                                phoneNumber.setVisibility(View.GONE);
//                                verification.setVisibility(View.VISIBLE);
//                                verifyButton.setVisibility(View.VISIBLE);
//                                sendOTP.setVisibility(View.GONE);
//                                // The SMS verification code has been sent to the provided phone number, we
//                                // now need to ask the user to enter the code and then construct a credential
//                                // by combining the code with a verification ID.
//                                Log.d("Message", "onCodeSent:" + verificationId);
//
//                                // Save verification ID and resending token so we can use them later
//                                mVerificationId = verificationId;
//                                mResendToken = token;
//                               // PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
//
//                                // ...
//                            }
//                        }
//                );
//
//
//
//
//            }
//        });
//        verifyButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                name.setVisibility(View.GONE);
//                location.setVisibility(View.GONE);
//                phoneNumber.setVisibility(View.GONE);
//                verification.setVisibility(View.VISIBLE);
//                verifyButton.setVisibility(View.VISIBLE);
//
//                sendOTP.setVisibility(View.GONE);
//
//                String code=verification.getText().toString().trim();
//                if (code.equals(null)) {
//                    verification.setError("Empty");
//                    return;
//
//                }
//                try {
//                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
//                    signInWithPhoneAuthCredential(credential);
//
//                }catch (Exception e){
//                    verification.setError("Empty");
//                }
//
//            }
//        });
//
//
//    }
//
//    private Bitmap createBlurBitmap() {
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
//                R.drawable.adminpic);
//        if (bitmap != null) {
//            ImageHelper.blurBitmapWithRenderscript(rs, bitmap);
//        }
//
//        return bitmap;
//    }
//
//    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull final Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
////                            final String currentUSer=mAuth.getCurrentUser().getUid();
////                            databaseReference.child(currentUSer).addValueEventListener(new ValueEventListener() {
////                                @Override
////                                public void onDataChange(DataSnapshot dataSnapshot) {
////                                    if (dataSnapshot.exists()){
////                                        Admin admin=dataSnapshot.getValue(Admin.class);
////
////                                        admin.getIsAdmin();
////                                        admin.getPhoneNumber();
////                                       // Toast.makeText(MainActivity.this, ""+admin.getIsAdmin()+admin.getPhoneNumber()+currentUSer+admin, Toast.LENGTH_SHORT).show();
////                                        if (admin.getIsAdmin().equals("yes")&&admin.getPhoneNumber().equals(phoneNumber.getText().toString())){
////                                            Intent intent=new Intent(MainActivity.this,DashBoardActivity.class);
////                                            editor.putInt(COUNT,1);
////                                            editor.apply();
////
////                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                                            finish();
////                                            startActivity(intent);
////                                        }else {
////
////                                            mAuth.signOut();
////
////                                            Toast.makeText(MainActivity.this, "admin is already registered", Toast.LENGTH_SHORT).show();
////                                        }
////                                        }else {
////                                        Admin user1=new Admin();
////                                        user1.setId(currentUSer);
////                                        user1.setIsAdmin("yes");
////                                        editor.putInt(COUNT,1);
////                                        editor.apply();
////
////
////                                        user1.setUserName(name.getText().toString().trim());
////
////                                        user1.setPhoneNumber(phoneNumber.getText().toString().trim());
////
////
////                                        databaseReference.child(currentUSer).setValue(user1).addOnSuccessListener(new OnSuccessListener<Void>() {
////                                            @Override
////                                            public void onSuccess(Void aVoid) {
////                                                Toast.makeText(MainActivity.this, "registered" +
////                                                        " successfuly", Toast.LENGTH_SHORT).show();
////                                            }
////                                        });
////                                        progressDialog.dismiss();
////                                        FirebaseUser user = task.getResult().getUser();
////                                        Intent intent=new Intent(MainActivity.this,DashBoardActivity.class);
////                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                                        finish();
////                                        startActivity(intent);
////                                    }
////                        }
////
////                                @Override
////                                public void onCancelled(DatabaseError databaseError) {
////
////                                }
////                            });
//
//                            // ...
//                            String currentUSer=mAuth.getCurrentUser().getUid();
////                            User user1=new User();
////                            user1.setUserID(currentUSer);
////                            user1.setFollowing("no");
////                            user1.setUserID("no");
////                            user1.setIsAdmin("no");
////                            user1.setImageUrl("");
////                            user1.setUserName(name.getText().toString().trim());
////                            user1.setLoaction(location.getText().toString().trim());
////                            user1.setPhoneNumber(phoneNumber.getText().toString().trim());
//
//
//                            Map<String,Object> map=new HashMap<String,Object>();
//
//                            map.put("userID",currentUSer);
//                            map.put("loaction",location.getText().toString().trim());
//                            map.put("userName",name.getText().toString().trim());
//                            map.put("phoneNumber",phoneNumber.getText().toString());
//                            map.put("isAdmin","no");
//                            map.put("following","no");
//                            databaseReference.child(currentUSer).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//                                    Toast.makeText(MainActivity.this, "verified" +
//                                            " successfuly", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                            progressDialog.dismiss();
//                            FirebaseUser user = task.getResult().getUser();
//                            Intent intent=new Intent(MainActivity.this,DashBoardActivity.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            editor.putInt(COUNT,1);
//                                            editor.apply();
//                            overridePendingTransition(0, 0);
//                            finish();
//                            startActivity(intent);
//                        } else {
//                            progressDialog.dismiss();
//                            Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
//                            // Sign in failed, display a message and update the UI
//                            Log.w("Message", "signInWithCredential:failure", task.getException());
//                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
//
//                            }
//                        }
//                    }
//                });
//    }
//    @Override
//    protected void onDestroy() {
//        if (mBlurBitmap != null) {
//            mBlurBitmap.recycle();
//        }
//        super.onDestroy();
//    }
//    public Bitmap captureView(View view) {
//        //Find the view we are after
//        //Create a Bitmap with the same dimensions
//        Bitmap image = Bitmap.createBitmap(view.getMeasuredWidth(),
//                view.getMeasuredHeight(),
//                Bitmap.Config.ARGB_4444); //reduce quality and remove opacity
//        //Draw the view inside the Bitmap
//        Canvas canvas = new Canvas(image);
//        view.draw(canvas);
//
//        //Make it frosty
//        Paint paint = new Paint();
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        ColorFilter filter = new LightingColorFilter(0xFFFFFFFF, 0x00222222); // lighten
//        //ColorFilter filter = new LightingColorFilter(0xFF7F7F7F, 0x00000000);    // darken
//        paint.setColorFilter(filter);
//        canvas.drawBitmap(image, 0, 0, paint);
//
//        return image;
//    }
//
//}
