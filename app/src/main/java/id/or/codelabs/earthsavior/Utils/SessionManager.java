package id.or.codelabs.earthsavior.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import id.or.codelabs.earthsavior.ActivityLogin;

/**
 * Created by CodeLabs on 28/03/2016.
 */
public class SessionManager {
    private SharedPreferences pref;

    private SharedPreferences.Editor editor;

    private Context mContext;

    private static final String PREF_NAME = "EarthSaviorPref";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_IDUSER = "iduser";
    public static final String KEY_NAMAUSER = "namauser";
    public static final String KEY_EMAILUSER = "emailuser";
    public static final String KEY_FOTOUSER = "fotouser";
    public static final String KEY_POINTUSER = "pointuser";



    public SessionManager(Context context) {
        mContext = context;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void createLoginSession(String idUser, String nama, String email,String foto,int point) {

        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_IDUSER, idUser);
        editor.putString(KEY_NAMAUSER, nama);
        editor.putString(KEY_EMAILUSER, email);
        editor.putString(KEY_FOTOUSER, foto);
        editor.putString(KEY_POINTUSER, String.valueOf(point));
        editor.commit();
    }

    public void addProfile(String idUser, String nama, String email,String foto,int point) {


        editor.putString(KEY_IDUSER,idUser);
        editor.putString(KEY_NAMAUSER,nama);
        editor.putString(KEY_EMAILUSER,email);
        editor.putString(KEY_FOTOUSER,foto);
        editor.putString(KEY_POINTUSER,String.valueOf(point));
        editor.commit();
    }

    public void logoutUser() {

        editor.clear();
        editor.commit();

        Intent i = new Intent(mContext, ActivityLogin.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(i);
        pref.getBoolean(IS_LOGIN, false);
    }

    public HashMap<String, String> getUserDetails() {

        HashMap<String, String> user = new HashMap<>();

        user.put(KEY_IDUSER, pref.getString(KEY_IDUSER, null));
        user.put(KEY_EMAILUSER, pref.getString(KEY_EMAILUSER, null));
        user.put(KEY_NAMAUSER, pref.getString(KEY_NAMAUSER, null));
        user.put(KEY_FOTOUSER, pref.getString(KEY_FOTOUSER, null));
        user.put(KEY_POINTUSER, pref.getString(KEY_POINTUSER, null));
        return user;
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void checkLogin(){

        if(!this.isLoggedIn()){

            Intent i = new Intent(mContext, ActivityLogin.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(i);
        }

    }
}
