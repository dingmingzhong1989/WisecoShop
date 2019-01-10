package com.wiseco.wisecoshop.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wiseco.wisecoshop.R;

/**
 * Created by wiseco on 2018/11/7.
 */

public class LoadingView extends FrameLayout implements View.OnClickListener {

    private LinearLayout empty;
    private LinearLayout error;
    private LinearLayout loading;
    private State state;
    private OnRetryListener listener;
    private OnErroyClickListener erroyListener;
    private ImageView imageView;

    public interface OnRetryListener {
        void onRetry();
    }

    public enum State {
        ing, error, done, empty
    }
public interface OnErroyClickListener{
    void erroyClick();
}
    public LoadingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView(context);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(context);
    }

    public LoadingView(Context context) {
        super(context);
        initializeView(context);
    }

    private void initializeView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.widget_loading_view, this);
        empty = (LinearLayout) findViewById(R.id.empty);
        loading = (LinearLayout) findViewById(R.id.loading);
        error = (LinearLayout)findViewById(R.id.error);
        imageView = findViewById(R.id.progressBar);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();
        setOnClickListener(this);
        notifyDataChanged(State.ing);
    }

    public void notifyDataChanged(State state) {
        this.state = state;
        switch (state) {
            case ing:
                setVisibility(View.VISIBLE);
                loading.setVisibility(View.VISIBLE);
                empty.setVisibility(View.GONE);
                error.setVisibility(View.GONE);

                break;
            case empty:
                setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);
                empty.setVisibility(View.VISIBLE);
                error.setVisibility(View.GONE);
                break;
            case error:
                setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);
                empty.setVisibility(View.GONE);
                error.setVisibility(View.VISIBLE);
                break;
            case done:
                setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }
    public void setEmptyView(View view) {
        empty.removeAllViews();
        empty.addView(view);
    }
    public void setOnRetryListener(OnRetryListener listener) {
        this.listener = listener;
    }


    public void setOnErroyClickListener(OnErroyClickListener erroyListener){
        this.erroyListener = erroyListener;
    }
    @Override
    public void onClick(View v) {
        if (listener != null && state == State.error) {
            listener.onRetry();
            erroyListener.erroyClick();
        }
    }
}