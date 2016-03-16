package jp.ac.hal.ths30470;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


public class showList extends ActionBarActivity implements SearchView.OnQueryTextListener {

    private ArrayAdapter<Movie> adapter;
    ListView lv;
    SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);



        search = (SearchView) findViewById(R.id.searchView);

        // SearchViewの初期表示状態を設定
        search.setIconifiedByDefault(false);

        // SearchViewにOnQueryChangeListenerを設定
        search.setOnQueryTextListener(this);

        // SearchViewのSubmitボタンを使用不可にする
        search.setSubmitButtonEnabled(true);

        // SearchViewに何も入力していない時のテキストを設定
        search.setQueryHint("検索したいタイトルを入力");
        this.search.setSubmitButtonEnabled(false);

        adapter = new ArrayAdapter<Movie>(this,R.layout.layout_listview_custom);

        lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(adapter);
        lv.setTextFilterEnabled(true);
        //ListViewÇ™ÉNÉäÉbÉNÇ≥ÇÍÇΩÇ∆Ç´ÇÃèàóù
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                //ÉNÉäÉbÉNÇ≥ÇÍÇΩListÇÃéÊìæ
                Movie movie = adapter.getItem(position);
                Intent intent = new Intent(showList.this, Update.class);
                intent.putExtra("title", movie);
                startActivity(intent);

            }

        });


        Button searchButton = (Button) findViewById(R.id.button10);



        //ボタンをインスタンス化
        searchButton.setOnClickListener(new View.OnClickListener() {

            ///インテント -> 追加画面(addNew)
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(showList.this, Search.class);
                startActivity(intent);

            }
        });

        //DBÇ…ê⁄ë±ÇµtitleÇéÊìæÇ∑ÇÈÅBéÊìæÇµÇΩtitleÇadapterÇ…í«â¡
        //adapter.add("ï∂éöóÒ");
        MyDatabaseHelper h = new MyDatabaseHelper(this);
        SQLiteDatabase db = h.getReadableDatabase();

        //ÉeÅ[ÉuÉãÇÃÉJÉâÉÄñºÇîzóÒÇ…Ç∑ÇÈ(îzóÒñº:col)
        String[] col = {"id","title","genre","actor","rating","date"};

        //ÉJÅ[É\ÉãÇê›íËÇµñ‚Ç¢çáÇÌÇπÇé¿çs
        Cursor c = db.query("movies", col, null, null, null, null, null);

        //ÉJÅ[É\ÉãÇêÊì™Ç…à⁄ìÆÇµåJÇËï‘Ç∑
        boolean flg = c.moveToFirst();


        while(flg){
            adapter.add(new Movie(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5)));
            flg = c.moveToNext();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.select, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        search.clearFocus();

        // adapterのクリア
        adapter.clear();

        // 表示処理
        MyDatabaseHelper h = new MyDatabaseHelper(this);
        SQLiteDatabase db = h.getReadableDatabase();

        String[] col = new String[]{"id","title","genre","actor","rating","date"};

        Cursor c = db.query("movies", col, null, null, null, null, null);

        boolean b = c.moveToFirst();

        String str ="";
        while(b){
            int id = c.getInt(0);
            String title = c.getString(1);
            String genre = c.getString(2);
            String actor = c.getString(3);
            String rating = c.getString(4);
            String date = c.getString(5);

            adapter.add(new Movie(id,title,genre,actor,rating,date));
            b = c.moveToNext();
        }
        db.close();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (TextUtils.isEmpty(query)) {
            lv.clearTextFilter();
        } else {
            lv.setFilterText(query.toString());
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }



}