package com.example.image_picker_with_seekbar;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ImagePickerDialog extends Dialog {

    private LinearLayout imgcontainer;
    private TextView txtTitle;
    private SeekBar SpeedSeekBar,MilageSeekBar;

    public interface OnSpeedSeekBar{
       void OnSeekBarChange(int SpeedValue);
    }
    private OnSpeedSeekBar onSpeedSeekBar=null;

    public void setSpeedSeekBar(OnSpeedSeekBar onSpeedSeekBar){
        this.onSpeedSeekBar = onSpeedSeekBar;
    }



    public interface OnMilageSeekBar{
        void OnMilageBarChange(int MilageValue);
    }
    private OnMilageSeekBar onMilageSeekBar=null;

    public void setMilageSeekBar(OnMilageSeekBar onMilageSeekBar){
        this.onMilageSeekBar = onMilageSeekBar;
    }



    public interface OnImageSelectedListener {
        void onImageSelect(ImagePickerDialog imagePickerDialog, int imageID);
    }

    private OnImageSelectedListener onImageSelectedListener = null;

    public void setOnImageSelectedListener(OnImageSelectedListener onImageSelectedListener) {
        this.onImageSelectedListener = onImageSelectedListener;
    }

    public ImagePickerDialog(Context context, int[] imageIds, String title) {
        super(context);
        setContentView(R.layout.image_picker_dialog);
        imgcontainer = findViewById(R.id.imgcontainer);
        txtTitle = findViewById(R.id.txttitle);
        txtTitle.setText(title);
        SpeedSeekBar=findViewById(R.id.SpeedSeekBar);
        MilageSeekBar=findViewById(R.id.MilageSeekBar);



       SpeedSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
           public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
               if(onSpeedSeekBar != null){
                   onSpeedSeekBar.OnSeekBarChange(SpeedSeekBar.getProgress());
               }
               Toast.makeText(getContext().getApplicationContext(),"Speed : "+i,Toast.LENGTH_SHORT).show();

           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {

           }

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {

           }
       });

        MilageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (onMilageSeekBar!=null){
                    onMilageSeekBar.OnMilageBarChange(MilageSeekBar.getProgress());
                }

                Toast.makeText(getContext().getApplicationContext(),"Milage : "+i,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        for (int imageId : imageIds) {
            ImageView img = new ImageView(context);
            img.setImageResource(imageId);
            img.setLayoutParams(
                    new ViewGroup.LayoutParams(200, 200)
            );

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onImageSelectedListener != null) {
                        onImageSelectedListener.onImageSelect(
                                ImagePickerDialog.this,
                                imageId
                        );
                    }
                }
            });

            imgcontainer.addView(img);
        }


    }


}

