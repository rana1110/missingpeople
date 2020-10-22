package com.ohm.missingpeople.activity;

import android.os.Bundle;

import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.navigation.NavigationView;
import com.ohm.missingpeople.R;
import com.ohm.missingpeople.adapter.AllMissingPersonAdapter;
import com.ohm.missingpeople.networkoperation.model.AllMissingPeople;
import com.ohm.missingpeople.networkoperation.model.MissingPeopleDataClass;
import com.ohm.missingpeople.networkoperation.restclient.ApiClient;
import com.ohm.missingpeople.networkoperation.restclient.ApiInterface;
import com.ohm.missingpeople.utils.ISharedPreferenceHelper;
import com.ohm.missingpeople.utils.SharedPreferenceHelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreenActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {
    Toolbar toolbar;
    LinearLayout headerLayout;
    NavigationView navigationView;
    NavigationMenuView navMenuView;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    private SwipeRefreshLayout swipeRefreshLayout;
    ApiInterface apiInterface;
    Call<AllMissingPeople> newsGetDatacall;
    private RecyclerView recyclerView;
    private AllMissingPersonAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ISharedPreferenceHelper iSharedPreferenceHelper;
    TextView userName;
    TextView logOut;
    View navViewForHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iSharedPreferenceHelper = new SharedPreferenceHelper(this);
        if (iSharedPreferenceHelper.getFName() != null && iSharedPreferenceHelper.getLName() != null) {
            Log.e("test123 ", iSharedPreferenceHelper.getFName());
            Log.e("test123 ", iSharedPreferenceHelper.getLName());
            Log.e("test123 ", iSharedPreferenceHelper.getContactNum());
            Log.e("test123 ", iSharedPreferenceHelper.getToken());
            Log.e("test123 ", "" + iSharedPreferenceHelper.checkRememberMe());
        }
        loadUI();
        populateData();

    }

    public void loadUI() {
        setContentView(R.layout.activity_home_screen);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view_menu);
        drawer = findViewById(R.id.drawer_layout);
        navMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        headerLayout = (LinearLayout) HomeScreenActivity.this.findViewById(R.id.headerLayout);
        swipeRefreshLayout = findViewById(R.id.pullToRefresh);
        recyclerView = findViewById(R.id.recycler_view);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.brandColorCode1));
        layoutManager = new LinearLayoutManager(HomeScreenActivity.this);

        navMenuView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        navigationView.setNavigationItemSelectedListener(HomeScreenActivity.this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        navViewForHeader = navigationView.getHeaderView(0);
        userName = (TextView) navViewForHeader.findViewById(R.id.user_name);
        logOut = (TextView) navViewForHeader.findViewById(R.id.logout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.navigationToggleColor));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    private void populateData() {
        userName.setText(iSharedPreferenceHelper.getFName() + " " + iSharedPreferenceHelper.getLName());
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        newsGetDatacall = apiInterface.getMissingPeopleDetail();
        newsGetDatacall.enqueue(new Callback<AllMissingPeople>() {
            @Override
            public void onResponse(Call<AllMissingPeople> call, Response<AllMissingPeople> response) {
                Log.e("", response.body().toString());
                generateNewsList((ArrayList<MissingPeopleDataClass>) response.body().getData());
            }

            @Override
            public void onFailure(Call<AllMissingPeople> call, Throwable t) {
                Log.e("error- ", t.getMessage().toString());
            }
        });
    }

    private void generateNewsList(ArrayList<MissingPeopleDataClass> empDataList) {
        recyclerView = findViewById(R.id.recycler_view);
        //  adapter = new NewsAdapter(empDataList, getApplicationContext(), newsCategory);
        adapter = new AllMissingPersonAdapter(empDataList, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void logOutFromApp(View v) {
        //iSharedPreferenceHelper.deleteLoginCreds();
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }
}