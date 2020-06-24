package com.liaohongwang.xuxingzuo.fengyinguanli.custom;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.Result;
import com.google.zxing.client.android.Intents;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.liaohongwang.xuxingzuo.fengyinguanli.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomScanFragment extends Fragment implements View.OnClickListener, Toolbar.OnMenuItemClickListener {

    private static final int REQUEST_CODE_PICK_IMAGE = 1000;

    private Toolbar toolbar;
    CaptureManager captureManager;
    DecoratedBarcodeView dbvCustom;
    Bundle mSavedInstanceState;
    Menu mMenu;
    public CustomScanFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_custom_scan, container, false);
        initView(rootView);
        initCodeManager();
        return rootView;
    }

    private void initCodeManager() {
        captureManager = new CaptureManager(getActivity(), dbvCustom);
        captureManager.initializeFromIntent(getActivity().getIntent(), mSavedInstanceState);
        captureManager.decode();
    }

    private void initView(View rootView) {
        toolbar = rootView.findViewById(R.id.toolBar);
        toolbar.setNavigationIcon(R.mipmap.ic_action_back);
        toolbar.inflateMenu(R.menu.menu_scan);
        mMenu = toolbar.getMenu();
        toolbar.setOnMenuItemClickListener(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        dbvCustom = rootView.findViewById(R.id.dbvCustom);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSavedInstanceState = savedInstanceState;
    }

    @Override
    public void onResume() {
        super.onResume();
        captureManager.onResume();
    }

    @Override
    public void onPause() {
        captureManager.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        captureManager.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        captureManager.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.menu_thumb:
//                pickImage();
//                break;
            default:
                break;
        }
        return false;
    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        boolean intentResolvable = isIntentResolvable(intent);
        if (intentResolvable) {
            startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
        }
    }

    public boolean isIntentResolvable(Intent intent) {
        return intent.resolveActivity(getActivity().getPackageManager()) != null;
    }

    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_PICK_IMAGE:
                if (Activity.RESULT_OK == resultCode && null != data) {
                    readQrCodeFromImage(data.getData());
                }
                break;
            default:
                break;
        }
    }

    private void readQrCodeFromImage(Uri imageUri) {
        if (imageUri == null) {
            return;
        }

        ImageScanningTask scanningTask = new ImageScanningTask(imageUri, new ImageScanningTask.ImageScanningCallback() {
            @Override public void onFinishScanning(Result result) {
                if (result != null) {
                    Intent intent = new Intent();
                    intent.putExtra(Intents.Scan.RESULT, result.getText());
                    getActivity().setResult(Activity.RESULT_OK, intent);
                    getActivity().finish();
                }else {
                    Toast.makeText(getActivity(), "识别失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        scanningTask.execute();
    }

}
