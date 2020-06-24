package com.liaohongwang.xuxingzuo.fengyinguanli.main;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.liaohongwang.xuxingzuo.fengyinguanli.R;
import com.liaohongwang.xuxingzuo.fengyinguanli.custom.CustomScanActivity;
import com.liaohongwang.xuxingzuo.fengyinguanli.service.WebService;
import com.liaohongwang.xuxingzuo.fengyinguanli.utils.Utils;

import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.jiluusername;
import static com.liaohongwang.xuxingzuo.fengyinguanli.utils.Utils.jiequshuizi;

public class LingYongActivity extends AppCompatActivity {
    private String dangefanhui;
    private String piliangfanhui;
    private EditText ed_dan;
    private EditText ed_pi_1;
    private EditText ed_pi_2;
    private ImageView btn_scan;
    Button btn_dan;
    Button btn_pi;
    public static final int aaaa = 1;
    public static final int bbbb = 2;
    public static final int cccc = 3;
    private ProgressDialog dialog;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case aaaa:
                    dialog.dismiss();
                    Toast.makeText(LingYongActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ling_yong);
        initView();

    }

    private void initView() {
        ed_dan = findViewById(R.id.ed_lingyong_dan);
//        ed_dan.setFocusable(false);
//        ed_dan.setFocusableInTouchMode(false);
        ed_pi_1 = findViewById(R.id.ed_lingyong_pi_info_1);
        ed_pi_2 = findViewById(R.id.ed_lingyong_pi_info_2);
        btn_dan = findViewById(R.id.btn_lingyong_dan_submit);
        btn_pi = findViewById(R.id.btn_lingyong_pi_submit);
        btn_scan = findViewById(R.id.btn_scan_lingyong);
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(LingYongActivity.this);
                intentIntegrator.setCaptureActivity(CustomScanActivity.class);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.initiateScan();
            }
        });
        btn_dan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()) {
                    String str;
                    if (TextUtils.isEmpty(ed_dan.getText().toString())) {
                        Toast.makeText(LingYongActivity.this, "封印号码不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    dialog = new ProgressDialog(LingYongActivity.this);
                    dialog.setTitle("温馨提示");
                    dialog.setMessage("正在处理，请稍后...");
                    dialog.setCancelable(false);
                    dialog.show();
                    str = ed_dan.getText().toString();
//                    if (!(ed_dan.getText().toString().length() == 14)) {
//                        Toast.makeText(LingYongActivity.this, "封印号不是14位", Toast.LENGTH_SHORT).show();
//                        dialog.dismiss();
//                        return;
//                    }
//                    str = str.substring(str.length() - 14, str.length());
                    dangelingqu(str, jiluusername);
                    ed_dan.setText("");
                }

            }
        });
        btn_pi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()) {
                    if (TextUtils.isEmpty(ed_pi_1.getText().toString()) || TextUtils.isEmpty(ed_pi_2.getText().toString())) {
                        Toast.makeText(LingYongActivity.this, "开始号码和结束号码不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
//                    if (!(ed_pi_1.getText().toString().length() == 14) || !(ed_pi_2.getText().toString().length() == 14)) {
//                        Toast.makeText(LingYongActivity.this, "封印号不是14位", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
                    if (Long.parseLong(ed_pi_1.getText().toString()) >= Long.parseLong(ed_pi_2.getText().toString())) {
                        Toast.makeText(LingYongActivity.this, "开始号码大于结束号码", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    // 提示框
                    dialog = new ProgressDialog(LingYongActivity.this);
                    dialog.setTitle("温馨提示");
                    dialog.setMessage("正在处理，请稍后...");
                    dialog.setCancelable(false);
                    dialog.show();
                    pilianglingqu(ed_pi_1.getText().toString(), ed_pi_2.getText().toString(), jiluusername);
                    ed_pi_1.setText("");
                    ed_pi_2.setText("");
                }

            }
        });
    }

    public void dangelingqu(final String fengyinid, final String username) {

        new Thread() {
            @Override
            public void run() {
                dangefanhui = WebService.dangelingyong(fengyinid, username);
                System.out.println(dangefanhui);
                if (dangefanhui != null) {
                    if (dangefanhui.equals("11")){

                        Utils.runOnBackgroundThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(50);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                android.os.Message message1 = new android.os.Message();
                                message1.what = aaaa;
                                handler.sendMessage(message1);
                            }
                        });
                    } else if (dangefanhui.equals("22")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                                Toast toast = Toast.makeText(LingYongActivity.this, "提交失败,可能已被领取或其他！", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        });
                    }
                } else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                            dialog.dismiss();
                            Toast toast = Toast.makeText(LingYongActivity.this, "提交成功！服务器正在处理！", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });

                }

            }
        }.start();
    }

    public void pilianglingqu(final String fengyinid1, final String fengyinid2, final String username) {

        new Thread() {
            @Override
            public void run() {
                piliangfanhui = WebService.pilianglingyong(fengyinid1, fengyinid2, username);
                System.out.println(piliangfanhui);
                if (piliangfanhui != null) {
                    if (piliangfanhui.equals("11")) {

                        Utils.runOnBackgroundThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(50);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                android.os.Message message1 = new android.os.Message();
                                message1.what = aaaa;
                                handler.sendMessage(message1);
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.dismiss();
                                    Toast toast = Toast.makeText(LingYongActivity.this, "提交成功！服务器正在处理！重复领用不会记录", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            });

                    }
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                            Toast toast = Toast.makeText(LingYongActivity.this, "提交成功！服务器正在处理！重复领用不会记录", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });

                }

            }
        }.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentIntegrator.REQUEST_CODE && data != null) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result == null) {
                Toast.makeText(this, "HaveNoResult", Toast.LENGTH_SHORT).show();
            } else {
                String content = result.getContents();
//                if (content.length() > 14) {
//                    content = content.substring(content.length() - 14, content.length());
//                    ed_dan.setText(content);
//                } else {
//                    Toast.makeText(this, "请扫描正确的封印号", Toast.LENGTH_SHORT).show();
//                }
                content=jiequshuizi(content);
                ed_dan.setText(content);
            }
        }
    }


}
