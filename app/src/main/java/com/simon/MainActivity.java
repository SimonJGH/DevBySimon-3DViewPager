package com.simon;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView.ScaleType;

public class MainActivity extends Activity {

	private Image3DSwitchView imageSwitchView;
	int c = 6;// 这个数值和下面for循环的次数取决于给出的实际图片数量
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				boolean countDown = false;
				if (c > 0) {
					countDown = true;
					c--;
					if (c == 0) {
						c = 6;
					}
					imageSwitchView.scrollToNext();
				}
				if (countDown) {
					handler.sendEmptyMessageDelayed(1, 2000);
				}
				break;

			default:
				break;
			}
		};
	};

	private int[] images = new int[] { R.drawable.beautifulgirl01,
			R.drawable.beautifulgirl02, R.drawable.beautifulgirl03,
			R.drawable.beautifulgirl04, R.drawable.beautifulgirl05,
			R.drawable.beautifulgirl06 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		imageSwitchView = (Image3DSwitchView) findViewById(R.id.image_switch_view);
		imageSwitchView.setOnImageSwitchListener(new Image3DSwitchView.OnImageSwitchListener() {
			@Override
			public void onImageSwitch(int currentImage) {
				// Log.d("TAG", "current image is " + currentImage);
			}
		});
		/**
		 * 这里动态添加Image3DView
		 */
		for (int i = 0; i < 6; i++) {
			Image3DView image3DView = new Image3DView(getApplicationContext(),
					null);
			image3DView.setScaleType(ScaleType.FIT_XY);
			image3DView.setImageResource(images[i]);
			imageSwitchView.addView(image3DView);
		}
		imageSwitchView.setCurrentImage(1);
		// 设置延迟2秒的无限循环
		handler.sendEmptyMessageDelayed(1, 2000);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		imageSwitchView.clear();
	}

}
