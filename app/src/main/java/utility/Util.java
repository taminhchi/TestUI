package utility;

import android.util.Log;

public class Util {
    public static void handleException(Exception ex)
    {
        try{
            Log.e("TestUI",ex.toString());
        }catch (Exception e)
        {
            //do nothing
        }
    }
}
