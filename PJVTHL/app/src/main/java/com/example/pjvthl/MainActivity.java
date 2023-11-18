package com.example.pjvthl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentTransaction;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.pjvthl.fragment.AccountFragment;
import com.example.pjvthl.fragment.CartFragment;
import com.example.pjvthl.fragment.ContactUsFragment;
import com.example.pjvthl.fragment.HomeFragment;
import com.example.pjvthl.fragment.InfoFragment;
import com.example.pjvthl.fragment.OrderFragment;
import com.example.pjvthl.fragment.SearchFragment;
import com.example.pjvthl.fragment.UsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_INFO = 1;
    private static final int FRAGMENT_CONTACTUS = 2;
    private static final int FRAGMENT_ORDER = 3;
    private static final int FRAGMENT_LOGOUT = 4;
    private static final int FRAGMENT_US = 5;
    private static final int FRAGMENT_SEARCH = 6;
    private static final int FRAGMENT_CART = 7;
    private static final int FRAGMENT_ACCOUNT = 8;
    private int mCurrentFragment = FRAGMENT_HOME;
    private DrawerLayout mDrawerLayout;
    public static user user;
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        replaceFragment(new HomeFragment());
        navigationView.getMenu().findItem(R.id.nav_Home).setChecked(true);


        //lấy dữ liệu từ login
        Bundle bundle = getIntent().getExtras();
        user = new user();
        user = (com.example.pjvthl.user) bundle.getSerializable(Utils.KEY_USER_PROFILE);

        mBottomNavigationView = findViewById(R.id.bottom_menu);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.mn_home:
                        openHomeFragment();
                        break;
                    case R.id.mn_search:
                        openSearchFragment();
                        break;
                    case R.id.mn_cart:
                        openCartFragment();
                        break;
                    case R.id.mn_account:
                        openAccountFragment();
                        break;
                }
                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return true;
    }
    private void openHomeFragment(){
        if(mCurrentFragment!=FRAGMENT_HOME){
            replaceFragment(new HomeFragment());
            mCurrentFragment = FRAGMENT_HOME;
        }
    }
    private void openSearchFragment(){
        if(mCurrentFragment!=FRAGMENT_SEARCH){
            replaceFragment(new SearchFragment());
            mCurrentFragment = FRAGMENT_SEARCH;
        }
    }
    private void openCartFragment(){
        if(mCurrentFragment!=FRAGMENT_CART){
                replaceFragment(new CartFragment());
                mCurrentFragment = FRAGMENT_CART;
        }
    }
    private void openAccountFragment(){
        if(mCurrentFragment!=FRAGMENT_ACCOUNT){
            //truyền dữ liệu từ Mainacti vào account fragment
            Bundle bundle1 = new Bundle();
            bundle1.putSerializable("user",user);
            AccountFragment accountFragment = new AccountFragment();
            accountFragment.setArguments(bundle1);
            replaceFragment(accountFragment);
            mCurrentFragment = FRAGMENT_ACCOUNT;
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.nav_Home:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();
                break;

            case R.id.nav_Info:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new InfoFragment()).commit();
                break;

            case R.id.nav_ContactUs:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new ContactUsFragment()).commit();
                break;

            case R.id.nav_Order:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new OrderFragment()).commit();
                break;

            case R.id.nav_Logout:
                Toast.makeText(this, "Đăng xuất", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                //Tạo cờ để người dùng không thể quay lại trang trước đó
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.nav_Us:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new UsFragment()).commit();
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void replaceFragment (Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }

}
