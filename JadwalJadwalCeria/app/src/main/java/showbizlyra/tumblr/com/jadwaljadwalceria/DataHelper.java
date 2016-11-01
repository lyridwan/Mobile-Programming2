package showbizlyra.tumblr.com.jadwaljadwalceria;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ASUS X550D on 31/10/2016.
 */

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dbJadwal.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table jadwal(kode_matkul text primary key, nama_matkul text not null, hari text not null, waktu text not null, ruangan text not null, dosen text not null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
        sql = "INSERT INTO jadwal (kode_matkul, nama_matkul, hari, waktu, ruangan, dosen) VALUES ('IK-121', 'Grafika Komputer', 'Senin', '09.30', 'Lab Umum', 'Rosa A.S');";
        db.execSQL(sql);

        sql = "INSERT INTO jadwal (kode_matkul, nama_matkul, hari, waktu, ruangan, dosen) VALUES ('IK-122', 'TRO', 'Senin', '09.30', 'B-205', 'Enjun');";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

}
