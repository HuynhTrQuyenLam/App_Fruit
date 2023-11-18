package com.example.pjvthl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pjvthl.model.CartDb;
import com.example.pjvthl.model.ProductDB;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DBHelper {
    String DBNAME = "PJVTHL.db";
    Context context;

    SQLiteDatabase db;
    public DBHelper(Context context) {
        this.context = context;
    }

    public SQLiteDatabase openDB(){
        return context.openOrCreateDatabase(DBNAME,Context.MODE_PRIVATE, null);
    }
    public void CopyDatabaseFromAssets(){
        File dbFile = context.getDatabasePath(DBNAME);
        if(!dbFile.exists()){
            try {
                InputStream is= context.getAssets().open(DBNAME);
                OutputStream os = new FileOutputStream(dbFile);
                byte[] buffer = new byte[1024];
                while (is.read(buffer) > 0){
                    os.write(buffer);
                }
                os.flush();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public ArrayList<ProductDB> getprodetail(){
        ArrayList<ProductDB> tmp = new ArrayList<>();
        db = openDB();
        String sql = "SELECT * FROM Product";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String namepro= cursor.getString(1);
            Double quantity = cursor.getDouble(2);
            Double price = cursor.getDouble(3);
            String decs = cursor.getString(4);
            String images = cursor.getString(5);
            String cate = cursor.getString(6);
            String prokind = cursor.getString(7);
            ProductDB productDB = new ProductDB(id,namepro,quantity,price,decs,images,cate,prokind);
            tmp.add(productDB);
        }
        db.close();
        return tmp;
    }
    public ArrayList<ProductDB> getproduct(){
        ArrayList<ProductDB> tmp = new ArrayList<>();
        db = openDB();
        String sql = "SELECT * FROM Product";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String namepro= cursor.getString(1);
            Double quantity = cursor.getDouble(2);
            Double price = cursor.getDouble(3);
            String decs = cursor.getString(4);
            String images = cursor.getString(5);
            String cate = cursor.getString(6);
            String prokind = cursor.getString(7);
            ProductDB productDB = new ProductDB(id,namepro,quantity,price,decs,images,cate,prokind);
            tmp.add(productDB);
        }
        db.close();
        return tmp;
    }
    public ArrayList<ProductDB> getproductnew(){
        ArrayList<ProductDB> tmp = new ArrayList<>();
        db = openDB();
        String sql = "SELECT * FROM Product where prokind = 'new' ";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String namepro= cursor.getString(1);
            Double quantity = cursor.getDouble(2);
            Double price = cursor.getDouble(3);
            String decs = cursor.getString(4);
            String images = cursor.getString(5);
            String cate = cursor.getString(6);
            String prokind = cursor.getString(7);
            ProductDB productDB = new ProductDB(id,namepro,quantity,price,decs,images,cate,prokind);
            tmp.add(productDB);
        }
        db.close();
        return tmp;
    }
    public ArrayList<ProductDB> getproductoutstanding(){
        ArrayList<ProductDB> tmp = new ArrayList<>();
        db = openDB();
        String sql = "SELECT * FROM Product where prokind = 'outstanding' ";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String namepro= cursor.getString(1);
            Double quantity = cursor.getDouble(2);
            Double price = cursor.getDouble(3);
            String decs = cursor.getString(4);
            String images = cursor.getString(5);
            String cate = cursor.getString(6);
            String prokind = cursor.getString(7);
            ProductDB productDB = new ProductDB(id,namepro,quantity,price,decs,images,cate,prokind);
            tmp.add(productDB);
        }
        db.close();
        return tmp;
    }
    public void inserttocart(ProductDB productDB){
        db = openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",productDB.getNamepro());
        contentValues.put("images",productDB.getImages());
        contentValues.put("price",productDB.getPrice());
        db.insert("Cart",null,contentValues);
        db.close();
    }

    public ArrayList<CartDb> getcartitem(){
        ArrayList<CartDb> tmp = new ArrayList<>();
        db = openDB();
        String sql = "SELECT * FROM Cart ";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String images = cursor.getString(2);
            Double price = cursor.getDouble(3);
            Integer quantity = cursor.getInt(4);
            CartDb cartDb = new CartDb(id,name,images,price,quantity);
            tmp.add(cartDb);
        }
        db.close();
        return tmp;
    }
    public ArrayList<CartDb> getitembycate(String s){
        ArrayList<CartDb> tmp = new ArrayList<>();
        db = openDB();
        String sql = "SELECT * FROM Product where cate = '" + s + "' ";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String images = cursor.getString(5);
            Double price = cursor.getDouble(3);
            Integer quantity = cursor.getInt(2);
            CartDb cartDb = new CartDb(id,name,images,price,quantity);
            tmp.add(cartDb);
        }
        db.close();
        return tmp;
    }
    public boolean itemcartexits(ProductDB productDB) {
        db = openDB();
        String sql = "SELECT EXISTS (SELECT * FROM Cart WHERE name='"+productDB.getNamepro()+"' LIMIT 1)";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        // cursor.getInt(0) is 1 if column with value exists
        if (cursor.getInt(0) == 1) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }
    public void updatequantity(Integer i,ProductDB productDB){
        db = openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put("quantity",i);
        db.update("Cart",contentValues," name = " + "'" + productDB.getNamepro()+"'",null);
        db.close();
    }
    public Integer getQuantity(){
        db = openDB();
        Integer quantity =0 ;
        String sql = "SELECT * FROM Cart";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            Integer quantity1 = cursor.getInt(4);
            quantity=quantity1;
        }
        return quantity;
    }
   public ArrayList<ProductDB> searchpro(String s){
        ArrayList<ProductDB> tmp = new ArrayList<>();
        db = openDB();
        String sql = "SELECT * FROM Product where namepro like + '%" + s + "%' ";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String namepro= cursor.getString(1);
            Double quantity = cursor.getDouble(2);
            Double price = cursor.getDouble(3);
            String decs = cursor.getString(4);
            String images = cursor.getString(5);
            String cate = cursor.getString(6);
            String prokind = cursor.getString(7);
            ProductDB productDB = new ProductDB(id,namepro,quantity,price,decs,images,cate,prokind);
            tmp.add(productDB);
        }
        db.close();
        return tmp;
    }
}
