package com.example.b_building;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Classroom extends Activity
{
	int num[][] = {
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 701, 702, 703, 706, 707 },
			{ 0, 0, 0, 0, 0, 0, 601, 600, 602, 603, 605, 606, 608, 609, 612,
					613 },
			{ 0, 501, 500, 502, 503, 506, 507, 509, 510, 511, 513, 514, 516,
					517, 520, 521 },
			{ 401, 403, 406, 407, 408, 411, 412, 414, 415, 416, 418, 419, 421,
					422, 425, 426 },
			{ 301, 303, 306, 307, 308, 311, 312, 314, 315, 316, 318, 0, 320, 0,
					0, 0 },
			{ 201, 203, 206, 207, 208, 211, 215, 216, 217, 0, 0, 0, 0, 0, 0, 0 },
			{ 101, 102, 105, 106, 107, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

	private TextView text[][];
	private TableLayout table;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.room);
//		TableLayout table = new TableLayout(Classroom.this);
		table=(TableLayout)findViewById(R.id.table);
		text=new TextView[7][16];
		for (int i = 0; i < 7; i++)
		{
			TableRow row = new TableRow(Classroom.this);
			for (int j = 0; j < 16; j++)
			{
				text[i][j] = new TextView(Classroom.this);
				text[i][j].setId(i*16+j);
				if (num[i][j] % 100 != 0)
				{
					System.out.println(num[i][j]);
					text[i][j].setText(num[i][j]+"");
					text[i][j].setOnClickListener(new OnClickListener()
					{
						@Override
						public void onClick(View v)
						{
							// TODO Auto-generated method stub
							Intent intent=new Intent(Classroom.this, MainActivity.class);
//							intent.putExtra("num", Integer.parseInt(getText().toString()));
							setResult(RESULT_OK, intent);
						}
					});
				}
			}
			table.addView(row);
//			new AlertDialog.Builder(this).setView(table).show();
		}
		// TableRow raw=new TableRow(Classroom.this);
	}
}
