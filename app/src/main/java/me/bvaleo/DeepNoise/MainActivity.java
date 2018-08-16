package me.bvaleo.deepnoise;

import android.Manifest;
import android.os.Environment;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import me.bvaleo.deepnoise.recorder.WavAudioRecorder;
import me.bvaleo.deepnoise.util.Util;

public class MainActivity extends AppCompatActivity {

    private static final String mRecordFilePath = Environment.getExternalStorageDirectory() + "/testwave.wav";

    private FloatingActionButton mBtnRecord;
    private TextView mTvInfo;
    private WavAudioRecorder recorder = WavAudioRecorder.getInstanse();
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Util.requestPermission(this, Manifest.permission.RECORD_AUDIO);
        Util.requestPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Util.requestPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        mBtnRecord = findViewById(R.id.fab_record);
        mTvInfo = findViewById(R.id.tv_info);

        mHandler = new Handler();
        recorder.setOutputFile(mRecordFilePath);

        mBtnRecord.setOnClickListener(v -> {
            if (WavAudioRecorder.State.INITIALIZING == recorder.getState()) {
                recorder.prepare();
                mHandler.postDelayed(stopRecording, 7000);
                recorder.start();
                mTvInfo.setText("Recording...");
                mBtnRecord.setEnabled(false);
            }

        });


    }

    final Runnable stopRecording = () -> {
        recorder.stop();
        recorder.reset();
        mTvInfo.setText(getString(R.string.start_description));
        mBtnRecord.setEnabled(true);
    };
}
