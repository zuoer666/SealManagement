package com.liaohongwang.xuxingzuo.fengyinguanli.custom;

import android.net.Uri;
import android.os.AsyncTask;

import com.google.zxing.Result;
import com.liaohongwang.xuxingzuo.fengyinguanli.CWApplication.ApplicationPackege;

/**
 * @author feilang-liuyansong
 * @date 2018/3/6 14:01
 * @description
 */

public class ImageScanningTask extends AsyncTask<Uri, Void, Result> {
    private Uri uri;
    private ImageScanningCallback callback;
    public ImageScanningTask(Uri uri, ImageScanningCallback callback) {
        this.uri = uri;
        this.callback = callback;
    }

    @Override
    protected Result doInBackground(Uri... params) {
        return CodeScanUtils.scanImage(ApplicationPackege.getGloableApplicationContext(), uri);
    }

    @Override
    protected void onPostExecute(Result result) {
        super.onPostExecute(result);
        callback.onFinishScanning(result);
    }

    public interface ImageScanningCallback {
        void onFinishScanning(Result result);
    }

}
