package com.ohm.missingpeople.activity;

import android.os.Bundle;

import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.navigation.NavigationView;
import com.ohm.missingpeople.R;
import com.ohm.missingpeople.adapter.NewsAdapter;
import com.ohm.missingpeople.networkoperation.model.AllMissingPeople;
import com.ohm.missingpeople.networkoperation.model.MissingPeopleDataClass;
import com.ohm.missingpeople.networkoperation.restclient.ApiClient;
import com.ohm.missingpeople.networkoperation.restclient.ApiInterface;

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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreenActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {
    Toolbar toolbar;
    NavigationView navigationView;
    NavigationMenuView navMenuView;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    private SwipeRefreshLayout swipeRefreshLayout;
    ApiInterface apiInterface;
    Call<AllMissingPeople> newsGetDatacall;
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadUI();
        populateData();

    }

    public void loadUI() {
        setContentView(R.layout.activity_home_screen);

        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view_menu);
        drawer = findViewById(R.id.drawer_layout);
        navMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        swipeRefreshLayout = findViewById(R.id.pullToRefresh);
        recyclerView = findViewById(R.id.recycler_view);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.brandColor));
        layoutManager = new LinearLayoutManager(HomeScreenActivity.this);
        navMenuView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        navigationView.setNavigationItemSelectedListener(HomeScreenActivity.this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Test");
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
            adapter = new NewsAdapter(empDataList, this, "");
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
    }
}