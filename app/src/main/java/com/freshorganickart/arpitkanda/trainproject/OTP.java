package com.freshorganickart.arpitkanda.trainproject;

//import com.portfolio.ansyst.portfolioapp.R;
//
//import com.portfolio.ansyst.portfolioapp.adapters.UserViewAdapterClass;
//
//import com.portfolio.ansyst.portfolioapp.model.Admin;
//import com.portfolio.ansyst.portfolioapp.model.UserProfile;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.mikhaellopez.circularimageview.CircularImageView;


//public class MainActivity extends AppCompatActivity
//        implements NavigationView.OnNavigationItemSelectedListener {
//
//
//    CardView firstCategory,secondCatgory,thirdCategory,fourthCategory,fifthcategory,sixthCategry,seventhCategory;
//    CircularImageView profileImageUser,adminProflePic;
//    TextView userName;
//    FirebaseAuth auth;
//    String adminUid;
//    FirebaseDatabase db;
//    DatabaseReference databaseReference,userList;
//    String Storage_Path = "All_Image_Uploads/";
//    private View navHeader;
//    RecyclerView recyclerView;
//    StorageReference storageReference;
//      List<UserProfile> list = new ArrayList<>();
//    private FirebaseStorage mFirebaseStorage;
//    TextView viewAll;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//       toolbar.setTitle("");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            toolbar.setElevation(0);
//        }
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        recyclerView = (RecyclerView) findViewById(R.id.userList);
//        adminProflePic=findViewById(R.id.adminProfile);
//        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
//        auth=FirebaseAuth.getInstance();
//        viewAll=findViewById(R.id.viewAll);
//        storageReference = FirebaseStorage.getInstance().getReference();
//        userList = FirebaseDatabase.getInstance().getReference("Users");
//       // Toast.makeText(this, ""+currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();
//      //  Log.d("UUID",currentFirebaseUser.getUid());
//        mFirebaseStorage = FirebaseStorage.getInstance();
//        LinearLayoutManager layoutManager =
//                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(layoutManager);
//        userList.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    list = new ArrayList<UserProfile>();
//                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                        UserProfile value = dataSnapshot1.getValue(UserProfile.class);
//
//                        UserProfile userProfile = new UserProfile();
//                        String admin = value.getIsAdmin();
//                        if (admin.equals("no")) {
//                            String name = value.getUsername();
//                            String photoUrl = value.getPhotoUrl();
//                            userProfile.setUsername(name);
//                            userProfile.setPhotoUrl(photoUrl);
//                            list.add(userProfile);
//                            UserViewAdapterClass userViewAdapterClass = new UserViewAdapterClass(list, MainActivity.this);
//
//                            recyclerView.setAdapter(userViewAdapterClass);
//                        }
//                    }
//
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//        userList.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                        Admin value = dataSnapshot1.getValue(Admin.class);
//
//
//                        String admin = value.getIsAdmin();
//                        if (admin.equals("yes")) {
//                            adminUid = value.getId();
//                            String photoUrl = value.getAdminProfilePicUrl();
//                            if (photoUrl != null) {
//                                StorageReference imageRef = mFirebaseStorage.getReferenceFromUrl(photoUrl);
//
//                                Glide.with(MainActivity.this)
//                                        .load(imageRef)
//
//                                        .into(adminProflePic);
//
//
//                            }
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//        adminProflePic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,AboutActivity.class);
//
//                intent.putExtra("ADMINID",adminUid);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//            }
//        });
//
//
//
//
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//        navHeader = navigationView.getHeaderView(0);
//        profileImageUser=navHeader.findViewById(R.id.profilePhoto);
//        userName=navHeader.findViewById(R.id.userName);
//        firstCategory=findViewById(R.id.firstCategory);
//        secondCatgory=findViewById(R.id.secondCategory);
//        thirdCategory=findViewById(R.id.thirdCategory);
//        fourthCategory=findViewById(R.id.fourthCategory);
//        fifthcategory=findViewById(R.id.fifthCatgory);
//        sixthCategry=findViewById(R.id.sixthcategory);
//        seventhCategory=findViewById(R.id.seventhCategory);
//        firstCategory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this,ShowAllPages.class);
//                intent.putExtra("category",0);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                //Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
//                startActivity(intent);
//            }
//        });
//     if (auth.getCurrentUser()!=null){
//         navigationView.getMenu().findItem(R.id.login).setVisible(false);
//
//
//     }else {
//         navigationView.getMenu().findItem(R.id.logout).setVisible(false);
//     }
//
//
//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference ref = database.getReference("Users");
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//if (user!=null) {
//    ref.child(currentFirebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
//        @Override
//        public void onDataChange(DataSnapshot dataSnapshot) {
//            if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
//
//                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
//                if (userProfile.getIsAdmin().equals("no")) {
//                    //String email = dataSnapshot.child("username").getValue(String.class);
//                    //String photourl=dataSnapshot.child("photoUrl").getValue(String.class);
////                                                                     String name = ds.child("name").getValue(String.class);
//                    userName.setText(userProfile.getUsername());
//                    String photourl = userProfile.getPhotoUrl();
//                    Log.d("userName", userProfile.getUsername());
//                    //Toast.makeText(MainActivity.this, ""+photourl, Toast.LENGTH_SHORT).show();
//                    //Toast.makeText(MainActivity.this, ""+email, Toast.LENGTH_SHORT).show();
//                    //userName.setText(imageUploadInfo.getImage());
//                    //  Toast.makeText(MainActivity.this, ""+imageUploadInfo.getPhotoUrl(), Toast.LENGTH_SHORT).show();
//                    if (photourl != null) {
//
//                        StorageReference imageRef = mFirebaseStorage.getReferenceFromUrl(photourl);
//
//                        Glide.with(getApplicationContext())
//                                .load(imageRef)
//
//                                .into(profileImageUser);
//
//
//                    }
//                }
//            }
//
//
//        }
//
//        @Override
//        public void onCancelled(DatabaseError databaseError) {
//
//        }
//    });
//}
//        secondCatgory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this,ShowAllPages.class);
//                intent.putExtra("category",1);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                startActivity(intent);
//            }
//        });
//        thirdCategory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this,ShowAllPages.class);
//                intent.putExtra("category",2);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                startActivity(intent);
//            }
//        });
//        fourthCategory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this,ShowAllPages.class);
//                intent.putExtra("category",3);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                startActivity(intent);
//            }
//        });
//        fifthcategory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this,ShowAllPages.class);
//                intent.putExtra("category",4);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                startActivity(intent);
//            }
//        });
//        sixthCategry.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this,ShowAllPages.class);
//                intent.putExtra("category",5);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                startActivity(intent);
//            }
//        });
//        seventhCategory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,ShowAllPages.class);
//                intent.putExtra("category",6);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                startActivity(intent);
//
//            }
//        });
//        viewAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,ShowAllPages.class);
//                intent.putExtra("category",5);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                startActivity(intent);
//
//            }
//        });
//
//    }
//
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//       // getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.login) {
//            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(intent);
//            finish();
//
//        } else if (id == R.id.home) {
//
//
//
//        } else if (id == R.id.profile) {
//            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//            if (user != null) {
//                Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
//                intent.putExtra("activity",1);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//
//            }else {
//                Toast.makeText(this, "You are not logged in", Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent(MainActivity.this,SignInActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//            }
//
//
//        }  else if (id == R.id.nav_share) {
//            try {
//                Intent i = new Intent(Intent.ACTION_SEND);
//                i.setType("text/plain");
//                i.putExtra(Intent.EXTRA_SUBJECT, "Jiya");
//                String sAux = "\nDownload this awesome app\n\n";
//                sAux = sAux + "https://play.google.com/store/apps/details?id=the.package.id \n\n";
//                i.putExtra(Intent.EXTRA_TEXT, sAux);
//                startActivity(Intent.createChooser(i, "choose one"));
//            } catch(Exception e) {
//                //e.toString();
//            }
//
//        }
//
//        else if (id==R.id.logout){
//            AlertDialog.Builder alertDialog=new AlertDialog.Builder(MainActivity.this);
//            alertDialog.setTitle("Logout");
//            alertDialog.setMessage("Are you sure you want to log out").setCancelable(false)
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel();
//                            auth.signOut();
//                            Intent intent=new Intent(getApplicationContext(),SignInActivity.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            startActivity(intent);
//
//                        }
//                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.cancel();
//                }
//            });
//            AlertDialog alertDialog1=alertDialog.create();
//            alertDialog1.show();
//
//
//
//
//
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
//    private void loadFragment(Fragment fragment) {
//        // load fragment
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }
////    public void onStart() {
////        super.onStart();
////
////        // Initialize Firebase listeners in adapter
////        adapter.startListening();
////
////        // Make sure new events are visible
////        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
////            @Override
////            public void onItemRangeChanged(int positionStart, int itemCount) {
////                recyclerView.smoothScrollToPosition(adapter.getItemCount());
////
////            }
////        });
////    }
////    @Override
////    public void onStop() {
////        super.onStop();
////
////        // Tear down Firebase listeners in adapter
////        adapter.stopListening();
//   // }
//}
