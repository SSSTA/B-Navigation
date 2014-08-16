package com.example.b_building;

import java.util.Stack;

import com.polites.android.GestureImageView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Map extends Activity
{
	private GestureImageView image;
	private TextView text;
	private TextView route;
	private TextView entrance;
	private Button button;
	private int doorNum;
	private String entranceName[] = { "��5", "��4", "��1", "��2", "��3", "��1", "��6",
			"��2", "��7", "��8" };

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);//ȥ����Ϣ��
		setContentView(R.layout.map);
		text = (TextView) findViewById(R.id.text);
		Intent intent = new Intent();
		intent = getIntent();
		int s = intent.getIntExtra("s", 0);
		int d = intent.getIntExtra("t", 0);
		// text.setText(s + " " + d);
		text.setText("��У��ƽ��ͼ");
		image = (GestureImageView) findViewById(R.id.image);
		Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.map).copy(Bitmap.Config.ARGB_8888, true);
		Canvas canvas = new Canvas(picture);
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStrokeWidth(5);
		// Graph g=new Graph(Map.this);
		Graph g = MainActivity.g;
		// g.init();
		// g.build();
		g.spfa(s);
		System.out.println(g.point.length);
//		 canvas.drawPoint(g.point[s].x, g.point[s].y, paint);
		int door = g.getEntrance(d);
		int w = picture.getWidth(), h = picture.getHeight();
		int t = door;
		while (t != s)
		{
			System.out.println("dis " + t);
			if (t < 100)
				canvas.drawLine((float) g.point[t].x / 827 * w, (float) g.point[t].y / 669 * h, (float) g.point[g.p[t]].x / 827 * w, (float) g.point[g.p[t]].y / 669 * h, paint);
			// str+=t/2+""+(t%2==0?"��":"ǰ")+" ";
			t = g.p[t];
		}
		image.setImageBitmap(picture);

		route = (TextView) findViewById(R.id.route);
		int end=g.getActualTerminal(d);
		t = end;
		Stack<Integer> stack = new Stack<Integer>();
		while (t != door)
		{
			if((t/2)%100==0)
			{
				t=g.p[t];
				continue;
			}
			if (!stack.empty() && (t ^ 1) == stack.peek())
				stack.push(t ^ 1);
			else
				stack.push(t);
			System.out.println("t " + t);
			t = g.p[t];
			// str+=t/2+""+(t%2==0?"��":"ǰ")+" ";
		}
		// s.push(door);
		String str = "";
		/*if (door / 2 == 335)
			str += "�ӱ�¥���Ž� ";
		else if (door / 2 == 340)
			str += "�ӱ�¥���Ž� ";
		else
			str += String.format("��%s��ͨ���ڽ� ", door / 2 + (door % 2 == 0 ? "��" : "ǰ"));*/
		entrance=(TextView)findViewById(R.id.door);
		entrance.setText("��"+entranceName[g.doorNum.get(door)]+"�Ž�");

		doorNum=g.doorNum.get(door);
		button=(Button)findViewById(R.id.getPic);
		button.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Intent intent=new Intent(Map.this,Entrance.class);
				intent.putExtra("num", doorNum);
				startActivity(intent);
			}
		});
		int last = door;
		str="";
		if(door==end)
			str+="����"+t / 2 + (t % 2 == 0 ? "��" : "ǰ") + "��";
		while (!stack.empty())
		{
			t = stack.pop();
			System.out.println(t+" "+last);
			if (last / 100 < t / 100 && ((t % 100) - 60)*((last%100) -60)>0)
			{
//				System.out.println(last+" "+t);
				str += "��¥��"+ t / 2 + (t % 2 == 0 ? "��" : "ǰ") + "��";
			}
			else if (last / 100 > t / 100)
				str += "��¥��" +t / 2 + (t % 2 == 0 ? "��" : "ǰ") + "��";
			else
				str += "����"+ t / 2 + (t % 2 == 0 ? "��" : "ǰ") + "��";
			if (!stack.empty() && t == stack.peek())
			{
				str += "�߹�" + t / 2 + "����";
				last=stack.pop();
				continue;
			}
			last = t;
		}
		route.setText(str);
	}
}
