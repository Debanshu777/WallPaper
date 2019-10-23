package com.example.wallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    GridView myGridView;
    ImageView myCurrentWallpaper;
    Drawable myDrawable;
    WallpaperManager myWallManager;
    Integer[] myImageArray={
            R.drawable.thumb1,R.drawable.thumb2,R.drawable.thumb3,R.drawable.thumb4,R.drawable.thumb5
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myGridView=findViewById(R.id.myGridView);
        myCurrentWallpaper=findViewById(R.id.myImageView);
        myGridView.setAdapter(new ImageAdapter(getApplicationContext()));
        //UpdateMyWallpaper();


    }
    private void UpdateMyWallpaper()
    {
        myWallManager=WallpaperManager.getInstance(getApplicationContext());
        myDrawable=myWallManager.getDrawable();
        myCurrentWallpaper.setImageDrawable(myDrawable);
    }
    public class ImageAdapter extends BaseAdapter{

        Context myContext;
        public ImageAdapter(Context applicationContext) {
            myContext=applicationContext;
        }

        @Override
        public int getCount() {
            return myImageArray.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
           ImageButton GridImageView;
            if(convertView==null)
            {
                GridImageView=new ImageButton(myContext);
                GridImageView.setLayoutParams(new GridView.LayoutParams(512,512));
                GridImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
            else{
                GridImageView=(ImageButton) convertView;
            }
            GridImageView.setImageResource(myImageArray[position]);
            GridImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        myWallManager.setResource(myImageArray[position]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //UpdateMyWallpaper();
                }
            });

            return GridImageView;
        }
    }
}
