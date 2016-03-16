package jp.ac.hal.ths30470;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class addNew extends ActionBarActivity {

    EditText et1;
    EditText et2;
    EditText et3;
    RatingBar ratingbar1;
    ImageButton btn1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        et1 = (EditText)findViewById(R.id.editText);
        et1.setMaxLines(1);
        et2 = (EditText)findViewById(R.id.editText2);
        et2.setMaxLines(1);
        et3 = (EditText)findViewById(R.id.editText3);
        et3.setMaxLines(1);
        ratingbar1=(RatingBar)findViewById(R.id.ratingBar);

        btn1 = (ImageButton)findViewById(R.id.imageButton3);

       btn1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(et1.length() != 0 && et2.length() != 0 && et3.length() != 0) {

                    MyDatabaseHelper h = new MyDatabaseHelper(addNew.this);
                    SQLiteDatabase db = h.getWritableDatabase();

                    String title = et1.getText().toString();
                    String genre = et2.getText().toString();
                    String actor = et3.getText().toString();
                    String rating = String.valueOf(ratingbar1.getRating());
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                    String strDate = df.format(cal.getTime());

                    ContentValues cv = new ContentValues();
                    cv.put("title", title);
                    cv.put("genre", genre);
                    cv.put("actor", actor);
                    cv.put("rating", rating);
                    cv.put("date", strDate);

                    db.insert("movies", null, cv);
                    db.close();
                    finish();
                    showSucc();
                }else{
                    showErr();
                }
            }

        });


    }

    private void showErr() {
        Toast.makeText(this, "未入力項目があります。", Toast.LENGTH_SHORT).show();
    }

    private void showSucc() {
        Toast.makeText(this, "追加されました", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
