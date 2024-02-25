package com.example.image_picker_with_seekbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private Button btnimagepicker;
    private ImageView imagepickprofile;
    private SeekBar Speed,Milage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnimagepicker=findViewById(R.id.btnimagepicker);
        imagepickprofile=findViewById(R.id.imagepickprofile);
        Speed = findViewById(R.id.Speed);
        Milage = findViewById(R.id.Milage);

        btnimagepicker.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int[] imageIds={R.drawable.car1,R.drawable.car2,R.drawable.car3,R.drawable.car4};

                        ImagePickerDialog imagePickerDialog =new ImagePickerDialog(
                                MainActivity.this,
                                imageIds,"Select Profile pic"
                        );

                        imagePickerDialog.setSpeedSeekBar(new SpeedSeekListener());
                        imagePickerDialog.setMilageSeekBar(new MilageSeekBar());
                        imagePickerDialog.setOnImageSelectedListener(new MyOnImageSelectedListener());
                        imagePickerDialog.show();
                    }
                }
        );
    }

    private  class  MyOnImageSelectedListener implements  ImagePickerDialog.OnImageSelectedListener{
        @Override
        public void onImageSelect(ImagePickerDialog imagePickerDialog, int imageID) {
            imagepickprofile.setImageResource(imageID);
            imagePickerDialog.dismiss();
        }
    }
    private class SpeedSeekListener implements ImagePickerDialog.OnSpeedSeekBar{
        @Override
        public void OnSeekBarChange(int SpeedValue) {
            Speed.setProgress(SpeedValue);
        }
    }

    private  class  MilageSeekBar implements  ImagePickerDialog.OnMilageSeekBar{
        @Override
        public void OnMilageBarChange(int MilageValue) {
            Milage.setProgress(MilageValue);
        }
    }
}