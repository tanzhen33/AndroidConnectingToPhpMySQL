package com.example.androidhive;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by tanz on 2015-08-26 026.
 */
public class MainActivity extends Activity {

    private Context mContext;
    private Button Blogin;
    private Button bClear;
    private CheckBox chk;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mContext = this;
        Blogin = (Button) this.findViewById(R.id.loginLog);
        bClear = (Button) this.findViewById(R.id.loginClear);

        final EditText eUid = (EditText) this.findViewById(R.id.EditTextuid);
        final EditText eMima = (EditText) this.findViewById(R.id.EditTextPwd);
        chk = (CheckBox) findViewById(R.id.chkSaveName);
        pref = getSharedPreferences("UserInfo", MODE_PRIVATE);
        editor = pref.edit();

        String strUid = pref.getString("EditTextuid","");

        if (strUid == null) {
            chk.setChecked(false);
        } else {
            chk.setChecked(true);
            eUid.setText(strUid);
        }

        Blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,MainScreenActivity.class);
                String strUid = eUid.getText().toString().trim();
                String strPwd = eMima.getText().toString().trim();

                if (strUid.equals("tanz") && strPwd.equals("t123456")) {
                    if (chk.isChecked()) {
                        editor.putString("EditTextuid",strUid);
                        editor.commit();
                    } else {
                        editor.remove("EditTextuid");
                        editor.commit();
                    }
                    Toast.makeText(MainActivity.this,"Success", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else {
                    if (strUid.equals("liq") && strPwd.equals("l123456")) {
                        if (chk.isChecked()) {
                            editor.putString("EditTextuid",strUid);
                            editor.commit();
                        } else {
                            editor.remove("EditTextUid");
                            editor.commit();
                        }
                        Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Username or Password Error!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        bClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eUid.setText("");
                eMima.setText("");
            }
        });
    }
}