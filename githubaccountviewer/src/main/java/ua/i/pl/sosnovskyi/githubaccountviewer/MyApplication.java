package ua.i.pl.sosnovskyi.githubaccountviewer;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class MyApplication extends Application {
    private MyService myService;
    private PreferensesLoader preferensesLoader = new PreferensesLoader();

    public static MyApplication from(@NonNull Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myService = new MyService();
    }

    public MyService getMyService() {
        return myService;
    }


    public PreferensesLoader getPreferensesLoader() {
        return preferensesLoader;
    }

    class PreferensesLoader {
        private static final String preferensesName = "ua.i.pl.sosnovskyi.githubaccountviewer";
        private String accessToken;
        private String ACCESS_TOKEN = "token";
        private SharedPreferences sPref;

        public  void setAccessToken(String response) {
//            String queryString = response.split("\\?")[1];
//            String [] arguments = queryString.split("&");
//            for (String argument : arguments){
//                String [] data = argument.split("=");
//                if("access_token".equalsIgnoreCase(data[0])){
//                    SharedPreferences sPref = getSharedPreferences(preferensesName, MODE_PRIVATE);
//                    sPref.edit()
//                            .putString(ACCESS_TOKEN, data[1])
//                            .apply();
//                    break;
//                }
//            }
           String  regex="(?<=access_token=)(\\S{1,40})";
            Pattern pattern=Pattern.compile(regex);
            Matcher matcher=pattern.matcher(response);
            matcher.find();
            String data=matcher.group(1);
            SharedPreferences sPref = getSharedPreferences(preferensesName, MODE_PRIVATE);
            sPref.edit()
                    .putString(ACCESS_TOKEN, data)
                    .apply();
        }

      public   String getAccessToken() {
            sPref = getSharedPreferences(preferensesName, MODE_PRIVATE);
            return sPref.getString(ACCESS_TOKEN, "");
        }
    }

}
