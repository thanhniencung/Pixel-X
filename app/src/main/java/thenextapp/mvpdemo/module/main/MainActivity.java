package thenextapp.mvpdemo.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import thenextapp.mvpdemo.R;
import thenextapp.mvpdemo.adapter.PhotoAdapter;
import thenextapp.mvpdemo.adapter.holder.MainHolder;
import thenextapp.mvpdemo.data.api.Feature;
import thenextapp.mvpdemo.data.model.Photo;
import thenextapp.mvpdemo.data.model.PhotoList;
import thenextapp.mvpdemo.module.base.BaseActivity;
import thenextapp.mvpdemo.module.photo.PhotoActivity;
import thenextapp.mvpdemo.module.search.SearchActivity;
import thenextapp.mvpdemo.uitil.EndlessRecyclerOnScrollListener;

/**
 * Created by Sandy on 12/26/15.
 */
public class MainActivity extends BaseActivity implements IMainView,
        NavigationView.OnNavigationItemSelectedListener, MainHolder.ClickListener{

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.status)
    TextView status;

    @Bind(R.id.progress)
    ProgressBar progressBar;

    @Bind(R.id.loading)
    LinearLayout llLoading;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;

    @Bind(R.id.nav_view)
    NavigationView navigationView;

    @Bind(R.id.loadMoreView)
    TextView loadMoreView;


    MainPresenter mainPresenter;

    private GridLayoutManager gridLayoutManager;
    private List<Photo> photoList;
    private PhotoAdapter photoAdapter;
    private int currentPage = 1;
    private String feature = Feature.Type.POPULAR;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainPresenter = new MainPresenter(this);
        mainPresenter.attachView(this);

        initUI();

        mainPresenter.getPhotos(currentPage, feature, false);
    }


    private void initUI() {
        setTitle("500px");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);

        gridLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(
                gridLayoutManager) {
            @Override
            public void onLoadMore() {
                currentPage++;
                mainPresenter.getPhotos(currentPage, feature, true);
            }

        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        photoList = new ArrayList<Photo>();
        photoAdapter = new PhotoAdapter(this, photoList, this);
        recyclerView.setAdapter(photoAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
            overridePendingTransition( R.anim.slide_in_up, 0 );
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        resetAdapter();

        switch (item.getItemId()) {
            case R.id.popuplar:
                feature = Feature.Type.POPULAR;
                setTitle(getResources().getString(R.string.popular));
                break;

            case R.id.editors:
                feature = Feature.Type.EDITORS;
                setTitle(getResources().getString(R.string.editors));
                break;

            case R.id.upcomming:
                feature = Feature.Type.UPCOMMING;
                setTitle(getResources().getString(R.string.upcomming));
                break;

            case R.id.fresh:
                feature = Feature.Type.FRESH;
                setTitle(getResources().getString(R.string.fresh));
                break;
        }

        mainPresenter.getPhotos(currentPage, feature, false);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void resetAdapter() {
        currentPage = 1;
        photoList.clear();
        photoAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadingData() {
        llLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingData() {
        llLoading.setVisibility(View.GONE);
        loadMoreView.setVisibility(View.GONE);
    }

    @Override
    public void showError(final String e) {
        status.setText(e);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showPhotoList(PhotoList data) {
        photoList.addAll(data.photos);
        photoAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadMore() {
        loadMoreView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        photoList = null;
    }

    @Override
    public void onItemClicked(View v, int position) {
        Intent intent = new Intent(MainActivity.this, PhotoActivity.class);
        intent.putExtra(PhotoActivity.PHOTO_ID, photoList.get(position));
        startActivity(intent);
    }
}
