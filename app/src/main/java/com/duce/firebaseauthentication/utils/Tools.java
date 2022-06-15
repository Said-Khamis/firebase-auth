package com.duce.firebaseauthentication.utils;

import android.content.Context;
import android.widget.Toast;

public class Tools {

    private static Tools tools;
    private Context mContext;

    private Tools (Context context){
          this.mContext = context;
    }

    public static synchronized  Tools getTools(Context context){
         if (tools == null){
              tools = new Tools(context);
         }
         return  tools;
    }


    public void showToast(String message){

        Toast.makeText(mContext,message, Toast.LENGTH_LONG).show();

    }

}
