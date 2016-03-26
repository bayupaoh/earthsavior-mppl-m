package id.or.codelabs.earthsavior;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    Toolbar mToolbar;
    float distance;
    float avgSpeed;

    TextView txtJarak;
    TextView txtEmisi;
    TextView txtKecepatan;
    TextView txtPoint;
    Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        declareWidget();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle b = getIntent().getExtras();
        distance = b.getFloat("distance");
        avgSpeed = b.getFloat("speed");

        int point =  (int) (0.31 * (distance / 1000) * 1000);

        float emisi = (float) (0.31 * (distance / 1000));

        txtPoint.setText(String.valueOf(point));
        txtKecepatan.setText(String.valueOf(String.format("%.2f",avgSpeed)));
        txtJarak.setText(String.valueOf(String.format("%.2f",distance)));
        txtEmisi.setText(String.valueOf(String.format("%.2f",emisi)));

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareintent = new Intent();
                shareintent.setAction(Intent.ACTION_SEND);
                shareintent.setType("text/plain");
                shareintent.putExtra(
                        Intent.EXTRA_TEXT,
                        "Saya telah mengurangi emisi "
                                + String.valueOf(String.format("%.3f", 0.31 * (distance / 1000)))
                                + " KgCO2.\n Ayo download earth savior. earthsavior.esy.es");
                startActivity(Intent.createChooser(shareintent, "SHARE YOUR RESULT"));
            }
        });
    }

    private void declareWidget() {
        txtJarak = (TextView) findViewById(R.id.txt_distance);
        txtEmisi = (TextView) findViewById(R.id.txt_emisi);
        txtKecepatan = (TextView) findViewById(R.id.txt_kecepatan);
        txtPoint = (TextView) findViewById(R.id.txt_point);
        btnShare = (Button) findViewById(R.id.btn_share_media);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
