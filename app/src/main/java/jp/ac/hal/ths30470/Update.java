package jp.ac.hal.ths30470;

        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.RatingBar;
        import android.widget.TextView;
        import android.widget.AdapterView.OnItemClickListener;
        import android.view.View.OnClickListener;
        import android.database.sqlite.SQLiteDatabase;
        import android.view.View;
        import android.content.ContentValues;
        import android.widget.Toast;

        import java.text.SimpleDateFormat;
        import java.util.Calendar;

public class Update extends ActionBarActivity {

    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Movie movie = (Movie)getIntent().getExtras().get("title");


        //TextView Id = (TextView)findViewById(R.id.textView3);
        EditText etTitle = (EditText)findViewById(R.id.editText4);
        EditText etGenre = (EditText)findViewById(R.id.editText5);
        EditText etActor = (EditText)findViewById(R.id.editText6);
        RatingBar rb = (RatingBar)findViewById(R.id.ratingBar2);
        TextView tv = (TextView)findViewById(R.id.textView2);

        id = movie.getId();

        etTitle.setText(movie.getTitle());
        etGenre.setText(movie.getGenre());
        etActor.setText(movie.getActor());
        rb.setRating(Float.parseFloat(movie.getRating()));
        tv.setText(movie.getDate());

        ImageButton change = (ImageButton) findViewById(R.id.imageButton5);

        change.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                update();

            }

        });

        ImageButton del = (ImageButton) findViewById(R.id.imageButton4);

        del.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                delete();
                showErr();

            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update, menu);
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

    private void update(){

        //EditTextに入っていいる内容の取得
        EditText etTitle = (EditText)findViewById(R.id.editText4);
        EditText etGenre = (EditText)findViewById(R.id.editText5);
        EditText etActor = (EditText)findViewById(R.id.editText6);
        RatingBar rb = (RatingBar)findViewById(R.id.ratingBar2);

        MyDatabaseHelper h = new MyDatabaseHelper(this);
        SQLiteDatabase db = h.getWritableDatabase();

        //更新文字列取得
        String updateTtl = etTitle.getText().toString();
        String updateGnr = etGenre.getText().toString();
        String updateAct = etActor.getText().toString();
        String rating = String.valueOf(rb.getRating());

        //日付
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String strDate = df.format(cal.getTime());

        //更新情報をまとめる
        ContentValues cv = new ContentValues();
        cv.put("title", updateTtl);
        cv.put("genre", updateGnr);
        cv.put("actor", updateAct);
        cv.put("rating", rating);
        cv.put("date", strDate);

        String[] args = new String[1];
        args[0] = String.valueOf(id);

        db.update("movies", cv, "id=?", args);

        showSucc();
        finish();
    }

    private void delete() {
        // TODO Auto-generated method stub
        MyDatabaseHelper h = new MyDatabaseHelper(this);
        SQLiteDatabase db = h.getWritableDatabase();

        String[] args = new String[1];
        args[0] = String.valueOf(id);

        // テーブルmemoから削除
        db.delete("movies","id=?", args );
        db.close();
//        Toast.makeText(this, "削除しました", Toast.LENGTH_LONG).show();
        finish();
    }

    private Button findViewById(OnClickListener onClickListener) {
        // TODO Auto-generated method stub
        return null;
    }

    private void showSucc() {
        Toast.makeText(this, "更新が完了しました。", Toast.LENGTH_SHORT).show();
    }

    private void showErr() {
        Toast.makeText(this, "削除しました。", Toast.LENGTH_SHORT).show();
    }

}