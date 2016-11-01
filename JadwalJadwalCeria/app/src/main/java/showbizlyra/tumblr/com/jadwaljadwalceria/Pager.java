package showbizlyra.tumblr.com.jadwaljadwalceria;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by ASUS X550D on 31/10/2016.
 */

public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                senin senin1 = new senin();
                return senin1;
            case 1:
                selasa selasa1 = new selasa();
                return selasa1;
            case 2:
                rabu rabu1 = new rabu();
                return rabu1;
            case 3:
                kamis kamis1 = new kamis();
                return kamis1;
            case 4:
                jumat jumat1 = new jumat();
                return jumat1;
            case 5:
                sabtu sabtu1 = new sabtu();
                return sabtu1;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}