package com.example.yuya0817.ReviveSeat;

import android.content.Context;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

///**
// * Created by yuya0817 on 2016/11/26.
// */
class TempDataUtil {
//    保存するファイル名
    private final static String FILE_NAME = "ViewDto.obj";

    /**
     * データを保存する
     * @param context
     * @param object 保存するオブジェクト
     */
    static void store(Context context, Serializable object){
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE));
            out.writeObject(object);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * データを読み込む
     * @param context
     * @return 保存しているデータがない場合は null
     */
    static Object load(Context context){
        Object retObj = null;
        try {
            ObjectInputStream in = new ObjectInputStream(context.openFileInput(FILE_NAME));
            retObj = in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return retObj;
    }
}
