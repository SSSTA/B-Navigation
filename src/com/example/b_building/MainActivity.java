package com.example.b_building;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.polites.android.GestureImageView;

import android.R.color;
import android.R.integer;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity
{
	private int num[][] = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 701, 0, 703, 706, 707 }, { 0, 0, 0, 0, 0, 0, 601, 0, 602, 603, 605, 606, 608, 609, 612, 613 }, { 0, 501, 0, 502, 503, 506, 507, 509, 510, 511, 513, 514, 516, 517, 520, 521 }, { 401, 403, 406, 407, 408, 411, 412, 414, 415, 416, 418, 419, 421, 422, 425, 426 }, { 301, 303, 306, 307, 308, 311, 312, 314, 315, 316, 318, 0, 320, 0, 0, 0 }, { 201, 203, 206, 207, 208, 211, 0, 216, 217, 0, 0, 0, 0, 0, 0, 0 }, { 101, 0, 105, 106, 107, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
	private int northNum[][] = { { 538, 537, 532, 529, 528 }, { 442, 443, 437, 434, 433 } };
	private boolean judge(int n)
	{
		for (int i = 0; i < 7; i++)
			for (int j = 0; j < 16; j++)
				if (num[i][j] % 100 != 0)
					if (num[i][j] == n)
						return true;
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 5; j++)
				if (northNum[i][j] == n)
					return true;
		return false;
	}
	private final String apartment[] = { "����12,13��¥", "����11��¥", "����14,15��¥", "��԰1,2��¥", "��԰3,4��¥", "����5,6��¥", "����7,8��¥", "����9,10��¥" };
	private final int node[] = { 1, 3, 2, 43, 40, 35, 33, 30 };
	private Spinner spinner;
	private Button button;
	private EditText edit;
	private TextView home;
	private TextView room;
	private ArrayAdapter adapter;
	private int start;
	private static int state = 0;
	private Button changeState;
	private RelativeLayout layout;
	private int dayPic = R.drawable.z;
	private int nightPic = R.drawable.zzz;
	// private List<Integer> classroom;
	public static Graph g;
	// ProgressDialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// ȥ����Ϣ��
		setContentView(R.layout.activity_main);
		// android.app.ActionBar action = getActionBar();
		// action.hide();
		if (savedInstanceState == null)
		{
			getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
		/*
		 * new Thread(new Runnable() { ProgressDialog dialog;
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub
		 * runOnUiThread(new Runnable() { public void run() { //
		 * Toast.makeText(MainActivity.this, "��ʼ���С���", //
		 * Toast.LENGTH_SHORT).show(); dialog = new
		 * ProgressDialog(MainActivity.this);
		 * dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		 * dialog.setMessage("��ʼ�������У����Ժ�"); dialog.setIndeterminate(false);
		 * dialog.show(); } }); g = new Graph(MainActivity.this);
		 * runOnUiThread(new Runnable() { public void run() { dialog.dismiss();
		 * Toast.makeText(MainActivity.this, "��ʼ�����",
		 * Toast.LENGTH_SHORT).show(); } }); } }).start();
		 */
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		menu.add(0, 1, 1, "����");
		home = (TextView) findViewById(R.id.home);
		room = (TextView) findViewById(R.id.room);
		edit = (EditText) findViewById(R.id.edit);
		edit.setBackgroundColor(Color.WHITE);
		edit.getBackground().setAlpha(150);
		edit.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				// TODO Auto-generated method stub
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{
				// TODO Auto-generated method stub
			}
			@Override
			public void afterTextChanged(Editable s)
			{
				// TODO Auto-generated method stub
				if (edit.getText().toString().equals(""))
				{
					button.setEnabled(false);
					// button.setClickable(false);
					// button.setTextColor(Color.GRAY);
					return;
				}
				int n = Integer.parseInt(edit.getText().toString());
				// if (!classroom.contains(n))
				if (!judge(n))
				{
					// button.setClickable(false);
					// button.setTextColor(Color.GRAY);
					button.setEnabled(false);
				}
				else
				{
					button.setEnabled(true);
					// button.setClickable(true);
					// button.setTextColor(Color.BLACK);
				}
			}
		});
		layout = (RelativeLayout) findViewById(R.id.mainLayout);
		// layout.setBackground(R.drawable.bg3);
		// layout.setBackgroundResource(R.drawable.bg3);
		button = (Button) findViewById(R.id.button);
		button.setBackgroundColor(Color.WHITE);
		button.getBackground().setAlpha(150);
		// button.setTextColor(Color.GRAY);
		// button.setClickable(false);
		button.setEnabled(false);
		button.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("s", node[start]);
				intent.putExtra("t", Integer.parseInt(edit.getText().toString()));
				// intent.putExtra("graph", g);
				intent.setClass(MainActivity.this, Map.class);
				startActivity(intent);
			}
		});
		changeState = (Button) findViewById(R.id.state);
		changeState.setBackgroundColor(Color.WHITE);
		changeState.getBackground().setAlpha(150);
		changeState.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				MainActivity.state ^= 1;
				new Thread(new Runnable()
				{
					ProgressDialog dialog;
					@Override
					public void run()
					{
						// TODO Auto-generated method stub
						runOnUiThread(new Runnable()
						{
							public void run()
							{
								// Toast.makeText(MainActivity.this, "��ʼ���С���",
								// Toast.LENGTH_SHORT).show();
								dialog = new ProgressDialog(MainActivity.this);
								dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
								dialog.setMessage("��ʼ�������У����Ժ�");
								dialog.setIndeterminate(false);
								dialog.show();
							}
						});
						g = new Graph(MainActivity.this, MainActivity.state);
						runOnUiThread(new Runnable()
						{
							public void run()
							{
								dialog.dismiss();
								Toast.makeText(MainActivity.this, "��ʼ�����", Toast.LENGTH_SHORT).show();
							}
						});
					}
				}).start();
				if (MainActivity.state == 0)
				{
					changeState.setText("�л���ҹ��ģʽ");
					layout.setBackgroundResource(dayPic);
					home.setTextColor(Color.BLACK);
					room.setTextColor(Color.BLACK);
				}
				else
				{
					layout.setBackgroundResource(nightPic);
					changeState.setText("�л����ռ�ģʽ");
					home.setTextColor(Color.WHITE);
					room.setTextColor(Color.WHITE);
				}
			}
		});
		if (MainActivity.state == 0)
		{
			changeState.setText("�л���ҹ��ģʽ");
			layout.setBackgroundResource(dayPic);
			home.setTextColor(Color.BLACK);
			room.setTextColor(Color.BLACK);
		}
		else
		{
			layout.setBackgroundResource(nightPic);
			changeState.setText("�л����ռ�ģʽ");
			home.setTextColor(Color.WHITE);
			room.setTextColor(Color.WHITE);
		}
		spinner = (Spinner) findViewById(R.id.myspinner);
		spinner.setBackgroundColor(Color.WHITE);
		spinner.getBackground().setAlpha(150);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, apartment);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				start = position;
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
				// TODO Auto-generated method stub
			}
		});
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		else if(id==1)
		{
			String help;
			help="���Ӧ�����B¥�Ľ�ѧ¥����ʾ�����ᵽ��ѧ¥��·�ߡ�����ֱ������B¥�Ľ��Һţ�ϵͳ���жϽ����Ƿ���ڣ���ok�󼴿���ʾ·��\n";
			help+="����B¥���Ϻ���ĩ��һЩ���ǲ����ŵģ��ʴ���ҹ��ģʽ��������������ڽ���\n";
			help+="��У��ƽ��ͼ��B¥���ʾ��ͼ��֧�����Ų鿴����Ӧ���������������õ�������У����ô�õ����绷���²���ʹ�ã�ϣ���ܶԲ���Ϥ��ͬѧ�ṩ����\n";
			help+="\nauthor:z451538473 �������������䷴����602530919@qq.com";
			
			new AlertDialog.Builder(this).setTitle("����")
			.setMessage(help).setPositiveButton("ȷ��", null).show();
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment
	{
		public PlaceholderFragment()
		{}
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			return rootView;
		}
	}
	@Override
	protected void onDestroy()
	{
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
