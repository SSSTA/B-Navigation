package com.example.b_building;

import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

public class Entrance extends Activity
{
	int draw[] = { R.drawable.e211, R.drawable.e216, R.drawable.e320,
			R.drawable.e318, R.drawable.e217, R.drawable.northwest,
			R.drawable.e107, R.drawable.northeast, R.drawable.e105 ,R.drawable.hou217};
	private ImageView image;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.entrance);
		Intent intent = getIntent();
		image = (ImageView) findViewById(R.id.entranceImage);
		// System.out.println(image==null);
		/*if (image == null)
			System.out.println("true");
		else
			System.out.println("false");*/
		int p = intent.getIntExtra("num", 0);
//		System.out.println("p " + p);/
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		// 获取资源图片
		InputStream is = getResources().openRawResource(draw[p]);
		Bitmap bm=BitmapFactory.decodeStream(is,null,opt);
		// int imageHeight = options.outHeight;
		// int imageWidth = options.outWidth;
		// String imageType = options.outMimeType;
		image.setMaxHeight(bm.getHeight());
		image.setMaxWidth(bm.getWidth());
		image.setImageBitmap(bm);
		image.invalidate();
		// image.setImageResource(R.drawable.e105);
	}
}
