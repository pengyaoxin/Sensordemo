package cn.edu.gdmec.s07150738.sensordemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
        private SensorManager mSensorManager;
        private Sensor mAccelerometer;
        private Sensor mOrientation;
        private Sensor mLight;
        private TextView tAccelerometer;
        private TextView tOrientation;
        private TextView tLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tAccelerometer = (TextView)this.findViewById(R.id.accelerometer);
        tOrientation = (TextView)this.findViewById(R.id.orientation);
        tLight = (TextView)this.findViewById(R.id.light);
        //获得传感器管理器
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        //获得加速度传感器
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //获得方向度传感器
        mOrientation = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        //获得光线传感器
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    public void onResume() {
        super.onResume();
        //对加速度传感器注册传感器监听器
        mSensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        //对方向传感器注册传感器监听器
        mSensorManager.registerListener(this,mOrientation,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this,mLight,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        //取消传感器监听器的注册
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //在此方法中，编写当某个传感器的数值发生变化时应执行的操作
        //得到方向的值
        float x = event.values[SensorManager.DATA_X];
        float y = event.values[SensorManager.DATA_Y];
        float z = event.values[SensorManager.DATA_Z];
        if (event.sensor.getType()==Sensor.TYPE_ORIENTATION){
            tOrientation.setText("方位：" + x + ", "+ y +  ", "+z);
        }

        //得到加速度的值
        else if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            tAccelerometer.setText("加速度： "+ x +", "+ y +", "+z);
        }else if (event.sensor.getType()==Sensor.TYPE_LIGHT){
            tLight.setText("光线： " + event.values[0]);
        }

    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
