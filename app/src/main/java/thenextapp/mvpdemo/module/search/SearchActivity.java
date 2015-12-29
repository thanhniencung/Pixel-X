package thenextapp.mvpdemo.module.search;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import thenextapp.mvpdemo.R;
import thenextapp.mvpdemo.adapter.PhotoAdapter;
import thenextapp.mvpdemo.adapter.SearchAdapter;
import thenextapp.mvpdemo.adapter.holder.MainHolder;
import thenextapp.mvpdemo.data.model.Photo;
import thenextapp.mvpdemo.data.model.PhotoList;
import thenextapp.mvpdemo.module.base.BaseActivity;
import thenextapp.mvpdemo.module.photo.PhotoActivity;
import thenextapp.mvpdemo.uitil.CommonUtils;
import thenextapp.mvpdemo.uitil.LogUtils;
import thenextapp.mvpdemo.uitil.StringUtils;

import static java.lang.String.format;

/**
 * Created by Sandy on 12/26/15.
 */
public class SearchActivity extends BaseActivity implements ISearchView, MainHolder.ClickListener{

    public static final String TAG = "SearchActivity";

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.loading)
    ProgressBar loading;

    @Bind(R.id.searchBox)
    EditText searchbox;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<Photo> photoList;
    private SearchAdapter searchAdapter;
    private SearchPresenter searchPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        searchPresenter = new SearchPresenter(this);
        searchPresenter.attachView(this);

        initUI();
    }

    private void initUI() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_search_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        photoList = new ArrayList<Photo>();
        searchAdapter = new SearchAdapter(this, photoList, this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(searchAdapter);

        RxTextView.textChangeEvents(searchbox)
                .debounce(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getSearchObserver());

        searchbox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    CommonUtils.hideKeyboard(SearchActivity.this);
                    return true;
                }
                return false;
            }
        });


    }

    private Observer<TextViewTextChangeEvent> getSearchObserver() {
        return new Observer<TextViewTextChangeEvent>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(TextViewTextChangeEvent onTextChangeEvent) {
                if (isCurrentlyOnMainThread()) {
                    String keyword = onTextChangeEvent.text().toString();
                    LogUtils.debug(TAG, keyword);
                    if (!StringUtils.isEmpty(keyword.trim())) {
                        searchPresenter.search(onTextChangeEvent.text().toString());
                    }
                }
            }
        };
    }

    private boolean isCurrentlyOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    @Override
    public void showLoadingData() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingData() {
        loading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showResult(List<Photo> photos) {
        searchAdapter.setData(photos);
    }

    @Override
    public void emptyResult() {

    }

    @Override
    public void showError(String e) {

    }

    @Override
    public void onItemClicked(View v, int position) {
        Intent intent = new Intent(SearchActivity.this, PhotoActivity.class);
        intent.putExtra(PhotoActivity.PHOTO_ID, photoList.get(position));
        startActivity(intent);
    }
}
