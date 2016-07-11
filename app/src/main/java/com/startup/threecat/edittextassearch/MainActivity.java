package com.startup.threecat.edittextassearch;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView txt;
    TextView txt2;
    Button btn;

    int count = 0;
    long oldTime = 0;
    long newTime = 0;


    /**
     * it is on create, it use init view of app
     * vjjgjg\
     * gkg
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.edit_text);
        txt = (TextView)findViewById(R.id.txt);
        txt2 = (TextView)findViewById(R.id.txt2);
        btn = (Button)findViewById(R.id.btn);

        Drawable img = this.getResources().getDrawable( R.drawable.navigation_icon_city );
        img.setBounds( 0, 0, 60, 60 );
        btn.setCompoundDrawables( img, null, null, null );

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Toast.makeText(MainActivity.this, editText.getText().toString(), Toast.LENGTH_LONG).show();
                }
                Toast.makeText(MainActivity.this, "ngoai " + editText.getText().toString(), Toast.LENGTH_LONG).show();

                return false;
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                txt.setText(s.length() + ", start : " + start + ", before : " + before + ", count : " + count);


            }

            @Override
            public void afterTextChanged(Editable s) {

                newTime = System.currentTimeMillis();
                update(newTime);
//                if(newTime - oldTime > 1000) {
//                    count ++;
//                    txt2.setText(s.toString() + " count : " + count);
//                }
//                long temp = (newTime - oldTime);
//
//                Toast.makeText(MainActivity.this, temp + "", Toast.LENGTH_SHORT).show();
//                oldTime = newTime;
            }
        });
    }

    public void update(final long time) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    String query = txt.getText().toString();
                    System.out.println(query);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(time == newTime) {
                                txt2.setText(newTime + "");
                            }
                        }
                    });


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

//       new AsyncTask<Long, Void, Long>() {
//
//           @Override
//           protected Long doInBackground(Long... params) {
//               SystemClock.sleep(500);
//               SystemClock.
//               return params[0];
//           }
//
//           @Override
//           protected void onPostExecute(Long aLong) {
//
//               if(aLong == newTime) {
////                   Toast.makeText(MainActivity.this, time + "", Toast.LENGTH_SHORT).show();
//                   Log.d("count :" , newTime + "");
//                   txt2.setText(newTime + "");
//               }
////               Toast.makeText(MainActivity.this, time + "", Toast.LENGTH_SHORT).show();
//           }
//       }.execute(time);
    }
}
