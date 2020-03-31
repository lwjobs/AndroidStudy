package cn.edu.jssvc.nfcapp;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private String mPackageName;
    private NfcAdapter mNfcAdapter;
    private PendingIntent mPendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()), 0);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InstalledAppListActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (mPackageName == null)
            return;

        Tag detectedTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        writeNFCTag(detectedTag);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mNfcAdapter != null)
            //拦截其他NFC应用
            mNfcAdapter.enableForegroundDispatch(this, mPendingIntent, null,
                    null);
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mNfcAdapter != null)
            //取消拦截
            mNfcAdapter.disableForegroundDispatch(this);
    }

    public void writeNFCTag(Tag tag) {
        if (tag == null) {
            return;
        }
        NdefMessage ndefMessage = new NdefMessage(
                new NdefRecord[]{NdefRecord
                        .createApplicationRecord(mPackageName)});
        int size = ndefMessage.toByteArray().length;
        try {
            Ndef ndef = Ndef.get(tag);
            if (ndef != null) {
                ndef.connect();

                if (!ndef.isWritable()) {
                    return;
                }
                if (ndef.getMaxSize() < size) {
                    return;
                }
                ndef.writeNdefMessage(ndefMessage);
                Toast.makeText(this, "写入成功", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            button.setText(data.getExtras().getString(
                    "package_name"));
            String temp = button.getText().toString();
            mPackageName = temp.substring(temp.indexOf("\n") + 1);
        }

    }


}
