package showbizlyra.tumblr.com.jadwaljadwalceria;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by ASUS X550D on 31/10/2016.
 */

public class kamis extends Fragment {
    private ArrayList<String> items = new ArrayList<>();
    ArrayAdapter adapter;

    public static MainActivity ma;
    String[] daftar;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public View v;

    @Override
    public void onResume(){
        RefreshList();
        super.onResume();
    }

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.senin, container, false);


        Button btn = (Button) v.findViewById(R.id.button2);


        btn.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View arg0) {

                Intent inte = new Intent(getActivity(), BuatBiodata.class);
                inte.putExtra("hari", "Kamis");
                getActivity().startActivity(inte);

            }

        });

        dbcenter = new DataHelper(getActivity());
        RefreshList();
        return v;
        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
//        return inflater.inflate(R.layout.senin, container, false);
    }

    public void RefreshList(){
        ListView lstItems = (ListView)v.findViewById(R.id.listView1);
        ArrayList<String> prueba = new ArrayList<String>();

        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM jadwal where hari = 'Kamis'",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();

        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
            prueba.add(daftar[cc]);

        }


        ArrayAdapter<String> allItemsAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1,prueba);

        lstItems.setAdapter(allItemsAdapter);

        lstItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2]; //.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"Lihat Jadwal", "Update Jadwal", "Hapus Jadwal"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item){
                            case 0 :
                                Intent i = new Intent(getActivity(), LihatBiodata.class);
                                i.putExtra("nama", selection);
                                getActivity().startActivity(i);
                                break;
                            case 1 :
                                Intent in = new Intent(getActivity(), UpdateBiodata.class);
                                in.putExtra("nama", selection);
                                getActivity().startActivity(in);
                                break;
                            case 2 :
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from jadwal where nama_matkul = '"+selection+"'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();

            }});

        ((ArrayAdapter)lstItems.getAdapter()).notifyDataSetInvalidated();

    }
}
