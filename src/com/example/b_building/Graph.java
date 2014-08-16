package com.example.b_building;

import java.io.FileReader;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;

public class Graph implements Serializable
{
	int n;
	final int MAXN = 2005;
	final int MAXM = 100005;
	final int dx[] = { 0, 1, 0, -1 };
	final int dy[] = { 1, 0, -1, 0 };
	// final int doors[] = { 2 * 215 + 1, 2 * 216, 2 * 211 + 1, 2 * 107 + 1, 2 *
	// 105 + 1, 2 * 318, 2 * 320 };
	Context context;
	List<Integer> doors;
	boolean[] vis;
	int[] dis;
	int[] p;
	Point point[];
	HashMap<Integer, Integer> doorNum;

	class Edge
	{
		public int v, w, next;

		public Edge()
		{
			// TODO Auto-generated constructor stub
			v = w = next = 0;
		}
	};

	public Edge edge[];
	int head[], e;

	public Graph(Context context, int state)
	{
		// TODO Auto-generated constructor stub
		this.context = context;
		doors = new ArrayList<Integer>();
		doorNum = new HashMap<Integer, Integer>();
		this.init();
		this.build(state);
	}

	void init()
	{
		// System.out.println("init ok");
		edge = new Edge[MAXM];
		for (int i = 0; i < MAXM; i++)
			edge[i] = new Edge();
		// System.out.println(edge.length);
		head = new int[MAXN];
		for (int i = 0; i < MAXN; i++)
			head[i] = -1;
		e = 0;
	}

	boolean inboard(int x, int y)
	{
		return x >= 0 && x < 7 && y >= 0 && y < 16;
	}

	void build(int state)
	{
		// System.out.println("build ok");
		try
		{
			// System.out.println("ok");
			InputStream in = context.getResources().getAssets().open("data1.txt");
			// System.out.println("num " + in.available());
			Scanner cin = new Scanner(in);// (new FileReader("data1.txt"));
			int n = cin.nextInt(), m = cin.nextInt();
			// System.out.println("n " + n + " " + m);
			int map[][] = new int[50][50];
			for (int i = 0; i < n; i++)
			{
				for (int j = 0; j < m; j++)
				{
					map[i][j] = cin.nextInt();
				}
			}
			for (int i = 0; i < n; i++)
			{
				for (int j = 0; j < m; j++)
				{
					if (map[i][j] != 0)
					{
						// System.out.println(map[i][j]);
						addedge(2 * map[i][j], 2 * map[i][j] + 1, 16);
						addedge(2 * map[i][j] + 1, 2 * map[i][j], 16);
					}
					else
						continue;
					for (int k = 0; k < 4; k++)
					{
						int x = i + dx[k], y = j + dy[k];
						if (inboard(x, y))
						{
							if (map[x][y] == 0)
								continue;
							if (k == 0)
							{
								addedge(2 * map[i][j] + 1, 2 * map[x][y], 3);
								addedge(2 * map[x][y], 2 * map[i][j] + 1, 3);
							}
							else if (k == 2)
							{
								addedge(2 * map[i][j], 2 * map[x][y] + 1, 3);
								addedge(2 * map[x][y] + 1, 2 * map[i][j], 3);
							}
						}
					}
				}
			}
			in.close();
			in = context.getResources().getAssets().open("data2.txt");
			cin = new Scanner(in);// (new FileReader("data2.txt"));
			n = cin.nextInt();
			m = cin.nextInt();
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
				{
					map[i][j] = cin.nextInt();
					addedge(2 * map[i][j], 2 * map[i][j] + 1, 24);
					addedge(2 * map[i][j] + 1, 2 * map[i][j], 24);
					for (int k = 0; k < 4; k++)
					{
						int x = i + dx[k], y = j + dy[k];
						if (inboard(x, y))
						{
							if (map[x][y] == 0)
								continue;
							if (k == 0)
							{
								addedge(2 * map[i][j] + 1, 2 * map[x][y], 24);
								addedge(2 * map[x][y], 2 * map[i][j] + 1, 24);
							}
							else if (k == 2)
							{
								addedge(2 * map[i][j], 2 * map[x][y] + 1, 24);
								addedge(2 * map[x][y] + 1, 2 * map[i][j], 24);
							}
						}
					}
				}
			in.close();
			in = context.getResources().getAssets().open("louti.txt");
			cin = new Scanner(in);
			while (cin.hasNext())
			{
				String[] num = cin.nextLine().split(" ");
				int k = Integer.parseInt(num[0]);
				for (int i = 1; i < num.length - 1; i++)
				{
					addedge(Integer.parseInt(num[i]) * 2 + k, Integer.parseInt(num[i + 1]) * 2 + k, 16);
					addedge(Integer.parseInt(num[i + 1]) * 2 + k, Integer.parseInt(num[i]) * 2 + k, 16);
				}
			}
			/*
			 * addedge(2 * 442 + 1, 2 * 538 + 1, 24); addedge(2 * 443, 2 * 537,
			 * 24); addedge(2 * 434 + 1, 2 * 529 + 1, 24); addedge(2 * 433, 2 *
			 * 528, 24);
			 */
			addedge(2 * 411 + 1, 2 * 537, 10);
			addedge(2 * 513 + 1, 2 * 528 + 1, 10);
			addedge(2 * 312 + 1, 2 * 443 + 1, 10);
			addedge(2 * 314 + 1, 2 * 437 + 1, 10);
			in.close();
			in = context.getResources().getAssets().open("dian_new.txt");
			cin = new Scanner(in);
			point = new Point[100];
			// int i = 0;
			// while (cin.hasNextInt())
			for (int i = 1;; i++)
			{
				point[i] = new Point();
				try
				{
					point[i].x = cin.nextInt();
					point[i].y = cin.nextInt();
				}
				catch (Exception e)
				{
					break;
				}
			}
//			System.out.println("bian ok");
			in.close();
			in = context.getResources().getAssets().open("bian2.txt");
			cin = new Scanner(in);
			while (cin.hasNext())
			{
				int u = cin.nextInt(), v = cin.nextInt();
				// System.out.println(u + " " + v);
				// System.out.println(point[u].x+" "+point[u].y);
				// System.out.println(point[v].x+" "+point[v].y);
				addedge(u, v, getdis(point[u], point[v]));
				// System.out.println("ok");
			}
			in.close();
			/*
			 * for(int i=0;i<5;i++) { addedge(14, doors[i], 0); } for(int
			 * i=5;i<7;i++) addedge(13, doors[i], 0);
			 */
			addedge(14, 211 * 2 + 1, 16);
			addedge(14, 216 * 2, 0);
			if (state == 0)
			{
//				System.out.println("ok extra");
				addedge(13, 2 * 320, 0);
				addedge(13, 318 * 2 + 1, 0);
				 addedge(50, 2 * 217, 0);
				addedge(50, 335 * 2, 0);
				addedge(50, 335 * 2 + 1, 0);
				addedge(47, 2 * 106 + 1, 0);
				addedge(47, 2 * 340 + 1, 0);
				addedge(47, 2 * 340, 0);
				addedge(48, 2 * 105 + 1, 0);
				addedge(49, 2 * 217 + 1, 0);
			}
			doorNum.put(2 * 211 + 1, 0);
			doorNum.put(216 * 2, 1);
			doorNum.put(2 * 320, 2);
			doorNum.put(318 * 2 + 1, 3);
			doorNum.put(2 * 217 + 1, 4);
			doorNum.put(335 * 2, 5);
			doorNum.put(335 * 2 + 1, 5);
			doorNum.put(2 * 106 + 1, 6);
			doorNum.put(2 * 340, 7);
			doorNum.put(2 * 340 + 1, 7);
			doorNum.put(2 * 105 + 1, 8);
			doorNum.put(2 * 217, 9);
			doors.add(14);
			doors.add(13);
			doors.add(50);
			doors.add(47);
			doors.add(48);
			doors.add(49);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			// System.out.println(e.getMessage());
		}
	}

	int getdis(Point x, Point y)
	{
		double base = 1400 / 827;
		return (int) (Point.distance(x.x, x.y, y.x, y.y) * base);
	}

	void addedge(int u, int v, int w)
	{
		// System.out.println(e+" "+edge[0].v);
		edge[e].v = v;
		edge[e].w = w;
		edge[e].next = head[u];
		head[u] = e++;
	}

	void spfa(int s)
	{
		vis = new boolean[MAXN];
		dis = new int[MAXN];
		p = new int[MAXN];
		for (int i = 0; i < MAXN; i++)
		{
			vis[i] = false;
			dis[i] = 0x3f3f3f3f;
			p[i] = 0;
		}
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(s);
		dis[s] = 0;
		while (q.size() > 0)
		{
			int u = q.poll();
//			System.out.println("u " + u);
			vis[u] = false;
			for (int i = head[u]; i != -1; i = edge[i].next)
			{
				int v = edge[i].v, w = edge[i].w;
				if (dis[v] > dis[u] + w)
				{
					dis[v] = dis[u] + w;
					p[v] = u;
					if (!vis[v])
					{
						vis[v] = true;
						q.add(v);
					}
				}
			}
		}
	}

	boolean judge(int k)
	{
		for (int d : doors)
			if (d == k)
				return true;
		return false;
	}

	int getEntrance(int num)
	{
		int v = getActualTerminal(num);
		while (v != 0)
		{
			if (judge(p[v]))
				return v;
			v = p[v];
		}
		return 0;
	}

	public int getActualTerminal(int num)
	{
		int v;
		if (dis[num * 2] < dis[num * 2 + 1])
			v = num * 2;
		else
			v = num * 2 + 1;
		return v;
	}
}
