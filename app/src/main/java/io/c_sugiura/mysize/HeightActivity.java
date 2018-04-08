package io.c_sugiura.mysize;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class HeightActivity extends AppCompatActivity {

    public static final String HEIGHT = "HEIGHT";
    private TextView mHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height);

        mHeight = (TextView)findViewById(R.id.height);

        // 共有プリファレンスから値を取得して表示
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        int height = pref.getInt(HEIGHT, 160);
        mHeight.setText(String.valueOf(height));

        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            /**
             * 一覧から選択された時に呼ばれるメソッド
             * @param parent 選択が発生した親ビュー
             * @param view 選択されたビュー
             * @param position 選択されたビューの位置
             * @param id 選択された項目のid
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                Spinner spinner = (Spinner)parent;
                String item = (String)spinner.getSelectedItem();

                if(!item.isEmpty()){
                    mHeight.setText(item);
                }
            }

            /**
             * 選択中の項目、またはnullを取得します
             * @param parent
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent){}
        });

        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setProgress(height);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean formUser){
                String value = String.valueOf(progress);
                mHeight.setText(value);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar){}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar){}
        });

    }

    @Override
    protected void onPause(){
        super.onPause();;
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(HEIGHT, Integer.parseInt(mHeight.getText().toString()));

        editor.commit();
    }
}
