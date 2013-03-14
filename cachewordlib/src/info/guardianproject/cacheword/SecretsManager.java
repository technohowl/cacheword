
package info.guardianproject.cacheword;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

public class SecretsManager {

    public static boolean isInitialized(Context ctx) {
        return getPrefs(ctx).getBoolean(Constants.SHARED_PREFS_INITIALIZED, false);
    }

    public static boolean saveBytes(Context ctx, String key, byte[] value) {
        String encoded = Base64.encodeToString(value, Base64.DEFAULT);
        Editor e = getPrefs(ctx).edit();
        e.putString(key, encoded);
        return e.commit();
    }

    public static byte[] getBytes(Context ctx, String key) {
        String encoded = getPrefs(ctx).getString(key, null);
        if( encoded == null ) return null;
        return Base64.decode(encoded, Base64.DEFAULT);
    }

    private static SharedPreferences getPrefs(Context ctx) {
        return ctx
                .getSharedPreferences(Constants.SHARED_PREFS, Constants.SHARED_PREFS_PRIVATE_MODE);
    }

}
