package com.example.demo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.storage.StorageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.demo.Model.model_loading_user;
import com.example.demo.Model.model_product;
import com.example.demo.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class adapter_product extends BaseAdapter {
    private StorageReference mStorageRef;
    private Bitmap bitmap;
    private Context context;
    private int layout;
    private List<model_product> model_products;

    public adapter_product(Context context, int layout, List<model_product> model_products) {
        this.mStorageRef = mStorageRef;
        this.bitmap = bitmap;
        this.context = context;
        this.layout = layout;
        this.model_products = model_products;
    }

    @Override
    public int getCount() {
        return model_products.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView txt_title, txt_money, txt_date_pro, txt_address_pro;
        ImageView img_product;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txt_title = convertView.findViewById(R.id.txt_Title_pro);
            holder.txt_address_pro = convertView.findViewById(R.id.txt_address_pro);
            holder.txt_date_pro = convertView.findViewById(R.id.txt_date_pro);
            holder.txt_money = convertView.findViewById(R.id.txt_money_pro);
            holder.img_product = convertView.findViewById(R.id.img_product);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        model_product model_product = model_products.get(position);
        String img = model_product.getImage().trim();
        mStorageRef = FirebaseStorage.getInstance().getReference().child("images/"+img+".jpg");
        try {
            final File localFile = File.createTempFile(img, "jpg");
            mStorageRef.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        holder.txt_title.setText(model_product.getTitle());
        holder.txt_address_pro.setText(model_product.getAddress());
        holder.txt_money.setText(model_product.getMoney());
        holder.txt_date_pro.setText(model_product.getDate());
        holder.img_product.setImageBitmap(bitmap);
        return convertView;
    }
}
