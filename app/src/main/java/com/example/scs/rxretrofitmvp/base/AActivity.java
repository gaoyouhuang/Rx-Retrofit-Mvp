package com.example.scs.rxretrofitmvp.base;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by scs on 18-3-12.
 */

public abstract class AActivity<P extends BasePresenter, V extends BaseView> extends BaseActivity {
    public AActivity activity = this;
    public P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setlayout());

        getLoaderManager().initLoader(getLoaderid(), null, new LoaderManager.LoaderCallbacks<P>() {
            @Override
            public Loader<P> onCreateLoader(int i, Bundle bundle) {
                return new PresenterLoader<P>(AActivity.this, new PresenterFactory<P>() {
                    @Override
                    public P create() {
                        return createPresenter();
                    }
                });
            }

            @Override
            public void onLoadFinished(Loader<P> loader, P p) {
                AActivity.this.presenter = p;
            }

            @Override
            public void onLoaderReset(Loader<P> loader) {

            }
        });

        init();
    }

    public void show_Toast(String st) {
        Toast.makeText(activity, st, Toast.LENGTH_SHORT).show();
    }

    public abstract void init();

    protected abstract P createPresenter();

    public abstract int setlayout();

    public abstract int getLoaderid();
    @Override
    protected void onStart() {
        super.onStart();
        presenter.attach((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detach();
            presenter = null;
        }
    }
}
