package id.sch.smkn2cikbar.easyschedule.config;

/**
 * Created by Robby Akbar on 26/12/16.
 */

public class Config {
    public static final String SERVER_API = "http://android-inyourhand.esy.es/api-sms/";
    public static final String IMAGE = "http://android-inyourhand.esy.es/api-sms/img/";
    public static final String LOGIN_URL_GURU = Config.SERVER_API +"login_guru.php";
    public static final String LOGIN_URL_SISWA = Config.SERVER_API +"login_siswa.php";
    public static final String AMBIL_DATA_GURU = Config.SERVER_API +"ambilDataguru.php?nip=";
    public static final String AMBIL_DATA_SISWA = Config.SERVER_API +"ambilDatasiswa.php?nis=";
    public static final String PENGUMUMAN = Config.SERVER_API +"list_pengumuman.php";
    public static final String SIGN_UP = Config.SERVER_API +"insert_siswa.php";
    public static final String JADWAL_SISWA = Config.SERVER_API +"list_jadwal_siswa.php?kode_kelas=";
    public static final String JADWAL_GURU = Config.SERVER_API +"list_jadwal_guru.php?nip=";
    public static final String NIP = "nip";
    public static final String NIS = "nis";
    public static final String KEY_PASS = "password";
    public static final String LOGIN_SUCCESS = "success";
    public static final String SHARED_PREF_NAME = "myloginapp";
    public static final String USERNAME_SHARED_PREF_GURU = "nip";
    public static final String USERNAME_SHARED_PREF_SISWA = "nis";
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
    public static final String TAG_KELAS="kelas";
    public static final String TAG_HARI="hari";
}
